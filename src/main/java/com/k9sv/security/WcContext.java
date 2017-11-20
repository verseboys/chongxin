package com.k9sv.security;

import java.util.Arrays;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.k9sv.domain.pojo.Account;

public class WcContext {

	private static final GrantedAuthority[] DEFAULT_GRANTED_AUTHORITIES = new GrantedAuthority[] { new SimpleGrantedAuthority(
			"ROLE_USER") };

	/*
	 * private static final GrantedAuthority[] DEFAULT_GRANTED_AUTHORITIES = new
	 * GrantedAuthorityImpl[] { new GrantedAuthorityImpl( "ROLE_USER") };
	 */

	public static Account getCurrentAccount() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication auth = (Authentication) context.getAuthentication();
		if (auth == null || auth.getPrincipal().equals("anonymousUser"))
			return null;
		// 匿名用户操作:未登录系統的时候调用getCurrentAccount方法会返回字符串"anonymousUser",不可强制转化为Account
		Account account = (Account) auth.getPrincipal();
		return account;
	}

	public static void successfulAuthentication(Authentication authResult) {
		SecurityContextHolder.getContext().setAuthentication(authResult);
	}

	public static void setAuthentication(UserDetails userDetails, Account _account) {
		Authentication authentication = new UsernamePasswordAuthenticationToken(
				_account, _account.getPassword(), userDetails.getAuthorities());
		SecurityContext securityContext = SecurityContextHolder.getContext();
		securityContext.setAuthentication(authentication);
	}

	public static void unsuccessfulAuthentication(Authentication requestToken) {
		SecurityContextHolder.getContext().setAuthentication(null);
	}

	public static void login(PrincipalPasswordAuthenticationToken token) {
		Authentication authResult = createSuccessAuthentication(token);
		successfulAuthentication(authResult);
	}

	/**
	 * Create an {@link PrincipalPasswordAuthenticationToken
	 * PrincipalPasswordAuthenticationToken} when the user login successfully
	 * based on the given {@code account} and {@code token}. <br />
	 * Note we use {@link Account#getId() account id} as the return
	 * authentication object's principal since the username and phone number of
	 * the login account would be changed during its login time. <br />
	 * And we use the {@link PrincipalPasswordAuthenticationToken token's}
	 * credentials as the return object's credentials so that the user can be
	 * re-authenticated if it is required (If the user change its password
	 * during its login time).
	 * 
	 * @param account
	 *            the login account.
	 * @param token
	 *            the {@link PrincipalPasswordAuthenticationToken} that the user
	 *            request. The request contains the account's username and
	 *            password.
	 * @return a authentication token that with the account's ID as its
	 *         principal, and the {@code token token's} credentials as its
	 *         credentials.
	 */
	public static Authentication createSuccessAuthentication(
			PrincipalPasswordAuthenticationToken token) {
		return new PrincipalPasswordAuthenticationToken(
				Arrays.asList(DEFAULT_GRANTED_AUTHORITIES),
				token.getPrincipal(), token.getCredentials());
	}
}
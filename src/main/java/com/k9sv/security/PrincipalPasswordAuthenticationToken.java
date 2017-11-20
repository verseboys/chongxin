package com.k9sv.security;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class PrincipalPasswordAuthenticationToken extends
		AbstractAuthenticationToken {

	

	private static final long serialVersionUID = 2649807609384747058L;

	/**
	 * object that represent the login user.
	 */
	private Object principal;

	/**
	 * The credentials that prove the principal is correct. This is usually a
	 * password, but could be anything relevant to the
	 * <code>AuthenticationManager</code>. Callers are expected to populate the
	 * credentials.
	 */
	private Object credentials;
	
	public PrincipalPasswordAuthenticationToken(Object principal, Object credentials) {
		this(null, principal, credentials);
	}
	
	public PrincipalPasswordAuthenticationToken(Collection<? extends GrantedAuthority> authorities,Object principal, Object credentials) {
		super(authorities);
		this.principal = principal;
		this.credentials = credentials;
	}
	
	@Override
	public Object getCredentials() {
		return credentials;
	}

	@Override
	public Object getPrincipal() {
		return principal;
	}

}

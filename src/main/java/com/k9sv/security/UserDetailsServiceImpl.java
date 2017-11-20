package com.k9sv.security;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.k9sv.domain.pojo.Account;
import com.k9sv.service.IUserManager;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	private static Logger LOG = Logger.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	private IUserManager userManager;

	@Override
	public User loadUserByUsername(String username)
			throws UsernameNotFoundException {
		if (username == null) {
			LOG.error("username is null");
		}
		Account _account = userManager.getAccount(username);
		if (_account == null) {
			return null;
		}
		//User user = new User(_account.getUsername(), _account.getPassword(),_account.getAuthorities());
		
		Set<GrantedAuthority> grantedAuths = obtionGrantedAuthorities();
        
	    User userdetail = new UserPrincipal(_account.getUsername(), 
	    									_account.getUsername(),
                            	            _account.getId(),
                            	            "role",
                            	            "",
                            	            _account.getPassword(), 
                            	            true,
                            	            true,
                            	            true,
                            	            true,
                            	            grantedAuths);
	    
	    return userdetail;
	}
	
	// 取得用户的权限
	private Set<GrantedAuthority> obtionGrantedAuthorities() {
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
		authSet.add(new SimpleGrantedAuthority("ROLE_USER"));
		return authSet;
	}

	// 取得指定角色的权限
	@SuppressWarnings("unused")
	private Set<GrantedAuthority> obtionGrantedAuthorities(String[] roles) {
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
		for (String role : roles) {
			authSet.add(new SimpleGrantedAuthority("ROLE_" + role));
		}
		return authSet;
	}
	    
}

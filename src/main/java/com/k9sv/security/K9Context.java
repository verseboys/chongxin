package com.k9sv.security;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class K9Context {

	 public static WebAuthenticationDetails getWebAuthentication(){

	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        WebAuthenticationDetails wauth = (WebAuthenticationDetails)auth.getDetails();
	        
	        return wauth;
	    }
	    
	    public static UserPrincipal getUserPrincipal(){

	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        UserPrincipal user = (UserPrincipal)auth.getPrincipal();
	        
	        return user;
	    }
	    
	    /**
	     * 获取当前登录系统所有用户
	     * @return List<UserPrincipal>
	     */
	    public static List<Object> getAllUserPrincipal(){
	        
	        SessionRegistry sessionRegistry = (SessionRegistry)SpringContextUtil.getBean("sessionRegistry");
	        List<Object> principals = sessionRegistry.getAllPrincipals();
	        
	        for(Object principal : principals){
	            sessionRegistry.getAllSessions(principal, true).get(0).getLastRequest();
	        }
	        return principals;
	    }
	    
	    /**
	     * 强制退出系统
	     * @param userId
	     * @return
	     */
	    public static boolean expire(String userId){
	        
	        SessionRegistry sessionRegistry = (SessionRegistry)SpringContextUtil.getBean("sessionRegistry");
	        List<Object> principals = sessionRegistry.getAllPrincipals();
	        for(Object principal : principals){
	            UserPrincipal user = (UserPrincipal)principal;
	            if (user.getUserId().equals(userId)){
	                List<SessionInformation> sessionInfos = sessionRegistry.getAllSessions(principal, true); //第二个参数,单点登录时超出限制被弹出的用户
	                for(SessionInformation sessionInfo : sessionInfos){
	                    sessionInfo.expireNow();
	                }
	                return true;
	            }
	        }
	        return false;
	    }
}
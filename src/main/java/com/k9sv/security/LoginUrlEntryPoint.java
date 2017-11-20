package com.k9sv.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class LoginUrlEntryPoint implements AuthenticationEntryPoint {

	public void commence(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		String targetUrl = null;
		String url = request.getRequestURI();
		if (url.indexOf("admin") != -1) {
			targetUrl = "/admin/login";
		} else {
			targetUrl = "/member/login";
		}
		targetUrl = request.getContextPath() + targetUrl;
		response.sendRedirect(targetUrl);
	}
}

package com.k9sv.web.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.k9sv.cache.UserCache;
import com.k9sv.service.impl.BaiduManager;
import com.k9sv.util.CookieUtil;

public class MemberUrlFilter implements Filter {

	private static final Logger LOG = Logger.getLogger(BaiduManager.class);
	// 不需要过滤的地址
	public static final List<String> NoFilterUrl = Arrays.asList("/member",
			"/member/login", "/member/loginaction", "/member/logout",
			"/member/wechat/callback", "/member/bind", "/member/bindaction",
			"/member/registeraction", "/member/qrloginaction");

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String path = httpRequest.getRequestURI();
		LOG.info("url:" + path);
		// 登陆、注册或动态列表无需过滤
		if (!NoFilterUrl.contains(path)) {// 客户端接口
			String sessionid = CookieUtil.getCookieValue(httpRequest,
					"k9membertoken");
			if (UserCache.OnlineUsers.contains(sessionid)) {
				chain.doFilter(httpRequest, httpResponse);
			} else {
				// 跳转到登陆页面
				httpResponse.sendRedirect("/member/login");
			}
		} else {
			chain.doFilter(httpRequest, httpResponse);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}

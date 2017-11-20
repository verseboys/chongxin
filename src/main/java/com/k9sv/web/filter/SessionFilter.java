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

import com.k9sv.Errorcode;
import com.k9sv.cache.UserCache;
import com.k9sv.domain.dto.ResDto;

public class SessionFilter implements Filter {

	private static final Logger LOG = Logger.getLogger(SessionFilter.class);
	// 不需要过滤的地址
	public static final List<String> NoFilterUrl = Arrays.asList(
			"/server/user/register", "/server/user/login",
			"/server/user/thirdlogin","/server/user/chanagepassword",
			"/server/toutiao/list", "/server/user/getcode",
			"/server/user/confirmcode", "/server/user/wxregister",
			"/server/product/weixinpay", "/server/product/alipay",
			"/weixin/index", "/weixin/login",
			"/weixin/m", "/weixin/register");

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// 获得在下面代码中要用的request,response,session对象
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		// 获得用户请求的URI
		String url = servletRequest.getRequestURL().toString().toLowerCase();
		String path = servletRequest.getRequestURI();
		LOG.info("url:" + url);
		LOG.info("path:" + path);
		// 登陆、注册或动态列表无需过滤
		if (!NoFilterUrl.contains(path)) {// 客户端接口
			String sid = servletRequest.getParameter("sid");
			ResDto res = new ResDto();
			res.setState(1);
			if (UserCache.OnlineUsers.contains(sid)) {
				chain.doFilter(servletRequest, servletResponse);
			} else {
				response.setContentType("application/json; charset=UTF-8");
				// response.setContentType("text/plain; charset=UTF-8");
				res.setErrorcode(Errorcode.reLogin.getCode());
				res.setErrormsg(Errorcode.reLogin.getErrormsg());
				response.getWriter().print(res.toString());
			}
		} else {
			chain.doFilter(servletRequest, servletResponse);
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

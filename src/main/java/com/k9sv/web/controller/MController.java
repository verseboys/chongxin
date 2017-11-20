package com.k9sv.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;



@Scope("prototype")
@Controller
@RequestMapping("/m/")
public class MController extends MultiActionController {
	private static final Logger LOG = Logger.getLogger(MController.class);

	@RequestMapping("login")
	public String index(HttpServletRequest request,HttpServletResponse response,String sid,Model model) throws Exception {


		long i= System.currentTimeMillis();
		model.addAttribute("sid", sid);

		String ip = request.getRemoteAddr();//返回发出请求的IP地址
		LOG.info(ip);
		
		LOG.info(System.currentTimeMillis()-i);

		return "m/login";
	}
}

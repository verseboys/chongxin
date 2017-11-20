package com.k9sv.web.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.k9sv.Config;
import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.OnlineUser;
import com.k9sv.service.IUserManager;
import com.k9sv.util.CookieUtil;
import com.k9sv.util.MD5;

/**
 * 登录
 * 
 * @author mcp
 * 
 */
@Controller
@Scope("prototype")
@RequestMapping("/admin")
public class LoginController {
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private IUserManager userManager;

	@RequestMapping("")
	public String Default() {
		return "redirect:/admin/login";
	}

	@RequestMapping("/login")
	public String login() {
		return "admin/login";
	}

	@RequestMapping("/login/confirm")
	public String logsave(Model model, Account account, HttpSession session,
			HttpServletResponse response) {

		UserDetails userDetails = userDetailsService.loadUserByUsername(account
				.getUsername().trim());// 用于判断用户是否存在

		if (userDetails == null) {// 用户不存在 直接跳转回登录页面
			model.addAttribute("error", "没有这个帐号");
			model.addAttribute("account", account);
			return "admin/login";
		}

		Account _account = userManager.getAccountByUsername(account// account(报告)
				.getUsername().trim());// 通过页面输入的用户名 获取一个Account 此时在Account内存在
								// userName、password和Id
		if (_account != null
				&& _account.getPassword().equals(
						MD5.md5(account.getPassword() + Config.pwdExtention))) {// MD5加密明文
			String sessionid = this.getSessionId(_account.getId());
			userManager.login(_account, sessionid, Config.LoginPlatformWeb);
			CookieUtil.addCookie(response, "k9admintoken", sessionid, 0);
			return "redirect:/admin/index";
		} else {// 密码不能匹配
			model.addAttribute("error", "帐号密码错误");
			model.addAttribute("account", account);
			return "admin/login";
		}
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session, HttpServletRequest request) {
		String sessionid = CookieUtil
				.getCookieValue(request, "k9admintoken");
		OnlineUser _onlineUser = userManager.getByClassId(OnlineUser.class,
				sessionid);
		userManager.onlineUserSet(_onlineUser, 0);
		userManager.delete(_onlineUser);
		return "redirect:/admin/login";
	}

	private String getSessionId(int id) {
		Long i = System.currentTimeMillis();
		String sessionId = MD5.md5(i + " " + id);
		return sessionId;
	}

}

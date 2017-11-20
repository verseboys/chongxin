package com.k9sv.web.member.controller;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.k9sv.Config;
import com.k9sv.PayConfig;
import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.OnlineUser;
import com.k9sv.service.IUserManager;
import com.k9sv.util.CookieUtil;
import com.k9sv.util.MD5;

/**
 * 会员登录
 * 
 * @author Administrator
 * 
 */
@RequestMapping("/member")
@Scope("prototype")
@Controller("memberLoginController")
public class LoginController {
	@Autowired
	private IUserManager userManager;

	@RequestMapping("")
	public String Default() {
		return "redirect:/member/login";
	}

	@SuppressWarnings("deprecation")
	@RequestMapping("/login")
	public String login(Model model) {
		// 生成唯一ID
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
		String uuid = sdf.format(new Date());

		String redirect_uri = URLEncoder
				.encode("http://www.ichongxin.com/member/wechat/callback");

		String url = "https://open.weixin.qq.com/connect/qrconnect?appid="
				+ PayConfig.WebAPPID + "&redirect_uri=" + redirect_uri
				+ "&response_type=code&scope=snsapi_login&state=" + uuid
				+ "#wechat_redirect";
		model.addAttribute("url", url);
		return "member/memberlogin";
	}

	@RequestMapping("/loginaction")
	public String logsave(Model model, Account account, HttpSession session,
			HttpServletResponse response) {

		Account _account = userManager.getCheckedAccount(account.getUsername()
				.trim());

		if (_account == null) {// 用户不存在 直接跳转回登录页面
			model.addAttribute("error", "没有这个帐号");
			model.addAttribute("account", account);
			return "member/memberlogin";
		}
		if (_account.getPassword().equals(
				MD5.md5(account.getPassword() + Config.pwdExtention))) {// MD5加密明文
			String sessionid = this.getSessionId(_account.getId());
			userManager.login(_account, sessionid, Config.LoginPlatformWeb);
			CookieUtil.addCookie(response, "k9membertoken", sessionid, 0);
			return "redirect:/member/index";
		} else {// 密码不能匹配
			model.addAttribute("error", "帐号密码错误");
			model.addAttribute("account", account);
			return "member/memberlogin";
		}
	}

	/**
	 * 推出
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session, HttpServletRequest request) {
		String sessionid = CookieUtil.getCookieValue(request, "k9membertoken");
		OnlineUser _onlineUser = userManager.getByClassId(OnlineUser.class,
				sessionid);
		userManager.onlineUserSet(_onlineUser, 0);
		userManager.delete(_onlineUser);
		return "redirect:/member/login";
	}

	private String getSessionId(int id) {
		Long i = System.currentTimeMillis();
		String sessionId = MD5.md5(i + " " + id);
		return sessionId;
	}

}

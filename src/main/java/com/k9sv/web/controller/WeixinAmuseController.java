package com.k9sv.web.controller;

import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.k9sv.Config;
import com.k9sv.PayConfig;
import com.k9sv.domain.dto.ProfileDto;
import com.k9sv.domain.dto.WeixinUser;
import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.Profile;
import com.k9sv.domain.pojo.WeixinShare;
import com.k9sv.service.IShareNotesManager;
import com.k9sv.service.IUserManager;
import com.k9sv.util.CookieUtil;
import com.k9sv.util.MD5;
import com.k9sv.util.StringUtil;
import com.k9sv.util.WeixinUtil;

/**
 * 微信朋友圈 爆笑分享
 * 
 * @author mcp
 * 
 */
@Scope("prototype")
@Controller
@RequestMapping("/amuse")
public class WeixinAmuseController {

	@Autowired
	private IUserManager userManager;
	@Autowired
	private IShareNotesManager shareNotesManager;

	@SuppressWarnings("deprecation")
	@RequestMapping("/share/{type}")
	public String index(HttpServletRequest request, HttpServletResponse response, @PathVariable("type") String type,
			Integer fromuid, Integer gotoplay, Model model) throws Exception {

		Account account = userManager.getByClassId(Account.class, fromuid);
		if (gotoplay != null && gotoplay.intValue() == 1) {// 我也要玩
			String callbackurl = URLEncoder.encode("http://www.ichongxin.com/amuse/loginaction?fromuid=" + fromuid);
			String state = type;
			String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + PayConfig.WeixinFuwu
					+ "&redirect_uri=" + callbackurl + "&response_type=code&scope=snsapi_userinfo&state=" + state
					+ "#wechat_redirect";
			response.sendRedirect(url);
		} else {
			StringBuffer url = request.getRequestURL();
			WeixinUtil signUtil = new WeixinUtil();
			WeixinShare weixinShare = signUtil.sign(url.toString());
			model.addAttribute("weixinShare", weixinShare);
			model.addAttribute("user", new ProfileDto(account.getProfile()));
		}
		return "h5/amuse/" + type;

		/*
		 * String token = CookieUtil.getCookieValue(request, "amuse"); Account
		 * account = userManager.getOnlineUser(token);
		 * CookieUtil.addCookie(response, "amuse", null, 1); if ((gotoplay ==
		 * null || gotoplay == 0) && account == null) {// 查看别人的直接放过 Account
		 * account2 = userManager.getByClassId(Account.class, fromuid);//
		 * 是谁分享过来的 StringBuffer url = request.getRequestURL(); WeixinUtil
		 * signUtil = new WeixinUtil(); WeixinShare weixinShare =
		 * signUtil.sign(url.toString()); model.addAttribute("weixinShare",
		 * weixinShare); model.addAttribute("user", new
		 * ProfileDto(account2.getProfile())); return "amuse/" + type; } if
		 * (account == null) { String callbackurl = URLEncoder
		 * .encode("http://www.ichongxin.com/amuse/loginaction?fromuid=" +
		 * fromuid); String state = type; String url =
		 * "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" +
		 * PayConfig.WeixinFuwu + "&redirect_uri=" + callbackurl +
		 * "&response_type=code&scope=snsapi_userinfo&state=" + state +
		 * "#wechat_redirect"; response.sendRedirect(url); } else { Account
		 * account2 = userManager.getByClassId(Account.class, uid);// 是谁分享过来的
		 * shareNotesManager.insert(account, account2, type); // 插入分享记录
		 * StringBuffer url = request.getRequestURL(); WeixinUtil signUtil = new
		 * WeixinUtil(); WeixinShare weixinShare =
		 * signUtil.sign(url.toString()); model.addAttribute("weixinShare",
		 * weixinShare); model.addAttribute("user", new
		 * ProfileDto(account.getProfile())); } return "amuse/" + type;
		 */
	}

	@RequestMapping("/loginaction")
	public void login(HttpServletRequest request, HttpServletResponse response, String code, String state,
			Integer fromuid, Model model) throws Exception {
		if (code != null) {
			WeixinUser wxuser = WeixinUtil.getAccessToken(code);
			String unionid = wxuser.getUnionid();
			Account account = userManager.getAccountByUnionid(unionid);
			Integer uid = null;
			String sessionid = null;
			if (account != null) {
				uid = account.getId();
				sessionid = CookieUtil.getSessionId(account.getId());
				userManager.login(account, sessionid, Config.LoginPlatformWeixin);
			} else {
				wxuser = WeixinUtil.getUserinfo(wxuser);
				Account _a = new Account();
				_a.setUnionid(wxuser.getUnionid());
				_a.setCreated(new Date());
				_a.setUsername(wxuser.getUnionid());
				_a.setPlatform(Config.LoginPlatformWeixin);
				_a.setPassword(MD5.md5(Config.DefaultPassword + Config.pwdExtention));
				Profile _p = new Profile();
				_p.setNickName(wxuser.getNickname());
				_p.setAvatar(wxuser.getAvatar());
				_p.setAccount(_a);
				_p.setSex(wxuser.getSex());
				_a.setProfile(_p);
				userManager.save(_a);
				uid = _a.getId();
				sessionid = CookieUtil.getSessionId(_a.getId());
				userManager.login(_a, sessionid, Config.LoginPlatformWeixin);
			}
			shareNotesManager.insert(uid, fromuid, state); // 插入分享记录
			// CookieUtil.addCookie(response, "amuse" + uid, sessionid, 0);
			if (StringUtil.isNotEmpty(state)) {
				response.sendRedirect("/amuse/share/" + state + "?fromuid=" + uid);// 反转
			}
		}
	}
}

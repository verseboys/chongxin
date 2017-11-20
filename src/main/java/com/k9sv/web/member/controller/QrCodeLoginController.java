package com.k9sv.web.member.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.k9sv.Config;
import com.k9sv.PayConfig;
import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.Profile;
import com.k9sv.service.IUserManager;
import com.k9sv.util.CookieUtil;
import com.k9sv.util.JsonUtil;
import com.k9sv.util.MD5;

/**
 * 二维码扫描登录
 * 
 * @author mcp
 * 
 */
@RequestMapping("/member")
@Scope("prototype")
@SuppressWarnings("deprecation")
@Controller("qrCodeLoginController")
public class QrCodeLoginController {

	Logger LOG = Logger.getLogger(QrCodeLoginController.class);
	private static HttpClient httpClient = new DefaultHttpClient();

	private static String access_token;
	private static String openid;
	private static String unionid;
	private static String nickname;
	private static String avatar;

	@Autowired
	IUserManager userManager;

	/**
	 * 微信回调、扫码登录
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/wechat/callback")
	public String callback(HttpServletRequest request, HttpServletResponse response, String code, String state,
			Model model) {

		Account account = userManager.getAccountByUnionid(unionid);
		try {
			this.getAccessToken(code);
			this.getUnionid();
			LOG.info("member-unionid:" + unionid);
			if (account == null) {
				account = this.addAccount();
			}
			String sessionid = this.getSessionId(account.getId());
			userManager.login(account, sessionid, Config.LoginPlatformWeb);
			CookieUtil.addCookie(response, "k9membertoken", sessionid, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/member/index";
	}

	/**
	 * 没有帐号默认添加一个
	 * 
	 * @return
	 */
	private Account addAccount() {
		Account _account = new Account();
		_account.setUsername(unionid);
		_account.setPassword(MD5.md5(Config.DefaultPassword + Config.pwdExtention));
		_account.setCreated(new Date());
		_account.setPlatform("weixin");
		_account.setChecked(0);
		_account.setUnionid(unionid);
		Profile _p = new Profile();
		_p.setNickName(nickname);
		_p.setGoldCount(0);
		_p.setSex(1);
		_p.setAvatar(avatar);
		_p.setLevel(1);
		_p.setScore(50);
		_p.setPrestige(0);
		_p.setViewCount(0);
		_account.setProfile(_p);
		_p.setAccount(_account);
		userManager.saveOrUpdate(_account);
		return _account;
	}

	/**
	 * 请求access_token
	 * 
	 * @param code
	 * @throws Exception
	 */
	private void getAccessToken(String code) throws Exception {
		HttpGet httpGet = new HttpGet("https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + PayConfig.WebAPPID
				+ "&secret=" + PayConfig.WebSECRET + "&code=" + code + "&grant_type=authorization_code");
		HttpResponse httpResponse = httpClient.execute(httpGet);
		HttpEntity httpEntity = httpResponse.getEntity();
		String html = EntityUtils.toString(httpEntity);
		JSONObject jsObject = new JSONObject(html);
		access_token = JsonUtil.getString(jsObject, "access_token");
		openid = JsonUtil.getString(jsObject, "openid");
		String uni = JsonUtil.getString(jsObject, "unionid");
		LOG.info("member-token-unionid:" + uni);
	}

	/**
	 * 请求用户信息
	 * 
	 * @throws Exception
	 */
	private void getUnionid() throws Exception {
		HttpGet httpGet2 = new HttpGet(
				"https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token + "&openid=" + openid);
		HttpResponse httpResponse2 = httpClient.execute(httpGet2);
		HttpEntity httpEntity2 = httpResponse2.getEntity();
		String html2 = EntityUtils.toString(httpEntity2);
		JSONObject jsObject2 = new JSONObject(html2);
		unionid = JsonUtil.getString(jsObject2, "unionid");
		nickname = JsonUtil.getString(jsObject2, "nickname");
		avatar = JsonUtil.getString(jsObject2, "headimgurl");
		// System.out.println(unionid);
	}

	private String getSessionId(int id) {
		Long i = System.currentTimeMillis();
		String sessionId = MD5.md5(i + " " + id);
		return sessionId;
	}

}

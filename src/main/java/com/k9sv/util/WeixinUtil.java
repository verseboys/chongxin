package com.k9sv.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.UUID;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.k9sv.PayConfig;
import com.k9sv.cache.WeixinCache;
import com.k9sv.domain.dto.WeixinAccessToken;
import com.k9sv.domain.dto.WeixinUser;
import com.k9sv.domain.pojo.WeixinShare;
import com.k9sv.web.controller.IndexController;

public class WeixinUtil {

	private static final Logger LOG = Logger.getLogger(IndexController.class);
	
	private static  WeixinAccessToken accessToken ;
	
	@SuppressWarnings("unused")
	private String getWeixinAccessToken() throws Exception {
		
		if(accessToken == null || (System.currentTimeMillis() - accessToken.getCreated()) > 7200){
			GetMethod getMethod = new GetMethod(
					"https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
							+ PayConfig.WeixinFuwu + "&secret="+ PayConfig.WeixinFuwuSecret);
			HttpClient httpClient = new HttpClient(); 
			httpClient.getParams().setContentCharset("UTF-8");
			getMethod.getParams().setContentCharset("UTF-8");
			//使用流的方式读取也可以如下设置
			httpClient.executeMethod(getMethod);
			InputStream in = getMethod.getResponseBodyAsStream();  
			//这里的编码规则要与上面的相对应  
			BufferedReader br = new BufferedReader(new InputStreamReader(in,"UTF-8"));
			
			JSONObject json = JsonUtil.getJson(br);
			LOG.info("callback response:"+json.toString());
			
			accessToken =  new WeixinAccessToken();
			accessToken.setToken(JsonUtil.getString(json, "access_token"));
			accessToken.setCreated(System.currentTimeMillis());
		}
		return accessToken.getToken();
		
	}

	public WeixinShare sign(String url) {
		// 公众号的全局唯一票据 令牌
		String jsapi_ticket = WeixinCache.AccessToken;
		String nonceStr = this.create_nonce_str();
		String timestamp = this.create_timestamp();
		String string1 = "";
		String signature = "";
		// 注意这里参数名必须全部小写，且必须有序
		string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonceStr + "&timestamp=" + timestamp + "&url=" + url;
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		WeixinShare weixinShare = new WeixinShare();
		weixinShare.setUrl(url);
		weixinShare.setTimestamp(timestamp);
		weixinShare.setSignature(signature);
		weixinShare.setNonceStr(nonceStr);
		weixinShare.setAppId(PayConfig.APPID);
		return weixinShare;
	}

	private String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	private String create_nonce_str() {
		return UUID.randomUUID().toString();
	}

	private String create_timestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}
	

	/**
	 * 请求access_token
	 * 
	 * @param code
	 * @throws Exception
	 */
	public static WeixinUser getAccessToken(String code) throws Exception {
		GetMethod getMethod = new GetMethod(
				"https://api.weixin.qq.com/sns/oauth2/access_token?appid="
						+ PayConfig.WeixinFuwu + "&secret=" + PayConfig.WeixinFuwuSecret
						+ "&code=" + code + "&grant_type=authorization_code");
		HttpClient httpClient = new HttpClient(); 
		
		httpClient.getParams().setContentCharset("UTF-8");
		getMethod.getParams().setContentCharset("UTF-8");
		//使用流的方式读取也可以如下设置
		httpClient.executeMethod(getMethod);
		InputStream in = getMethod.getResponseBodyAsStream();  
		//这里的编码规则要与上面的相对应  
		BufferedReader br = new BufferedReader(new InputStreamReader(in,"UTF-8"));
		
		JSONObject json = JsonUtil.getJson(br);
		LOG.info("callback response:"+json.toString());
		
		WeixinUser wx = new WeixinUser();
		wx.setAccess_token(JsonUtil.getString(json, "access_token"));
		wx.setOpenid(JsonUtil.getString(json, "openid"));
		wx.setUnionid(JsonUtil.getString(json, "unionid"));
		return wx;
	}

	/**
	 * 请求用户信息
	 * 
	 * @throws Exception
	 */
	public static WeixinUser getUserinfo(WeixinUser wx) throws Exception {
		GetMethod getMethod = new GetMethod("https://api.weixin.qq.com/sns/userinfo?access_token=" + wx.getAccess_token()
				+ "&openid=" + wx.getOpenid()+"&lang=zh_CN");
		HttpClient httpClient = new HttpClient(); 
		httpClient.getParams().setContentCharset("UTF-8");
		getMethod.getParams().setContentCharset("UTF-8");
		httpClient.executeMethod(getMethod);
		//使用流的方式读取也可以如下设置
		InputStream in = getMethod.getResponseBodyAsStream();  
		//这里的编码规则要与上面的相对应  
		BufferedReader br = new BufferedReader(new InputStreamReader(in,"UTF-8"));
		
		JSONObject json = JsonUtil.getJson(br);
		LOG.info("getuser info response:"+json.toString());
		String unionid = JsonUtil.getString(json, "unionid");
		wx.setUnionid(unionid);
		wx.setNickname(JsonUtil.getString(json, "nickname"));
		wx.setCity(JsonUtil.getString(json, "city"));
		wx.setSex(JsonUtil.getInt(json, "sex"));
		wx.setAvatar(JsonUtil.getString(json, "headimgurl"));
		return wx;
	}
}

package com.k9sv.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.k9sv.domain.dto.PushDto;
import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.Profile;

public class GetuiPush {

	private static final Logger LOG = Logger.getLogger(GetuiPush.class);

	// 个推
	private static String appId = "BxlmunkRg38vwtm8Fhah09";
	private static String appkey = "BBta5KLiax9DtI3K1YMLj1";
	private static String master = "VIZYRR1JaKAOoppzcIpay9";

	private static String appIdtest = "cj14dH76Lj5GXyRrMx1Ed5";
	private static String appkeytest = "LP1fy9oxus7YmiE0fRSxo2";
	private static String mastertest = "9B9wqmJFTJAUmSwkIj7hg9";
	//
	static String host = "http://sdk.open.api.igexin.com/apiex.htm";
	static String host2 = "http://sdk.open.api.igexin.com/serviceex";

	public static void push(String alter, PushDto pushDto, Profile toProfile) {

		String msg = pushDto.toString();
		LOG.info("push:" + msg);
		LOG.info("from--to:" + alter + "--" + toProfile.getNickName());
		try {
			pushSingleMessage(alter, msg, toProfile.getAccount());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 推单个透传消息
	 * */
	public static void pushSingleMessage(String alter, String msg,
			Account account) throws Exception {

		IGtPush push;
		TransmissionTemplate template;
		String appid;
		if ("ios-test".equals(account.getPlatform())) {
			push = new IGtPush(host, appkeytest, mastertest);
			appid = appIdtest;
			template = TransmissionTemplateDemo(appIdtest, appkeytest, msg,
					alter);
		} else {
			push = new IGtPush(host, appkey, master);
			template = TransmissionTemplateDemo(appId, appkey, msg, alter);
			appid = appId;
		}

		LOG.info("pushSingleMessage:" + account.getPlatform() + "|"
				+ account.getUsername() + "|" + msg);

		SingleMessage message = new SingleMessage();
		message.setOffline(true);
		// 离线有效时间，单位为毫秒，可选
		message.setOfflineExpireTime(20 * 1000 * 3600);

		message.setData(template);
		message.setPushNetWorkType(0); // 是否wifi推送，1：wifi推送；0：不限制推送方式

		Target target1 = new Target();
		target1.setAppId(appid);
		// 用户别名推送，cid和用户别名只能2者选其一
		target1.setClientId(account.getClientid());
		// target1.setAlias(clientid);
		try {
			IPushResult ret = push.pushMessageToSingle(message, target1);
			System.out.println("正常：" + ret.getResponse().toString());

		} catch (RequestException e) {
			String requstId = e.getRequestId();
			IPushResult ret = push.pushMessageToSingle(message, target1,
					requstId);

			System.out.println("异常：" + ret.getResponse().toString());
		}
		Thread.sleep(3);

	}

	/**
	 * 对指定列表用户推送消息
	 * 
	 * @param msg
	 * @param toProfiles
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void pushListMessage(Profile fromProfile,
			final PushDto pushDto, final List<Account> accounts, String alter) {

		String msg = pushDto.toString();
		// private String alter = "";
		LOG.info("pushListMessage:" + fromProfile.getNickName() + "|" + msg);
		LOG.info("alter:" + alter);
		String _appid = appId;
		String _appkey = appkey;
		String _master = master;
		try {
			if ("ios-test".equals(fromProfile.getAccount().getPlatform())) {
				// ios-test时只能ios间调试
				_appid = appIdtest;
				_appkey = appkeytest;
				_master = mastertest;
			}
			IGtPush push = new IGtPush(host, _appkey, _master); // 通知透传模板
			TransmissionTemplate template = TransmissionTemplateDemo(_appid,
					_appkey, msg, alter);

			ListMessage message = new ListMessage();
			message.setData(template);

			// 设置消息离线，并设置离线时间
			message.setOffline(true);
			// 离线有效时间，单位为毫秒，可选
			message.setOfflineExpireTime(20 * 1000 * 3600);
			message.setPushNetWorkType(0); // 是否wifi推送，1：wifi推送；0：不限制推送方式

			// 配置推送目标
			List targets = new ArrayList();
			for (Account account : accounts) {
				Target target = new Target();
				target.setAppId(_appid);
				target.setClientId(account.getClientid());
				targets.add(target);
			}
			// 获取taskID
			String taskId = push.getContentId(message);
			// 使用taskID对目标进行推送
			IPushResult ret = push.pushMessageToList(taskId, targets);
			System.out.println("正常：" + ret.getResponse().toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	public static TransmissionTemplate TransmissionTemplateDemo(String appId,
			String appKey, String msg, String alter) {

		TransmissionTemplate template = new TransmissionTemplate();
		// 设置APPID与APPKEY
		template.setAppId(appId);
		template.setAppkey(appKey);
		// 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
		template.setTransmissionType(2);
		template.setTransmissionContent(msg);

		APNPayload payload = new APNPayload();
		payload.setBadge(1);
		payload.setContentAvailable(1);
		payload.setSound("default");
		payload.setCategory("$由客户端定义");
		payload.setAlertMsg(new APNPayload.SimpleAlertMsg(alter));
		template.setAPNInfo(payload);

		return template;
	}
}

package com.k9sv.tenpay;

import java.io.ByteArrayInputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import com.k9sv.tenpay.client.HttpClient;
import com.k9sv.util.MD5;
import com.k9sv.util.StringUtil;

public class PrepayIdRequestHandler extends RequestHandler {
	
	Logger LOG = Logger.getLogger(PrepayIdRequestHandler.class);

	public PrepayIdRequestHandler(HttpServletRequest request,
			HttpServletResponse response) {
		super(request, response);
	}

	/**
	 * 创建预支付签名
	 * 
	 * @param signParams
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public String createSign() {
		StringBuffer sb = new StringBuffer();
		Set es = super.getAllParameters().entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			sb.append(k + "=" + v + "&");
		}
		String params = sb.toString() + "key=" + super.getKey();
		String prePaySign = MD5.MD5Encode(params, "UTF-8").toUpperCase();
		return prePaySign;
	}

	// 提交预支付
	@SuppressWarnings("rawtypes")
	public String sendPrepay() throws Exception {
		String prepayid = "";
		StringBuffer sb = new StringBuffer("<xml>");
		Set es = super.getAllParameters().entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (StringUtil.isNotEmpty(v)) {
				sb.append("<" + k + ">" + v + "</" + k + ">");
			}
		}
		sb.append("</xml>");
		String params = sb.toString();
		LOG.info("params------值 " + params);
		String requestUrl = super.getGateUrl();
		HttpClient httpClient = new HttpClient();
		httpClient.setReqUrl(requestUrl);
		httpClient.setCharset("UTF-8");
		httpClient.setTimeOut(10);

		if (httpClient.callHttpPost(requestUrl, params)) {

			String resContent = httpClient.getResContent();// 应答内容
			LOG.info("prepay_id===="+resContent);
			try {
				SAXReader reader = new SAXReader();
				Document document = reader.read(new ByteArrayInputStream(
						resContent.getBytes("UTF-8")));
				Element root = document.getRootElement();
				if ("SUCCESS".equals(root.elementText("return_code"))
						&& "SUCCESS".equals(root.elementText("result_code"))) {
					prepayid = root.elementText("prepay_id");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return prepayid;
	}
}

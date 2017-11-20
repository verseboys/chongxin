package com.k9sv.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URLEncoder;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.log4j.Logger;

public class SMS {

	private static String NO = "6SDK-EMY-6688-KBYTQ";
	public static String key = "570637";
	private static final Logger LOG = Logger.getLogger(AppUtil.class);
	private static HttpClient client = null;

	static {
		MultiThreadedHttpConnectionManager httpConnectionManager = new MultiThreadedHttpConnectionManager();
		HttpConnectionManagerParams params = new HttpConnectionManagerParams();
		// 默认连接超时时间
		params.setConnectionTimeout(5000);
		// 默认读取超时时间
		params.setSoTimeout(10000);
		// 默认单个host最大连接数
		params.setDefaultMaxConnectionsPerHost(100);// very important!!
		// 最大总连接数
		params.setMaxTotalConnections(256);// very important!!
		httpConnectionManager.setParams(params);

		client = new HttpClient(httpConnectionManager);
	}

	@SuppressWarnings("unused")
	public static void sendSMS(String phone, String message) {
		Long i = System.currentTimeMillis();
		String response = "";
		try {
			String url = "http://sdk4report.eucp.b2m.cn:8080/sdkproxy/sendsms.action?cdkey=" + SMS.NO + "&password="
					+ SMS.key + "&phone=" + phone + "&message=" + URLEncoder.encode(message, "UTF-8") + "&seqid=" + i
					+ "&addserial=10";
			HttpMethod httpmethod = new GetMethod(url);
			int statusCode = client.executeMethod(httpmethod);
			InputStream _InputStream = null;
			if (statusCode == HttpStatus.SC_OK) {
				_InputStream = httpmethod.getResponseBodyAsStream();
			}
			if (_InputStream != null) {
				response = GetResponseString(_InputStream, "UTF-8");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param _InputStream
	 * @param Charset
	 * @return
	 */
	public static String GetResponseString(InputStream _InputStream, String Charset) {
		String response = "";
		try {
			if (_InputStream != null) {
				StringBuffer buffer = new StringBuffer();
				InputStreamReader isr = new InputStreamReader(_InputStream, Charset);
				Reader in = new BufferedReader(isr);
				int ch;
				while ((ch = in.read()) > -1) {
					buffer.append((char) ch);
				}
				response = buffer.toString();
				buffer = null;
			}
		} catch (Exception e) {
			LOG.error("获取响应错误，原因：" + e.getMessage());
			response = response + e.getMessage();
			e.printStackTrace();
		}
		return response;
	}

	public static void main(String[] args) {
		SMS.sendSMS("18058748997", "");
	}

}

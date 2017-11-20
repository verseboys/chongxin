package com.k9sv.tenpay.client;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import com.k9sv.tenpay.util.HttpClientUtil;

public class HttpClient {

	// private static final String user_agent_value =
	// "Mozilla/4.0 (compatible; MSIE 6.0; Windows XP)";
	private static final String user_agent_value = "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/534.3 (KHTML, like Gecko) Chrome/6.0.472.63 Safari/534.3";

	/** 请求链接 */
	private String requestUrl;
	/** 字符编码 */
	private String charset;
	/** 响应超时时间 S */
	private int timeOut;
	/** 请求内容 */
	private String reqContent;
	// ////////////////////////////////////////////////
	/** http应答编码 */
	private int responseCode;
	/** http应答输入流 */
	private InputStream inputStream;
	/** 错误信息 */
	@SuppressWarnings("unused")
	private String errInfo;
	/** 应答内容 */
	private String resContent;

	/**
	 * 设置请求链接
	 * 
	 * @param requestUrl
	 */
	public void setReqUrl(String requestUrl) {
		this.setRequestUrl(requestUrl);
	}
	
	/**
	 * 请求Post应答
	 * 
	 * @param requestUrl
	 * @param params
	 * @return
	 */
	public boolean callHttpPost(String requestUrl, String params) {
		boolean flag = false;
		byte[] postData;
		try {
			postData = params.getBytes(this.charset);
			this.httpPostMethod(requestUrl, postData);
			flag = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 以http post方式通信
	 * 
	 * @param url
	 * @param postData
	 * @throws IOException
	 */
	protected void httpPostMethod(String url, byte[] postData)
			throws IOException {
		HttpURLConnection conn = HttpClientUtil.getHttpURLConnection(url);
		this.doPost(conn, postData);
	}

	/**
	 * post方式处理
	 * 
	 * @param conn
	 * @param postData
	 * @throws IOException
	 */
	protected void doPost(HttpURLConnection conn, byte[] postData)
			throws IOException {

		// 以post方式通信
		conn.setRequestMethod("POST");
		// 设置请求默认属性
		this.setHttpRequest(conn);
		// Content-Type
		conn.setRequestProperty("Content-Type", "text/xml");

		BufferedOutputStream out = new BufferedOutputStream(
				conn.getOutputStream());

		final int len = 1024; // 1KB
		HttpClientUtil.doOutput(out, postData, len);
		// 关闭流
		out.close();
		// 获取响应返回状态码
		this.responseCode = conn.getResponseCode();
		// 获取应答输入流
		this.inputStream = conn.getInputStream();

	}

	/**
	 * 设置http请求默认属性
	 * 
	 * @param httpConnection
	 */
	protected void setHttpRequest(HttpURLConnection httpConnection) {

		// 设置连接超时时间
		httpConnection.setConnectTimeout(this.timeOut * 1000);

		// User-Agent
		// httpConnection.setRequestProperty("User-Agent", "Mozilla/4.0");

		httpConnection.setRequestProperty("User-Agent",
				HttpClient.user_agent_value);
		// 不使用缓存
		httpConnection.setUseCaches(false);
		// 允许输入输出
		httpConnection.setDoInput(true);
		httpConnection.setDoOutput(true);
	}

	/**
	 * 获取结果内容
	 * 
	 * @return String
	 * @throws IOException
	 */
	public String getResContent() {
		try {
			this.doResponse();
		} catch (IOException e) {
			this.errInfo = e.getMessage();
		}
		return this.resContent;
	}

	/**
	 * 处理应答
	 * 
	 * @throws IOException
	 */
	protected void doResponse() throws IOException {

		if (null == this.inputStream) {
			return;
		}
		// 获取应答内容
		this.resContent = HttpClientUtil.InputStreamTOString(this.inputStream,
				this.charset);
		// 关闭输入流
		this.inputStream.close();
	}

	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public int getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}

	public String getReqContent() {
		return reqContent;
	}

	public void setReqContent(String reqContent) {
		this.reqContent = reqContent;
	}

}

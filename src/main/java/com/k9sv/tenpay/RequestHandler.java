package com.k9sv.tenpay;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.k9sv.tenpay.util.TenpayUtil;
import com.k9sv.util.MD5;

/**
 * 
 * 
 * @author mcp
 * 
 */
@SuppressWarnings("rawtypes")
public class RequestHandler {

	/** 网关url地址 */
	private String gateUrl;

	/** 密钥 */
	private String key;

	/** 请求的参数 */
	private SortedMap parameters;

	protected HttpServletRequest request;

	protected HttpServletResponse response;

	/**
	 * 构造函数
	 * 
	 * @param request
	 * @param response
	 */
	public RequestHandler(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.parameters = new TreeMap();
	}

	/**
	 * 初始化函数。
	 */
	public void init() {

	}

	/**
	 * 获取带参数的请求URL
	 * 
	 * @return String
	 * @throws UnsupportedEncodingException
	 */
	public String getRequestURL() throws UnsupportedEncodingException {

		this.createSign();

		StringBuffer sb = new StringBuffer();
		String enc = TenpayUtil.getCharacterEncoding(this.request, this.response);
		Set es = this.parameters.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (!"spbill_create_ip".equals(k)) {
				sb.append(k + "=" + URLEncoder.encode(v, enc) + "&");
			} else {
				sb.append(k + "=" + v.replace("\\.", "%2E") + "&");
			}
		}
		// 去掉最后一个&
		String reqPars = sb.substring(0, sb.lastIndexOf("&"));
		return this.getGateUrl() + "?" + reqPars;
	}

	/**
	 * 创建md5摘要,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
	 */
	protected String createSign() {
		StringBuffer sb = new StringBuffer();
		Set es = this.parameters.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + this.getKey());
		String enc = TenpayUtil.getCharacterEncoding(this.request, this.response);
		String sign = MD5.MD5Encode(sb.toString(), enc).toUpperCase();
		this.setParameter("sign", sign);
		return sign;
	}

	/**
	 * 设置参数值
	 * 
	 * @param parameter
	 *            参数名称
	 * @param parameterValue
	 *            参数值
	 */
	@SuppressWarnings("unchecked")
	public void setParameter(String parameter, String parameterValue) {
		String v = "";
		if (null != parameterValue) {
			v = parameterValue.trim();
		}
		this.parameters.put(parameter, v);
	}

	/**
	 * 返回所有的参数
	 * 
	 * @return SortedMap
	 */
	public SortedMap getAllParameters() {
		return this.parameters;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getGateUrl() {
		return gateUrl;
	}

	public void setGateUrl(String gateUrl) {
		this.gateUrl = gateUrl;
	}

}

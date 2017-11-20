package com.k9sv.tenpay;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.k9sv.util.MD5;

//import com.k9sv.util.MD5;

public class ClientRequestHandler extends PrepayIdRequestHandler {

	public ClientRequestHandler(HttpServletRequest request,
			HttpServletResponse response) {
		super(request, response);
	}

	/**
	 * 创建签名
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
}

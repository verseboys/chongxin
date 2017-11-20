package com.k9sv.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.log4j.Logger;


public class NewClientUtil {
	private static String NO ="6SDK-EMY-6688-KBYTQ";
	public static String key = "570637";
	private static final Logger LOG = Logger.getLogger(AppUtil.class);
	private  static HttpClient client = null;
	//构造单例
	private NewClientUtil(){
		
		MultiThreadedHttpConnectionManager httpConnectionManager = new MultiThreadedHttpConnectionManager();       
		HttpConnectionManagerParams params = new HttpConnectionManagerParams();	
		// 默认连接超时时间
        params.setConnectionTimeout(5000);
        // 默认读取超时时间
        params.setSoTimeout(10000); 
        // 默认单个host最大连接数
        params.setDefaultMaxConnectionsPerHost(100);//very important!! 
        // 最大总连接数
        params.setMaxTotalConnections(256);//very important!! 
        httpConnectionManager.setParams(params);
        
        client = new HttpClient(httpConnectionManager);
        
        client.getParams().setConnectionManagerTimeout(3000);
//      client.getParams().setIntParameter("http.socket.timeout", 10000);
//      client.getParams().setIntParameter("http.connection.timeout", 5000);
	}
	 private static class ClientUtilInstance {
	        private static final NewClientUtil ClientUtil = new NewClientUtil();
	    }

	 public static NewClientUtil getInstance() {
	      return ClientUtilInstance.ClientUtil;
	 }
	 /**
     * 发送http GET请求，并返回http响应字符串
     * 
     * @param urlstr 完整的请求url字符串
     * @return
     */
	 public String doGetRequest(String urlstr) {    	
    	String response = "";
    	
        HttpMethod httpmethod = new GetMethod(urlstr);
        try {
            int statusCode = client.executeMethod(httpmethod);
            InputStream _InputStream = null;
            if (statusCode == HttpStatus.SC_OK) {
            	 _InputStream = httpmethod.getResponseBodyAsStream();
            }
            if (_InputStream != null) {
            	response = GetResponseString(_InputStream,"UTF-8");
            }
        } catch (HttpException e) {
        	LOG.error("获取响应错误，原因：" + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
        	LOG.error("获取响应错误，原因1：" + e.getMessage());
            e.printStackTrace();
        } finally {
            httpmethod.releaseConnection();
           
        }
        return response;
    }
	 /**
     * 发送http GET请求，并返回http响应字符串
     * 
     * @param urlstr 完整的请求url字符串
     * @return
     */
	 public String doPostRequest(String url, Map<String, String> params) {    	
    	String response = "";
    	PostMethod postMethod = new PostMethod(url);   
        try {
        	if(params != null && params.size() > 0) {
        		NameValuePair[] nameValue=new NameValuePair[] {
        				new NameValuePair("cdkey", params.get("cdkey")),
        				new NameValuePair("password",params.get("password")),
        				new NameValuePair("phone", params.get("phone")),
        				new NameValuePair("message", params.get("message")),
        				new NameValuePair("addserial", params.get("addserial")),
        				new NameValuePair("seqid", params.get("seqid")),
        				new NameValuePair("smspriority", params.get("smspriority"))
        		};
        		postMethod.setRequestBody(nameValue); 
        	}
            int statusCode = client.executeMethod(postMethod);
            InputStream _InputStream = null;
            if (statusCode == HttpStatus.SC_OK) {
            	 _InputStream = postMethod.getResponseBodyAsStream();
            }
            if (_InputStream != null) {
            	response = GetResponseString(_InputStream,"UTF-8");
            }
        } catch (HttpException e) {
        	LOG.error("获取响应错误，原因：" + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
        	LOG.error("获取响应错误，原因1：" + e.getMessage());
            e.printStackTrace();
        } finally {
        	postMethod.releaseConnection();
        }
        return response;
    }
	  
    /**
	 * 
	 * @param _InputStream
	 * @param Charset
	 * @return
	 */
	 public String GetResponseString(InputStream _InputStream, String Charset) {
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
	 
	 
    public static void main(String[] args) throws Exception{
    	NewClientUtil n = new NewClientUtil();
    	String phone = "18058748997";
    	String message = "【宠信app】的验证码是:1234";
    	Long i = System.currentTimeMillis();
    	String url = "http://sdk4report.eucp.b2m.cn:8080/sdkproxy/sendsms.action?cdkey="+NewClientUtil.NO+
    			"&password="+NewClientUtil.key+"&phone="+phone+"&message="+URLEncoder.encode(message,"UTF-8")+"&seqid="+i+"&addserial=10";
    	System.out.println(n.doGetRequest(url));
    }
}

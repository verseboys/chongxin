package com.k9sv.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class ActionUtil {
	/**
	 * session中用户名
	 */
	public static final String SESSION_USERNAME = "session_username";
	/**
	 * action中返回值
	 */
	public static final class Result{
		/**成功*/
		public static final String SUCCESS = "success";
		/**返回输入页面*/
		public static final String INPUT = "input";
		/**重定向到登录页面*/
		public static final String LOGIN = "login";
		
		public static final String LOGIN_DIALOG = "login_dialog";
		/**内部转向到action属性中url值对应的jsp页面*/
		public static final String FORWARD = "forward";
		/**重定向到action属性中url值对应的地址*/
		public static final String REDIRECT = "redirect";
		/**内部转向到action属性中url值对应的action*/
		public static final String CHAIN = "chain";
	}
	/**
	 * 是否ajax请求
	 */
	public static boolean isAjaxRequest(HttpServletRequest request){
		if ("XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With")) || request.getParameter("ajax") != null){
			return true;
		}
		return false;
	}
	
	/**
	 * 得到分页的页数
	 * @param total
	 * @param pageSize
	 * @return
	 */
	public static int getNumOfPages(int totalCount,int pageSize){
		if (totalCount<=0){
			return 0;
		}
		if (pageSize<=0){
			pageSize = 20;
		}
		if (totalCount%pageSize==0){
			return totalCount/pageSize;
		}else{
			return totalCount/pageSize+1;
		}
	}
	
	/**
	 * 得到dwz ajax返回的字符串
      "statusCode":"200", 
      "message":"操作成功", 
      "navTabId":"", 
      "rel":"", 
      "callbackType":"closeCurrent",
      "forwardUrl":""
	 * 服务器转回navTabId可以把那个navTab标记为reloadFlag=1, 下次切换到那个navTab时会重新载入内容. 
	 * callbackType如果是closeCurrent就会关闭当前tab
	 * 只有callbackType="forward"时需要forwardUrl值
	 * @return
	 */
	public static String getDWZajaxReturn(String code,String msg,String navTabId,String rel,String callbackType,String forwardUrl){
		StringBuffer sb = new StringBuffer();
		sb.append("{\"statusCode\":\"").append(code).append("\",\"message\":\"").append(msg).append("\"");
		if (StringUtil.isNotEmpty(navTabId)){
			sb.append(",\"navTabId\":\"").append(navTabId).append("\"");
		}
		if (StringUtil.isNotEmpty(rel)){
			sb.append(",\"rel\":\"").append(rel).append("\"");
		}
		if (StringUtil.isNotEmpty(callbackType)){
			sb.append(",\"callbackType\":\"").append(callbackType).append("\""   );
		}
		if (StringUtil.isNotEmpty(forwardUrl)){
			sb.append(",\"forwardUrl\":\"").append(forwardUrl).append("\"");
		}
		sb.append("}");
		return sb.toString();
	}
	
	public static String getDWZajaxReturn2(String code,String msg,String navTabId,String rel,String callbackType,String forwardUrl,String menu){
		StringBuffer sb = new StringBuffer();
		sb.append("{\"statusCode\":\"").append(code).append("\",\"message\":\"").append(msg).append("\"");
		if (StringUtil.isNotEmpty(navTabId)){
			sb.append(",\"navTabId\":\"").append(navTabId).append("\"");
		}
		if (StringUtil.isNotEmpty(rel)){
			sb.append(",\"rel\":\"").append(rel).append("\"");
		}
		if (StringUtil.isNotEmpty(callbackType)){
			sb.append(",\"callbackType\":\"").append(callbackType).append("\""   );
		}
		if (StringUtil.isNotEmpty(forwardUrl)){
			sb.append(",\"forwardUrl\":\"").append(forwardUrl).append("\"");
		}
		if (StringUtil.isNotEmpty(menu)){
			sb.append(",\"refresh\":\"").append(menu).append("\"");
		}
		sb.append("}");
		//System.out.println(sb.toString());
		return sb.toString();
	}
	
	/**
	 * 上传文件工具
	 * @param src
	 * @param dst
	 */
	// 上传文件/复制文件。src本地文件路径，dst服务器文件路径
	public static void copyFile(File src, File dst) {
		try {
			int BUFFER_SIZE = 16 * 1024;
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream(src),
						BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream(dst),
						BUFFER_SIZE);
				byte[] buffer = new byte[BUFFER_SIZE]; 
				for (int byteRead = 0; (byteRead = in.read(buffer)) > 0; ) 
				{ 
					out.write(buffer, 0, byteRead); 
				} 

			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *  分页处理函数
	 * @param totalPage
	 * @param numPerPage
	 * @param pageNum
	 * @return
	 */
    public static List<String> getPages(int totalPage,int numPerPage, int pageNum){
    	
    	List<String> pages = new ArrayList<String>();
		int minNext = pageNum/numPerPage<1?numPerPage*(pageNum/numPerPage)+1:numPerPage*(pageNum/numPerPage);
		int maxNext = numPerPage*(pageNum/numPerPage)+numPerPage-1>=totalPage?totalPage:numPerPage*(pageNum/numPerPage)+numPerPage-1;
		for (int j = minNext; j<=maxNext; j++){
			pages.add(j+"");
		}
		if(maxNext<totalPage){
			pages.add("0");
			pages.add(totalPage+"");
		}
		return pages;

    	
    }
}
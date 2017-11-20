package com.k9sv.util;

import java.util.Random;
import java.util.regex.Pattern;

public class StringUtil {

	/**
	 * 获取随机数字串
	 * 
	 * @param size
	 * @return
	 */
	public static String getRandomNum(int size) {// 随机字符串
		char[] c = { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };
		Random random = new Random(); // 初始化随机数产生器
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < size; i++) {
			sb.append(c[Math.abs(random.nextInt()) % c.length]);
		}
		return sb.toString();
	}
	
	/**
	 * 获取随机字符串
	 * 
	 * @param size
	 * @return
	 */
	public static String getRandomString(int size) {// 随机字符串
		char[] c = { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'q',
				'Q', 'w', 'W', 'e', 'E', 'r', 'R', 't', 'T', 'y', 'Y', 'u',
				'U', 'i', 'I', 'o', 'O', 'p', 'P', 'a', 'A', 's', 'S', 'd',
				'D', 'f', 'F', 'g', 'G', 'h', 'H', 'j', 'J', 'k', 'K', 'l',
				'L', 'z', 'Z', 'x', 'X', 'c', 'C', 'v', 'V', 'b', 'B', 'n',
				'N', 'm', 'M' };
		Random random = new Random(); // 初始化随机数产生器
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < size; i++) {
			sb.append(c[Math.abs(random.nextInt()) % c.length]);
		}
		return sb.toString();
	}
	
	public static boolean isNotEmpty(String str) {
		if (str == null || "".equals(str.trim()))
			return false;
		return true;
	}
	
	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str.trim()))
			return true;
		return false;
	}
	
	public static String Html2Text(String inputString) {
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";
		if (isNotEmpty(inputString)) {
			java.util.regex.Pattern p_script;
			java.util.regex.Matcher m_script;
			java.util.regex.Pattern p_style;
			java.util.regex.Matcher m_style;
			java.util.regex.Pattern p_html;
			java.util.regex.Matcher m_html;

			try {
				String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
				// }
				String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
				// }
				String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

				p_script = Pattern.compile(regEx_script,
						Pattern.CASE_INSENSITIVE);
				m_script = p_script.matcher(htmlStr);
				htmlStr = m_script.replaceAll(""); // 过滤script标签

				p_style = Pattern
						.compile(regEx_style, Pattern.CASE_INSENSITIVE);
				m_style = p_style.matcher(htmlStr);
				htmlStr = m_style.replaceAll(""); // 过滤style标签

				p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
				m_html = p_html.matcher(htmlStr);
				htmlStr = m_html.replaceAll(""); // 过滤html标签

				textStr = htmlStr;

			} catch (Exception e) {
				System.err.println("Html2Text: " + e.getMessage());
			}
		}

		return textStr;// 返回文本字符串
	}
	
	public static int getSex(String gender){
		if("男".equals(gender)){
			return 1;
		}
		return 0;
	}
}

package com.k9sv.util;

/**
 * 
 * @author mcp
 * 
 */
public class PageUtil {
	/**
	 * 效果： 共有30条记录,当前显示第 2 页 < 1 2 3 4 ... 10>
	 * 
	 * @param total
	 * @param pagesize
	 * @param pagenum
	 * @param pageurl
	 * @return
	 */
	public static String getPagerNormal(int total, int pagesize, int pagenum,
			String pageurl) {
		int count = total / pagesize;
		if (total % pagesize >= 0) {
			count++;
		}
		if (pageurl.indexOf("?") > -1) {
			pageurl = pageurl + "&";
		} else {
			pageurl = pageurl + "?";
		}
		int pre = pagenum - 1;
		if (pre <= 0) {
			pre = 1;
		}
		int next = pagenum + 1;
		if (next >= count) {
			next = count;
		}
		StringBuffer buf = new StringBuffer();
		buf.append("<div class='message'>共<i class='blue'>" + total
				+ "</i>条记录，当前显示第&nbsp;<i class='blue'>" + pagenum
				+ "&nbsp;</i>页</div>");
		buf.append(" <ul class='paginList'><li class='paginItem'><a href="
				+ pageurl + "pageNum=" + pre
				+ "><span class='pagepre'></span></a></li>");
		int bound1 = ((pagenum - 2) <= 0) ? 1 : (pagenum - 2);
		int bound2 = ((pagenum + 2) >= count) ? count : (pagenum + 2);
		for (int i = bound1; i <= bound2; i++) {
			if (i == pagenum) {
				buf.append("<li class='paginItem current'><a href='javascript:;'>"
						+ i + "</a></li>");
			} else {
				buf.append("<li class='paginItem'><a href=" + pageurl
						+ "pageNum=" + i + ">" + i + "</a></li>");
			}
		}
		if (bound2 < count) {
			buf.append("<li class='paginItem more'><a href='javascript:;'>...</a></li><li class='paginItem'><a href="
					+ pageurl + "pageNum=" + count + ">" + count + "</a></li>");
		}
		buf.append("<li class='paginItem'><a href=" + pageurl + "pageNum="
				+ next + "><span class='pagenxt'></span></a></li></ul>");
		return buf.toString();
	}

	public static String getPagerNormal2(int total, int pagesize, int pagenum,
			String pageurl, int type) {
		int count = total / pagesize;
		if (total % pagesize >= 0) {
			count++;
		}
		if (pageurl.indexOf("?") > -1) {
			pageurl = pageurl + "&";
		} else {
			pageurl = pageurl + "?";
		}
		int pre = pagenum - 1;
		if (pre <= 0) {
			pre = 1;
		}
		int next = pagenum + 1;
		if (next >= count) {
			next = count;
		}
		StringBuffer buf = new StringBuffer();
		buf.append("<div class='message'>共<i class='blue'>" + total
				+ "</i>条记录，当前显示第&nbsp;<i class='blue'>" + pagenum
				+ "&nbsp;</i>页</div>");
		buf.append(" <ul class='paginList'><li class='paginItem'><a href='javascript:;' onclick='javascript:querydog("
				+ type
				+ ","
				+ pre
				+ ");'><span class='pagepre'></span></a></li>");
		int bound1 = ((pagenum - 2) <= 0) ? 1 : (pagenum - 2);
		int bound2 = ((pagenum + 2) >= count) ? count : (pagenum + 2);
		for (int i = bound1; i <= bound2; i++) {
			if (i == pagenum) {
				buf.append("<li class='paginItem current'><a href='javascript:;'>"
						+ i + "</a></li>");
			} else {
				buf.append("<li class='paginItem'><a href='javascript:;' onclick='javascript:querydog("
						+ type + "," + i + ");'>" + i + "</a></li>");
			}
		}
		if (bound2 < count) {
			buf.append("<li class='paginItem more'><a href='javascript:;'>...</a></li><li class='paginItem'><a href='javascript:;' onclick='javascript:querydog("
					+ type + "," + count + ");'>" + count + "</a></li>");
		}
		buf.append("<li class='paginItem'><a href='javascript:;' onclick='javascript:querydog("
				+ type
				+ ","
				+ next
				+ ");'><span class='pagenxt'></span></a></li></ul>");
		return buf.toString();
	}

}
package com.k9sv.util;


/**
 * 菜单主体
 * 
 * @author Administrator
 * 
 */
public class MenuUtil {
	private String head;
	
	private String menuId;

	private StringBuffer menu = new StringBuffer();

	private int isFloder;// 0:不带文件夹; 1:带文件夹
	
	public MenuUtil() {
		
	}

	public MenuUtil(String head, int isFloder) {
		this.head = head;
		this.isFloder = isFloder;
	}
	
	public MenuUtil(String head,String menuId, int isFloder) {
		this.head = head;
		this.menuId = menuId;
		this.isFloder = isFloder;
	}

	private String head() {
		StringBuffer sb = new StringBuffer();
		sb.append("<li>");
		if ((this.menuId)!=null && !"".equals(this.menuId)) {
			sb.append("<a  menuid=\""+this.menuId+"\" name=\""+this.head+"\">" + this.head + "</a>");
		}else {
			sb.append("<a>" + this.head + "</a>");
		}
		sb.append("<ul>");
		return sb.toString();
	}

	private String foot() {
		StringBuffer sb = new StringBuffer();
		sb.append("</ul>");
		sb.append("</li>");
		return sb.toString();
	}
	/**
	 * 左侧滑动菜单
	 * @param url
	 * @param target
	 * @param rel
	 * @param name
	 */
	public void createMenu(String url, String target, String rel, String name) {
		this.menu.append("<li><a href=\"" + url + "\" target=\"" + target
				+ "\" rel=\"" + rel + "\">" + name + "</a></li>");
	}
	/**
	 * 页面中要用到的菜单
	 * @param name
	 */
	public void createTreeMenu(String name,String menuId) {
		this.menu.append("<li><a menuid=\""+menuId+"\" name=\""+name+"\">" + name + "</a></li>");
	}

	public void addString(String string) {
		this.menu.append(string);
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		if (this.isFloder == 1)
			sb.append(head());
		sb.append(this.menu.toString());
		if (this.isFloder == 1)
			sb.append(foot());
		return sb.toString();
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public StringBuffer getMenu() {
		return menu;
	}

	public void setMenu(StringBuffer menu) {
		this.menu = menu;
	}

	public int getIsFloder() {
		return isFloder;
	}

	public void setIsFloder(int isFloder) {
		this.isFloder = isFloder;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

}

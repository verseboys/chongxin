package com.k9sv.util;

public class AccordionMenu {

	private String menuName;

	private String body;

	public AccordionMenu() {
		super();
	}

	/********************* 左侧菜单列表使用 ********************************/
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<div class=\"accordionHeader\"><h2><span>icon</span>");
		sb.append(menuName);
		sb.append("</h2></div><div class=\"accordionContent\" style=\"height:200px\"><ul class=\"tree treeFolder collapse\">");
		sb.append(body);
		sb.append("</ul></div>");

		return sb.toString();
	}

	/********************* 页面菜单列表使用 ********************************/
	public String toTreeString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<ul class=\"tree treeFolder expand\">");
		sb.append(body);
		sb.append("</ul>");
		return sb.toString();
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}

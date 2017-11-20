package com.k9sv.domain.pojo;

import java.util.Date;

public class Blog implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1812609479145220044L;

	private int id;
	
	private int type;//0 日常博客，1关于我们，系统文章，
	
	private int status;//-1回收站，0 正常，1推荐
	
	private int uid;
	
	private String title;
	
	private String content;
	
	private Date created;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	
}

package com.k9sv.domain.pojo;

import java.util.Date;

/**
 * Menu entity. @author MyEclipse Persistence Tools
 */

public class AdminMenu implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String fid;
	private Boolean state;
	private String url;
	private int type;
	private Date createtime;

	// Constructors

	/** default constructor */
	public AdminMenu() {
	}

	/** minimal constructor */
	public AdminMenu(String name, Boolean state) {
		this.name = name;
		this.state = state;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFid() {
		return this.fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public Boolean getState() {
		return this.state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
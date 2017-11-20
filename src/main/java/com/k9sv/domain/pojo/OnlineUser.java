package com.k9sv.domain.pojo;

import java.util.Date;

public class OnlineUser implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8670951420035418484L;
	
	private String id;
	
	private int uid;
	
	private Date updated;
	
	private String platform; //"mobile","web","weixin"
	
	private Account account;

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}
	
	

}

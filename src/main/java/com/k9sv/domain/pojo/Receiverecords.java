package com.k9sv.domain.pojo;

/**
 * Receiverecords entity. @author MyEclipse Persistence Tools
 */

public class Receiverecords implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1561744182207992414L;
	private int id;
	private int activityid;
	private String unionid;
	private int accountid;
	private Account account;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getActivityid() {
		return activityid;
	}

	public void setActivityid(int activityid) {
		this.activityid = activityid;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public int getAccountid() {
		return accountid;
	}

	public void setAccountid(int accountid) {
		this.accountid = accountid;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
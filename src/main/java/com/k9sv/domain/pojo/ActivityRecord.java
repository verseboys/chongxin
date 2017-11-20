package com.k9sv.domain.pojo;

import java.util.Date;

import com.k9sv.util.DateUtil;
@SuppressWarnings("unused")
public class ActivityRecord implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3251678663039003504L;

	private int id;

	private int aid;

	private int uid;

	private String mobile;

	private Account account;

	private Activity activity;

	private Date created;

	private int beused;

	private String createdStr;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public String getCreatedStr() {
		try {
			return DateUtil.getFormatDateTime(this.created, "yyyy-MM-dd");
		} catch (Exception e) {
			return null;
		}
	}

	public int getBeused() {
		return beused;
	}

	public void setBeused(int beused) {
		this.beused = beused;
	}

}

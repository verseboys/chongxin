package com.k9sv.domain.pojo;

import java.util.Date;

import com.k9sv.util.DateUtil;

/**
 * WarrantyOperation entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings({ "unused", "serial" })
public class WarrantyOperation implements java.io.Serializable {

	// Fields

	private int id;
	private String wid;
	private int uid;
	private int money;
	private String remark;
	private Date created;
	private String createdStr;

	private Profile profile;

	// Constructors

	/** default constructor */
	public WarrantyOperation() {
	}

	/** full constructor */
	public WarrantyOperation(String wid, int uid, String remark, Date created) {
		this.wid = wid;
		this.uid = uid;
		this.remark = remark;
		this.created = created;
	}

	// Property accessors

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWid() {
		return this.wid;
	}

	public void setWid(String wid) {
		this.wid = wid;
	}

	public int getUid() {
		return this.uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public String getCreatedStr() {
		return DateUtil.getFormatDateTime(this.created, "yyyy-MM-dd");
	}

}
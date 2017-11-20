package com.k9sv.domain.pojo;

import java.util.Date;

import com.k9sv.util.DateUtil;

/**
 * Warranty entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings({ "unused", "serial" })
public class Warranty implements java.io.Serializable {

	// Fields

	private String id;
	private String blood;
	private int uid;
	private int price;
	private int checked;
	private int deleted;
	private Date created;
	private Date paytime;
	private Date starttime;
	private Date endtime;

	private Profile profile;
	private String createdStr;
	private String endtimeStr;

	// Constructors

	/** default constructor */
	public Warranty() {
	}

	/** full constructor */
	public Warranty(String blood, int uid, int price, int checked, int deleted, Date created, Date paytime,
			Date starttime, Date endtime) {
		this.setBlood(blood);
		this.uid = uid;
		this.price = price;
		this.checked = checked;
		this.deleted = deleted;
		this.created = created;
		this.paytime = paytime;
		this.starttime = starttime;
		this.endtime = endtime;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getUid() {
		return this.uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getChecked() {
		return this.checked;
	}

	public void setChecked(int checked) {
		this.checked = checked;
	}

	public int getDeleted() {
		return this.deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getPaytime() {
		return this.paytime;
	}

	public void setPaytime(Date paytime) {
		this.paytime = paytime;
	}

	public Date getStarttime() {
		return this.starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEndtime() {
		return this.endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public String getBlood() {
		return blood;
	}

	public void setBlood(String blood) {
		this.blood = blood;
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

	public String getEndtimeStr() {
		return DateUtil.getFormatDateTime(this.endtime, "yyyy-MM-dd");
	}

}
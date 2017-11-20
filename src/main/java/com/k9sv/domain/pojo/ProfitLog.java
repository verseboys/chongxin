package com.k9sv.domain.pojo;

import java.util.Date;

import com.k9sv.util.DateUtil;

/**
 * ProfitLog entity. 收益记录
 */

public class ProfitLog implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7724732606603188852L;
	private int id;
	private int status;
	private int uid;
	private String buyId;
	private int type;
	private int fid;
	private float profit;
	private Date created;
	@SuppressWarnings("unused")
	private String createdStr;

	private Profile profile;
	private Profile friend;
	private Buy buy;

	// Constructors

	/** default constructor */
	public ProfitLog() {
	}

	/** full constructor */
	public ProfitLog(int status, int uid, String buyId, int type, int fid,
			float profit, Date created) {
		this.status = status;
		this.uid = uid;
		this.buyId = buyId;
		this.type = type;
		this.fid = fid;
		this.profit = profit;
		this.created = created;
	}

	// Property accessors

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getUid() {
		return this.uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getBuyId() {
		return this.buyId;
	}

	public void setBuyId(String buyId) {
		this.buyId = buyId;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getFid() {
		return this.fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public float getProfit() {
		return this.profit;
	}

	public void setProfit(float profit) {
		this.profit = profit;
	}

	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Profile getFriend() {
		return friend;
	}

	public void setFriend(Profile friend) {
		this.friend = friend;
	}

	public Buy getBuy() {
		return buy;
	}

	public void setBuy(Buy buy) {
		this.buy = buy;
	}

	public String getCreatedStr() {
		return DateUtil.getFormatDateTime(this.created, "yyyy-MM-dd HH:mm");
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

}
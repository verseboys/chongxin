package com.k9sv.domain.pojo;

import java.util.Date;

/**
 * Goldrecord entity. @author MyEclipse Persistence Tools
 */

public class Goldrecord implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int uid;
	private int fid;
	private int type;
	private int gold;
	private Date createdtime;

	// Constructors

	/** default constructor */
	public Goldrecord() {
	}

	/** full constructor */
	public Goldrecord(int uid, int fid, int type, int gold,
			Date createdtime) {
		this.uid = uid;
		this.fid = fid;
		this.type = type;
		this.gold = gold;
		this.createdtime = createdtime;
	}

	// Property accessors

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUid() {
		return this.uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getFid() {
		return this.fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getGold() {
		return this.gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public Date getCreatedtime() {
		return this.createdtime;
	}

	public void setCreatedtime(Date createdtime) {
		this.createdtime = createdtime;
	}

}
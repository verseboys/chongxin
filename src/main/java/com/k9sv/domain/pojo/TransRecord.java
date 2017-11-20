package com.k9sv.domain.pojo;

import java.util.Date;

import com.k9sv.util.DateUtil;

/**
 * 宠物转让
 */
public class TransRecord implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -6711357960381267554L;
	private int id;
	private int dogid;
	private int fromUid;
	private int toUid;
	private Date transtime;

	private Dog dog;
	private Profile old;
	private Profile now;
	@SuppressWarnings("unused")
	private String transtimeStr;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDogid() {
		return dogid;
	}

	public void setDogid(int dogid) {
		this.dogid = dogid;
	}

	public int getFromUid() {
		return fromUid;
	}

	public void setFromUid(int fromUid) {
		this.fromUid = fromUid;
	}

	public int getToUid() {
		return toUid;
	}

	public void setToUid(int toUid) {
		this.toUid = toUid;
	}

	public Date getTranstime() {
		return transtime;
	}

	public void setTranstime(Date transtime) {
		this.transtime = transtime;
	}

	public Dog getDog() {
		return dog;
	}

	public void setDog(Dog dog) {
		this.dog = dog;
	}

	public Profile getOld() {
		return old;
	}

	public void setOld(Profile old) {
		this.old = old;
	}

	public Profile getNow() {
		return now;
	}

	public void setNow(Profile now) {
		this.now = now;
	}

	public void setTranstimeStr(String transtimeStr) {
		this.transtimeStr = transtimeStr;
	}

	public String getTranstimeStr() {
		return DateUtil.getFormatDateTime(this.transtime, "yyyy-MM-dd");
	}

}
package com.k9sv.domain.pojo;

import java.util.Date;

import com.k9sv.util.DateUtil;

/**
 * ToutiaoPublish entity. @author MyEclipse Persistence Tools
 */

public class ToutiaoPublish implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */

	private static final long serialVersionUID = -6209149548110615617L;
	private int id;
	private int sid;
	private Date published;
	@SuppressWarnings("unused")
	private String publishedStr;
	private String qrCode;
	private int viewCount;

	private ToutiaoSucai sucai;

	// Constructors

	/** default constructor */
	public ToutiaoPublish() {
	}

	/** full constructor */
	public ToutiaoPublish(int sid, Date created) {
		this.sid = sid;
		this.setPublished(created);
	}

	// Property accessors
	public Date getPublished() {
		return published;
	}

	public void setPublished(Date published) {
		this.published = published;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSid() {
		return this.sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getPublishedStr() {
		try {
			return DateUtil.getFormatDateTime(published, "yyyy-MM-dd HH");
		} catch (Exception e) {
			return null;
		}
	}

	public ToutiaoSucai getSucai() {
		return sucai;
	}

	public void setSucai(ToutiaoSucai sucai) {
		this.sucai = sucai;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

}
package com.k9sv.domain.pojo;

import java.util.Date;

import com.k9sv.util.DateUtil;

/**
 * NoticeRecord entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("unused")
public class NoticeRecord implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1653262691088787141L;
	private int id;
	private int companyid;
	private String notice;
	private Date created;
	private String createdStr;
	private int deleted;

	// Constructors

	/** default constructor */
	public NoticeRecord() {
	}

	/** full constructor */
	public NoticeRecord(int companyid, String notice, Date created,
			int deleted) {
		this.companyid = companyid;
		this.notice = notice;
		this.created = created;
		this.deleted = deleted;
	}

	// Property accessors

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCompanyid() {
		return this.companyid;
	}

	public void setCompanyid(int companyid) {
		this.companyid = companyid;
	}

	public String getNotice() {
		return this.notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public int getDeleted() {
		return this.deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	
	public String getCreatedStr() {
		try {
			return DateUtil.getFormatDateTime(created, "yyyy-MM-dd HH:mm");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
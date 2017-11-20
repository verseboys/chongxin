package com.k9sv.domain.pojo;

import java.util.Date;

import com.k9sv.util.DateUtil;

/**
 * ToutiaoSucai entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("unused")
public class ToutiaoSucai implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 8500204516932837207L;
	/**
	 * 
	 */
	private int id;
	private String title;
	private String logo;
	private String intro;
	private String content;
	private String link;
	private String auth;
	private Date created;
	private String createdStr;
	private int viewed;
	private int deleted;

	// Constructors

	/** default constructor */
	public ToutiaoSucai() {
	}

	/** full constructor */
	public ToutiaoSucai(String title, String logo, String intro, String content, String link, String auth, Date created,
			int viewed, int deleted) {
		this.title = title;
		this.logo = logo;
		this.intro = intro;
		this.content = content;
		this.link = link;
		this.auth = auth;
		this.setCreated(created);
		this.viewed = viewed;
		this.deleted = deleted;
	}

	// Property accessors

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getIntro() {
		return this.intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getAuth() {
		return this.auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public int getViewed() {
		return this.viewed;
	}

	public void setViewed(int viewed) {
		this.viewed = viewed;
	}

	public int getDeleted() {
		return this.deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getCreatedStr() {
		try {
			return DateUtil.getFormatDateTime(created, "yyyy-MM-dd");
		} catch (Exception e) {
			return null;
		}
	}
}
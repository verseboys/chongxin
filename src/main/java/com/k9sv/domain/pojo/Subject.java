package com.k9sv.domain.pojo;

/**
 * Subject entity. @author MyEclipse Persistence Tools
 */

public class Subject implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String logo;
	private String banner;
	private String summary;
	private String content;
	private String qrcode;
	private String domain;
	private int deleted;

	// Constructors

	/** default constructor */
	public Subject() {
	}

	/** full constructor */
	public Subject(String name, String logo, String banner, String content,
			String qrcode, String domain) {
		this.name = name;
		this.logo = logo;
		this.banner = banner;
		this.content = content;
		this.qrcode = qrcode;
		this.domain = domain;
	}

	// Property accessors

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getBanner() {
		return this.banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getQrcode() {
		return this.qrcode;
	}

	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}

	public String getDomain() {
		return this.domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

}
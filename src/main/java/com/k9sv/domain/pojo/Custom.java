package com.k9sv.domain.pojo;

import java.util.Date;

public class Custom  implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 957121693587774548L;

	private int id;
	
	private String qq;
	
	private String mobile;
	
	private String cname;
	
	private String notes;
	
	private String city;
	
	private String content;
	
	private Date created;
	
	private Date updated;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

}

package com.k9sv.domain.pojo;

import java.util.Date;

/**
 * Scheduled entity. @author MyEclipse Persistence Tools
 */

public class Scheduled implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String telephone;
	private String qq;
	private String dogclass;
	private int subjectid;
	private int state;
	private Date createdtime;

	// Constructors

	/** default constructor */
	public Scheduled() {
	}

	/** full constructor */
	public Scheduled(String name, String telephone, String qq, String dogclass,
			int subjectid, int state, Date createdtime) {
		this.name = name;
		this.telephone = telephone;
		this.qq = qq;
		this.dogclass = dogclass;
		this.subjectid = subjectid;
		this.state = state;
		this.createdtime = createdtime;
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

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getDogclass() {
		return this.dogclass;
	}

	public void setDogclass(String dogclass) {
		this.dogclass = dogclass;
	}

	public int getSubjectid() {
		return this.subjectid;
	}

	public void setSubjectid(int subjectid) {
		this.subjectid = subjectid;
	}

	public int getState() {
		return this.state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Date getCreatedtime() {
		return this.createdtime;
	}

	public void setCreatedtime(Date createdtime) {
		this.createdtime = createdtime;
	}

}
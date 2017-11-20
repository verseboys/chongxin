package com.k9sv.domain.pojo;
// Generated 2013-3-5 1:50:15 by Hibernate Tools 3.6.0

import java.util.Date;

/**
 * Show generated by hbm2java
 */
public class Show implements java.io.Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 3255188517313426998L;
	private int id;
	private String title;
	private String content;
	private Date startTime;
	private Date endTime;
	private int dogCount;
	private int status;
	private int year;
	private Date created;

	public Show() {
	}

	public Show(String title, String content, Date startTime, Date endTime, int dogCount, int status, int year,
			Date created) {
		this.title = title;
		this.content = content;
		this.startTime = startTime;
		this.endTime = endTime;
		this.dogCount = dogCount;
		this.status = status;
		this.year = year;
		this.created = created;
	}

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

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getDogCount() {
		return this.dogCount;
	}

	public void setDogCount(int dogCount) {
		this.dogCount = dogCount;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getYear() {
		return this.year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}
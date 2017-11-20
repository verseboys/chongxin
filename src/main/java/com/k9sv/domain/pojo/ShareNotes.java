package com.k9sv.domain.pojo;

import java.util.Date;

import com.k9sv.util.DateUtil;

public class ShareNotes {

	private int id;

	private int toUid;

	private int fromUid;

	private String notes;

	private Date created;

	@SuppressWarnings("unused")
	private String date;

	public String getDate() {

		return DateUtil.getFormatDateTime(created);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getToUid() {
		return toUid;
	}

	public void setToUid(int toUid) {
		this.toUid = toUid;
	}

	public int getFromUid() {
		return fromUid;
	}

	public void setFromUid(int fromUid) {
		this.fromUid = fromUid;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

}

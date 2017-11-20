package com.k9sv.domain.pojo;

import java.util.Date;

import com.k9sv.util.DateUtil;
@SuppressWarnings("unused")
public class Message {

	private int id;

	private int toUid;

	private int fromUid;

	private String content;

	private int status;

	private Date created;

	private Profile profile;

	private Profile toProfile;

	private String date;

	private int type;

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Profile getToProfile() {
		return toProfile;
	}

	public void setToProfile(Profile toProfile) {
		this.toProfile = toProfile;
	}

}

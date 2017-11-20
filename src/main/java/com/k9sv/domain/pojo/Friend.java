package com.k9sv.domain.pojo;

import java.util.Date;

public class Friend {

	private int id;

	private int uid;

	private int fid;

	private int status;

	private Profile profile;

	private Profile fProfile;

	private Date created;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
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

	public Profile getfProfile() {
		return fProfile;
	}

	public void setfProfile(Profile fProfile) {
		this.fProfile = fProfile;
	}

}

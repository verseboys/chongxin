package com.k9sv.domain.pojo;

import java.util.Date;

public class Visitor implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2459476093467564077L;

	private int id;

	private int type;

	private int vid;

	private int uid;

	private Profile profile;

	private Date created;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getVid() {
		return vid;
	}

	public void setVid(int vid) {
		this.vid = vid;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

}

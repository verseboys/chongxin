package com.k9sv.domain.pojo;

import java.io.Serializable;
import java.util.Set;

import com.google.gson.Gson;

public class Ask implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8445302603290465165L;

	private int id;

	private int uid;

	private int score;

	private String created;

	private String content;

	private int status;

	private int deleted;

	private int total;

	private Profile profile;

	private Set<AskPic> askPics;

	public String toString() {
		Gson gson = new Gson();
		String str = gson.toJson(this);
		return str;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Ask other = (Ask) obj;
		if (this.getId() != other.getId())
			return false;
		return true;
	}

	public int hashCode() {
		return id;
	}

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

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
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

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Set<AskPic> getAskPics() {
		return askPics;
	}

	public void setAskPics(Set<AskPic> askPics) {
		this.askPics = askPics;
	}

}

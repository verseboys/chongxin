package com.k9sv.domain.pojo;

import java.io.Serializable;
import java.util.Set;

public class Answer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1349272158325795456L;

	private int id;

	private String content;

	private int uid;

	private String created;

	private int status;

	private Profile profile;

	private int askId;

	private int toUid;

	private Profile toProfile;

	private int deleted;

	private Set<AnswerPic> answerPics;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public int getAskId() {
		return askId;
	}

	public void setAskId(int askId) {
		this.askId = askId;
	}

	public int getToUid() {
		return toUid;
	}

	public void setToUid(int toUid) {
		this.toUid = toUid;
	}

	public Profile getToProfile() {
		return toProfile;
	}

	public void setToProfile(Profile toProfile) {
		this.toProfile = toProfile;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public Set<AnswerPic> getAnswerPics() {
		return answerPics;
	}

	public void setAnswerPics(Set<AnswerPic> answerPics) {
		this.answerPics = answerPics;
	}
	
	

}

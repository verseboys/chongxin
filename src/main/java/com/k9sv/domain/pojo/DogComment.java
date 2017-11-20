package com.k9sv.domain.pojo;

import java.util.Date;

public class DogComment implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5965765795393077148L;

	private int id;
	
	private int dogId;
	
	private int zan;
	
	private int uid;
	
	private String content;
	
	private Date created;
	
	private Profile profile;
	
	private Dog dog;
	
	private int action;//操作时候，使用 0 正常，1删除

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDogId() {
		return dogId;
	}

	public void setDogId(int dogId) {
		this.dogId = dogId;
	}

	public int getZan() {
		return zan;
	}

	public void setZan(int zan) {
		this.zan = zan;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
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

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Dog getDog() {
		return dog;
	}

	public void setDog(Dog dog) {
		this.dog = dog;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}
	
}

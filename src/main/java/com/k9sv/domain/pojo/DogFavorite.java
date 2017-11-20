package com.k9sv.domain.pojo;

import java.util.Date;

public class DogFavorite  implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2799899063879302406L;

	private int id;
	
	private int uid;
	
	private int dogId;
	
	private Dog dog;
	
	private Profile profile;
	
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

	public int getDogId() {
		return dogId;
	}

	public void setDogId(int dogId) {
		this.dogId = dogId;
	}

	public Dog getDog() {
		return dog;
	}

	public void setDog(Dog dog) {
		this.dog = dog;
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
	
	
}

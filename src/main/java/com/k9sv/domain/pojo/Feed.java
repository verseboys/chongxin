package com.k9sv.domain.pojo;

import java.util.Date;
import java.util.Set;

/**
 * Feed entity. @author MyEclipse Persistence Tools
 */

public class Feed implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -2697416890777565871L;
	private int id;
	private int uid;
	private Profile profile;
	private String content;
	private String photo;
	private int replyCount;
	private int goodCount;
	private Date created;
	private double latitude;
	private double longtitude;
	private int deleted;
	private String address;
	private int type;
	private String card;
	private int relationId;
	private Dog dog;

	private Set<FeedPic> feedPics;

	// Constructors

	/** default constructor */
	public Feed() {
	}

	/** minimal constructor */
	public Feed(int uid, Date created, int deleted, int type) {
		this.uid = uid;
		this.created = created;
		this.deleted = deleted;
		this.type = type;
	}

	/** full constructor */
	public Feed(int uid, String content, String photo, int replyCount,
			int goodCount, Date created, Float latitude, Float longtitude,
			int deleted, String address, int type, String card) {
		this.uid = uid;
		this.content = content;
		this.photo = photo;
		this.replyCount = replyCount;
		this.goodCount = goodCount;
		this.created = created;
		this.latitude = latitude;
		this.longtitude = longtitude;
		this.deleted = deleted;
		this.address = address;
		this.type = type;
		this.card = card;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Feed other = (Feed) obj;
		if (this.getId() != other.getId())
			return false;
		return true;
	}

	// Property accessors

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUid() {
		return this.uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public int getReplyCount() {
		return this.replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	public int getGoodCount() {
		return this.goodCount;
	}

	public void setGoodCount(int goodCount) {
		this.goodCount = goodCount;
	}

	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongtitude() {
		return this.longtitude;
	}

	public void setLongtitude(double longtitude) {
		this.longtitude = longtitude;
	}

	public int getDeleted() {
		return this.deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getCard() {
		return this.card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public Set<FeedPic> getFeedPics() {
		return feedPics;
	}

	public void setFeedPics(Set<FeedPic> feedPics) {
		this.feedPics = feedPics;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public int getRelationId() {
		return relationId;
	}

	public void setRelationId(int relationId) {
		this.relationId = relationId;
	}

	public Dog getDog() {
		return dog;
	}

	public void setDog(Dog dog) {
		this.dog = dog;
	}

}
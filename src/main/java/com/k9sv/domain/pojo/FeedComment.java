package com.k9sv.domain.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 动态评论
 * 
 * @author wuyq
 * 
 */
public class FeedComment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3456232137946717951L;

	private int id;

	private int fid;

	private Feed feed;

	private int uid;

	private int toUid;

	private Profile profile;

	private Profile toProfile;

	private String comment;

	private int zan;

	private Date created;

	private int tuiId;

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final FeedComment other = (FeedComment) obj;
		if (this.getId() != other.getId())
			return false;
		return true;
	}

	public int getTuiId() {
		return tuiId;
	}

	public void setTuiId(int tuiId) {
		this.tuiId = tuiId;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getZan() {
		return zan;
	}

	public void setZan(int zan) {
		this.zan = zan;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
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

	public Feed getFeed() {
		return feed;
	}

	public void setFeed(Feed feed) {
		this.feed = feed;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}

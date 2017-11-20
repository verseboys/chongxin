package com.k9sv.domain.pojo;

import java.util.Date;

import com.k9sv.util.StringUtil;

/**
 * FeedPic entity. @author MyEclipse Persistence Tools
 */

public class FeedPic implements java.io.Serializable, Comparable<FeedPic> {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7370690646278897387L;
	private int id;
	private int fid;
	private int uid;
	private String url;

	private Feed feed;

	private Date created;

	// Constructors

	/** default constructor */
	public FeedPic() {
	}

	/** minimal constructor */
	public FeedPic(int fid, int uid) {
		this.fid = fid;
		this.uid = uid;
	}

	/** full constructor */
	public FeedPic(int fid, int uid, String url) {
		this.fid = fid;
		this.uid = uid;
		this.url = url;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final FeedPic other = (FeedPic) obj;
		if (StringUtil.isEmpty(this.getUrl())
				&& StringUtil.isEmpty(other.getUrl())) {
			return true;
		} else if (StringUtil.isNotEmpty(this.getUrl())
				&& StringUtil.isNotEmpty(other.getUrl())) {
			if (!this.getUrl().equals(other.getUrl()))
				return false;
			return true;
		}
		return false;
	}

	public int compareTo(FeedPic pic) {
		if (this.url.hashCode() < pic.url.hashCode()) {
			return 1;
		} else {
			return -1;
		}
	}

	// Property accessors

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFid() {
		return this.fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public int getUid() {
		return this.uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Feed getFeed() {
		return feed;
	}

	public void setFeed(Feed feed) {
		this.feed = feed;
	}

}
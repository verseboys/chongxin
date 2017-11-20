package com.k9sv.domain.pojo;

import java.util.Date;

import com.k9sv.util.StringUtil;

/**
 * FeedPic entity. @author MyEclipse Persistence Tools
 */

public class RecordPic implements java.io.Serializable, Comparable<RecordPic> {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7370690646278897387L;
	private int id;
	private int rid;
	private int uid;
	private String url;

	private Record record;

	private Date created;

	// Constructors

	/** default constructor */
	public RecordPic() {
	}

	/** full constructor */
	public RecordPic(int rid, int uid, String url) {
		this.rid = rid;
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
		final RecordPic other = (RecordPic) obj;
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
	
	public int hashCode() {
		return url.hashCode();
	}

	public int compareTo(RecordPic pic) {
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

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public Record getRecord() {
		return record;
	}

	public void setRecord(Record record) {
		this.record = record;
	}

}
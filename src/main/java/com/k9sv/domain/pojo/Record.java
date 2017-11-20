package com.k9sv.domain.pojo;

import java.util.Date;
import java.util.Set;

import com.k9sv.util.DateUtil;

/**
 * Record entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("unused")
public class Record implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -353593834857155866L;
	private int id;
	private int feedid;
	private int classifyid;
	private Double weight;
	private Double height;
	private String remark;
	private Date recordTime;
	private Date created;
	private int dogid;
	private int uid;
	private int deleted;

	private String recordTimeStr;
	private String createdStr;
	private Feed feed;
	private Classify classify;

	private Set<RecordPic> recordPics;

	// Constructors

	/** default constructor */
	public Record() {
	}

	/** full constructor */
	public Record(int feedid, int recordType, Double weight, Double height, String remark, Date recordTime,
			Date created, int dogid, int uid) {
		this.feedid = feedid;
		this.weight = weight;
		this.height = height;
		this.remark = remark;
		this.recordTime = recordTime;
		this.created = created;
		this.dogid = dogid;
		this.uid = uid;
	}

	// Property accessors

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFeedid() {
		return this.feedid;
	}

	public void setFeedid(int feedid) {
		this.feedid = feedid;
	}

	public Double getWeight() {
		return this.weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getHeight() {
		return this.height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getRecordTime() {
		return this.recordTime;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public int getDogid() {
		return this.dogid;
	}

	public void setDogid(int dogid) {
		this.dogid = dogid;
	}

	public int getUid() {
		return this.uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public Feed getFeed() {
		return feed;
	}

	public void setFeed(Feed feed) {
		this.feed = feed;
	}

	public int getClassifyid() {
		return classifyid;
	}

	public void setClassifyid(int classifyid) {
		this.classifyid = classifyid;
	}

	public Classify getClassify() {
		return classify;
	}

	public void setClassify(Classify classify) {
		this.classify = classify;
	}

	public String getRecordTimeStr() {
		return DateUtil.getFormatDateTime(this.recordTime, "yyyy-MM-dd");
	}

	public String getCreatedStr() {
		return DateUtil.getFormatDateTime(this.created, "yyyy-MM-dd");
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public Set<RecordPic> getRecordPics() {
		return recordPics;
	}

	public void setRecordPics(Set<RecordPic> recordPics) {
		this.recordPics = recordPics;
	}

}
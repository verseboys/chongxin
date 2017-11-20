package com.k9sv.domain.pojo;

import java.util.Date;

public class Feedback  implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7851019571575129714L;

	private int id;
	
	private int uid;
	
	private int type; // 0 用户反馈，1用户投诉动态，2用户举报人
	
	private int relationId;  // 关联id
	
	private String content;  // 反馈内容
	
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getRelationId() {
		return relationId;
	}

	public void setRelationId(int relationId) {
		this.relationId = relationId;
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
	
	
}

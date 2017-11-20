package com.k9sv.domain.pojo;

import java.util.Date;

/**
 * 用户邀请记录
 * @author wuyuqi
 *
 */
public class UserInvite implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -36530855261252498L;

	private int id;
	
	private int uid;      // 
	
	private int invited;  // 被邀请的用户
	
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

	public int getInvited() {
		return invited;
	}

	public void setInvited(int invited) {
		this.invited = invited;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	
	
}

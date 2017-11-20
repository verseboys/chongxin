package com.k9sv.domain.dto;

/**
 * 动态、服务、头条状态（是否有更新）
 * 
 * @author mcp
 * 
 */
public class StateDto {

	private Integer feedid;
	private Integer toutiaoid;

	public Integer getFeedid() {
		return feedid;
	}

	public void setFeedid(Integer feedid) {
		this.feedid = feedid;
	}

	public Integer getToutiaoid() {
		return toutiaoid;
	}

	public void setToutiaoid(Integer toutiaoid) {
		this.toutiaoid = toutiaoid;
	}
}

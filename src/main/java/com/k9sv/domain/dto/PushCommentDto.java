package com.k9sv.domain.dto;

import com.k9sv.domain.pojo.FeedComment;
import com.k9sv.util.DateUtil;

public class PushCommentDto {

	private Integer zan;

	private String comment;

	private UserDto user;

	private String created;

	private FeedDto feed;

	public PushCommentDto() {

	}

	public PushCommentDto(FeedComment comment) {
		this.zan = comment.getZan();
		this.comment = comment.getComment();
		this.feed = new FeedDto(comment.getFeed(), null);
		if (comment.getProfile() != null) {
			this.user = new UserDto(comment.getProfile(), 0);
		}
		this.created = DateUtil.getFormatDateTime(comment.getCreated());
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getZan() {
		return zan;
	}

	public void setZan(Integer zan) {
		this.zan = zan;
	}

	public FeedDto getFeed() {
		return feed;
	}

	public void setFeed(FeedDto feed) {
		this.feed = feed;
	}

}

package com.k9sv.domain.dto;

import com.k9sv.domain.pojo.FeedComment;
import com.k9sv.util.DateUtil;

public class FeedCommentDto {

	private int commentid;

	private String content;

	private UserDto user;

	private UserDto touser;

	private String created;

	private Integer gold;

	public FeedCommentDto() {

	}

	public FeedCommentDto(FeedComment comment) {
		this.commentid = comment.getId();
		this.content = comment.getComment();
		if (comment.getProfile() != null) {
			this.user = new UserDto(comment.getProfile(), 0);
		}
		if (comment.getToProfile() != null) {
			this.touser = new UserDto(comment.getToProfile(), 0);
		}
		this.created = DateUtil.getFormatDateTime(comment.getCreated());
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public UserDto getTouser() {
		return touser;
	}

	public void setTouser(UserDto touser) {
		this.touser = touser;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public int getCommentid() {
		return commentid;
	}

	public void setCommentid(int commentid) {
		this.commentid = commentid;
	}

	public Integer getGold() {
		return gold;
	}

	public void setGold(Integer gold) {
		this.gold = gold;
	}

}

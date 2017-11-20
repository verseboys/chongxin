package com.k9sv.domain.dto;

import com.k9sv.domain.pojo.Message;
@SuppressWarnings("unused")
public class MessageDto {

	private Long mid;

	private Integer type;

	private String content;

	private String created;

	private UserDto user;

	private Integer touid;

	public MessageDto() {

	}

	public MessageDto(Message message) {
		this.mid = new Long(message.getId());
		this.type = message.getType();
		this.content = message.getContent();
		this.created = message.getDate();
		this.touid = message.getToUid();
		if (message.getProfile() != null) {
			this.user = new UserDto(message.getProfile(), 0);
		}
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public Long getMid() {
		return mid;
	}

	public void setMid(Long mid) {
		this.mid = mid;
	}

}

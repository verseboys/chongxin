package com.k9sv.domain.dto;

import com.k9sv.domain.pojo.TransRecord;
import com.k9sv.util.DateUtil;

public class PushTransDto {

	private UserDto user;
	private String transtime;

	public PushTransDto(TransRecord record) {
		if (record.getOld() != null) {
			this.user = new UserDto(record.getOld(), 0);
		}
		this.transtime = DateUtil.getFormatDateTime(record.getTranstime());
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public String getTranstime() {
		return transtime;
	}

	public void setTranstime(String transtime) {
		this.transtime = transtime;
	}

}
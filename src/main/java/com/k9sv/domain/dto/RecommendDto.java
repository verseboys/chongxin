package com.k9sv.domain.dto;

import com.k9sv.domain.pojo.UserInfo;

public class RecommendDto {

	private Integer count;
	private UserInfoDto info;

	public RecommendDto(Integer count, UserInfo userInfo) {
		this.count = count;
		if (userInfo != null) {
			this.setInfo(new UserInfoDto(userInfo));
		}
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public UserInfoDto getInfo() {
		return info;
	}

	public void setInfo(UserInfoDto info) {
		this.info = info;
	}

}
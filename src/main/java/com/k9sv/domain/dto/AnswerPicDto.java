package com.k9sv.domain.dto;

import com.k9sv.Config2;
import com.k9sv.domain.pojo.AnswerPic;
import com.k9sv.util.StringUtil;

public class AnswerPicDto {

	private String photo;

	private Integer pid;

	public AnswerPicDto(AnswerPic pic) {
		if (StringUtil.isNotEmpty(pic.getPhoto())) {
			this.photo = Config2.ImgServer + pic.getPhoto();
		}
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}
}

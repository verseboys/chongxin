package com.k9sv.domain.dto;

import com.k9sv.Config2;
import com.k9sv.domain.pojo.AskPic;

public class AskPicDto {

	private String photo;

	private Integer pid;

	public AskPicDto(AskPic pic) {
		this.setPhoto(Config2.ImgServer + pic.getPhoto());
		this.setPid(pic.getPid());
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

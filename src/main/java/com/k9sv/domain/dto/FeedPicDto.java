package com.k9sv.domain.dto;

import com.k9sv.Config2;
import com.k9sv.domain.pojo.FeedPic;

public class FeedPicDto {

	private int fid;
	private String photo;

	public FeedPicDto(FeedPic pic) {
		this.setPhoto(Config2.ImgServer + pic.getUrl());
		this.fid = pic.getFid();
	}

	public FeedPicDto(String server, FeedPic pic) {
		this.setPhoto(server + pic.getUrl());
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}
}

package com.k9sv.domain.dto;

import com.k9sv.Config2;
import com.k9sv.domain.pojo.RecordPic;

public class RecordPicDto {

	private int rid;
	private int picid;
	private String photo;

	public RecordPicDto(RecordPic pic) {
		this.photo = Config2.ImgServer + pic.getUrl();
		this.setRid(pic.getRid());
		this.setPicid(pic.getId());
	}

	public RecordPicDto(String server, RecordPic pic) {
		this.setPhoto(server + pic.getUrl());
		this.setRid(pic.getRid());
		this.setPicid(pic.getId());
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public int getPicid() {
		return picid;
	}

	public void setPicid(int picid) {
		this.picid = picid;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}
}

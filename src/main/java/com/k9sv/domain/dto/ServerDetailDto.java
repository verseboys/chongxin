package com.k9sv.domain.dto;

import com.k9sv.domain.pojo.Server;

public class ServerDetailDto {

	private int sid;

	private String title;

	private String price;

	private int cid;

	public ServerDetailDto() {

	}

	public ServerDetailDto(Server server) {
		this.sid = server.getId();
		this.title = server.getClassify().getName();
		if (server.getUnits() == null) {
			this.price = server.getPrice();
		} else {
			this.price = server.getPrice() + server.getUnits();
		}
		this.cid = server.getClassifyid();
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

}

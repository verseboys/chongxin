package com.k9sv.domain.dto;

import java.util.Date;

public class CheckCode {

	private String type;

	private String code;

	private String mobile;

	private Date created;

	private int status;

	private String token;

	public CheckCode(String code, String token) {
		this.code = code;
		this.setToken(token);
	}

	public CheckCode(String type, String code, String mobile, String sid) {
		this.type = type;
		this.code = code;
		this.created = new Date();
		this.status = 0;
		this.mobile = mobile;
		this.token = sid;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}

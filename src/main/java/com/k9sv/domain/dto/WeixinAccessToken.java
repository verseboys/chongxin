package com.k9sv.domain.dto;

public class WeixinAccessToken {

	private String token;
	
	private long created;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public long getCreated() {
		return created;
	}

	public void setCreated(long created) {
		this.created = created;
	}
	
}

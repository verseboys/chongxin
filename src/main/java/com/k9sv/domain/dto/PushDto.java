package com.k9sv.domain.dto;

import com.google.gson.Gson;

public class PushDto {

	private Object data;

	private String type;
	
	private String time;

	public String toString() {
		Gson gson = new Gson();
		String str = gson.toJson(this);
		return str;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}

package com.k9sv.domain.dto;

import com.google.gson.Gson;

public class ResDto {

	private Integer state;
	
	private String errorcode;
	
	private String errormsg;
	
	private String token;
	
	private Integer gold;
	
	private Object data;
	
	public String toString(){
		Gson gson = new Gson();
        String str = gson.toJson(this);
        return str;
	}
	
	

	public String getToken() {
		return token;
	}



	public void setToken(String token) {
		this.token = token;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	

	public String getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}

	public String getErrormsg() {
		return errormsg;
	}

	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}

	public Integer getState() {
		return state;
	}



	public void setState(Integer state) {
		this.state = state;
	}



	public Integer getGold() {
		return gold;
	}



	public void setGold(Integer gold) {
		this.gold = gold;
	}

	
	
}

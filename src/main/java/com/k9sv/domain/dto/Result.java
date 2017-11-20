package com.k9sv.domain.dto;

import com.k9sv.Errorcode;

public class Result {

	private int state;
	
	private Errorcode errorcode;

	private Object data;
	
	private String errormsg;
	
	
	
	public String getErrormsg() {
		return errormsg;
	}

	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Errorcode getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(Errorcode errorcode) {
		this.errorcode = errorcode;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	
}

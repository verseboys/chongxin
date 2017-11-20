package com.k9sv.domain.pojo;

import java.io.Serializable;

public class AskPic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8383737489014451430L;

	private int pid;

	private int aid;

	private String photo;

	private Ask ask;

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final AskPic other = (AskPic) obj;
		if (this.getPid() != other.getPid())
			return false;
		return true;
	}

	public int hashCode() {
		return pid;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Ask getAsk() {
		return ask;
	}

	public void setAsk(Ask ask) {
		this.ask = ask;
	}

}

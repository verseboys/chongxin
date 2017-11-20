package com.k9sv.domain.pojo;

import java.io.Serializable;

public class AnswerPic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6736693471733387348L;

	private int id;

	private int aid;

	private String photo;
	
	private Answer answer;

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final AnswerPic other = (AnswerPic) obj;
		if (this.getId() != other.getId())
			return false;
		return true;
	}

	/*public int hashCode() {
		return id;
	}*/

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

}

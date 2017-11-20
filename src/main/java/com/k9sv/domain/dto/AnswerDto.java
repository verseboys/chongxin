package com.k9sv.domain.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.k9sv.domain.pojo.Answer;
import com.k9sv.domain.pojo.AnswerPic;

public class AnswerDto {

	private int answerid;

	private String created;

	private String content;

	private UserDto touser;

	private UserDto user;

	private List<AnswerPicDto> photos;

	public AnswerDto(Answer answer) {
		this.answerid = answer.getId();
		this.created = answer.getCreated();
		this.content = answer.getContent();
		if (answer.getToProfile() != null) {
			this.touser = new UserDto(answer.getToProfile(), 0);
		}
		if (answer.getProfile() != null) {
			this.user = new UserDto(answer.getProfile(), 0);
		}
		this.photos = this.getAnswerPicDtos(answer.getAnswerPics());
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public UserDto getTouser() {
		return touser;
	}

	public void setTouser(UserDto touser) {
		this.touser = touser;
	}

	public int getAnswerid() {
		return answerid;
	}

	public void setAnswerid(int answerid) {
		this.answerid = answerid;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<AnswerPicDto> getPhotos() {
		return photos;
	}

	public void setPhotos(List<AnswerPicDto> photos) {
		this.photos = photos;
	}

	public List<AnswerPicDto> getAnswerPicDtos(Set<AnswerPic> set) {
		List<AnswerPicDto> _photos = new ArrayList<AnswerPicDto>();
		if (set != null && set.size() > 0) {
			for (AnswerPic pic : set) {
				_photos.add(new AnswerPicDto(pic));
			}
		}
		return _photos;
	}

}

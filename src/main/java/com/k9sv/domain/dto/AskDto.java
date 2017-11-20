package com.k9sv.domain.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;
import com.k9sv.domain.pojo.Ask;
import com.k9sv.domain.pojo.AskPic;

public class AskDto {

	private int aid;

	private int score;

	private String created;

	private String content;

	private int status;

	private int total;

	private ProfileDto profile;

	private List<AskPicDto> photos;

	public AskDto() {

	}

	public AskDto(Ask ask) {
		this.aid = ask.getId();
		this.content = ask.getContent();
		this.score = ask.getScore();
		this.total = ask.getTotal();
		if (ask.getProfile() != null) {
			this.profile = new ProfileDto(ask.getProfile());
		}
		this.photos = this.getAskPicDtos(ask.getAskPics());
	}

	public String toString() {
		Gson gson = new Gson();
		String str = gson.toJson(this);
		return str;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public ProfileDto getProfile() {
		return profile;
	}

	public void setProfile(ProfileDto profile) {
		this.profile = profile;
	}

	public List<AskPicDto> getAskPicDtos(Set<AskPic> set) {
		List<AskPicDto> _photos = new ArrayList<AskPicDto>();
		if (set != null && set.size() > 0) {
			for (AskPic pic : set) {
				_photos.add(new AskPicDto(pic));
			}
		}
		return _photos;
	}

	public List<AskPicDto> getPhotos() {
		return photos;
	}

	public void setPhotos(List<AskPicDto> photos) {
		this.photos = photos;
	}

}

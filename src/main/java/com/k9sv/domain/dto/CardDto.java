package com.k9sv.domain.dto;

import com.google.gson.Gson;
import com.k9sv.Config2;
import com.k9sv.domain.pojo.Dog;
import com.k9sv.util.StringUtil;

public class CardDto {
	
	private int uid;

	private int type; // 0 宠物，1 网页链接

	private String title;

	private int relation;// 宠物id

	private String photo;

	private String link;

	private String content;// 分享说明

	public CardDto() {

	}

	public CardDto(Dog dog, String content) {
		this.type = 0;
		this.title = dog.getName();
		this.relation = dog.getId();
		this.uid = dog.getOwnerId();
		if (StringUtil.isNotEmpty(dog.getAvatar())) {
			if (dog.getAvatar().startsWith("http")) {
				this.photo = dog.getAvatar();
			} else {
				this.photo = Config2.AvatarImgServer + dog.getAvatar();
			}

		}
		this.content = content;
	}

	public String toString() {
		Gson gson = new Gson();
		String str = gson.toJson(this);
		return str;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getRelation() {
		return relation;
	}

	public void setRelation(int relation) {
		this.relation = relation;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

}

package com.k9sv.domain.dto;

import com.k9sv.Config2;
import com.k9sv.domain.pojo.Profile;
import com.k9sv.util.StringUtil;

public class UserDto {

	private Integer uid;

	private Integer level;

	private String nickname;

	private Integer friendship;

	private String avatar;
	
	private Integer role;

	public UserDto() {

	}

	public UserDto(Profile profile, int friendship) {
		if (profile != null) {
			this.uid = profile.getId();
			this.nickname = profile.getNickName();
			this.friendship = friendship;
			this.level = profile.getPrestige();
			this.role = profile.getRoleId();
			if (StringUtil.isNotEmpty(profile.getAvatar())) {
				if (profile.getAvatar().startsWith("http")) {
					this.avatar = profile.getAvatar();
				} else {
					String avatar = profile.getAvatar() + "!avatar134";
					this.avatar = Config2.AvatarImgServer + avatar;
				}

			}
		}
		
	}

	public UserDto(Profile profile) {
		if (profile != null) {
			this.uid = profile.getId();
			this.nickname = profile.getNickName();
			this.level = profile.getPrestige();
			if (StringUtil.isNotEmpty(profile.getAvatar())) {
				if (profile.getAvatar().startsWith("http")) {
					this.avatar = profile.getAvatar();
				} else {
					String avatar = profile.getAvatar() + "!avatar134";
					this.avatar = Config2.AvatarImgServer + avatar;
				}
	
			}
			this.role = profile.getRoleId();
		}
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getFriendship() {
		return friendship;
	}

	public void setFriendship(Integer friendship) {
		this.friendship = friendship;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}
	

}

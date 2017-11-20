package com.k9sv.domain.dto;

import com.k9sv.Config;
import com.k9sv.Config2;
import com.k9sv.domain.pojo.Dog;
import com.k9sv.util.StringUtil;

public class DogDto {
	private Integer petid;
	private String avatar;
	private String name;
	private String birthday;
	private Integer classify;
	private Integer sex;
	private String chip;
	private Integer zan;
	private String shareurl;
	private Integer checked;
	private UserDto user;

	public DogDto(Dog dog) {
		if (dog != null) {
			this.setPetid(dog.getId());
			if (StringUtil.isNotEmpty(dog.getAvatar())) {
				if (dog.getAvatar().startsWith("http")) {
					this.avatar = dog.getAvatar();
				} else {
					this.avatar = Config2.AvatarImgServer + dog.getAvatar();
				}

			}
			this.name = dog.getName();
			this.birthday = dog.getBirthdayStr();
			this.classify = dog.getClassify();
			this.sex = dog.getSex();
			this.chip = dog.getBlood();
			this.zan = dog.getZan();
			this.shareurl = Config.ShareUrl;
			this.checked = dog.getIsok();
			this.user = new UserDto(dog.getOwner());
		}
	}

	// 新添或修改时调用
	public DogDto(Dog dog, Integer first) {
		this.setPetid(dog.getId());
		if (StringUtil.isNotEmpty(dog.getAvatar())) {
			if (first == 1) {
				this.avatar = Config2.AvatarImgServer + dog.getAvatar();
			} else {
				this.avatar = Config2.AvatarImgServer + dog.getAvatar();
			}
		}
		this.name = dog.getName();
		this.birthday = dog.getBirthdayStr();
		this.classify = dog.getClassify();
		this.sex = dog.getSex();
		this.setChip(dog.getBlood());
		this.shareurl = Config.ShareUrl;
		this.checked = dog.getIsok();
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Integer getClassify() {
		return classify;
	}

	public void setClassify(Integer classify) {
		this.classify = classify;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getPetid() {
		return petid;
	}

	public void setPetid(Integer petid) {
		this.petid = petid;
	}

	public String getChip() {
		return chip;
	}

	public void setChip(String chip) {
		this.chip = chip;
	}

	public Integer getZan() {
		return zan;
	}

	public void setZan(Integer zan) {
		this.zan = zan;
	}

	public String getShareurl() {
		return shareurl;
	}

	public void setShareurl(String shareurl) {
		this.shareurl = shareurl;
	}

	public Integer getChecked() {
		return checked;
	}

	public void setChecked(Integer checked) {
		this.checked = checked;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

}

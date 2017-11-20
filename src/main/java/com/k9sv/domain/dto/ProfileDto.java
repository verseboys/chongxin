package com.k9sv.domain.dto;

import com.k9sv.Config;
import com.k9sv.Config2;
import com.k9sv.domain.pojo.City;
import com.k9sv.domain.pojo.Company;
import com.k9sv.domain.pojo.Profile;
import com.k9sv.util.DateUtil;
import com.k9sv.util.StringUtil;
@SuppressWarnings("unused")
public class ProfileDto {

	private Integer uid;

	private String nickname;

	private int checked; // 1 手机号码验证过

	private String address;

	private Integer sex;

	private String sexname;

	private String mobile;

	private String avatar;

	private Integer score;

	private Integer prestige;

	private String intro;

	private Integer cityid;

	private String province;

	private String city;

	private String birthday;

	private Integer friendship;

	private String topicPic;

	private ZanCountDto count;

	private LevelDto level;

	private CompanyInfoDto company;
	
	private int role;

	public ProfileDto() {

	}

	public ProfileDto(Profile profile) {
		this.uid = profile.getId();
		String nickName = profile.getNickName();
		if (nickName == null) {
			nickName = profile.getId() + "";
		}
		this.nickname = nickName;
		this.sex = profile.getSex();
		this.mobile = profile.getMobile();
		if (StringUtil.isNotEmpty(profile.getAvatar())) {
			if (profile.getAvatar().startsWith("http")) {
				this.avatar = profile.getAvatar();
			} else {
				String avatar = profile.getAvatar() + "!avatar134";
				this.avatar = Config2.AvatarImgServer + avatar;
			}

		}
		this.score = profile.getScore();
		this.prestige = profile.getPrestige();
		this.intro = profile.getIntro();
		if (profile.getCityId() != 1) {
			this.cityid = profile.getCityId();
			City _city = profile.getCity();
			if (_city != null) {
				String cityName = _city.getName();
				this.city = cityName;
				if (!Config.Municipality.contains(cityName)
						&& _city.getPid() != 0) {
					this.province = _city.getCity().getName();
				}
			}
		}
		this.address = profile.getAddress();

		if (profile.getBirthday() != null) {
			this.birthday = DateUtil.getFormatDateTime(profile.getBirthday(),
					"yyyy-MM-dd");
		}
		this.sexname = this.sexname(profile.getSex());
		String topic = profile.getTopicPic();
		if (StringUtil.isNotEmpty(topic)) {
			this.topicPic = Config2.ImgServer + profile.getTopicPic();
		}
		this.checked = profile.getAccount().getChecked();
		this.role = profile.getRoleId();
	}

	// 新添和修改时调用
	public ProfileDto(Profile profile, Integer first) {
		this.uid = profile.getId();
		String nickName = profile.getNickName();
		if (nickName == null) {
			nickName = profile.getId() + "";
		}
		this.nickname = nickName;
		this.sex = profile.getSex();
		this.mobile = profile.getMobile();
		if (first == 1) {
			if (StringUtil.isNotEmpty(profile.getAvatar())) {
				if (profile.getAvatar().startsWith("http")) {
					this.avatar = profile.getAvatar();
				} else {
					String avatar = profile.getAvatar() + "!avatar134";
					this.avatar = Config2.AvatarImgServer + avatar;
				}

			}
		} else {
			if (StringUtil.isNotEmpty(profile.getAvatar())) {
				if (profile.getAvatar().startsWith("http")) {
					this.avatar = profile.getAvatar();
				} else {
					String avatar = profile.getAvatar() + "!avatar134";
					this.avatar = Config2.AvatarImgServer + avatar;
				}

			}
		}

		this.score = profile.getScore();
		this.prestige = profile.getPrestige();
		this.intro = profile.getIntro();
		if (profile.getCityId() != 1) {
			this.cityid = profile.getCityId();
			City _city = profile.getCity();
			if (_city != null) {
				String cityName = _city.getName();
				this.city = cityName;
				if (!Config.Municipality.contains(cityName)
						&& _city.getPid() != 0) {
					this.province = _city.getCity().getName();
				}
			}
		}
		this.address = profile.getAddress();

		if (profile.getBirthday() != null) {
			this.birthday = DateUtil.getFormatDateTime(profile.getBirthday(),
					"yyyy-MM-dd");
		}
		this.sexname = this.sexname(profile.getSex());
		String topic = profile.getTopicPic();
		if (StringUtil.isNotEmpty(topic)) {
			this.topicPic = Config2.ImgServer + profile.getTopicPic();
		}
		this.checked = profile.getAccount().getChecked();
		this.role = profile.getRoleId();
	}

	public ProfileDto(Profile profile, ZanCountDto countDto) {
		this.uid = profile.getId();
		String nickName = profile.getNickName();
		if (nickName == null) {
			nickName = profile.getId() + "";
		}
		this.nickname = nickName;
		this.sex = profile.getSex();
		this.mobile = profile.getMobile();
		if (StringUtil.isNotEmpty(profile.getAvatar())) {
			if (profile.getAvatar().startsWith("http")) {
				this.avatar = profile.getAvatar();
			} else {
				String avatar = profile.getAvatar() + "!avatar134";
				this.avatar = Config2.AvatarImgServer + avatar;
			}

		}
		this.score = profile.getScore();
		this.prestige = profile.getPrestige();
		this.intro = profile.getIntro();
		if (profile.getCityId() != 1) {
			this.cityid = profile.getCityId();
			City _city = profile.getCity();
			if (_city != null) {
				String cityName = _city.getName();
				this.city = cityName;
				if (!Config.Municipality.contains(cityName)
						&& _city.getPid() != 0) {
					this.province = _city.getCity().getName();
				}
			}
		}

		if (profile.getBirthday() != null) {
			this.birthday = DateUtil.getFormatDateTime(profile.getBirthday(),
					"yyyy-MM-dd");
		}
		this.count = countDto;
		this.address = profile.getAddress();
		this.sexname = this.sexname(profile.getSex());
		this.level = new LevelDto(profile);
		String topic = profile.getTopicPic();
		if (StringUtil.isNotEmpty(topic)) {
			this.topicPic = Config2.ImgServer + profile.getTopicPic();
		}
		this.checked = profile.getAccount().getChecked();
		this.role = profile.getRoleId();
	}

	public ProfileDto(Profile profile, ZanCountDto countDto, Integer friendship) {
		this.uid = profile.getId();
		String nickName = profile.getNickName();
		if (nickName == null) {
			nickName = profile.getId() + "";
		}
		this.nickname = nickName;
		this.sex = profile.getSex();
		this.mobile = profile.getMobile();
		if (StringUtil.isNotEmpty(profile.getAvatar())) {
			if (profile.getAvatar().startsWith("http")) {
				this.avatar = profile.getAvatar();
			} else {
				String avatar = profile.getAvatar() + "!avatar134";
				this.avatar = Config2.AvatarImgServer + avatar;
			}

		}
		this.score = profile.getScore();
		this.prestige = profile.getPrestige();
		this.intro = profile.getIntro();
		if (profile.getCityId() != 1) {
			this.cityid = profile.getCityId();
			City _city = profile.getCity();
			if (_city != null) {
				String cityName = _city.getName();
				this.city = cityName;
				if (!Config.Municipality.contains(cityName)
						&& _city.getPid() != 0) {
					this.province = _city.getCity().getName();
				}
			}
		}

		if (profile.getBirthday() != null) {
			this.birthday = DateUtil.getFormatDateTime(profile.getBirthday(),
					"yyyy-MM-dd");
		}
		this.count = countDto;
		this.address = profile.getAddress();
		this.sexname = this.sexname(profile.getSex());
		this.friendship = friendship;
		this.level = new LevelDto(profile);
		String topic = profile.getTopicPic();
		if (StringUtil.isNotEmpty(topic)) {
			this.topicPic = Config2.ImgServer + profile.getTopicPic();
		}
		this.checked = profile.getAccount().getChecked();
		this.role = profile.getRoleId();
	}

	public ProfileDto(int uid) {
		this.uid = uid;
	}

	public ProfileDto(String topicPic) {
		this.topicPic = Config2.ImgServer + topicPic;
	}

	public ProfileDto(Profile profile, ZanCountDto countDto, int friendship,
			int voucher, Company company) {
		this.uid = profile.getId();
		String nickName = profile.getNickName();
		if (nickName == null) {
			nickName = profile.getId() + "";
		}
		this.nickname = nickName;
		this.sex = profile.getSex();
		this.mobile = profile.getMobile();
		if (StringUtil.isNotEmpty(profile.getAvatar())) {
			if (profile.getAvatar().startsWith("http")) {
				this.avatar = profile.getAvatar();
			} else {
				String avatar = profile.getAvatar() + "!avatar134";
				this.avatar = Config2.AvatarImgServer + avatar;
			}

		}
		this.score = profile.getScore();
		this.prestige = profile.getPrestige();
		this.intro = profile.getIntro();
		if (profile.getCityId() != 1) {
			this.cityid = profile.getCityId();
			City _city = profile.getCity();
			if (_city != null) {
				String cityName = _city.getName();
				this.city = cityName;
				if (!Config.Municipality.contains(cityName)
						&& _city.getPid() != 0) {
					this.province = _city.getCity().getName();
				}
			}
		}

		if (profile.getBirthday() != null) {
			this.birthday = DateUtil.getFormatDateTime(profile.getBirthday(),
					"yyyy-MM-dd");
		}
		this.count = countDto;
		this.address = profile.getAddress();
		this.sexname = this.sexname(profile.getSex());
		this.friendship = friendship;
		this.level = new LevelDto(profile, voucher);
		String topic = profile.getTopicPic();
		if (StringUtil.isNotEmpty(topic)) {
			this.topicPic = Config2.ImgServer + profile.getTopicPic();
		}
		this.checked = profile.getAccount().getChecked();
		if (company != null) {
			this.company = new CompanyInfoDto(company, null);
		}
		this.role = profile.getRoleId();
	}

	public String sexname(int sex) {
		if (sex == 0) {
			sexname = "未选择";
		} else if (sex == 1) {
			sexname = "男";
		} else if (sex == 2) {
			sexname = "女";
		}
		return sexname;
	}

	public Integer getFriendship() {
		return friendship;
	}

	public void setFriendship(Integer friendship) {
		this.friendship = friendship;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getPrestige() {
		return prestige;
	}

	public void setPrestige(Integer prestige) {
		this.prestige = prestige;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public Integer getCityid() {
		return cityid;
	}

	public void setCityid(Integer cityid) {
		this.cityid = cityid;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public ZanCountDto getCount() {
		return count;
	}

	public void setCount(ZanCountDto count) {
		this.count = count;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSexname() {
		return sexname;
	}

	public void setSexname(String sexname) {
		this.sexname = sexname;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public LevelDto getLevel() {
		return level;
	}

	public void setLevel(LevelDto level) {
		this.level = level;
	}

	public String getTopicPic() {
		return topicPic;
	}

	public void setTopicPic(String topicPic) {
		this.topicPic = topicPic;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

}

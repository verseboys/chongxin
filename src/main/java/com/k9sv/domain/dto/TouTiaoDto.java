package com.k9sv.domain.dto;

import com.k9sv.Config;
import com.k9sv.domain.pojo.ToutiaoPublish;

public class TouTiaoDto {
	private Integer tid;
	private String title;
	private String intro;
	private String logo;
	private String published;
	private String url;

	public TouTiaoDto() {
	}

	public TouTiaoDto(ToutiaoPublish toutiaoPublish) {
		this.tid = toutiaoPublish.getId();
		this.title = toutiaoPublish.getSucai().getTitle();
		this.intro = toutiaoPublish.getSucai().getIntro();
		this.logo = toutiaoPublish.getSucai().getLogo();
		this.published = toutiaoPublish.getPublishedStr();
		this.url = Config.Toutiao + toutiaoPublish.getId();
	}

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPublished() {
		return published;
	}

	public void setPublished(String published) {
		this.published = published;
	}

}

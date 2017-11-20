package com.k9sv.domain.dto;

import java.util.List;

import com.k9sv.domain.pojo.Company;
import com.k9sv.util.BaiDuMapUtil;

@SuppressWarnings("unused")
public class CompanyInfoDto {

	private int cid;
	private String name;
	private String banner;
	private String telephone;
	private String address;
	private String latitude;
	private String longtitude;
	private String introduction;
	private Integer avgprice;
	private String special;
	private String opentime;
	private String notice;
	private double distance;
	private List<ServerDto> servers;

	public CompanyInfoDto(Company company, Location location) {
		this.cid = company.getId();
		this.name = company.getName();
		this.telephone = company.getTelephone();
		this.banner = company.getBanner();
		this.address = company.getAddress();
		this.latitude = company.getLatitude() + "";
		this.longtitude = company.getLongtitude() + "";
		this.opentime = company.getOpen_time();
		this.notice = company.getNotice();
		this.introduction = company.getIntroduction();
		if (location != null && company.getLatitude() > 0 && company.getLongtitude() > 0) {
			Double dis = BaiDuMapUtil.GetShortDistance(company.getLongtitude(), company.getLatitude(),
					location.getLongtitude(), location.getLatitude());
			distance = dis / 1000;
		}
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(String longtitude) {
		this.longtitude = longtitude;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Integer getAvgprice() {
		return avgprice;
	}

	public void setAvgprice(Integer avgprice) {
		this.avgprice = avgprice;
	}

	public String getSpecial() {
		return special;
	}

	public void setSpecial(String special) {
		this.special = special;
	}

	public String getOpentime() {
		return opentime;
	}

	public void setOpentime(String opentime) {
		this.opentime = opentime;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

}

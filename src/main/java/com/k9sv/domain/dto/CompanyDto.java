package com.k9sv.domain.dto;

import java.math.BigDecimal;

import com.k9sv.Config2;
import com.k9sv.domain.pojo.Company;
import com.k9sv.util.BaiDuMapUtil;

@SuppressWarnings("unused")
public class CompanyDto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7654029200119016214L;
	private int uid;
	private String name;
	private String logo;
	private String telephone;
	private String district;
	private String latitude;
	private String longtitude;
	private double distance; // 与客户之间的距离
	private String opentime;
	private String address;

	public CompanyDto(Company company) {
		this.uid = company.getId();
		this.name = company.getName();
		this.logo = this.getLogo(company);
		this.telephone = company.getTelephone();
		this.district = company.getDistrict();
		this.latitude = company.getLatitudeStr();
		this.longtitude = company.getLongtitudeStr();
		this.distance = getDistance2(company.getDistance());
		this.opentime = company.getOpen_time();
		this.address = company.getAddress();
	}

	public CompanyDto(Company company, Location location) {
		this.uid = company.getId();
		this.name = company.getName();
		this.logo = this.getLogo(company);
		this.telephone = company.getTelephone();
		this.district = company.getDistrict();
		this.opentime = company.getOpen_time();
		if (location != null && company.getLatitude() > 0 && company.getLongtitude() > 0) {
			Double dis = BaiDuMapUtil.GetShortDistance(company.getLongtitude(), company.getLatitude(),
					location.getLongtitude(), location.getLatitude());
			distance = getDistance2(dis / 10000);
		}
		this.address = company.getAddress();
	}

	private String getLogo(Company company) {
		String str = null;
		if (company.getProfile() != null) {
			if (company.getProfile().getAvatar() != null && company.getProfile().getAvatar().startsWith("http://")) {
				str = company.getProfile().getAvatar();
			} else {
				str = Config2.AvatarImgServer + company.getProfile().getAvatar();
			}
		}

		return str;
	}

	public double getDistance2(double distance) {
		BigDecimal b = new BigDecimal(distance);
		double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return f1;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
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

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getOpentime() {
		return opentime;
	}

	public void setOpentime(String opentime) {
		this.opentime = opentime;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
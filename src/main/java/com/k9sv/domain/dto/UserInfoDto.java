package com.k9sv.domain.dto;

import com.k9sv.Config;
import com.k9sv.domain.pojo.UserInfo;
import com.k9sv.util.FloatOperation;

public class UserInfoDto {

	private Integer uid;
	private String profit;
	private String noProfit;
	private String allProfit;
	private String alipay;
	private String name;
	private String qrCode;
	private String recommendUrl;

	private Integer checked;
	private String mobile;

	public UserInfoDto(UserInfo info) {
		if (info != null) {
			this.uid = info.getId();
			this.setProfit(Float.toString(info.getProfit()));
			this.setNoProfit(Float.toString(info.getNoProfit()));
			this.setAllProfit(Float.toString(FloatOperation.add(
					info.getProfit(), info.getNoProfit())));
			this.alipay = info.getAlipay();
			this.name = info.getName();
			this.qrCode = info.getQrCode();
			this.recommendUrl = Config.ShareUrl + Config.RecommendUrl
					+ info.getId();
			int _checked = info.getProfile().getAccount().getChecked();
			this.checked = _checked;
			if (_checked == 1) {
				// this.mobile = info.getProfile().getMobile();
				this.mobile = info.getProfile().getAccount().getUsername();
			}
		}
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getAlipay() {
		return alipay;
	}

	public void setAlipay(String alipay) {
		this.alipay = alipay;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public String getRecommendUrl() {
		return recommendUrl;
	}

	public void setRecommendUrl(String recommendUrl) {
		this.recommendUrl = recommendUrl;
	}

	public Integer getChecked() {
		return checked;
	}

	public void setChecked(Integer checked) {
		this.checked = checked;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getProfit() {
		return profit;
	}

	public void setProfit(String profit) {
		this.profit = profit;
	}

	public String getNoProfit() {
		return noProfit;
	}

	public void setNoProfit(String noProfit) {
		this.noProfit = noProfit;
	}

	public String getAllProfit() {
		return allProfit;
	}

	public void setAllProfit(String allProfit) {
		this.allProfit = allProfit;
	}

}
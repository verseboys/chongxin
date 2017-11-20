package com.k9sv.domain.pojo;

/**
 * UserInfo entity. 个人收益
 */

public class UserInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 4563257853629942679L;
	private int id;
	private float profit;
	private float noProfit;
	private String alipay;
	private String name;
	private String qrCode;

	private Profile profile;

	// Constructors

	/** default constructor */
	public UserInfo() {
	}

	/** full constructor */
	public UserInfo(float profit, float noProfit) {
		this.profit = profit;
		this.noProfit = noProfit;
	}

	// Property accessors

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getProfit() {
		return this.profit;
	}

	public void setProfit(float profit) {
		this.profit = profit;
	}

	public float getNoProfit() {
		return this.noProfit;
	}

	public void setNoProfit(float noProfit) {
		this.noProfit = noProfit;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
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

}
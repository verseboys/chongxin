package com.k9sv.domain.pojo;

import java.util.Date;
import java.util.Set;

import com.k9sv.util.DateUtil;

/**
 * Buy entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("unused")
public class Buy implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7319329621255275540L;
	private String id;
	private int uid;
	private int addressid;
	private int state;
	private String name;
	private String telephone;
	private String address;
	private Date buytime;
	private String buytimeStr;
	private int paytype;
	private float paycount;
	private String noncestr;
	private int gold;
	private int deleted;
	private String express;// 快递
	private String oddNumbers;// 单号

	private Set<BuyInfo> buyinfors;

	// Constructors

	/** default constructor */
	public Buy() {
	}

	/** full constructor */
	public Buy(int uid, String chip, int productid, int addressid, String name,
			String telephone, String address, int number) {
		this.uid = uid;
		this.addressid = addressid;
		this.name = name;
		this.telephone = telephone;
		this.address = address;
	}

	// Property accessors

	public int getUid() {
		return this.uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getAddressid() {
		return this.addressid;
	}

	public void setAddressid(int addressid) {
		this.addressid = addressid;
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

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Date getBuytime() {
		return buytime;
	}

	public void setBuytime(Date buytime) {
		this.buytime = buytime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getPaytype() {
		return paytype;
	}

	public void setPaytype(int paytype) {
		this.paytype = paytype;
	}

	public String getBuytimeStr() {
		return DateUtil.getFormatDateTime(this.buytime, "yyyy-MM-dd HH:mm:ss");
	}

	public String getNoncestr() {
		return noncestr;
	}

	public void setNoncestr(String noncestr) {
		this.noncestr = noncestr;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public Set<BuyInfo> getBuyinfors() {
		return buyinfors;
	}

	public void setBuyinfors(Set<BuyInfo> buyinfors) {
		this.buyinfors = buyinfors;
	}

	public float getPaycount() {
		return paycount;
	}

	public void setPaycount(float paycount) {
		this.paycount = paycount;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public String getExpress() {
		return express;
	}

	public void setExpress(String express) {
		this.express = express;
	}

	public String getOddNumbers() {
		return oddNumbers;
	}

	public void setOddNumbers(String oddNumbers) {
		this.oddNumbers = oddNumbers;
	}

}
package com.k9sv.domain.pojo;

import java.util.Date;

import com.k9sv.util.DateUtil;

/**
 * Product entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("unused")
public class Product implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String product;
	private int type;
	private float price;
	private String unit;
	private int count;
	private String url;
	private int deleted;
	private Date createdtime;
	private String createdtimeStr;

	private String imgsmall;
	private String imgurl;
	private String detail;
	private float marketprice;
	private int ismall;// 是不是商城产品

	// Constructors

	/** default constructor */
	public Product() {
	}

	/** full constructor */
	public Product(String product, int price, String unit, int count, int deleted, Date createdtime) {
		this.product = product;
		this.setPrice(price);
		this.unit = unit;
		this.count = count;
		this.deleted = deleted;
		this.createdtime = createdtime;
	}

	// Property accessors

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProduct() {
		return this.product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public int getCount() {
		return this.count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getDeleted() {
		return this.deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getCreatedtime() {
		return createdtime;
	}

	public void setCreatedtime(Date createdtime) {
		this.createdtime = createdtime;
	}

	public String getCreatedtimeStr() {
		return DateUtil.getFormatDateTime(this.createdtime, "yyyy-MM-dd");
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public int getIsmall() {
		return ismall;
	}

	public void setIsmall(int ismall) {
		this.ismall = ismall;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getMarketprice() {
		return marketprice;
	}

	public void setMarketprice(float marketprice) {
		this.marketprice = marketprice;
	}

	public String getImgsmall() {
		return imgsmall;
	}

	public void setImgsmall(String imgsmall) {
		this.imgsmall = imgsmall;
	}

}
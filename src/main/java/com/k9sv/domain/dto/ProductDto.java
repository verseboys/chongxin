package com.k9sv.domain.dto;

import com.k9sv.Config;
import com.k9sv.domain.pojo.Product;

public class ProductDto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer productid;
	private String product;
	private Float price;
	private String unit;
	private Integer count;
	private String createdtime;

	private String imgsmall;
	private String detail;
	private Float marketprice;
	private String detailurl;// 详情页面地址

	public ProductDto(Product product) {
		this.productid = product.getId();
		this.product = product.getProduct();
		this.setPrice(product.getPrice());
		this.unit = product.getUnit();
		this.count = product.getCount();
		this.createdtime = product.getCreatedtimeStr();
		this.imgsmall = product.getImgsmall();
		this.detail = product.getDetail();
		this.setMarketprice(product.getMarketprice());
		this.detailurl = Config.ShareUrl + "/product/mallproduct/"
				+ product.getId();
	}

	public ProductDto(Product product, String mallList) {
		this.productid = product.getId();
		this.product = product.getProduct();
		this.setPrice(product.getPrice());
		this.setMarketprice(product.getMarketprice());
		this.imgsmall = product.getImgsmall();
		this.detailurl = Config.ShareUrl + "/product/mallproduct/"
				+ product.getId();
	}

	public ProductDto(Integer productId, String product) {
		this.productid = productId;
		this.product = product;
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

	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getProductid() {
		return productid;
	}

	public void setProductid(Integer productId) {
		this.productid = productId;
	}

	public String getCreatedtime() {
		return createdtime;
	}

	public void setCreatedtime(String createdtime) {
		this.createdtime = createdtime;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Float getMarketprice() {
		return marketprice;
	}

	public void setMarketprice(Float marketprice) {
		this.marketprice = marketprice;
	}

	public String getImgsmall() {
		return imgsmall;
	}

	public void setImgsmall(String imgsmall) {
		this.imgsmall = imgsmall;
	}

	public String getDetailurl() {
		return detailurl;
	}

	public void setDetailurl(String detailurl) {
		this.detailurl = detailurl;
	}
}
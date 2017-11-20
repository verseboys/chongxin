package com.k9sv.domain.dto;

import com.k9sv.domain.pojo.BuyInfo;

public class BuyInforDto {

	private Integer productid;
	private String product;
	private String imgurl;
	private Integer number;
	private Float price;
	private Float marketprice;
	private String imgsmall;

	public BuyInforDto(BuyInfo buyInfor) {
		this.productid = buyInfor.getProductid();
		this.product = buyInfor.getProduct().getProduct();
		this.imgurl = buyInfor.getProduct().getImgurl();
		this.number = buyInfor.getNumber();
		this.setPrice(buyInfor.getProduct().getPrice());
		this.setMarketprice(buyInfor.getProduct().getMarketprice());
		this.imgsmall = buyInfor.getProduct().getImgsmall();
	}

	public Integer getProductid() {
		return this.productid;
	}

	public void setProductid(Integer productid) {
		this.productid = productid;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
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

}
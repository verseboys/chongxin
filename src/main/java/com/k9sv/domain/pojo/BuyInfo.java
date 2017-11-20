package com.k9sv.domain.pojo;

/**
 * BuyInfor entity. @author MyEclipse Persistence Tools
 */

public class BuyInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 5683302404245493832L;
	private int id;
	private String buyid;
	private int productid;
	private int number;

	private Buy buy;
	
	private Product product;

	// Constructors

	/** default constructor */
	public BuyInfo() {
	}

	/** full constructor */
	public BuyInfo(String buyid, int productid, int number) {
		this.buyid = buyid;
		this.productid = productid;
		this.number = number;
	}

	// Property accessors

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBuyid() {
		return this.buyid;
	}

	public void setBuyid(String buyid) {
		this.buyid = buyid;
	}

	public int getProductid() {
		return this.productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	public int getNumber() {
		return this.number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Buy getBuy() {
		return buy;
	}

	public void setBuy(Buy buy) {
		this.buy = buy;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
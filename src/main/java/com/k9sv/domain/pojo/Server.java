package com.k9sv.domain.pojo;

/**
 * Server entity. @author MyEclipse Persistence Tools
 */

public class Server implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4849371661469137234L;
	private int id;
	private int companyid;
	private int classifyid;
	private String price;
	private String units;

	private Company company;// 服务公司
	private Classify classify;// 服务类型
	

	// Constructors

	/** default constructor */
	public Server() {
	}

	/** full constructor */
	public Server(int companyid, int classifyid, String price) {
		this.companyid = companyid;
		this.classifyid = classifyid;
		this.price = price;
	}

	// Property accessors

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCompanyid() {
		return this.companyid;
	}

	public void setCompanyid(int companyid) {
		this.companyid = companyid;
	}

	public int getClassifyid() {
		return this.classifyid;
	}

	public void setClassifyid(int classifyid) {
		this.classifyid = classifyid;
	}

	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Classify getClassify() {
		return classify;
	}

	public void setClassify(Classify classify) {
		this.classify = classify;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

}
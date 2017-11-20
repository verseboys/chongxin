package com.k9sv.domain.pojo;

/**
 * Address entity. @author MyEclipse Persistence Tools
 */

public class Address implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -7469115801405118128L;
	private int id;
	private int uid;
	private String name;
	private String telephone;
	private String address;
	private int deleted;
	private int state;

	// Constructors

	/** default constructor */
	public Address() {
	}

	/** full constructor */
	public Address(int uid, String address, int deleted, int state) {
		this.setUid(uid);
		this.address = address;
		this.deleted = deleted;
		this.state = state;
	}

	// Property accessors

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDeleted() {
		return this.deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public int getState() {
		return this.state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

}
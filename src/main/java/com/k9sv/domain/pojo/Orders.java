package com.k9sv.domain.pojo;

import java.util.Date;

import com.k9sv.util.DateUtil;

/**
 * Orders entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("unused")
public class Orders implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -7048366824515790958L;
	private String id;
	private int uid;
	private int state;
	private int deleted;
	private String remark;
	private String adminremark;
	private Date created;
	private String createdStr;
	private int addressId;// 收货地址id
	private int doctorId;

	private String name;// 收货人
	private String telephone;// 收货人电话
	private String address;// 收货地址
	private Doctor doctor;

	// Constructors

	/** default constructor */
	public Orders() {
	}

	/** minimal constructor */
	public Orders(String id) {
		this.id = id;
	}

	/** full constructor */
	public Orders(String id, int uid, int state, int deleted,
			String remark, Date created, int addressId, int doctorId) {
		this.id = id;
		this.uid = uid;
		this.state = state;
		this.deleted = deleted;
		this.remark = remark;
		this.created = created;
		this.addressId = addressId;
		this.doctorId = doctorId;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getState() {
		return this.state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getDeleted() {
		return this.deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public String getCreatedStr() {
		return DateUtil.getFormatDateTime(this.created);
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAdminremark() {
		return adminremark;
	}

	public void setAdminremark(String adminremark) {
		this.adminremark = adminremark;
	}
}
package com.k9sv.domain.dto;

import com.k9sv.domain.pojo.Address;

public class AddressDto {
	private Integer addrid;
	private String address;
	private String name;
	private String telephone;
	private Integer state;

	public AddressDto(Address address) {
		this.address = address.getAddress();
		this.addrid = address.getId();
		this.name = address.getName();
		this.telephone = address.getTelephone();
		this.state = address.getState();
	}

	public AddressDto(Integer addrid, String address, String name,
			String telephone) {
		this.address = address;
		this.addrid = addrid;
		this.name = name;
		this.telephone = telephone;
	}

	public Integer getAddrid() {
		return addrid;
	}

	public void setAddrid(Integer addrid) {
		this.addrid = addrid;
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

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
}

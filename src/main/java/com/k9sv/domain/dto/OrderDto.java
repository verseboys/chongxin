package com.k9sv.domain.dto;

import com.k9sv.domain.pojo.Orders;
import com.k9sv.util.DateUtil;

public class OrderDto {
	private String orderid;
	private Integer state;
	private AddressDto address;
	private DoctorDto doctor;
	private String created;

	public OrderDto(Orders order) {
		this.orderid = order.getId();
		this.state = order.getState();
		this.address = new AddressDto(order.getAddressId(), order.getAddress(),
				order.getName(), order.getTelephone());
		if (order.getDoctor() != null) {
			this.doctor = new DoctorDto(order.getDoctor());
		}
		this.created = DateUtil.getFormatDateTime(order.getCreated());
	}
	
	public OrderDto(Orders order,String state) {
		this.orderid = order.getId();
		this.state = order.getState();
		if (order.getDoctor() != null) {
			this.doctor = new DoctorDto(order.getDoctor());
		}
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public AddressDto getAddress() {
		return address;
	}

	public void setAddress(AddressDto address) {
		this.address = address;
	}

	public DoctorDto getDoctor() {
		return doctor;
	}

	public void setDoctor(DoctorDto doctor) {
		this.doctor = doctor;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}
	
}

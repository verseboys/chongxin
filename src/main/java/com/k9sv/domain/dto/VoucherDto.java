package com.k9sv.domain.dto;

import com.k9sv.domain.pojo.Voucher;
import com.k9sv.domain.pojo.VoucherType;

public class VoucherDto {

	private Integer vid;
	private Integer price;
	private String endtime;
	private Integer beused;
	private Integer beoverdue;
	private VoucherTypeDto type;

	public VoucherDto(Voucher voucher) {
		this.vid = voucher.getId();
		this.price = voucher.getPrice();
		VoucherType voucherType = voucher.getType();
		if (voucherType != null) {
			this.type = new VoucherTypeDto(voucherType);
		}
		this.endtime = voucher.getEndtimeStr();
		this.beused = voucher.getBeused();
		this.beoverdue = voucher.getBeoverdue();
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public VoucherTypeDto getType() {
		return type;
	}

	public void setType(VoucherTypeDto type) {
		this.type = type;
	}

	public Integer getVid() {
		return vid;
	}

	public void setVid(Integer vid) {
		this.vid = vid;
	}

	public Integer getBeused() {
		return beused;
	}

	public void setBeused(Integer beused) {
		this.beused = beused;
	}

	public Integer getBeoverdue() {
		return beoverdue;
	}

	public void setBeoverdue(Integer beoverdue) {
		this.beoverdue = beoverdue;
	}

}
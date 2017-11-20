package com.k9sv.domain.dto;

import com.k9sv.domain.pojo.VoucherType;

public class VoucherTypeDto {

	private String name;
	private String intro;

	public VoucherTypeDto(VoucherType type) {
		this.name = type.getName();
		this.intro = type.getIntro();
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
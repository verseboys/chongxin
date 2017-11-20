package com.k9sv.domain.dto;

import com.k9sv.domain.pojo.Profile;

public class LevelDto {
	private Integer goldCount;
	private Integer voucherCount;

	public LevelDto(Profile profile) {
		this.goldCount = profile.getGoldCount();
	}

	public LevelDto(Profile profile, int voucher) {
		this.goldCount = profile.getGoldCount();
		this.voucherCount = voucher;
	}

	public Integer getGoldCount() {
		return goldCount;
	}

	public void setGoldCount(Integer goldCount) {
		this.goldCount = goldCount;
	}

	public Integer getVoucherCount() {
		return voucherCount;
	}

	public void setVoucherCount(Integer voucherCount) {
		this.voucherCount = voucherCount;
	}

}

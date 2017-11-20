package com.k9sv.domain.dto;

import com.k9sv.domain.pojo.Buy;

public class PushBuyDto {

	private String buyid;
	private String express;// 快递
	private String oddNumbers;// 单号

	public PushBuyDto(Buy buy) {
		this.buyid = buy.getId();
		this.express = buy.getExpress();
		this.oddNumbers = buy.getOddNumbers();
	}

	public String getBuyid() {
		return buyid;
	}

	public void setBuyid(String buyid) {
		this.buyid = buyid;
	}

	public String getExpress() {
		return express;
	}

	public void setExpress(String express) {
		this.express = express;
	}

	public String getOddNumbers() {
		return oddNumbers;
	}

	public void setOddNumbers(String oddNumbers) {
		this.oddNumbers = oddNumbers;
	}

}
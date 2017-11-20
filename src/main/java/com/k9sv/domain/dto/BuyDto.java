package com.k9sv.domain.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.k9sv.domain.pojo.Buy;
import com.k9sv.domain.pojo.BuyInfo;
import com.k9sv.util.DateUtil;
import com.k9sv.util.FloatOperation;

public class BuyDto {

	private String buyid;
	private AddressDto address;
	private Integer state;
	private String buytime;
	private Float paycount;
	private Float oldpay;
	private String express;// 快递
	private String oddNumbers;// 单号
	private PayDto pay;

	private List<BuyInforDto> buyInfors;

	public BuyDto(Buy buy) {
		this.buyid = buy.getId();
		this.address = new AddressDto(buy.getAddressid(), buy.getAddress(),
				buy.getName(), buy.getTelephone());
		this.state = buy.getState();
		this.buytime = DateUtil.getFormatDateTime(buy.getBuytime(),
				"yyyy-MM-dd HH:mm");
		this.setPaycount(buy.getPaycount());
		this.buyInfors = this.getBuyinforDtos(buy.getBuyinfors());
		this.express = buy.getExpress();
		this.oddNumbers = buy.getOddNumbers();
		this.oldpay = this.getOldpays(buy.getBuyinfors());
	}

	private Float getOldpays(Set<BuyInfo> set) {
		float oldpay = 0;
		if (set != null && set.size() > 0) {
			for (BuyInfo buyInfor : set) {
				float price = buyInfor.getProduct().getPrice();
				int number = buyInfor.getNumber();
				oldpay = FloatOperation.add(oldpay,
						FloatOperation.multiply(price, number));
			}
		}
		return oldpay;
	}

	private List<BuyInforDto> getBuyinforDtos(Set<BuyInfo> set) {
		List<BuyInforDto> _buyInforDtos = new ArrayList<BuyInforDto>();
		if (set != null && set.size() > 0) {
			for (BuyInfo buyInfor : set) {
				_buyInforDtos.add(new BuyInforDto(buyInfor));
			}
		}
		return _buyInforDtos;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getBuytime() {
		return buytime;
	}

	public void setBuytime(String buytime) {
		this.buytime = buytime;
	}

	public String getBuyid() {
		return buyid;
	}

	public void setBuyid(String buyid) {
		this.buyid = buyid;
	}

	public AddressDto getAddress() {
		return address;
	}

	public void setAddress(AddressDto address) {
		this.address = address;
	}

	public PayDto getPay() {
		return pay;
	}

	public void setPay(PayDto pay) {
		this.pay = pay;
	}

	public List<BuyInforDto> getBuyInfors() {
		return buyInfors;
	}

	public void setBuyInfors(List<BuyInforDto> buyInfors) {
		this.buyInfors = buyInfors;
	}

	public Float getPaycount() {
		return paycount;
	}

	public void setPaycount(Float paycount) {
		this.paycount = paycount;
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

	public Float getOldpay() {
		return oldpay;
	}

	public void setOldpay(Float oldpay) {
		this.oldpay = oldpay;
	}

}
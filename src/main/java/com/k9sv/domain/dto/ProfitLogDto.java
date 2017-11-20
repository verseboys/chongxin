package com.k9sv.domain.dto;

import com.k9sv.domain.pojo.ProfitLog;
import com.k9sv.util.DateUtil;

public class ProfitLogDto {

	private Integer pid;
	private String created;
	private UserDto friend;
	private Integer type;
	private Integer status;
	private BuyDto buy;
	private Float profit;
	private String remarks;

	public ProfitLogDto(ProfitLog profitLog) {
		this.pid = profitLog.getId();
		this.created = DateUtil.getFormatDateTime(profitLog.getCreated());
		this.friend = new UserDto(profitLog.getFriend());
		this.type = profitLog.getType();
		this.status = profitLog.getStatus();
		if (profitLog.getBuy() != null) {
			this.buy = new BuyDto(profitLog.getBuy());
		}
		this.profit = profitLog.getProfit();
		if (profitLog.getType() == 0) {
			this.remarks = "推荐注册成功";
		} else if (profitLog.getType() == 1) {
			this.remarks = "消费1单";
		} else if (profitLog.getType() == 2) {
			this.remarks = "提现";
		}
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public UserDto getFriend() {
		return friend;
	}

	public void setFriend(UserDto friend) {
		this.friend = friend;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public BuyDto getBuy() {
		return buy;
	}

	public void setBuy(BuyDto buy) {
		this.buy = buy;
	}

	public Float getProfit() {
		return profit;
	}

	public void setProfit(Float profit) {
		this.profit = profit;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
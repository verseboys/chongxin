package com.k9sv.service;

import java.util.List;

import com.k9sv.domain.pojo.Buy;
import com.k9sv.domain.pojo.ProfitLog;
import com.k9sv.domain.pojo.UserInfo;

public interface IProfitManager extends IBaseManager {
	/**
	 * 用户收入明细
	 * 
	 * @param uid
	 * @param start
	 * @param size
	 * @return
	 */
	List<ProfitLog> getProfitLogs(int uid, int start, int size);

	Integer getProfitLogTotal(Integer uid);

	/**
	 * 推荐人总数
	 * 
	 * @param id
	 * @return
	 */
	int getRecommendCount(int uid);

	/**
	 * 更新提现
	 * 
	 * @param info
	 * @param _profit
	 */
	void updateInfo(UserInfo info, float _profit);

	/**
	 * 确认收货 返利可用
	 * 
	 * @param buy
	 */
	void updateProfit(Buy buy);

	/**
	 * 付款成功 添加返利
	 * 
	 * @param buy
	 */
	void addProfit(Buy buy);

	/**
	 * 收入明细列表
	 * 
	 * @param nickname
	 * @param states
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 */
	List<ProfitLog> getProfitLogs2(String nickname, int states,
			Integer pageNum, Integer numPerPage);

	Integer getProfitLogTotal2(String nickname, int states);

}

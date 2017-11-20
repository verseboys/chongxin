package com.k9sv.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.k9sv.Config;
import com.k9sv.domain.pojo.Buy;
import com.k9sv.domain.pojo.Profile;
import com.k9sv.domain.pojo.ProfitLog;
import com.k9sv.domain.pojo.UserInfo;
import com.k9sv.service.IProfitManager;
import com.k9sv.util.FloatOperation;
import com.k9sv.util.StringUtil;

@Service("profitManager")
public class ProfitManager extends BaseManager implements IProfitManager {

	@Override
	public List<ProfitLog> getProfitLogs(int uid, int start, int size) {

		return super.queryStart(
				"from ProfitLog where uid = ? order by created desc",
				new Object[] { uid }, start, size);
	}

	@Override
	public int getRecommendCount(int uid) {
		Long total = (Long) super
				.getCount(
						"select count(id) from Profile where account.deleted = 0 and fromId = ?",
						new Object[] { uid });
		if (total == null) {
			return 0;
		}
		return total.intValue();
	}

	@Override
	public void updateInfo(UserInfo info, float _profit) {
		float _tem = info.getProfit();
		info.setProfit(FloatOperation.subtract(_tem, _profit));
		super.update(info);
		ProfitLog log = new ProfitLog();
		log.setCreated(new Date());
		log.setFid(info.getId());
		log.setType(2);
		log.setUid(info.getId());
		log.setProfit(FloatOperation.subtract(0, _profit));
		super.save(log);
	}

	@Override
	public void updateProfit(final Buy buy) {
		Thread t = new Thread(new Runnable() {
			public void run() {
				try {
					Profile profile = getByClassId(Profile.class, buy.getUid());
					ProfitLog log = getRecommendLog(profile);
					ProfitLog buyLog = getBuyLog(buy.getId());
					UserInfo info = getByClassId(UserInfo.class,
							profile.getFromId());
					float profit = info.getProfit();
					float noProfit = info.getNoProfit();
					if (log != null) { // 有未生效的推荐记录
						// 推荐时间之后的订单总和
						Date created = log.getCreated();
						float consume = getConsumeCount(profile, created);
						if (consume >= Config.MinCharge) {// 消费总额已满足
							log.setStatus(1);
							profit = FloatOperation.add(profit,
									Config.Recommend);
							noProfit = FloatOperation.subtract(noProfit,
									Config.Recommend);
						} else {
							log.setStatus(2);
						}
						update(log);
					}
					buyLog.setStatus(1);
					update(buyLog);
					profit = FloatOperation.add(profit, FloatOperation
							.multiply(buy.getPaycount(), Config.Rate));
					noProfit = FloatOperation.subtract(noProfit, FloatOperation
							.multiply(buy.getPaycount(), Config.Rate));
					info.setProfit(profit);
					info.setNoProfit(noProfit);
					update(info);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		t.start();
	}

	/**
	 * 购买返利记录
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected ProfitLog getBuyLog(String buyId) {
		List<ProfitLog> profitLogs = super.find(
				"from ProfitLog where buyId = ?", new Object[] { buyId });
		ProfitLog profitLog = null;
		if (profitLogs != null && profitLogs.size() > 0) {
			profitLog = profitLogs.get(0);
		}
		return profitLog;
	}

	/**
	 * 推荐时间之后的订单总和
	 * 
	 * @param profile
	 * @param created
	 * @return
	 */
	protected float getConsumeCount(Profile profile, Date created) {
		Double total = (Double) super.getCount(
				"select sum(paycount) from Buy where uid = ? and buytime >= ?",
				new Object[] { profile.getId(), created });
		if (total == null) {
			return 0;
		}
		return total.floatValue();
	}

	/**
	 * 用户id、推荐人id 取未生效的推荐记录
	 * 
	 * @param profile
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected ProfitLog getRecommendLog(Profile profile) {
		List<ProfitLog> profitLogs = super.find(
				"from ProfitLog where uid = ? and fid = ? and status != 1 and buyId is null",
				new Object[] { profile.getFromId(), profile.getId() });
		ProfitLog profitLog = null;
		if (profitLogs != null && profitLogs.size() > 0) {
			profitLog = profitLogs.get(0);
		}
		return profitLog;
	}

	@Override
	public void addProfit(final Buy buy) {
		Thread t = new Thread(new Runnable() {
			public void run() {
				try {
					Profile profile = getByClassId(Profile.class, buy.getUid());
					if (profile.getFromId() != 0) {
						ProfitLog log = new ProfitLog();
						log.setCreated(new Date());
						log.setFid(profile.getId());
						log.setType(1);
						log.setUid(profile.getFromId());
						log.setProfit(FloatOperation.multiply(
								buy.getPaycount(), Config.Rate));
						log.setStatus(0);
						log.setBuyId(buy.getId());
						save(log);
						UserInfo userInfo = getByClassId(UserInfo.class,
								profile.getFromId());
						if (userInfo == null) {
							userInfo = new UserInfo();
							userInfo.setId(profile.getFromId());
							userInfo.setNoProfit(FloatOperation.multiply(
									buy.getPaycount(), Config.Rate));
							save(userInfo);
						} else {
							userInfo.setNoProfit(FloatOperation.add(userInfo
									.getNoProfit(), FloatOperation.multiply(
									buy.getPaycount(), Config.Rate)));
							update(userInfo);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		t.start();
	}

	@Override
	public Integer getProfitLogTotal(Integer uid) {
		Long total = (Long) super.getCount(
				"select count(id) from ProfitLog where uid = ?",
				new Object[] { uid });
		if (total == null) {
			return 0;
		}
		return total.intValue();
	}

	@Override
	public List<ProfitLog> getProfitLogs2(String nickname, int states,
			Integer pageNum, Integer numPerPage) {
		if (states == -1) {
			return getProfitLogs(nickname, pageNum, numPerPage);
		}
		return getProfitLogs(nickname, states, pageNum, numPerPage);
	}

	private List<ProfitLog> getProfitLogs(String nickname, int states,
			Integer pageNum, Integer numPerPage) {
		if (StringUtil.isNotEmpty(nickname)) {
			return super
					.query("from ProfitLog where type = ? and profile.nickName like ? order by created desc",
							new Object[] { states, "%" + nickname + "%" },
							pageNum, numPerPage);
		} else {
			return super.query(
					"from ProfitLog where type = ? order by created desc",
					new Object[] { states }, pageNum, numPerPage);
		}
	}

	private List<ProfitLog> getProfitLogs(String nickname, Integer pageNum,
			Integer numPerPage) {
		if (StringUtil.isNotEmpty(nickname)) {
			return super
					.query("from ProfitLog where profile.nickName like ? order by created desc",
							new Object[] { "%" + nickname + "%" }, pageNum,
							numPerPage);
		} else {
			return super.query("from ProfitLog order by created desc", null,
					pageNum, numPerPage);
		}
	}

	@Override
	public Integer getProfitLogTotal2(String nickname, int states) {
		if (states == -1) {
			return getProfitLogTotal(nickname);
		}
		return getProfitLogTotal(nickname, states);
	}

	private Integer getProfitLogTotal(String nickname, int states) {
		if (StringUtil.isNotEmpty(nickname)) {
			Long total = (Long) super
					.getCount(
							"select count(id) from ProfitLog where type = ? and profile.nickName like ?",
							new Object[] { states, "%" + nickname + "%" });
			if (total == null) {
				return 0;
			}
			return total.intValue();
		} else {
			Long total = (Long) super.getCount(
					"select count(id) from ProfitLog where type = ?",
					new Object[] { states });
			if (total == null) {
				return 0;
			}
			return total.intValue();
		}
	}

	private Integer getProfitLogTotal(String nickname) {
		if (StringUtil.isNotEmpty(nickname)) {
			Long total = (Long) super
					.getCount(
							"select count(id) from ProfitLog where profile.nickName like ?",
							new Object[] { "%" + nickname + "%" });
			if (total == null) {
				return 0;
			}
			return total.intValue();
		} else {
			Long total = (Long) super.getCount(
					"select count(id) from ProfitLog", null);
			if (total == null) {
				return 0;
			}
			return total.intValue();
		}
	}

}

package com.k9sv.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.k9sv.Config;
import com.k9sv.domain.pojo.Goldrecord;
import com.k9sv.domain.pojo.Profile;
import com.k9sv.service.IGoldManager;

@Service("goldManager")
public class GoldManager extends BaseManager implements IGoldManager {

	Logger LOG = Logger.getLogger(GoldManager.class);

	@Override
	public int getGold(Integer uid, Integer fid, Integer type, Integer typeGold) {

		// 判断是否评论过
		Goldrecord goldrecord = this.getGoldRecord(uid, fid, type);
		if (goldrecord != null) {
			return 0;
		}
		// 取今日领取总量
		int dayGoldCount = this.getDayGoldCount(uid);
		int tem = dayGoldCount + typeGold;
		if (tem >= Config.DayGoldCount) {
			return Config.DayGoldCount - dayGoldCount;
		}
		return typeGold;
	}

	/**
	 * 今日已领取的总数
	 * 
	 * @param uid
	 * @return
	 */
	private int getDayGoldCount(Integer uid) {
		Long total = (Long) super
				.getCount(
						"select sum(gold) from Goldrecord where date(createdtime) = curdate() and uid = ?",
						new Object[] { uid });
		if (total == null) {
			return 0;
		}
		return total.intValue();
	}

	/**
	 * 判断是否领取过
	 * 
	 * @param uid
	 * @param fid
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Goldrecord getGoldRecord(Integer uid, Integer fid, Integer type) {
		List<Goldrecord> records = super
				.find("from Goldrecord where uid = ? and fid = ? and type = ? order by createdtime desc",
						new Object[] { uid, fid, type });
		Goldrecord record = null;
		if (records != null && records.size() > 0) {
			record = records.get(0);
		}
		return record;
	}

	@Override
	public void addGold(Profile profile, Integer fid, Integer type, int gold) {
		Integer uid = profile.getId();
		Goldrecord goldrecord = new Goldrecord();
		goldrecord.setUid(uid);
		goldrecord.setFid(fid);
		goldrecord.setType(type);
		goldrecord.setGold(gold);
		goldrecord.setCreatedtime(new Date());
		super.save(goldrecord);
		// update profile.goldCount
		Profile _profile = profile;
		int goldCont = _profile.getGoldCount();
		int score = _profile.getScore();
		int prestige = _profile.getPrestige();
		_profile.setGoldCount(goldCont + gold);
		// 暂时加1
		_profile.setScore(score + 1);
		_profile.setPrestige(prestige + 1);
		super.update(_profile);
	}

	@Override
	public void addGold(Profile profile, Integer type, int gold) {
		this.addGold(profile, 0, type, gold);
	}

	@Override
	public int getGold(Integer uid, Integer type, Integer typeGold) {
		// 判断当日任务是否已做过
		Goldrecord goldrecord = this.getGoldRecord(uid, type);
		if (goldrecord != null) {
			return 0;
		}
		// 取今日领取总量
		int dayGoldCount = this.getDayGoldCount(uid);
		int tem = dayGoldCount + typeGold;
		if (tem >= Config.DayGoldCount) {
			return Config.DayGoldCount - dayGoldCount;
		}
		return typeGold;
	}

	/**
	 * 判断每日任务是否领取过
	 * 
	 * @param uid
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Goldrecord getGoldRecord(Integer uid, Integer type) {
		List<Goldrecord> records = super.find(
				"from Goldrecord where uid = ? and type = ? and date(createdtime) = curdate() order by createdtime desc",
				new Object[] { uid, type });
		Goldrecord record = null;
		if (records != null && records.size() > 0) {
			record = records.get(0);
		}
		return record;
	}

}
package com.k9sv.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.k9sv.domain.pojo.Buy;
import com.k9sv.service.IBuyManager;
import com.k9sv.util.StringUtil;

@Service("buyManager")
public class BuyManager extends BaseManager implements IBuyManager {

	Logger LOG = Logger.getLogger(BuyManager.class);

	@Override
	public Integer getCount(String title) {
		if (StringUtil.isNotEmpty(title)) {
			Long total = (Long) super.getCount(
					"select count(id) from Buy where id like ?",
					new Object[] { "%" + title + "%" });
			if (total == null) {
				return 0;
			}
			return total.intValue();
		} else {
			Long total = (Long) super.getCount("select count(id) from Buy",
					null);
			if (total == null) {
				return 0;
			}
			return total.intValue();
		}
	}

	@Override
	public List<Buy> getBuys(String title, Integer pageNum, Integer numPerPage) {
		if (StringUtil.isNotEmpty(title)) {
			return super.query(
					"from Buy where id like ? order by buytime desc",
					new Object[] { "%" + title + "%" }, pageNum, numPerPage);
		} else {
			return super.query("from Buy order by buytime desc", null, pageNum,
					numPerPage);
		}
	}

	@Override
	public Integer getCount(String title, int state) {
		if (StringUtil.isNotEmpty(title)) {
			Long total = (Long) super.getCount(
					"select count(id) from Buy where id like ? and state = ?",
					new Object[] { "%" + title + "%", state });
			if (total == null) {
				return 0;
			}
			return total.intValue();
		} else {
			Long total = (Long) super.getCount(
					"select count(id) from Buy where state = ?",
					new Object[] { state });
			if (total == null) {
				return 0;
			}
			return total.intValue();
		}
	}

	@Override
	public List<Buy> getBuys(String title, int state, Integer pageNum,
			Integer numPerPage) {
		if (StringUtil.isNotEmpty(title)) {
			return super
					.query("from Buy where id like ? and state = ? order by buytime desc",
							new Object[] { "%" + title + "%", state }, pageNum,
							numPerPage);
		} else {
			return super.query("from Buy where state = ? order by buytime desc",
					new Object[] { state }, pageNum, numPerPage);
		}
	}

	@Override
	public Integer getCount2(String title, int state) {
		if (state == -2) {
			return this.getCount(title);
		}
		return this.getCount(title, state);
	}

	@Override
	public List<Buy> getBuys2(String title, int state, Integer pageNum,
			Integer numPerPage) {
		if (state == -2) {
			return this.getBuys(title, pageNum, numPerPage);
		}
		return this.getBuys(title, state, pageNum, numPerPage);
	}

}
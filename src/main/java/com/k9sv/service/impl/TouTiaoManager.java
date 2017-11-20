package com.k9sv.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.k9sv.domain.pojo.ToutiaoPublish;
import com.k9sv.service.ITouTiaoManager;

@Service("toutiaoManager")
public class TouTiaoManager extends BaseManager implements ITouTiaoManager {

	@Override
	public ToutiaoPublish getObjects(Integer id) {
		List<ToutiaoPublish> publishs = super.query(
				"from ToutiaoPublish where sid = ?", new Object[] { id }, 1, 1);
		return publishs.get(0);
	}

	@Override
	public void deleteSucai(Integer id) {
		super.executeSQL("delete from ToutiaoSucai where id = ?",
				new Object[] { id });
		super.executeSQL("delete from ToutiaoPublish where sid = ?",
				new Object[] { id });
	}

	@Override
	public void deletePublish(Integer id) {
		super.executeSQL("delete from ToutiaoPublish where id = ?",
				new Object[] { id });
	}

	@Override
	public List<ToutiaoPublish> getTouTiaos(Integer start, Integer pagesize,
			Date _now) {
		if (start == 0) {
			return super
					.queryStart(
							"from ToutiaoPublish where published < ? order by published desc",
							new Object[] { _now }, start, pagesize);
		} else {
			return super.queryStart(
					"from ToutiaoPublish where id < ? order by published desc",
					new Object[] { start }, 0, pagesize);
		}
	}

	@Override
	public Integer getMaxToutiao(Integer maxtoutiaoid) {
		List<Integer> _list = queryStart(
				"select id from ToutiaoPublish where id >= ? order by id desc",
				new Object[] { maxtoutiaoid }, 0, 1);
		if (_list == null) {
			return null;
		}
		return _list.size() == 1 ? _list.get(0) : null;
	}

}

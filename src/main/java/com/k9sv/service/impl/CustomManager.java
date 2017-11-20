package com.k9sv.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.k9sv.domain.pojo.Custom;
import com.k9sv.service.ICustomManager;

@Service("customManager")
public class CustomManager extends BaseManager implements ICustomManager {

	@Override
	public List<Custom> getCustoms(String sql, int start, int size) {

		return super.queryStart(sql, null, start, size);
	}

	@Override
	public List<Custom> getCustoms(int start, int size) {
		return super.queryStart("from Custom order by updated desc", null, start, size);
	}

	@Override
	public int getCustomCount() {
		Long i = (Long)super.getCount("select count(id) from Custom", null);
		return i.intValue();
	}

}

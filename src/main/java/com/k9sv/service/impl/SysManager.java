package com.k9sv.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.k9sv.domain.pojo.Category;
import com.k9sv.domain.pojo.City;
import com.k9sv.service.ISysManager;

@Service("sysManager")
public class SysManager extends BaseManager implements ISysManager {

	@Override
	public List<Category> getCategories(int pid, int start, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<City> getCities(int pid, int start, int size) {
		return super.queryStart("from City where pid = ?", new Object[]{pid}, start, size);
	}

	public List<City> getAllCities(int start, int size) {
		return super.queryStart("from City", null, start, size);
	}

}

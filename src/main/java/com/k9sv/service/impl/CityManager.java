package com.k9sv.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.k9sv.domain.pojo.City;
import com.k9sv.service.ICityManager;
import com.k9sv.util.StringUtil;

@SuppressWarnings("unchecked")
@Service("cityManager")
public class CityManager extends BaseManager implements ICityManager {

	Logger LOG = Logger.getLogger(CityManager.class);

	@Override
	public City getCity(String city, int type) {
		if (StringUtil.isEmpty(city)) {
			return null;
		}
		List<City> cityList = super.find("from City where name = ? and type = ?", new Object[] { city, type });
		City _city = null;
		if (cityList != null && cityList.size() > 0) {
			_city = cityList.get(0);
		}
		return _city;
	}

	@Override
	public List<City> getCitysByFid(int fid) {
		return super.find("from City where pid = ? order by id", new Object[] { fid });
	}

}
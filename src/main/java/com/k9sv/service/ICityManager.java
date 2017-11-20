package com.k9sv.service;

import java.util.List;

import com.k9sv.domain.pojo.City;

public interface ICityManager extends IBaseManager {

	/**
	 * 市名称取City
	 * 
	 * @param province
	 * @param type
	 * @return
	 */
	City getCity(String city, int type);

	/**
	 * 上级城市id获取城市列表
	 * 
	 * @param fid
	 * @return
	 */
	List<City> getCitysByFid(int fid);

}

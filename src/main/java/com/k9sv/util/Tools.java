package com.k9sv.util;

import java.util.List;

import com.k9sv.dao.IBaseDao;
import com.k9sv.domain.pojo.City;
import com.k9sv.domain.pojo.Ip;

public class Tools {

	private IBaseDao baseDao;

	public IBaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(IBaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public void city() throws Exception{
		List<City> _cities = baseDao.query("from City order by id desc", null, 0, 100000);
		
		List<Ip> _ips = baseDao.query("from Ip order by id desc", null, 0, 100000);
		
		for(City city:_cities){
			String cityname = city.getName();
			for(Ip ip:_ips){
				String ipcityname = ip.getProvince();
				if(ipcityname != null){
					ipcityname = ipcityname.substring(0,ipcityname.length()-1);
					if(ipcityname.equals(cityname)){
						ip.setCity_id(city.getId());
						baseDao.update(ip);
					}
				}
				
			}
		}
		
	}
	
	
}

package com.k9sv.service;

import java.util.List;

import com.k9sv.domain.pojo.Category;
import com.k9sv.domain.pojo.City;

public interface ISysManager extends IBaseManager {

	List<Category> getCategories(int pid ,int start,int size);
	
	List<City> getCities(int pid,int start,int size);
	
	List<City> getAllCities(int start,int size);
}

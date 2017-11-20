package com.k9sv.service;

import java.util.List;

import com.k9sv.domain.pojo.Custom;

public interface ICustomManager extends IBaseManager {

	List<Custom> getCustoms(String sql,int start,int size);
	
	List<Custom> getCustoms(int start,int size);
	
	int getCustomCount();
}

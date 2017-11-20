package com.k9sv.service;

import java.util.List;

import com.k9sv.domain.pojo.Profile;

public interface IVisitorManager extends IBaseManager {
	
	int saveVisitor(int type,int vid,int uid);

	List<Profile> getVisitors(int type,int vid,int start,int size);
}

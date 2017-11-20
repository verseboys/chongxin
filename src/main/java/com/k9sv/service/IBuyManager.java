package com.k9sv.service;

import java.util.List;

import com.k9sv.domain.pojo.Buy;

public interface IBuyManager extends IBaseManager {
	
	Integer getCount(String title);

	List<Buy> getBuys(String title, Integer pageNum,
			Integer numPerPage);

	Integer getCount(String title, int state);

	List<Buy> getBuys(String title, int state, Integer pageNum,
			Integer numPerPage);

	Integer getCount2(String title, int state);

	List<Buy> getBuys2(String title, int state, Integer pageNum,
			Integer numPerPage);

}

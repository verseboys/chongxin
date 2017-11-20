package com.k9sv.service;

import java.util.List;

import com.k9sv.domain.pojo.Category;

public interface ICategoryManager extends IBaseManager {

	List<Category> getCategories(int pid, int pageIndex, int pageSize);

	/**
	 * 取宠物种类
	 * 
	 * @param i
	 * @return
	 */
	List<Category> getCategorys(int pid);
}

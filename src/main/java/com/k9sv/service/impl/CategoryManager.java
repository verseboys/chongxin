package com.k9sv.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.k9sv.domain.pojo.Category;
import com.k9sv.service.ICategoryManager;
@SuppressWarnings("unchecked")
@Service("categoryManager")
public class CategoryManager extends BaseManager implements ICategoryManager {

	@Override
	public List<Category> getCategories(int pid, int pageIndex, int pageSize) {
		return super.query("from Category where pid=? order by id asc",
				new Object[] { pid }, pageIndex, pageSize);
	}

	@Override
	public List<Category> getCategorys(int pid) {
		return super.find("from Category where pid=? order by id asc",
				new Object[] { pid });
	}

}

package com.k9sv.dao.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.k9sv.dao.IAdminMenuDao;
import com.k9sv.domain.pojo.AdminMenu;

@SuppressWarnings({ "unchecked", "rawtypes" })
@Service("menuDao")
public class AdminMenuDao extends BaseDao implements IAdminMenuDao {

	@Override
	public List<AdminMenu> getMenus() {
		String hql = "from AdminMenu where state = 0 and type = 0 order by fid,createtime";
		return find(hql);
	}

	@Override
	public List<AdminMenu> getMemberMenus() {
		String hql = "from AdminMenu where state = 0 and type = 1 order by fid,createtime";
		return find(hql);
	}
}

package com.k9sv.dao;

import java.util.List;

import com.k9sv.domain.pojo.AdminMenu;


public interface IAdminMenuDao extends IBaseDao {
	
	/**
	 * 获取菜单
	 * @return
	 */
	List<AdminMenu> getMenus();
	/**
	 * 获取会员菜单
	 * @return
	 */
	List<AdminMenu> getMemberMenus();
	
}

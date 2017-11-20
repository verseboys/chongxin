package com.k9sv.service;

import java.util.List;

import com.k9sv.domain.pojo.AdminMenu;

/**
 * 菜单管理
 * 
 * @author Administrator
 * 
 */
public interface IAdminMenuManager extends IBaseManager {
	/**
	 * 获取菜单
	 * 
	 * @return
	 */
	List<AdminMenu> getMenus();

	/**
	 * 获取会员菜单
	 * 
	 * @return
	 */
	List<AdminMenu> getMemberMenus();

}

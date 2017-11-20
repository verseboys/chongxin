package com.k9sv.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.k9sv.dao.IAdminMenuDao;
import com.k9sv.domain.pojo.AdminMenu;
import com.k9sv.service.IAdminMenuManager;

/**
 * 
 * @version 1.0
 * 
 */
@Service("menuManager")
public class AdminMenuManager extends BaseManager implements IAdminMenuManager {

	@Autowired
	IAdminMenuDao menuDao;

	@Override
	public List<AdminMenu> getMenus() {
		return menuDao.getMenus();
	}

	@Override
	public List<AdminMenu> getMemberMenus() {

		return menuDao.getMemberMenus();
	}

}

package com.k9sv.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.k9sv.domain.pojo.AdminMenu;
import com.k9sv.service.IAdminMenuManager;

/**
 * 创建DWZ左侧滑动菜单
 * 
 * @author mchp
 * 
 */
public class CreateDWZMean {

	/**
	 * 
	 * @param objects
	 *            第一级菜单项 object类中要有可以判断是否有下级的标志
	 * @param role
	 *            用户角色
	 * @return 完整滑动菜单
	 */
	public String dwzMean(List<AdminMenu> objects, IAdminMenuManager menuManager) {
		List<AccordionMenu> accordionMenus = new ArrayList<AccordionMenu>();// 二级菜单及其下属的子菜单
		StringBuffer menu = new StringBuffer();// 总菜单的html代码容器
		for (int i = 0; i < objects.size(); i++) {
			// 给此菜单命名 实际应用中根据object类命名
			AdminMenu entityClass = objects.get(i);
			String name = entityClass.getName();
			// 不是一级菜单
			if (!"0".equals(entityClass.getFid())) {
				continue;
			}
			AccordionMenu accordionMenu = new AccordionMenu();// 一个滑动菜单项
			accordionMenu.setMenuName(name);
			MenuUtil menuUtil = new MenuUtil(name, 0);
			bodyInstall(menuUtil, entityClass, objects,menuManager);
			accordionMenu.setBody(menuUtil.toString());
			accordionMenus.add(accordionMenu);
		}
		for (AccordionMenu accordionMenu : accordionMenus) {
			menu.append(accordionMenu.toString());
		}
		return menu.toString();
	}

	/**
	 * 组装滑动菜单body
	 * 
	 * @param menuUtil
	 *            菜单主体
	 * @param entityClass
	 *            父菜单
	 * @param objects
	 */
	private void bodyInstall(MenuUtil menuUtil, AdminMenu entityClass,
			List<AdminMenu> objects,IAdminMenuManager menuManager) {
		String idString = entityClass.getId();
		for (int j = 0; j < objects.size(); j++) {
			AdminMenu entity = objects.get(j);
			String fid = entity.getFid();
			if (!idString.equals(fid)) {// 不是下属子菜单父id不能对应
				continue;
			}
			Map<String, Object> map = new HashMap<String, Object>(); 
			map.put("fid", entity.getId());
			Integer childs = menuManager.getTotalSize(AdminMenu.class, map);
			if (childs != null && childs.intValue() != 0) {// 还有子目录
				MenuUtil menuUtilEntity = new MenuUtil(entity.getName(), 1);
				bodyInstall(menuUtilEntity, entity, objects,menuManager);
				menuUtil.addString(menuUtilEntity.toString());
			} else {// 没有子目录
				menuUtil.createMenu(entity.getUrl(), "navTab", "rel_list_"
						+ entity.getId(), entity.getName().trim());
			}
		}

	}

}

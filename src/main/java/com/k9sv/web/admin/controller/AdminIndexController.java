package com.k9sv.web.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.AdminMenu;
import com.k9sv.domain.pojo.OnlineUser;
import com.k9sv.service.IAdminMenuManager;
import com.k9sv.service.IUserManager;
import com.k9sv.util.CookieUtil;
import com.k9sv.util.CreateDWZMean;

/**
 * 首页
 * 
 * @author mcp
 * 
 */
@Controller
@Scope("prototype")
public class AdminIndexController {
	@Autowired
	private IAdminMenuManager menuManager;
	@Autowired
	private IUserManager userManager;

	@RequestMapping("/admin/index")
	public String index(Model model, HttpSession session,
			HttpServletRequest request) {
		String sessionid = CookieUtil.getCookieValue(request, "k9admintoken");
		OnlineUser _onlineUser = userManager.getByClassId(OnlineUser.class,
				sessionid);
		if (_onlineUser == null) {
			model.addAttribute("error", "请登录");
			return "admin/login";
		}
		Account account = _onlineUser.getAccount();
		// 获取左侧菜单
		List<AdminMenu> menus = menuManager.getMenus();
		// 生成菜单html文本
		String menuStr = new CreateDWZMean().dwzMean(menus, menuManager);
		model.addAttribute("account", account);
		model.addAttribute("menuStr", menuStr);
		return "admin/admin";
	}

}

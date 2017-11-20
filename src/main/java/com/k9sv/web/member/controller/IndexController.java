package com.k9sv.web.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.k9sv.Config;
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
 * @author Administrator
 * 
 */
@Controller("memberIndexController")
@Scope("prototype")
@RequestMapping("/member/index")
public class IndexController {
	@Autowired
	private IAdminMenuManager menuManager;
	@Autowired
	private IUserManager userManager;

	@RequestMapping("")
	public String Default(Model model, HttpSession session,
			HttpServletRequest request) {
		String sessionid = CookieUtil.getCookieValue(request, "k9membertoken");
		OnlineUser _onlineUser = userManager.getByClassId(OnlineUser.class,
				sessionid);
		if (_onlineUser == null) {
			model.addAttribute("error", "请登录");
			return "member/memberlogin";
		}
		Account account = _onlineUser.getAccount();
		// 获取左侧菜单
		List<AdminMenu> menus = menuManager.getMemberMenus();
		// 生成菜单html文本
		String menuStr = new CreateDWZMean().dwzMean(menus, menuManager);
		model.addAttribute("account", account);
		model.addAttribute("menuStr", menuStr);
		return "member/member";
	}

	/**
	 * 顶部
	 * 
	 * @param model
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("/top")
	public String top(Model model, HttpSession session,
			HttpServletRequest request) {
		String sessionid = CookieUtil.getCookieValue(request, "k9membertoken");
		OnlineUser _onlineUser = userManager.getByClassId(OnlineUser.class,
				sessionid);
		Account account = _onlineUser.getAccount();
		model.addAttribute("account", account);
		model.addAttribute("PetFactoryUser", Config.PetFactoryUser);
		model.addAttribute("PetShopUser", Config.PetShopUser);
		return "member/common/top";
	}

	/**
	 * 左侧列表
	 * 
	 * @param model
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("/left")
	public String left(Model model, HttpSession session, Integer type,
			HttpServletRequest request) {
		String sessionid = CookieUtil.getCookieValue(request, "k9membertoken");
		OnlineUser _onlineUser = userManager.getByClassId(OnlineUser.class,
				sessionid);
		Account account = _onlineUser.getAccount();
		model.addAttribute("account", account);
		model.addAttribute("type", type);
		return "member/common/left";
	}

	/**
	 * 底部
	 * 
	 * @param model
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("/footer")
	public String bottom(Model model, HttpSession session,
			HttpServletRequest request) {
		return "member/common/footer";
	}

	/**
	 * 主页
	 * 
	 * @param model
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("/main")
	public String main(Model model, HttpSession session,
			HttpServletRequest request) {
		String sessionid = CookieUtil.getCookieValue(request, "k9membertoken");
		OnlineUser _onlineUser = userManager.getByClassId(OnlineUser.class,
				sessionid);
		Account account = _onlineUser.getAccount();
		model.addAttribute("account", account);
		model.addAttribute("PetFactoryUser", Config.PetFactoryUser);
		model.addAttribute("PetShopUser", Config.PetShopUser);
		return "member/index";
	}
}

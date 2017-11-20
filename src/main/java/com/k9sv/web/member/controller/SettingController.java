package com.k9sv.web.member.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.k9sv.Config;
import com.k9sv.Config2;
import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.City;
import com.k9sv.domain.pojo.OnlineUser;
import com.k9sv.service.ICityManager;
import com.k9sv.service.IUserManager;
import com.k9sv.util.CookieUtil;
import com.k9sv.util.MD5;

/**
 * 系统设置
 * 
 * @author Administrator
 * 
 */
@Controller()
@Scope("prototype")
@RequestMapping("/member/setting")
public class SettingController {

	@Autowired
	IUserManager userManager;
	@Autowired
	ICityManager cityManager;

	/**
	 * 验证手机
	 * 
	 * @param model
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("/checkmobile")
	public String checkmobile(Model model, HttpSession session,
			HttpServletRequest request) {
		String sessionid = CookieUtil.getCookieValue(request, "k9membertoken");
		model.addAttribute("sessionid", sessionid);
		return "member/setting/checkmobile";
	}

	/**
	 * 修改密码
	 * 
	 * @param model
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("/changepassword")
	public String changepassword(Model model, HttpSession session,
			HttpServletRequest request) {
		String sessionid = CookieUtil.getCookieValue(request, "k9membertoken");
		OnlineUser _onlineUser = userManager.getByClassId(OnlineUser.class,
				sessionid);
		Account account = _onlineUser.getAccount();
		model.addAttribute("sessionid", sessionid);
		model.addAttribute("account", account);
		return "member/setting/changepassword";
	}

	@RequestMapping("/changepassword/confirm")
	@ResponseBody
	public String confirm(Model model, HttpSession session, String token,
			String password, HttpServletRequest request) {
		String sessionid = CookieUtil.getCookieValue(request, "k9membertoken");
		OnlineUser _onlineUser = userManager.getByClassId(OnlineUser.class,
				sessionid);
		Account account = _onlineUser.getAccount();
		account.setPassword(MD5.md5(password.trim() + Config.pwdExtention));
		userManager.login(account, token, Config.LoginPlatformWeb);
		return "1";
	}

	/**
	 * 修改头像
	 * 
	 * @param model
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("/changeavatar")
	public String changeavatar(Model model, HttpSession session,
			HttpServletRequest request) {
		String sessionid = CookieUtil.getCookieValue(request, "k9membertoken");
		OnlineUser _onlineUser = userManager.getByClassId(OnlineUser.class,
				sessionid);
		Account account = _onlineUser.getAccount();
		model.addAttribute("localimg", Config2.LocalImgServer);
		model.addAttribute("sessionid", sessionid);
		model.addAttribute("account", account);
		return "member/setting/changeavatar";
	}

	/**
	 * 修改基本信息
	 * 
	 * @param model
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("/update")
	public String update(Model model, HttpSession session,
			HttpServletRequest request) {
		String sessionid = CookieUtil.getCookieValue(request, "k9membertoken");
		OnlineUser _onlineUser = userManager.getByClassId(OnlineUser.class,
				sessionid);
		Account account = _onlineUser.getAccount();
		List<City> fcities = cityManager.getCitysByFid(1);// 一级城市列表
		int _cityid = account.getProfile().getCityId();
		List<City> cities = new ArrayList<City>();// 二级城市列表
		if (_cityid > 1) {
			City _city = cityManager.getByClassId(City.class, _cityid);
			cities = cityManager.getCitysByFid(_city.getPid());
			model.addAttribute("_city", _city);
		} else {
			City city = fcities.get(0);
			cities = cityManager.getCitysByFid(city.getId());
		}
		model.addAttribute("sessionid", sessionid);
		model.addAttribute("account", account);
		model.addAttribute("fcities", fcities);
		model.addAttribute("cities", cities);
		return "member/setting/update";
	}

	/**
	 * 取父城市id下的所有城市
	 * 
	 * @param model
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("/update/city_{fid}")
	public String citylist(Model model, HttpSession session,
			@PathVariable("fid") Integer fid, HttpServletRequest request) {
		List<City> cities = cityManager.getCitysByFid(fid);
		model.addAttribute("cities", cities);
		return "member/setting/citylist";
	}
}

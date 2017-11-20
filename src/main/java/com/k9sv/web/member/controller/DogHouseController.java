package com.k9sv.web.member.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.k9sv.Config;
import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.Company;
import com.k9sv.domain.pojo.OnlineUser;
import com.k9sv.service.ICompanyManager;
import com.k9sv.service.IUserManager;
import com.k9sv.util.CookieUtil;
import com.k9sv.util.DateUtil;
import com.k9sv.util.QUploader;
import com.k9sv.util.StringUtil;

@Controller
@Scope("prototype")
@RequestMapping("/member/doghouse")
public class DogHouseController {
	@Autowired
	ICompanyManager companyManager;
	@Autowired
	private IUserManager userManager;

	@RequestMapping("/update")
	public String update(Model model, HttpServletRequest request) {
		String sessionid = CookieUtil.getCookieValue(request, "k9membertoken");
		OnlineUser _onlineUser = userManager.getByClassId(OnlineUser.class,
				sessionid);
		Account account = _onlineUser.getAccount();
		Company bean = companyManager.getCompany(account.getId());
		model.addAttribute("bean", bean);
		model.addAttribute("account", account);
		if (bean == null) {
			String createdtimeStr = DateUtil.getFormatDateTime(new Date(),
					"yyyy-MM-dd");
			model.addAttribute("createdtimeStr", createdtimeStr);
			model.addAttribute("type", 2);
			return "member/doghouse/add";
		}
		return "member/doghouse/update";
	}

	@RequestMapping(value = "/submit")
	public String submit(HttpServletRequest request,
			HttpServletResponse response, Company company, String createdStr) {
		String sessionid = CookieUtil.getCookieValue(request, "k9membertoken");
		OnlineUser _onlineUser = userManager.getByClassId(OnlineUser.class,
				sessionid);
		Account account = _onlineUser.getAccount();
		int uid = account.getId();
		if (StringUtil.isNotEmpty(createdStr)) {
			Date createdtime = DateUtil.parse(createdStr, null);
			company.setCreated(createdtime);
		}
		int roleid = account.getProfile().getRoleId();
		if (roleid == Config.PetFactoryUser.intValue()) {
			company.setType(1);
		} else if (roleid == Config.PetShopUser.intValue()) {
			company.setType(2);
		}
		companyManager.saveCompnany(company, uid);
		return "redirect:/member/doghouse/update";
	}

	/**
	 * ajax上传图片
	 * 
	 * @param request
	 * @param response
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/ajaxUploadPic", method = RequestMethod.POST)
	public @ResponseBody
	String ajaxUploadPic(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("file") MultipartFile file) {
		response.setContentType("text/html;charset=UTF-8");
		QUploader uploader = new QUploader(request);
		return uploader.upImagToQCloud(file);
	}

	/**
	 * 我的位置
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/baidumap")
	public String baidumap(Model model, HttpServletRequest request) {
		String sessionid = CookieUtil.getCookieValue(request, "k9membertoken");
		OnlineUser _onlineUser = userManager.getByClassId(OnlineUser.class,
				sessionid);
		Account account = _onlineUser.getAccount();
		Company bean = companyManager.getCompany(account.getId());
		if (bean == null) {
			String createdtimeStr = DateUtil.getFormatDateTime(new Date(),
					"yyyy-MM-dd");
			model.addAttribute("createdtimeStr", createdtimeStr);
			model.addAttribute("type", 2);
			return "member/doghouse/baidumap_add";
		}
		model.addAttribute("bean", bean);
		if (StringUtil.isEmpty(bean.getLatitudeStr())
				|| StringUtil.isEmpty(bean.getLongtitudeStr())) {
			return "member/doghouse/baidumap_update1";
		}
		return "member/doghouse/baidumap_update2";
	}

	@RequestMapping("/mapupdate")
	public String mapupdate(HttpServletRequest request,
			HttpServletResponse response, Company company, String createdStr) {
		if (company.getId() == 0) {
			if (StringUtil.isNotEmpty(createdStr)) {
				try {
					Date createdtime = DateUtil.parse(createdStr, null);
					company.setCreated(createdtime);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
			String sessionid = CookieUtil.getCookieValue(request,
					"k9membertoken");
			OnlineUser _onlineUser = userManager.getByClassId(OnlineUser.class,
					sessionid);
			Account account = _onlineUser.getAccount();
			Integer uid = account.getId();
			company.setName(account.getProfile().getNickName() + "的宠信店");
			int roleid = account.getProfile().getRoleId();
			if (roleid == Config.PetFactoryUser.intValue()) {
				company.setType(1);
			} else if (roleid == Config.PetShopUser.intValue()) {
				company.setType(2);
			}
			companyManager.saveCompnany(company, uid);
		} else {
			Company _company = companyManager.getByClassId(Company.class,
					company.getId());
			_company.setLatitude(company.getLatitude());
			_company.setLongtitude(company.getLongtitude());
			companyManager.saveCompnany(_company, 0);
		}
		return "redirect:/member/doghouse/baidumap";
	}
	
}

package com.k9sv.web.admin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.k9sv.Config;
import com.k9sv.domain.dto.ProfileDto;
import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.City;
import com.k9sv.domain.pojo.Company;
import com.k9sv.domain.pojo.Profile;
import com.k9sv.domain.pojo.ProfitLog;
import com.k9sv.domain.pojo.Role;
import com.k9sv.service.IBaseManager;
import com.k9sv.service.ICityManager;
import com.k9sv.service.ICompanyManager;
import com.k9sv.service.IProfitManager;
import com.k9sv.service.IUserManager;
import com.k9sv.util.ActionUtil;
import com.k9sv.util.MD5;
import com.k9sv.util.QUploader;
import com.k9sv.util.StringUtil;

/**
 * 用户管理
 * 
 * @author mcp
 * 
 */
@Controller
@Scope("prototype")
@RequestMapping("/admin/account")
public class AccountController {

	@Autowired
	IUserManager userManager;
	@Autowired
	IBaseManager baseManager;
	@Autowired
	ICompanyManager companyManager;
	@Autowired
	ICityManager cityManager;
	@Autowired
	IProfitManager profitManager;

	/**
	 * 用户列表
	 * 
	 * @param model
	 * @param userName
	 * @param nickname
	 * @param roleid
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 */
	@RequestMapping("/{roleid}")
	public String index(Model model, String userName, String nickname,
			@PathVariable("roleid") int roleid, Integer pageNum,
			Integer numPerPage) {
		if (pageNum == null) {
			pageNum = 1;
		}
		if (numPerPage == null) {
			numPerPage = 20;
		}
		Integer totalCount = userManager.getTotalBeans2(nickname, userName,
				roleid);
		List<Account> beans = userManager.getBeans2(nickname, userName, roleid,
				pageNum, numPerPage);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("numPerPage", numPerPage);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("beans", beans);
		model.addAttribute("userName", userName);
		model.addAttribute("nickname", nickname);
		model.addAttribute("roleid", roleid);
		return "admin/account/list";
	}

	/**
	 * 删除用户
	 * 
	 * @param response
	 * @param accountId
	 * @param roleid
	 */
	@RequestMapping("/delete")
	public void delete(HttpServletResponse response, Integer accountId,
			int roleid) {
		response.setContentType("text/html;charset=UTF-8");
		Account account = baseManager.getByClassId(Account.class, accountId);
		account.setDeleted(1);
		baseManager.saveOrUpdate(account);
		try {
			response.getWriter().print(
					ActionUtil.getDWZajaxReturn("200", "成功！", "", "",
							"forward", "/admin/account/" + roleid));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 设置用户身份
	 * 
	 * @param model
	 * @param accountId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/roleset")
	public String roleSet(Model model, Integer accountId, int roleid) {
		Profile profile = baseManager.getByClassId(Profile.class, accountId);
		List<Role> roles = baseManager.getObjects(Role.class);
		model.addAttribute("bean", profile);
		model.addAttribute("roles", roles);
		model.addAttribute("roleid", roleid);
		return "admin/account/roleset";
	}

	/**
	 * 身份提交修改
	 * 
	 * @param response
	 * @param accountId
	 * @param roleId
	 * @param roleid
	 */
	@RequestMapping("/submit")
	public void submit(HttpServletResponse response, Integer accountId,
			Integer roleId, int roleid) {
		response.setContentType("text/html;charset=UTF-8");
		Profile profile = baseManager.getByClassId(Profile.class, accountId);
		profile.setRoleId(roleId);
		/** 同步更新宠物店类型 **/
		if (roleId != null
				&& (roleId.intValue() == 2 || roleId.intValue() == 3)) {
			Company company = companyManager.getCompany(profile.getId());
			if (company != null) {
				if (roleId.intValue() == 2) {
					company.setType(1);
				} else if (roleId.intValue() == 3) {
					company.setType(2);
				}
				companyManager.saveCompnany(company, 0);
			}
		}
		baseManager.saveOrUpdate(profile);
		try {
			if (roleid == 0) {
				response.getWriter().print(
						ActionUtil.getDWZajaxReturn("200", "成功！",
								"rel_list_001001", "", "closeCurrent", ""));
				return;
			} else {
				String rel = "rel_list_001001";
				if (roleid == -1) {
					rel = "rel_list_001002";
				} else if (roleid == 100) {
					rel = "rel_list_001007";
				} else {
					int _tem = roleid + 2;
					rel = "rel_list_00100" + _tem;
				}
				response.getWriter().print(
						ActionUtil.getDWZajaxReturn("200", "成功！", rel, "",
								"closeCurrent", ""));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 修改用户信息
	 * 
	 * @param model
	 * @param accountId
	 * @param roleid
	 * @return
	 */
	@RequestMapping("/update")
	public String update(Model model, Integer accountId, int roleid) {
		Account account = baseManager.getByClassId(Account.class, accountId);
		ProfileDto dto = new ProfileDto(account.getProfile());

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

		model.addAttribute("bean", account);
		model.addAttribute("dto", dto);
		model.addAttribute("roleid", roleid);
		model.addAttribute("fcities", fcities);
		model.addAttribute("cities", cities);
		return "admin/account/details";
	}

	/**
	 * 确认修改用户信息
	 * 
	 * @param response
	 * @param accountId
	 * @param roleid
	 */
	@RequestMapping("/updateconfirm")
	public void updateconfirm(HttpServletResponse response, Account account,
			int roleid, Integer cityid1, Integer fcityid) {
		response.setContentType("text/html;charset=UTF-8");
		try {
			int accountid = account.getId();
			Account _account = userManager.getByClassId(Account.class,
					accountid);
			if (StringUtil.isNotEmpty(account.getProfile().getAvatar())) {
				_account.getProfile().setAvatar(
						account.getProfile().getAvatar());
			}
			if (StringUtil.isNotEmpty(account.getUsername())) {
				_account.setUsername(account.getUsername());
			}
			if (StringUtil.isNotEmpty(account.getProfile().getNickName())) {
				_account.getProfile().setNickName(
						account.getProfile().getNickName());
			}
			if (StringUtil.isNotEmpty(account.getProfile().getTrueName())) {
				_account.getProfile().setTrueName(
						account.getProfile().getTrueName());
			}
			_account.getProfile().setSex(account.getProfile().getSex());
			if (StringUtil.isNotEmpty(account.getProfile().getMobile())) {
				_account.getProfile().setMobile(
						account.getProfile().getMobile());
			}
			if (StringUtil.isNotEmpty(account.getProfile().getAddress())) {
				_account.getProfile().setAddress(
						account.getProfile().getAddress());
			}
			if (cityid1 == null || cityid1.intValue() == 0) {
				_account.getProfile().setCityId(fcityid);
			} else {
				_account.getProfile().setCityId(cityid1);
			}
			if (StringUtil.isNotEmpty(account.getPassword())) {
				_account.setPassword(MD5.md5(account.getPassword()
						+ Config.pwdExtention));
			}
			if (account.getChecked() == 1) {
				Account _temp = userManager.getCheckedAccount(account
						.getUsername().trim());
				if (_temp != null && !_temp.equals(_account)) {
					_temp.setChecked(0);
					userManager.update(_temp);
				}
			}
			_account.setChecked(account.getChecked());
			userManager.update(_account);
			response.getWriter().print(
					ActionUtil.getDWZajaxReturn("200", "成功！", "", "",
							"forward", "/admin/account/" + roleid));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 取父城市id下的所有城市
	 * 
	 * @param model
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("/city_{fid}")
	public String citylist(Model model, HttpSession session,
			@PathVariable("fid") Integer fid, HttpServletRequest request) {
		List<City> cities = cityManager.getCitysByFid(fid);
		model.addAttribute("cities", cities);
		return "admin/account/citys";
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

	@RequestMapping("/updatesubmit")
	public void updatesubmit(HttpServletResponse response, Integer accountId,
			Integer roleId, int roleid) {
		response.setContentType("text/html;charset=UTF-8");
		Profile profile = baseManager.getByClassId(Profile.class, accountId);
		profile.setRoleId(roleId);
		/** 同步更新宠物店类型 **/
		if (roleId != null
				&& (roleId.intValue() == 2 || roleId.intValue() == 3)) {
			Company company = companyManager.getCompany(profile.getId());
			if (company != null) {
				if (roleId.intValue() == 2) {
					company.setType(1);
				} else if (roleId.intValue() == 3) {
					company.setType(2);
				}
				companyManager.saveCompnany(company, 0);
			}
		}
		baseManager.saveOrUpdate(profile);
		try {
			if (roleid == 0) {
				response.getWriter().print(
						ActionUtil.getDWZajaxReturn("200", "成功！",
								"rel_list_001001", "", "closeCurrent", ""));
				return;
			} else {
				String rel = "rel_list_001001";
				if (roleid == -1) {
					rel = "rel_list_001002";
				} else if (roleid == 100) {
					rel = "rel_list_001007";
				} else {
					int _tem = roleid + 2;
					rel = "rel_list_00100" + _tem;
				}
				response.getWriter().print(
						ActionUtil.getDWZajaxReturn("200", "成功！", rel, "",
								"closeCurrent", ""));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 用户列表点进企业
	 * 
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping("/company")
	public String company(HttpServletResponse response, Integer id) {
		Company company = companyManager.getCompany(id);
		if (company == null) {
			Account account = userManager.getByClassId(Account.class, id);
			int companytype = account.getProfile().getRoleId() - 1;
			return "redirect:/admin/company/add?type=" + companytype
					+ "&accountId=" + id;
		}
		return "redirect:/admin/company/update?id=" + company.getId();
	}

	/**
	 * 设置手机号验证(未完成)
	 * 
	 * @param response
	 * @param accountId
	 */
	@RequestMapping("/checkmobile")
	public void checkmobile(HttpServletResponse response, Integer accountId) {
		response.setContentType("text/html;charset=UTF-8");
		Account account = baseManager.getByClassId(Account.class, accountId);
		account.setDeleted(1);
		baseManager.saveOrUpdate(account);
		try {
			response.getWriter().print(
					ActionUtil.getDWZajaxReturn("200", "成功！", "", "",
							"forward", "/admin/account"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 收益记录
	 * 
	 * @param model
	 * @param userName
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 */
	@RequestMapping("/profit")
	public String profit(Model model, Integer uid, Integer pageNum,
			Integer numPerPage) {
		if (pageNum == null) {
			pageNum = 1;
		}
		if (numPerPage == null) {
			numPerPage = 20;
		}

		int start = (pageNum - 1) * numPerPage;
		int size = numPerPage;
		List<ProfitLog> logs = profitManager.getProfitLogs(uid, start, size);
		Integer totalCount = profitManager.getProfitLogTotal(uid);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("numPerPage", numPerPage);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("beans", logs);
		model.addAttribute("uid", uid);

		return "admin/account/profitlist";
	}

}

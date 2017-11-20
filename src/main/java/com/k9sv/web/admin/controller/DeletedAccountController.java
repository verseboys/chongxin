package com.k9sv.web.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.k9sv.domain.dto.ProfileDto;
import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.Company;
import com.k9sv.domain.pojo.Profile;
import com.k9sv.domain.pojo.Role;
import com.k9sv.service.IBaseManager;
import com.k9sv.service.ICompanyManager;
import com.k9sv.service.IUserManager;
import com.k9sv.util.ActionUtil;

/**
 * 已删除用户管理
 * 
 * @author mcp
 * 
 */
@Controller
@Scope("prototype")
@RequestMapping("/admin/account/deleted")
public class DeletedAccountController {

	@Autowired
	IUserManager userManager;
	@Autowired
	IBaseManager baseManager;
	@Autowired
	ICompanyManager companyManager;

	@RequestMapping("")
	public String index(Model model, String userName, String nickname, Integer pageNum, Integer numPerPage) {
		if (pageNum == null) {
			pageNum = 1;
		}
		if (numPerPage == null) {
			numPerPage = 20;
		}
		Integer totalCount = userManager.getDeletedTotal(nickname, userName);
		List<Account> beans = userManager.getDeletedBeans(nickname, userName, pageNum, numPerPage);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("numPerPage", numPerPage);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("beans", beans);
		model.addAttribute("userName", userName);
		model.addAttribute("nickname", nickname);
		return "admin/account/deleted/list";
	}

	@RequestMapping("/details")
	public String update(Model model, Integer accountId) {
		Account account = baseManager.getByClassId(Account.class, accountId);
		ProfileDto dto = new ProfileDto(account.getProfile());
		// model.addAttribute("ImgServer", Config2.AvatarImgServer);
		model.addAttribute("bean", account);
		model.addAttribute("dto", dto);
		return "admin/account/deleted/details";
	}

	@RequestMapping("/delete")
	public void delete(HttpServletResponse response, Integer accountId) {
		response.setContentType("text/html;charset=UTF-8");
		Account account = baseManager.getByClassId(Account.class, accountId);
		account.setDeleted(1);
		baseManager.saveOrUpdate(account);
		try {
			response.getWriter()
					.print(ActionUtil.getDWZajaxReturn("200", "成功！", "", "", "forward", "/admin/account/deleted"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 用户身份
	 * 
	 * @param model
	 * @param accountId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/roleset")
	public String roleSet(Model model, Integer accountId) {
		Profile profile = baseManager.getByClassId(Profile.class, accountId);
		List<Role> roles = baseManager.getObjects(Role.class);
		model.addAttribute("bean", profile);
		model.addAttribute("roles", roles);
		return "admin/account/deleted/roleset";
	}

	@RequestMapping("/submit")
	public void submit(HttpServletResponse response, Integer accountId, Integer roleId) {
		response.setContentType("text/html;charset=UTF-8");
		Profile profile = baseManager.getByClassId(Profile.class, accountId);
		profile.setRoleId(roleId);
		/** 同步更新宠物店类型 **/
		if (roleId != null && (roleId.intValue() == 2 || roleId.intValue() == 3)) {
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
			response.getWriter()
					.print(ActionUtil.getDWZajaxReturn("200", "成功！", "rel_list_001008", "", "closeCurrent", ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
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
			response.getWriter().print(ActionUtil.getDWZajaxReturn("200", "成功！", "", "", "forward", "/admin/account"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

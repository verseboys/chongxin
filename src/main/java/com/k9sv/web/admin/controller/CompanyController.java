package com.k9sv.web.admin.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.Company;
import com.k9sv.domain.pojo.NoticeRecord;
import com.k9sv.domain.pojo.Profile;
import com.k9sv.service.ICompanyManager;
import com.k9sv.service.IUserManager;
import com.k9sv.util.ActionUtil;
import com.k9sv.util.DateUtil;
import com.k9sv.util.QUploader;
import com.k9sv.util.StringUtil;

/**
 * 合作伙伴管理
 * 
 * @author mcp
 * 
 */
@Controller
@Scope("prototype")
@RequestMapping("/admin/company")
public class CompanyController {
	@Autowired
	ICompanyManager companyManager;
	@Autowired
	private IUserManager userManager;

	/**
	 * 合作伙伴列表
	 * 
	 * @param model
	 * @param title
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 */
	@RequestMapping("/{type}")
	public String index(Model model, String title, Integer pageNum,
			Integer numPerPage, @PathVariable("type") Integer type) {
		// type=1犬舍，type=2宠物店
		if (pageNum == null) {
			pageNum = 1;
		}
		if (numPerPage == null) {
			numPerPage = 20;
		}
		Integer totalCount = companyManager.getCount(title, type);
		List<Company> beans = companyManager.getCompanys(title, type, pageNum,
				numPerPage);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("numPerPage", numPerPage);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("beans", beans);
		model.addAttribute("title", title);
		model.addAttribute("type", type);
		return "admin/company/list";
	}

	@RequestMapping("/add")
	public String add(Model model, Integer type, Integer accountId) {
		String createdtimeStr = DateUtil.getFormatDateTime(new Date(),
				"yyyy-MM-dd");
		model.addAttribute("createdtimeStr", createdtimeStr);
		model.addAttribute("type", type);
		Profile profile = companyManager.getByClassId(Profile.class,
				accountId);
		model.addAttribute("profile", profile);
		return "admin/company/add";
	}

	@RequestMapping("/update")
	public String update(Model model, Integer id) {
		Company bean = companyManager.getByClassId(Company.class, id);
		Profile profile = companyManager.getByClassId(Profile.class,
				bean.getId());
		model.addAttribute("bean", bean);
		model.addAttribute("profile", profile);
		return "admin/company/update";
	}

	@RequestMapping("/queryuser")
	public String queryuser(Model model, String userName, Integer pageNum,
			Integer numPerPage) {

		if (pageNum == null) {
			pageNum = 1;
		}
		if (numPerPage == null) {
			numPerPage = 20;
		}
		Integer totalCount = userManager.getTotalBeans(userName, null);
		List<Account> beans = userManager.getBeans(userName, null, pageNum,
				numPerPage);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("numPerPage", numPerPage);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("beans", beans);
		model.addAttribute("userName", userName);
		return "admin/company/queryuser";
	}

	@RequestMapping("/delete")
	public void delete(Model model, HttpServletResponse response, Integer id) {
		response.setContentType("text/html;charset=UTF-8");
		try {
			Company bean = companyManager.getByClassId(Company.class, id);
			bean.setDeleted(1);
			companyManager.saveCompnany(bean, 0);
			String rel = "rel_list_010001";
			if (bean.getType() == 2) {
				rel = "rel_list_010002";
			}
			response.getWriter().print(
					ActionUtil
							.getDWZajaxReturn("200", "删除成功！", rel, "", "", ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/submit")
	public void submit(HttpServletResponse response, Company company,
			Integer uid, String createdStr) {
		response.setContentType("text/html;charset=UTF-8");
		try {
			Company _company = companyManager.getCompany(uid);
			if (_company != null && _company.getId() != company.getId()) {
				response.getWriter().print(
						ActionUtil.getDWZajaxReturn("300", "用户已拥有店铺！", "", "",
								"", ""));
				return;
			}
			if (StringUtil.isNotEmpty(createdStr)) {
				Date createdtime = DateUtil.parse(createdStr, null);
				company.setCreated(createdtime);
			}
			companyManager.saveCompnany(company, uid);
			String rel = "rel_list_010001";
			if (company.getType() == 2) {
				rel = "rel_list_010002";
			}
			response.getWriter().print(
					ActionUtil.getDWZajaxReturn("200", "成功！", rel, "",
							"closeCurrent", ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 发通告
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/announcement")
	public String announcement(Model model, Integer id, Integer type) {
		model.addAttribute("id", id);
		model.addAttribute("type", type);
		return "admin/company/announcement";
	}

	@RequestMapping("/announcement/submit")
	public void announcement(HttpServletResponse response, Integer id,
			Integer type, String notice) {
		response.setContentType("text/html;charset=UTF-8");
		try {
			Company company = companyManager.getByClassId(Company.class, id);
			company.setNotice(notice);
			companyManager.saveCompnany(company, 0);
			NoticeRecord record = new NoticeRecord();
			record.setCompanyid(company.getId());
			record.setCreated(new Date());
			record.setDeleted(0);
			record.setNotice(notice);
			companyManager.save(record);
			String rel = "rel_list_010001";
			if (type == 2) {
				rel = "rel_list_010002";
			}
			response.getWriter().print(
					ActionUtil.getDWZajaxReturn("200", "成功！", rel, "",
							"closeCurrent", ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
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

}

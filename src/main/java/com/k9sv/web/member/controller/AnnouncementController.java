package com.k9sv.web.member.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.Company;
import com.k9sv.domain.pojo.NoticeRecord;
import com.k9sv.domain.pojo.OnlineUser;
import com.k9sv.service.IAnnouncementManager;
import com.k9sv.service.IClassifyManager;
import com.k9sv.service.ICompanyManager;
import com.k9sv.service.IUserManager;
import com.k9sv.util.CookieUtil;
import com.k9sv.util.PageUtil;

/**
 * 公告管理
 * 
 * @author mcp
 * 
 */
@Controller
@Scope("prototype")
@RequestMapping("/member/announcement")
public class AnnouncementController {
	@Autowired
	IAnnouncementManager announcementManager;
	@Autowired
	ICompanyManager companyManager;
	@Autowired
	IClassifyManager classifyManager;
	@Autowired
	IUserManager userManager;

	/**
	 * 公告列表
	 * 
	 * @param model
	 * @param title
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 */
	@RequestMapping("")
	public String index(HttpServletRequest request, Model model,
			Integer pageNum, Integer numPerPage) {
		if (pageNum == null) {
			pageNum = 1;
		}
		if (numPerPage == null) {
			numPerPage = 20;
		}
		String sessionid = CookieUtil.getCookieValue(request, "k9membertoken");
		OnlineUser _onlineUser = userManager.getByClassId(OnlineUser.class,
				sessionid);
		Account account = _onlineUser.getAccount();
		Company bean = companyManager.getCompany(account.getId());
		Integer totalCount = announcementManager.getCount(account.getId());
		List<NoticeRecord> beans = announcementManager.getNoticeRecords(
				account.getId(), pageNum, numPerPage);
		String page = PageUtil.getPagerNormal(totalCount, numPerPage, pageNum,
				"/member/announcement");
		model.addAttribute("page", page);
		model.addAttribute("beans", beans);
		model.addAttribute("check", 0);
		if (bean != null) {
			model.addAttribute("check", 1);
			model.addAttribute("companyid", bean.getId());
		}
		return "member/announcement/list";
	}

	/**
	 * 发布公告
	 * 
	 * @param model
	 * @param uid
	 * @param touid
	 * @param content
	 * @param type
	 * @return
	 */
	@RequestMapping("/add")
	public String chat(Model model, Integer companyId, String content) {
		if (companyId == null) {
			companyId = 0;
		}
		Company company = companyManager.getByClassId(Company.class, companyId);
		company.setNotice(content);
		companyManager.saveCompnany(company, 0);
		NoticeRecord record = new NoticeRecord();
		record.setCompanyid(companyId);
		record.setCreated(new Date());
		record.setDeleted(0);
		record.setNotice(content);
		companyManager.save(record);
		List<NoticeRecord> beans = announcementManager.getNoticeRecords(
				companyId, 1, 20);
		model.addAttribute("beans", beans);
		return "member/announcement/add";
	}

}

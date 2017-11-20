package com.k9sv.web.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.k9sv.domain.pojo.ProfitLog;
import com.k9sv.service.IBaseManager;
import com.k9sv.service.ICityManager;
import com.k9sv.service.ICompanyManager;
import com.k9sv.service.IProfitManager;
import com.k9sv.service.IUserManager;

/**
 * 收益管理
 * 
 * @author mcp
 * 
 */
@Controller
@Scope("prototype")
@RequestMapping("/admin/profit")
public class ProfitController {

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
	 * 收益列表
	 * 
	 * @param model
	 * @param userName
	 * @param nickname
	 * @param roleid
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 */
	@RequestMapping("/{states}")
	public String index(Model model, String nickname,
			@PathVariable("states") int states, Integer pageNum,
			Integer numPerPage) {
		if (pageNum == null) {
			pageNum = 1;
		}
		if (numPerPage == null) {
			numPerPage = 20;
		}

		List<ProfitLog> logs = profitManager.getProfitLogs2(nickname, states,
				pageNum, numPerPage);
		Integer totalCount = profitManager.getProfitLogTotal2(nickname, states);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("numPerPage", numPerPage);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("beans", logs);
		model.addAttribute("nickname", nickname);
		model.addAttribute("states", states);
		return "admin/profit/list";
	}

}

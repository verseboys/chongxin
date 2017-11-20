package com.k9sv.web.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.Classify;
import com.k9sv.domain.pojo.Company;
import com.k9sv.domain.pojo.Server;
import com.k9sv.service.IClassifyManager;
import com.k9sv.service.ICompanyManager;
import com.k9sv.service.IServerManager;
import com.k9sv.util.ActionUtil;
import com.k9sv.util.StringUtil;

/**
 * 服务管理
 * 
 * @author mcp
 * 
 */
@Controller
@Scope("prototype")
@RequestMapping("/admin/servers")
public class ServerController {
	@Autowired
	IServerManager serverManager;
	@Autowired
	ICompanyManager companyManager;
	@Autowired
	IClassifyManager classifyManager;

	/**
	 * 服务列表
	 * 
	 * @param model
	 * @param title
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 */
	@RequestMapping("/{accountId}")
	public String index(Model model, String title, Integer pageNum,
			Integer numPerPage, @PathVariable("accountId") int accountId) {
		if (pageNum == null) {
			pageNum = 1;
		}
		if (numPerPage == null) {
			numPerPage = 20;
		}
		Account account = serverManager.getByClassId(Account.class, accountId);
		Integer totalCount = serverManager.getCount2(title, account);
		List<Server> beans = serverManager.getServers2(title, accountId,
				pageNum, numPerPage);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("numPerPage", numPerPage);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("beans", beans);
		model.addAttribute("title", title);
		model.addAttribute("accountId", accountId);
		return "admin/server/list";
	}

	@RequestMapping("/add")
	public String add(Model model, int accountId) {
		List<Classify> classifies = classifyManager.getClassifys(0);// 一级分类
		Classify classify = classifies.get(0);
		List<Classify> classifies2 = classifyManager.find(classify.getId());
		model.addAttribute("classifies", classifies);
		model.addAttribute("classifies2", classifies2);
		model.addAttribute("accountId", accountId);
		return "admin/server/add";
	}

	@RequestMapping("/update")
	public String update(Model model, Integer id, int accountId) {

		Server bean = serverManager.getByClassId(Server.class, id);
		Company company = companyManager.getByClassId(Company.class,
				bean.getCompanyid());
		Classify classify = classifyManager.getByClassId(Classify.class,
				bean.getClassifyid());
		List<Classify> classifies = classifyManager.getClassifys(0);// 一级分类
		List<Classify> classifies2 = classifyManager.find(classify.getPid());
		model.addAttribute("classifies", classifies);
		model.addAttribute("classifies2", classifies2);
		model.addAttribute("company", company);
		model.addAttribute("classify", classify);
		model.addAttribute("bean", bean);
		model.addAttribute("accountId", accountId);
		return "admin/server/update";
	}

	@RequestMapping("/delete")
	public void delete(Model model, HttpServletResponse response, Integer id,
			int accountId) {
		response.setContentType("text/html;charset=UTF-8");
		try {
			Server bean = serverManager.getByClassId(Server.class, id);
			serverManager.delete(bean);
			response.getWriter().print(
					ActionUtil.getDWZajaxReturn("200", "成功！", "", "",
							"forward", "/admin/servers/" + accountId));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/submit")
	public void submit(HttpServletResponse response, Server server,
			int accountId) {
		response.setContentType("text/html;charset=UTF-8");
		try {
			boolean already = serverManager.find(server);
			if (already) {
				response.getWriter().print(
						ActionUtil.getDWZajaxReturn("300", "此服务已存在！", "", "",
								"", ""));
				return;
			}
			serverManager.saveOrUpdate(server);
			response.getWriter().print(
					ActionUtil.getDWZajaxReturn("200", "成功！", "", "",
							"forward", "/admin/servers/" + accountId));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/querycompany")
	public String querycompany(Model model, String companyName,
			Integer pageNum, Integer numPerPage) {
		if (pageNum == null) {
			pageNum = 1;
		}
		if (numPerPage == null) {
			numPerPage = 20;
		}
		if (StringUtil.isNotEmpty(companyName)) {
			model.addAttribute("companyName", companyName);
		}
		Integer totalCount = companyManager.getTotalBeans(companyName);
		List<Company> beans = companyManager.getBeans(companyName, pageNum,
				numPerPage);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("numPerPage", numPerPage);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("beans", beans);

		return "admin/server/querycompany";
	}

	@RequestMapping("/classify_{value}")
	public String classifylist(Model model, Server server,
			@PathVariable("value") Integer value) {
		List<Classify> classifies = classifyManager.find(value);
		model.addAttribute("classifies", classifies);
		return "admin/server/classifytype";
	}

}

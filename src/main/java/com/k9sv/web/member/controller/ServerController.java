package com.k9sv.web.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.Classify;
import com.k9sv.domain.pojo.Company;
import com.k9sv.domain.pojo.OnlineUser;
import com.k9sv.domain.pojo.Server;
import com.k9sv.service.IClassifyManager;
import com.k9sv.service.ICompanyManager;
import com.k9sv.service.IServerManager;
import com.k9sv.service.IUserManager;
import com.k9sv.util.CookieUtil;
import com.k9sv.util.PageUtil;

/**
 * 服务管理
 * 
 * @author mcp
 * 
 */
@Controller("ServerController")
@Scope("prototype")
@RequestMapping("/member/servers")
public class ServerController {
	@Autowired
	IServerManager serverManager;
	@Autowired
	ICompanyManager companyManager;
	@Autowired
	IClassifyManager classifyManager;
	@Autowired
	IUserManager userManager;

	/**
	 * 服务列表
	 * 
	 * @param model
	 * @param title
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 */
	@RequestMapping("")
	public String index(HttpServletRequest request, Model model, String title,
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
		Integer totalCount = serverManager.getCount(title, account);
		List<Server> beans = serverManager.getServers(title, account.getId(),
				pageNum, numPerPage);
		String page = PageUtil.getPagerNormal(totalCount, numPerPage, pageNum,
				"/member/servers");
		
		List<Classify> classifies = classifyManager.getClassifys(0);// 一级分类
		Classify classify = classifies.get(0);
		List<Classify> classifies2 = classifyManager.find(classify.getId());
		model.addAttribute("classifies", classifies);
		model.addAttribute("classifies2", classifies2);
		
		model.addAttribute("page", page);
		model.addAttribute("beans", beans);
		model.addAttribute("title", title);
		model.addAttribute("check", 0);
		if (bean != null) {
			model.addAttribute("check", 1);
		}
		return "member/server/list";
	}

	@RequestMapping("/add")
	public String add(Model model) {
		List<Classify> classifies = classifyManager.getClassifys(0);// 一级分类
		Classify classify = classifies.get(0);
		List<Classify> classifies2 = classifyManager.find(classify.getId());
		model.addAttribute("classifies", classifies);
		model.addAttribute("classifies2", classifies2);
		return "member/server/add";
	}

	@RequestMapping("/update")
	public String update(Model model, Integer id) {

		Server bean = serverManager.getByClassId(Server.class, id);
		Classify classify = classifyManager.getByClassId(Classify.class,
				bean.getClassifyid());
		List<Classify> classifies = classifyManager.getClassifys(0);// 一级分类
		List<Classify> classifies2 = classifyManager.find(classify.getPid());
		model.addAttribute("classifies", classifies);
		model.addAttribute("classifies2", classifies2);
		model.addAttribute("classify", classify);
		model.addAttribute("bean", bean);
		return "member/server/update";
	}

	@RequestMapping("/delete")
	@ResponseBody
	public String delete(Model model, HttpServletResponse response, Integer id) {
		response.setContentType("text/html;charset=UTF-8");
		Server bean = serverManager.getByClassId(Server.class, id);
		serverManager.delete(bean);
		return "1";
	}

	@RequestMapping("/submit")
	public String submit(HttpServletRequest request,
			HttpServletResponse response, Server server) {
		response.setContentType("text/html;charset=UTF-8");
		String sessionid = CookieUtil.getCookieValue(request, "k9membertoken");
		OnlineUser _onlineUser = userManager.getByClassId(OnlineUser.class,
				sessionid);
		Account account = _onlineUser.getAccount();
		Company company = companyManager.getCompany(account.getId());
		server.setCompanyid(company.getId());
		boolean already = serverManager.find(server);
		if (!already) {
			serverManager.saveOrUpdate(server);
		}
		return "redirect:/member/servers";
	}

	/**
	 * 级联下拉
	 * 
	 * @param model
	 * @param server
	 * @param value
	 * @return
	 */
	@RequestMapping("/classify_{value}")
	public String classifylist(Model model, Server server,
			@PathVariable("value") Integer value) {
		List<Classify> classifies = classifyManager.find(value);
		model.addAttribute("classifies", classifies);
		return "member/server/classifytype";
	}

}

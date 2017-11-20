package com.k9sv.web.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.k9sv.domain.pojo.Classify;
import com.k9sv.service.IClassifyManager;
import com.k9sv.util.ActionUtil;

/**
 * 记录类型管理
 * 
 * @author mcp
 * 
 */
@Controller
@Scope("prototype")
@RequestMapping("/admin/recordtype")
public class RecordTypeController {

	@Autowired
	IClassifyManager classifyManager;

	/**
	 * 记录类型列表
	 * 
	 * @param model
	 * @param title
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 */
	@RequestMapping("")
	public String index(Model model, String name, Integer pageNum,
			Integer numPerPage) {
		if (pageNum == null) {
			pageNum = 1;
		}
		if (numPerPage == null) {
			numPerPage = 20;
		}
		Integer totalCount = classifyManager.getCount(name);
		List<Classify> beans = classifyManager.getRecordTypes(name, pageNum,
				numPerPage);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("numPerPage", numPerPage);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("beans", beans);
		model.addAttribute("name", name);
		return "admin/recordtype/list";
	}

	@RequestMapping("/add")
	public String add(Model model) {
		model.addAttribute("pid", 13);
		model.addAttribute("type", 1);
		return "admin/recordtype/add";
	}

	@RequestMapping("/update")
	public String update(Model model, Integer id) {
		Classify classify = classifyManager.getByClassId(Classify.class, id);
		model.addAttribute("classify", classify);
		return "admin/recordtype/update";
	}

	@RequestMapping("/delete")
	public void delete(HttpServletResponse response, Model model, Integer id) {
		response.setContentType("text/html;charset=UTF-8");
		try {
			Classify classify = classifyManager
					.getByClassId(Classify.class, id);
			classifyManager.delete(classify);
			response.getWriter().print(
					ActionUtil.getDWZajaxReturn("200", "删除成功！",
							"rel_list_014001", "", "", ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/submit")
	public void submit(HttpServletResponse response, Classify classify,
			Integer type) {
		response.setContentType("text/html;charset=UTF-8");
		try {
			classifyManager.saveOrUpdate(classify);
			response.getWriter().print(
					ActionUtil.getDWZajaxReturn("200", "成功！",
							"rel_list_014001", "", "closeCurrent", ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

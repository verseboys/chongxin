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
 * 服务类型管理
 * 
 * @author mcp
 * 
 */
@Controller
@Scope("prototype")
@RequestMapping("/admin/classify")
public class ClassifyController {

	@Autowired
	IClassifyManager classifyManager;

	/**
	 * 服务类型列表
	 * 
	 * @param model
	 * @param title
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 */
	@RequestMapping("")
	public String index(Model model, Integer pid, Integer pageNum,
			Integer numPerPage) {
		if (pageNum == null) {
			pageNum = 1;
		}
		if (numPerPage == null) {
			numPerPage = 20;
		}
		if (pid == null) {
			pid = 0;
		}
		List<Classify> classifies = classifyManager.getClassifys(0);// 一级分类
		Integer totalCount = classifyManager.getCount(pid);
		List<Classify> beans = classifyManager.getClassifys(pid, pageNum,
				numPerPage);
		model.addAttribute("classifies", classifies);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("numPerPage", numPerPage);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("beans", beans);
		model.addAttribute("pid", pid);
		return "admin/server/classify/list";
	}

	@RequestMapping("/add")
	public String add(Model model, Integer isfirst) {
		// isfirst判断 是不是添加一级分类 1的时候添加一级分类
		if (isfirst != null && isfirst.intValue() == 0) {
			List<Classify> classifies = classifyManager.getClassifys(0);// 一级分类
			model.addAttribute("classifies", classifies);
		}
		model.addAttribute("isfirst", isfirst);
		model.addAttribute("type", 0);
		return "admin/server/classify/add";
	}

	@RequestMapping("/update")
	public String update(Model model, Integer id) {
		Classify classify = classifyManager.getByClassId(Classify.class, id);
		model.addAttribute("isfirst", 1);
		int pid = classify.getPid();
		if (pid != 0) {
			List<Classify> classifies = classifyManager.getClassifys(0);// 一级分类
			model.addAttribute("classifies", classifies);
			model.addAttribute("isfirst", 0);
		}
		model.addAttribute("classify", classify);
		return "admin/server/classify/update";
	}

	@RequestMapping("/delete")
	public void delete(HttpServletResponse response, Model model, Integer id) {
		response.setContentType("text/html;charset=UTF-8");
		try {
			Classify classify = classifyManager
					.getByClassId(Classify.class, id);
			int pid = classify.getPid();
			if (pid == 0) {
				classifyManager.deleteClassify(classify.getId());
			}
			classifyManager.delete(classify);
			response.getWriter().print(
					ActionUtil.getDWZajaxReturn("200", "删除成功！",
							"rel_list_013001", "", "", ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/submit")
	public void submit(HttpServletResponse response, Classify classify,
			Integer isfirst) {
		response.setContentType("text/html;charset=UTF-8");
		try {
			if (isfirst.intValue() == 1) {// isfirst=1是一级分类
				classify.setPid(0);
			}
			classifyManager.saveOrUpdate(classify);
			response.getWriter().print(
					ActionUtil.getDWZajaxReturn("200", "成功！",
							"rel_list_013001", "", "closeCurrent", ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

package com.k9sv.web.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.k9sv.domain.pojo.Activity;
import com.k9sv.service.IActivityManager;
import com.k9sv.service.IBaseManager;
import com.k9sv.util.ActionUtil;

/**
 * 活动管理
 * 
 * @author mcp
 * 
 */
@Controller
@Scope("prototype")
@RequestMapping("/admin/activity")
public class ActivityController {

	@Autowired
	IBaseManager baseManager;
	@Autowired
	IActivityManager activityManager;

	/**
	 * 活动列表
	 * 
	 * @param model
	 * @param title
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 */
	@RequestMapping("")
	public String index(Model model, Integer isfinished, String title,
			Integer pageNum, Integer numPerPage) {
		if (pageNum == null) {
			pageNum = 1;
		}
		if (numPerPage == null) {
			numPerPage = 20;
		}
		if (isfinished == null) {
			isfinished = 0;
		}

		Integer totalCount = activityManager.getCount(isfinished, title);
		List<Activity> beans = activityManager.getActivitys(isfinished, title,
				pageNum, numPerPage);
		model.addAttribute("isfinished", isfinished);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("numPerPage", numPerPage);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("beans", beans);
		model.addAttribute("title", title);
		return "admin/activity/list";
	}

	@RequestMapping("/add")
	public String add(Model model) {
		return "admin/activity/add";
	}

	@RequestMapping("/update")
	public String update(Model model, Integer id) {
		Activity bean = activityManager.getByClassId(Activity.class, id);
		model.addAttribute("bean", bean);
		return "admin/activity/update";
	}

	/*@RequestMapping("/delete")
	public void delete(Model model, HttpServletResponse response, Integer id) {
		response.setContentType("text/html;charset=UTF-8");
		try {
			Activity bean = activityManager.getByClassId(Activity.class, id);
			activityManager.delete(bean);
			response.getWriter().print(
					ActionUtil.getDWZajaxReturn("200", "删除成功！",
							"rel_list_015001", "", "", ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	@RequestMapping("/submit")
	public void submit(HttpServletResponse response, Activity activity) {
		response.setContentType("text/html;charset=UTF-8");
		try {
			activityManager.saveOrUpdate(activity);
			response.getWriter().print(
					ActionUtil.getDWZajaxReturn("200", "成功！",
							"rel_list_015001", "", "closeCurrent", ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

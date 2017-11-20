package com.k9sv.web.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.k9sv.domain.pojo.ActivityRecord;
import com.k9sv.service.IActivityManager;
import com.k9sv.service.IBaseManager;
import com.k9sv.util.ActionUtil;

/**
 * 活动记录管理
 * 
 * @author mcp
 * 
 */
@Controller
@Scope("prototype")
@RequestMapping("/admin/activityrecord")
public class ActivityRecordController {

	@Autowired
	IBaseManager baseManager;
	@Autowired
	IActivityManager activityManager;

	/**
	 * 活动记录列表
	 * 
	 * @param model
	 * @param title
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 */
	@RequestMapping("")
	public String index(Model model, String title, Integer pageNum,
			Integer numPerPage) {
		if (pageNum == null) {
			pageNum = 1;
		}
		if (numPerPage == null) {
			numPerPage = 20;
		}

		Integer totalCount = activityManager.getRecordCount(title);
		List<ActivityRecord> beans = activityManager.getRecords(title, pageNum,
				numPerPage);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("numPerPage", numPerPage);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("beans", beans);
		model.addAttribute("title", title);
		return "admin/activity/record/list";
	}

	/**
	 * 完成体检
	 * 
	 * @param model
	 * @param response
	 * @param id
	 */
	@RequestMapping("/update")
	public void update(Model model, HttpServletResponse response, Integer id) {
		response.setContentType("text/html;charset=UTF-8");
		try {
			ActivityRecord bean = activityManager.getByClassId(
					ActivityRecord.class, id);
			bean.setBeused(1);
			activityManager.update(bean);
			response.getWriter().print(
					ActionUtil.getDWZajaxReturn("200", "修改成功！",
							"rel_list_015002", "", "", ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

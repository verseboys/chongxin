package com.k9sv.web.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.k9sv.domain.pojo.Doctor;
import com.k9sv.service.IDoctorManager;
import com.k9sv.util.ActionUtil;

/**
 * 医师管理
 * 
 * @author mcp
 * 
 */
@Controller
@Scope("prototype")
@RequestMapping("/admin/doctor")
public class DoctorController {
	@Autowired
	IDoctorManager doctorManager;

	/**
	 * 医师列表
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
		Integer totalCount = doctorManager.getCount(title);
		List<Doctor> beans = doctorManager.getDoctors(title, pageNum,
				numPerPage);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("numPerPage", numPerPage);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("beans", beans);
		model.addAttribute("title", title);
		return "admin/doctor/list";
	}

	@RequestMapping("/add")
	public String add(Model model) {
		return "admin/doctor/add";
	}

	@RequestMapping("/update")
	public String update(Model model, Integer id) {
		Doctor bean = doctorManager.getByClassId(Doctor.class, id);
		model.addAttribute("bean", bean);
		return "admin/doctor/update";
	}

	@RequestMapping("/delete")
	public void delete(Model model, HttpServletResponse response, Integer id) {
		response.setContentType("text/html;charset=UTF-8");
		try {
			Doctor bean = doctorManager.getByClassId(Doctor.class, id);
			bean.setDeleted(1);
			doctorManager.update(bean);
			response.getWriter().print(
					ActionUtil.getDWZajaxReturn("200", "删除成功！",
							"rel_list_006001", "", "", ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/submit")
	public void submit(HttpServletResponse response, Doctor doctor) {
		response.setContentType("text/html;charset=UTF-8");
		try {
			doctorManager.saveOrUpdate(doctor);
			response.getWriter().print(
					ActionUtil.getDWZajaxReturn("200", "成功！",
							"rel_list_006001", "", "closeCurrent", ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

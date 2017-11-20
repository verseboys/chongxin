package com.k9sv.web.admin.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.k9sv.domain.pojo.Doctor;
import com.k9sv.domain.pojo.Orderoperation;
import com.k9sv.domain.pojo.Orders;
import com.k9sv.service.IDoctorManager;
import com.k9sv.service.IOrderManager;
import com.k9sv.util.ActionUtil;
import com.k9sv.util.DateUtil;
import com.k9sv.util.StringUtil;

/**
 * 预约管理
 * 
 * @author mcp
 * 
 */
@Controller
@Scope("prototype")
@RequestMapping("/admin/appointment")
public class AppointmentController {
	@Autowired
	IOrderManager orderManager;
	@Autowired
	IDoctorManager doctorManager;

	/**
	 * 预约列表
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
		Integer totalCount = orderManager.getCount(title);
		List<Orders> beans = orderManager.getOrders(title, pageNum, numPerPage);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("numPerPage", numPerPage);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("beans", beans);
		model.addAttribute("title", title);
		return "admin/appointment/list";
	}

	@RequestMapping("/update")
	public String update(Model model, String id) {
		Orders bean = orderManager.getByClassId(Orders.class, id);
		List<Doctor> beans = doctorManager.find();
		model.addAttribute("bean", bean);
		model.addAttribute("beans", beans);
		return "admin/appointment/update";
	}

	@RequestMapping("/submit")
	public void submit(HttpServletResponse response, Orders orders,
			Integer oldstate, String createdStr1) {
		response.setContentType("text/html;charset=UTF-8");
		try {
			if (orders.getState() != 0 && orders.getState() != 3
					&& 0 == orders.getDoctorId()) {
				response.getWriter().print(
						ActionUtil.getDWZajaxReturn("300", "请选择医师！",
								"rel_list_007001", "", "", ""));
				return;
			}
			if (StringUtil.isNotEmpty(createdStr1)) {
				orders.setCreated(DateUtil.parse(createdStr1, "0"));
			}
			orderManager.saveOrUpdate(orders);
			Doctor doctor = orderManager.getByClassId(Doctor.class,
					orders.getDoctorId());
			orders.setDoctor(doctor);
			if (oldstate != orders.getState()) {
				Orderoperation orderoperation = new Orderoperation();
				orderoperation.setStatefrom(oldstate);
				orderoperation.setStateto(orders.getState());
				orderoperation.setOrderid(orders.getId());
				orderoperation.setTime(new Date());
				orderManager.save(orderoperation);
				orderManager.pushOrder(orders);
			}

			response.getWriter().print(
					ActionUtil.getDWZajaxReturn("200", "成功！",
							"rel_list_007001", "", "closeCurrent", ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

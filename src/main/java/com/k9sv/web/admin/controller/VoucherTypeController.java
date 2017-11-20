package com.k9sv.web.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.k9sv.domain.pojo.VoucherType;
import com.k9sv.service.IBaseManager;
import com.k9sv.service.IVoucherManager;
import com.k9sv.util.ActionUtil;

/**
 * 代金券分类管理
 * 
 * @author mcp
 * 
 */
@Controller
@Scope("prototype")
@RequestMapping("/admin/vouchertype")
public class VoucherTypeController {

	@Autowired
	IBaseManager baseManager;
	@Autowired
	IVoucherManager voucherManager;

	/**
	 * 代金券分类列表
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

		Integer totalCount = voucherManager.getTypeCount(title);
		List<VoucherType> beans = voucherManager.getTypes(title, pageNum,
				numPerPage);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("numPerPage", numPerPage);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("beans", beans);
		model.addAttribute("title", title);
		return "admin/voucher/type/list";
	}

	@RequestMapping("/add")
	public String add(Model model) {
		return "admin/voucher/type/add";
	}

	@RequestMapping("/update")
	public String update(Model model, Integer id) {
		VoucherType bean = voucherManager.getByClassId(VoucherType.class, id);
		model.addAttribute("bean", bean);
		return "admin/voucher/type/update";
	}

	@RequestMapping("/submit")
	public void submit(HttpServletResponse response, VoucherType voucherType) {
		response.setContentType("text/html;charset=UTF-8");
		try {
			voucherManager.saveOrUpdate(voucherType);
			response.getWriter().print(
					ActionUtil.getDWZajaxReturn("200", "成功！",
							"rel_list_016001", "", "closeCurrent", ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

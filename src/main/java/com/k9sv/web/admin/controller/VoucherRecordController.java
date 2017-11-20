package com.k9sv.web.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.k9sv.domain.pojo.Voucher;
import com.k9sv.domain.pojo.VoucherType;
import com.k9sv.service.IBaseManager;
import com.k9sv.service.IVoucherManager;
import com.k9sv.util.ActionUtil;

/**
 * 代金券记录管理
 * 
 * @author mcp
 * 
 */
@Controller
@Scope("prototype")
@RequestMapping("/admin/voucher")
public class VoucherRecordController {

	@Autowired
	IBaseManager baseManager;
	@Autowired
	IVoucherManager voucherManager;

	/**
	 * 代金券记录列表
	 * 
	 * @param model
	 * @param title
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 */
	@RequestMapping("")
	public String index(Model model, String title, Integer pageNum,
			Integer type, Integer numPerPage) {
		if (pageNum == null) {
			pageNum = 1;
		}
		if (numPerPage == null) {
			numPerPage = 20;
		}
		List<VoucherType> voucherTypes = voucherManager.findType();
		if (type == null) {
			if (voucherTypes == null || voucherTypes.size() == 0) {
				type = 0;
			} else {
				VoucherType voucherType = voucherTypes.get(0);
				type = voucherType.getId();
			}
		}
		Integer totalCount = voucherManager.getCount(type, title);
		List<Voucher> beans = voucherManager.getVoucherRecords(type, title,
				pageNum, numPerPage);
		model.addAttribute("type", type);
		model.addAttribute("voucherTypes", voucherTypes);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("numPerPage", numPerPage);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("beans", beans);
		model.addAttribute("title", title);
		return "admin/voucher/list";
	}

	@RequestMapping("/update")
	public void update(Model model, HttpServletResponse response, Integer id) {
		response.setContentType("text/html;charset=UTF-8");
		try {
			Voucher bean = voucherManager.getByClassId(Voucher.class, id);
			bean.setBeused(1);
			voucherManager.update(bean);
			response.getWriter().print(
					ActionUtil.getDWZajaxReturn("200", "修改成功！",
							"rel_list_016002", "", "", ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

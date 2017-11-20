package com.k9sv.web.admin.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.OnlineUser;
import com.k9sv.domain.pojo.Warranty;
import com.k9sv.domain.pojo.WarrantyOperation;
import com.k9sv.service.IUserManager;
import com.k9sv.service.IWarrantyManager;
import com.k9sv.util.ActionUtil;
import com.k9sv.util.CookieUtil;
import com.k9sv.util.DateUtil;
import com.k9sv.util.StringUtil;

/**
 * 保单管理
 * 
 * @author mcp
 * 
 */
@Controller
@Scope("prototype")
@RequestMapping("/admin/warranty")
public class WarrantyController {
	@Autowired
	IWarrantyManager warrantyManager;
	@Autowired
	IUserManager userManager;

	/**
	 * 保单列表
	 * 
	 * @param model
	 * @param title
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 */
	@RequestMapping("/{checked}")
	public String index(Model model, String title, String title2,
			@PathVariable("checked") int checked, Integer pageNum,
			Integer numPerPage) {
		if (pageNum == null) {
			pageNum = 1;
		}
		if (numPerPage == null) {
			numPerPage = 20;
		}

		Integer totalCount = warrantyManager.getCount(title, title2, checked);
		List<Warranty> beans = warrantyManager.getWarrantys(title, title2,
				checked, pageNum, numPerPage);

		model.addAttribute("pageNum", pageNum);
		model.addAttribute("numPerPage", numPerPage);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("beans", beans);
		model.addAttribute("checked", checked);
		model.addAttribute("title", title);
		model.addAttribute("title2", title2);
		return "admin/warranty/list";
	}

	@RequestMapping("/check")
	public String check(Model model, String id, int checked) {
		Warranty warranty = warrantyManager.getByClassId(Warranty.class, id);
		Warranty latest = warrantyManager
				.getLatestWarranty(warranty.getBlood());
		if (latest != null) {
			Date date = DateUtil.getDateAfter(latest.getEndtime(), 1);
			String latestDate = DateUtil.getFormatDateTime(date, "yyyy-MM-dd");
			model.addAttribute("latestDate", latestDate);
		}
		model.addAttribute("bean", warranty);
		model.addAttribute("checked", checked);
		return "admin/warranty/checked";
	}

	/**
	 * 确认保单
	 * 
	 * @param model
	 * @param id
	 * @param checked
	 * @return
	 */
	@RequestMapping("/check/submit")
	public void submit(HttpServletRequest request,
			HttpServletResponse response, Warranty warranty,
			WarrantyOperation warrantyOperation, Integer checked1,
			String starttimeStr, String endtimeStr) {
		response.setContentType("text/html;charset=UTF-8");
		try {

			String sessionid = CookieUtil.getCookieValue(request,
					"k9admintoken");
			OnlineUser _onlineUser = userManager.getByClassId(OnlineUser.class,
					sessionid);
			Account account = _onlineUser.getAccount();
			Date date = new Date();
			Warranty _warranty = warrantyManager.getByClassId(Warranty.class,
					warrantyOperation.getWid());
			if (warrantyOperation.getMoney() > 0
					&& _warranty.getPaytime() == null) {
				_warranty.setChecked(1);
				_warranty.setPaytime(date);
			}
			_warranty.setBlood(warranty.getBlood());
			if (StringUtil.isNotEmpty(starttimeStr)) {
				_warranty.setStarttime(DateUtil.getDateObj(starttimeStr, "-"));
			}
			if (StringUtil.isNotEmpty(endtimeStr)) {
				_warranty.setEndtime(DateUtil.getDateObj(endtimeStr, "-"));
			}
			warrantyManager.update(_warranty);
			warrantyOperation.setCreated(date);
			warrantyOperation.setUid(account.getId());
			warrantyManager.save(warrantyOperation);
			int _tem = checked1 + 2;
			String rel = "rel_list_01700" + _tem;
			response.getWriter().print(
					ActionUtil.getDWZajaxReturn("200", "成功！", rel, "",
							"closeCurrent", ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 操作记录
	 * 
	 * @param model
	 * @param title
	 * @param title2
	 * @param checked
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 */
	@RequestMapping("/operation")
	public String operation(Model model, String title, Integer pageNum,
			Integer numPerPage) {
		if (pageNum == null) {
			pageNum = 1;
		}
		if (numPerPage == null) {
			numPerPage = 20;
		}

		Integer totalCount = warrantyManager.getOperationCount(title);
		List<WarrantyOperation> beans = warrantyManager.getOperations(title,
				pageNum, numPerPage);

		model.addAttribute("pageNum", pageNum);
		model.addAttribute("numPerPage", numPerPage);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("beans", beans);
		model.addAttribute("title", title);
		return "admin/warranty/operation/list";
	}

}

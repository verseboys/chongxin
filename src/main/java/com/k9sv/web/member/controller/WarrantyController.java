package com.k9sv.web.member.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.k9sv.domain.dto.DogDto;
import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.Category;
import com.k9sv.domain.pojo.Dog;
import com.k9sv.domain.pojo.OnlineUser;
import com.k9sv.domain.pojo.Warranty;
import com.k9sv.service.ICategoryManager;
import com.k9sv.service.IDogManager;
import com.k9sv.service.IUserManager;
import com.k9sv.service.IWarrantyManager;
import com.k9sv.util.CookieUtil;
import com.k9sv.util.DateUtil;
import com.k9sv.util.PageUtil;
import com.k9sv.util.StringUtil;

/**
 * 保单管理
 * 
 * @author mcp
 * 
 */
@Controller("WarrantyController")
@Scope("prototype")
@RequestMapping("/member/warranty")
public class WarrantyController {
	@Autowired
	private IWarrantyManager warrantyManager;
	@Autowired
	private IUserManager userManager;
	@Autowired
	private IDogManager dogManager;
	@Autowired
	private ICategoryManager categoryManager;

	/**
	 * 保单列表
	 * 
	 * @param model
	 * @param title
	 * @param request
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 */
	@RequestMapping("")
	public String index(Model model, String title, HttpServletRequest request,
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
		Account _account = _onlineUser.getAccount();

		Integer totalCount = warrantyManager.getCount(title, _account.getId());
		List<Warranty> beans = warrantyManager.getWarrantys(title,
				_account.getId(), pageNum, numPerPage);
		String pageUrl = "/member/warranty";
		if (StringUtil.isNotEmpty(title)) {
			pageUrl = pageUrl + "?title=" + title;
		}
		String page = PageUtil.getPagerNormal(totalCount, numPerPage, pageNum,
				pageUrl);

		model.addAttribute("page", page);
		model.addAttribute("beans", beans);
		model.addAttribute("title", title);

		return "member/warranty/list";
	}

	/**
	 * 添加保单
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/add")
	public String add(Model model, HttpServletRequest request) {
		Date date = new Date();
		String created = DateUtil.getFormatDateTime(date);
		model.addAttribute("created", created);
		return "member/warranty/add";
	}

	/**
	 * 更新保单
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/update")
	public String update(Model model, HttpServletRequest request, String id) {
		Warranty bean = warrantyManager.getByClassId(Warranty.class, id);
		model.addAttribute("bean", bean);
		return "member/warranty/update";
	}

	/**
	 * 删除保单
	 * 
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(HttpServletResponse response, String id) {
		response.setContentType("text/html;charset=UTF-8");
		Warranty bean = warrantyManager.getByClassId(Warranty.class, id);
		bean.setDeleted(1);
		warrantyManager.saveOrUpdate(bean);
		return "1";
	}

	/**
	 * 提交修改
	 * 
	 * @param model
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/submit")
	public String submit(Model model, HttpServletRequest request,
			Warranty warranty, String createdStr) {
		Warranty _warranty = warranty;
		if (StringUtil.isNotEmpty(warranty.getId())) {// 更新
			_warranty = warrantyManager.getByClassId(Warranty.class,
					warranty.getId());
		} else {
			// 生成订单号
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
			Random random = new Random();
			int rand = random.nextInt(9000) + 1000;
			String id = sdf.format(new Date()) + rand;
			_warranty.setId(id);
		}
		String sessionid = CookieUtil.getCookieValue(request, "k9membertoken");
		OnlineUser _onlineUser = userManager.getByClassId(OnlineUser.class,
				sessionid);
		Account _account = _onlineUser.getAccount();
		_warranty.setUid(_account.getId());
		_warranty.setBlood(warranty.getBlood());
		_warranty.setPrice(warranty.getPrice());
		if (StringUtil.isNotEmpty(createdStr)) {
			_warranty.setCreated(DateUtil.parse(createdStr, null));
		}
		warrantyManager.saveOrUpdate(_warranty);
		return "redirect:/member/warranty";
	}

	@ResponseBody
	@RequestMapping("/querydog")
	public String querydog(String blood) {
		int size = dogManager.getDogByBlood(blood);
		return size + "";
	}

	@RequestMapping("/dogdetails")
	public String dogdetails(Model model, String blood) {

		Dog dog = dogManager.getDog(blood);
		DogDto dto = new DogDto(dog);
		List<Category> categories = categoryManager.getCategorys(1);
		model.addAttribute("beans", categories);
		model.addAttribute("bean", dog);
		model.addAttribute("dto", dto);
		return "member/warranty/dogdetails";

	}

}

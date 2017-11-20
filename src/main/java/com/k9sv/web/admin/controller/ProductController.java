package com.k9sv.web.admin.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.k9sv.domain.pojo.Product;
import com.k9sv.service.IProductManager;
import com.k9sv.util.ActionUtil;
import com.k9sv.util.DateUtil;
import com.k9sv.util.QUploader;
import com.k9sv.util.StringUtil;

/**
 * 产品管理
 * 
 * @author mcp
 * 
 */
@Controller
@Scope("prototype")
@RequestMapping("/admin/product")
public class ProductController {

	@Autowired
	IProductManager productManager;

	/**
	 * 产品列表
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
		Integer totalCount = productManager.getCount(title);
		List<Product> beans = productManager.getProducts(title, pageNum,
				numPerPage);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("numPerPage", numPerPage);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("beans", beans);
		model.addAttribute("title", title);
		return "admin/product/list";
	}

	@RequestMapping("/add")
	public String add(Model model) {
		Date date = new Date();
		model.addAttribute("publish",
				DateUtil.getFormatDateTime(date, "yyyy-MM-dd"));
		return "admin/product/add";
	}

	@RequestMapping("/update")
	public String update(Model model, Integer id) {
		Product bean = productManager.getByClassId(Product.class, id);
		model.addAttribute("bean", bean);
		return "admin/product/update";
	}

	/**
	 * 产品下线
	 * 
	 * @param model
	 * @param response
	 * @param id
	 */
	@RequestMapping("/delete")
	public void delete(Model model, HttpServletResponse response, Integer id) {
		response.setContentType("text/html;charset=UTF-8");
		try {
			Product bean = productManager.getByClassId(Product.class, id);
			bean.setDeleted(1);
			productManager.update(bean);
			response.getWriter().print(
					ActionUtil.getDWZajaxReturn("200", "删除成功！",
							"rel_list_008001", "", "", ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 提交上线或修改产品
	 * 
	 * @param response
	 * @param doctor
	 */
	@RequestMapping("/submit")
	public void submit(HttpServletResponse response, Product product,
			String publish) {
		response.setContentType("text/html;charset=UTF-8");
		try {
			if (StringUtil.isNotEmpty(publish)) {
				product.setCreatedtime(DateUtil.getDateObj(publish, "-"));
			}
			productManager.saveOrUpdate(product);
			response.getWriter().print(
					ActionUtil.getDWZajaxReturn("200", "成功！",
							"rel_list_008001", "", "closeCurrent", ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ajax上传图片
	 * 
	 * @param request
	 * @param response
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/ajaxUploadPic", method = RequestMethod.POST)
	public @ResponseBody
	String ajaxUploadPic(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("file") MultipartFile file) {
		response.setContentType("text/html;charset=UTF-8");
		QUploader uploader = new QUploader(request);
		return uploader.mallProductImg(file);
	}

	@RequestMapping(value = "/ajaxUploadSmallPic", method = RequestMethod.POST)
	public @ResponseBody
	String ajaxUploadSmallPic(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("file2") MultipartFile file) {
		response.setContentType("text/html;charset=UTF-8");
		QUploader uploader = new QUploader(request);
		return uploader.mallProductSmallImg(file);
	}
}

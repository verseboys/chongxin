package com.k9sv.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.k9sv.domain.pojo.Product;
import com.k9sv.service.IBaseManager;

@Controller("ProductController")
@Scope("prototype")
@RequestMapping("/product")
public class ProductController {

	@Autowired
	IBaseManager baseManager;

	/**
	 * 产品详情展示
	 * 
	 * @param model
	 * @param domain
	 * @param productid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/{domain}_{productid}")
	public String oldIndex(Model model, @PathVariable("domain") String domain,
			@PathVariable("productid") Integer productid) throws Exception {
		Product product = baseManager.getByClassId(Product.class, productid);
		model.addAttribute("product", product);
		return "h5/product/" + domain;
	}

	/**
	 * 产品详情展示
	 * 
	 * @param model
	 * @param domain
	 * @param productid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/{domain}/{productid}")
	public String index(Model model, @PathVariable("domain") String domain,
			@PathVariable("productid") Integer productid) throws Exception {
		Product product = baseManager.getByClassId(Product.class, productid);
		model.addAttribute("product", product);
		return "h5/product/" + domain;
	}

}

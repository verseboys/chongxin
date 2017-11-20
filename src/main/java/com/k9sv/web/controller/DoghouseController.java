package com.k9sv.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.k9sv.domain.pojo.Company;
import com.k9sv.service.ICompanyManager;

@Controller()
@Scope("prototype")
@RequestMapping("/doghouse")
public class DoghouseController {

	@Autowired
	private ICompanyManager companyManager;

	@RequestMapping("/infor_{id}")
	public String infor(Model model, @PathVariable("id") Integer id)
			throws Exception {
		Company company = companyManager.getByClassId(Company.class, id);
		model.addAttribute("company", company);
		return "doghouse";
	}
	
	@RequestMapping("/contactway_{id}")
	public String contactway(Model model, @PathVariable("id") Integer id)
			throws Exception {
		Company company = companyManager.getByClassId(Company.class, id);
		model.addAttribute("company", company);
		return "contactway";
	}
}

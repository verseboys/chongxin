package com.k9sv.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.k9sv.domain.pojo.Profile;
import com.k9sv.service.IUserManager;

/**
 * 分享推荐
 * 
 * @author mcp
 * 
 */
@Controller("RecommendController")
@Scope("prototype")
public class RecommendController {

	@Autowired
	private IUserManager userManager;

	/**
	 * 推荐分享
	 * 
	 * @param model
	 * @param uid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/web/register/{uid}")
	public String share(Model model, @PathVariable("uid") Integer uid)
			throws Exception {
		Profile bean = userManager.getByClassId(Profile.class, uid);
		model.addAttribute("bean", bean);
		return "h5/recommend";
	}
}

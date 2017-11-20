package com.k9sv.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.k9sv.Config2;
import com.k9sv.domain.pojo.ToutiaoPublish;
import com.k9sv.domain.pojo.WeixinShare;
import com.k9sv.service.IBaseManager;
import com.k9sv.service.ITouTiaoManager;
import com.k9sv.util.WeixinUtil;

/**
 * 头条
 * 
 * @author Administrator
 * 
 */
@Controller
@Scope("prototype")
@RequestMapping("/toutiao")
public class ToutiaoController {

	@Autowired
	IBaseManager baseManager;
	@Autowired
	ITouTiaoManager touTiaoManager;

	/**
	 * 分享
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/{tid}")
	public String share(HttpServletRequest request, Model model,
			@PathVariable Integer tid) {
		StringBuffer url = request.getRequestURL();
		WeixinUtil signUtil = new WeixinUtil();
		WeixinShare weixinShare = signUtil.sign(url.toString());
		ToutiaoPublish toutiaoPublish = touTiaoManager.getByClassId(
				ToutiaoPublish.class, tid);
		model.addAttribute("weixinShare", weixinShare);
		model.addAttribute("id", tid);
		if (toutiaoPublish == null) {
			return "error";
		}
		model.addAttribute("viewCount", toutiaoPublish.getViewCount());
		model.addAttribute("bean", toutiaoPublish);
		model.addAttribute("ImgServer", Config2.ImgServer);
		toutiaoPublish.setViewCount(toutiaoPublish.getViewCount() + 1);
		touTiaoManager.update(toutiaoPublish);
		return "h5/toutiao";
	}
}

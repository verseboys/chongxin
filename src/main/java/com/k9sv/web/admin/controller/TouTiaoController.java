package com.k9sv.web.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.k9sv.domain.pojo.ToutiaoPublish;
import com.k9sv.service.IBaseManager;
import com.k9sv.service.ITouTiaoManager;
import com.k9sv.util.ActionUtil;
import com.k9sv.util.Constants;
import com.k9sv.util.SortColumn;
import com.k9sv.util.StringUtil;

/**
 * 头条管理
 * 
 * @author mcp
 * 
 */
@Controller
@Scope("prototype")
@RequestMapping("/admin/toutiao")
public class TouTiaoController {

	@Autowired
	IBaseManager baseManager;
	@Autowired
	ITouTiaoManager touTiaoManager;

	/**
	 * 头条列表
	 * 
	 * @param model
	 * @param title
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("")
	public String index(Model model, String title, Integer pageNum, Integer numPerPage) {
		if (pageNum == null) {
			pageNum = 1;
		}
		if (numPerPage == null) {
			numPerPage = 20;
		}
		Map<String, Object> searchMap = new HashMap<String, Object>();
		Map<String, Object> searchClassMap = new HashMap<String, Object>();
		searchClassMap.put("sucai.deleted", 0);
		if (StringUtil.isNotEmpty(title)) {
			searchClassMap.put("sucai.title", Constants.LIKE + title);
			model.addAttribute("title", title);
		}
		List<SortColumn> scs = new ArrayList<SortColumn>();
		scs.add(new SortColumn("published", "desc"));
		Integer totalCount = baseManager.getPaginationObjects(ToutiaoPublish.class, searchMap, searchClassMap);
		List<ToutiaoPublish> beans = baseManager.getPaginationObjects(ToutiaoPublish.class, pageNum, numPerPage,
				searchMap, searchClassMap, scs);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("numPerPage", numPerPage);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("beans", beans);
		return "admin/toutiao/list";
	}

	/**
	 * 删除头条
	 * 
	 * @param model
	 * @param response
	 * @param id
	 */
	@RequestMapping("/delete")
	public void delete(Model model, HttpServletResponse response, Integer id) {
		response.setContentType("text/html;charset=UTF-8");
		ToutiaoPublish toutiaoPublish = touTiaoManager.getByClassId(ToutiaoPublish.class, id);
		touTiaoManager.delete(toutiaoPublish);
		try {
			response.getWriter().print(ActionUtil.getDWZajaxReturn("200", "删除成功！", "rel_list_002002", "", "", ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

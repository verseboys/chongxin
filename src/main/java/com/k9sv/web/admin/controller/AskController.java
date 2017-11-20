package com.k9sv.web.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.k9sv.Config2;
import com.k9sv.domain.pojo.Answer;
import com.k9sv.domain.pojo.Ask;
import com.k9sv.service.IAskManager;
import com.k9sv.service.IBaseManager;
import com.k9sv.util.ActionUtil;

/**
 * 问答管理
 * 
 * @author mcp
 * 
 */
@Controller
@Scope("prototype")
@RequestMapping("/admin/ask")
public class AskController {

	@Autowired
	IBaseManager baseManager;
	@Autowired
	IAskManager askManager;

	/**
	 * 问题列表
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

		Integer totalCount = askManager.getCount(title);
		List<Ask> beans = askManager.getAsks(title, pageNum, numPerPage);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("numPerPage", numPerPage);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("beans", beans);
		model.addAttribute("title", title);
		return "admin/ask/list";
	}

	/**
	 * 审查问题是否违反相关规定
	 * 
	 * @return
	 */
	@RequestMapping("/shenchaask")
	public String shenChaAsk(Model model, Integer id) {
		Ask ask = baseManager.getByClassId(Ask.class, id);
		model.addAttribute("ImgServer", Config2.ImgServer);
		model.addAttribute("bean", ask);
		return "admin/ask/shenchaask";
	}

	/**
	 * 删除违规问题
	 * 
	 * @param response
	 * @param id
	 */
	@RequestMapping("/deleteask")
	public void deleteAsk(HttpServletResponse response, Integer id) {
		response.setContentType("text/html;charset=UTF-8");
		// feedManager.deleteFeed(id);
		Ask ask = askManager.getByClassId(Ask.class, id);
		ask.setDeleted(1);
		askManager.saveOrUpdate(ask);
		try {
			response.getWriter().print(
					ActionUtil.getDWZajaxReturn("200", "删除成功！",
							"rel_list_004001", "", "closeCurrent", ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查看回答
	 * 
	 * @param model
	 * @param title
	 * @param pageNum
	 * @param numPerPage
	 * @param id
	 * @return
	 */
	@RequestMapping("/answerlist")
	public String answerList(Model model, String title, Integer pageNum,
			Integer numPerPage, Integer id) {
		if (pageNum == null) {
			pageNum = 1;
		}
		if (numPerPage == null) {
			numPerPage = 20;
		}
		Integer totalCount = askManager.getCount(title, id);
		List<Answer> beans = askManager.getAnswers(title, id, pageNum,
				numPerPage);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("numPerPage", numPerPage);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("beans", beans);
		model.addAttribute("title", title);
		model.addAttribute("fid", id);
		return "admin/ask/answerlist";
	}

	/**
	 * 审查回答是否违反相关规定
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/shenchaanswer")
	public String shenChaAnswer(Model model, Integer id) {
		Answer answer = baseManager.getByClassId(Answer.class, id);
		model.addAttribute("ImgServer", Config2.ImgServer);
		model.addAttribute("bean", answer);
		return "admin/ask/shenchaanswer";
	}

	/**
	 * 删除违规回答
	 * 
	 * @param response
	 * @param id
	 */
	@RequestMapping("/deleteanswer")
	public void deleteAnswer(HttpServletResponse response, Integer id) {
		response.setContentType("text/html;charset=UTF-8");
		Answer answer = askManager.getByClassId(Answer.class, id.intValue());
		answer.setDeleted(1);
		Ask ask = askManager.getByClassId(Ask.class, answer.getAskId());
		ask.setTotal(ask.getTotal() - 1);
		askManager.saveOrUpdate(answer);
		askManager.saveOrUpdate(ask);
		try {
			response.getWriter().print(
					ActionUtil.getDWZajaxReturn("200", "删除成功！", "", "",
							"forward",
							"/admin/ask/answerlist?id=" + answer.getAskId()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

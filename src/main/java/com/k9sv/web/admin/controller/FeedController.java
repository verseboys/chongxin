package com.k9sv.web.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.k9sv.domain.dto.FeedDto;
import com.k9sv.domain.pojo.Feed;
import com.k9sv.domain.pojo.FeedComment;
import com.k9sv.domain.pojo.Record;
import com.k9sv.service.IBaseManager;
import com.k9sv.service.IFeedManager;
import com.k9sv.service.IRecordManager;
import com.k9sv.util.ActionUtil;

/**
 * 动态管理
 * 
 * @author mcp
 * 
 */
@Controller
@Scope("prototype")
@RequestMapping("/admin/feed")
public class FeedController {

	@Autowired
	IBaseManager baseManager;
	@Autowired
	IFeedManager feedManager;
	@Autowired
	IRecordManager recordManager;

	/**
	 * 动态列表
	 * 
	 * @param model
	 * @param title
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 */
	@RequestMapping("/{accountId}")
	public String index(Model model, String title, Integer pageNum,
			Integer numPerPage, @PathVariable("accountId") int accountId) {
		if (pageNum == null) {
			pageNum = 1;
		}
		if (numPerPage == null) {
			numPerPage = 20;
		}
		Integer totalCount = feedManager.getCount2(accountId, title);
		List<Feed> beans = feedManager.getFeeds2(accountId, title, pageNum,
				numPerPage);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("numPerPage", numPerPage);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("beans", beans);
		model.addAttribute("title", title);
		model.addAttribute("accountId", accountId);
		return "admin/feed/list";
	}

	/**
	 * 审查动态是否违反相关规定
	 * 
	 * @return
	 */
	@RequestMapping("/shenchafeed")
	public String shenChaFeed(Model model, Integer id, int accountId) {
		Feed feed = baseManager.getByClassId(Feed.class, id);
		FeedDto dto = new FeedDto(feed);
		model.addAttribute("dto", dto);
		model.addAttribute("bean", feed);
		model.addAttribute("accountId", accountId);
		return "admin/feed/shenchafeed";
	}

	/**
	 * 删除违规动态
	 * 
	 * @param response
	 * @param id
	 */
	@RequestMapping("/deletefeed")
	public void deleteFeed(HttpServletResponse response, Integer id,
			int accountId) {
		response.setContentType("text/html;charset=UTF-8");
		Feed feed = feedManager.getByClassId(Feed.class, id);
		Record record = recordManager.getRecordByFid(feed.getId());
		if (record != null) {
			record.setDeleted(1);
			recordManager.update(record);
		}
		feed.setDeleted(1);
		feedManager.saveOrUpdate(feed);
		try {
			response.getWriter().print(
					ActionUtil.getDWZajaxReturn("200", "成功！", "", "",
							"forward", "/admin/feed/" + accountId));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查看评论
	 * 
	 * @param model
	 * @param title
	 * @param pageNum
	 * @param numPerPage
	 * @param id
	 * @return
	 */
	@RequestMapping("/commentlist")
	public String commentList(Model model, String title, Integer pageNum,
			Integer numPerPage, Integer id) {
		if (pageNum == null) {
			pageNum = 1;
		}
		if (numPerPage == null) {
			numPerPage = 20;
		}
		Integer totalCount = feedManager.getCount(title, id);
		List<FeedComment> beans = feedManager.getFeedComments(title, id,
				pageNum, numPerPage);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("numPerPage", numPerPage);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("beans", beans);
		model.addAttribute("title", title);
		model.addAttribute("fid", id);
		return "admin/feed/commentlist";
	}

	/**
	 * 审查评论是否违反相关规定
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/shenchacomment")
	public String shenChaFeedComment(Model model, Integer id) {
		FeedComment feedComment = baseManager.getByClassId(FeedComment.class,
				id);
		model.addAttribute("bean", feedComment);
		return "admin/feed/shenchacomment";
	}

	/**
	 * 删除违规评论
	 * 
	 * @param response
	 * @param id
	 */
	@RequestMapping("/deletecomment")
	public void deleteFeedComment(HttpServletResponse response, Integer id) {
		response.setContentType("text/html;charset=UTF-8");
		FeedComment comment = feedManager.getByClassId(FeedComment.class,
				id.intValue());
		feedManager.deleteFeedComment(id);
		Feed feed = feedManager.getByClassId(Feed.class, comment.getFid());
		feed.setReplyCount(feed.getReplyCount() - 1);
		feedManager.saveOrUpdate(feed);
		try {
			response.getWriter().print(
					ActionUtil.getDWZajaxReturn("200", "删除成功！", "", "",
							"forward",
							"/admin/feed/commentlist?id=" + comment.getFid()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

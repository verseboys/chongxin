package com.k9sv.web.member.controller;

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

import com.k9sv.domain.pojo.Message;
import com.k9sv.domain.pojo.Profile;
import com.k9sv.service.ICompanyManager;
import com.k9sv.service.IMessageManager;
import com.k9sv.util.PageUtil;
import com.k9sv.util.QUploader;

@Controller("MessageController")
@Scope("prototype")
@RequestMapping("/member/message")
public class MessageController {
	@Autowired
	ICompanyManager companyManager;
	@Autowired
	private IMessageManager messageManager;

	/**
	 * 聊天列表
	 * 
	 * @param model
	 * @param uid
	 * @param request
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 */
	@RequestMapping("")
	public String index(Model model, Integer uid, HttpServletRequest request,
			Integer pageNum, Integer numPerPage) {
		if (pageNum == null) {
			pageNum = 1;
		}
		if (numPerPage == null) {
			numPerPage = 20;
		}
		if (uid == null) {
			uid = 0;
		}
		List<Message> beans = messageManager.getMessages(uid, pageNum,
				numPerPage);
		Integer count = messageManager.getCount(uid);
		String page = PageUtil.getPagerNormal(count, numPerPage, pageNum,
				"/member/message?uid=" + uid);
		model.addAttribute("page", page);
		model.addAttribute("beans", beans);
		model.addAttribute("uid", uid);

		return "member/message/list";
	}

	/**
	 * 与某人的聊天记录
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/details")
	public String details(Model model, Integer uid, Integer touid) {
		if (uid == null) {
			uid = 0;
		}
		if (touid == null) {
			touid = 0;
		}
		List<Message> beans = messageManager.getMessageDetails(uid, touid, 0,
				20);
		model.addAttribute("beans", beans);
		model.addAttribute("uid", uid);
		model.addAttribute("touid", touid);
		return "member/message/details";
	}

	/**
	 * 聊天
	 * 
	 * @param model
	 * @param uid
	 * @param touid
	 * @return
	 */
	@RequestMapping("/chat")
	public String chat(Model model, Integer uid, Integer touid, String content,
			Integer type) {
		if (uid == null) {
			uid = 0;
		}
		if (touid == null) {
			touid = 0;
		}
		if (type == null) {
			type = 0;
		}
		Message message = new Message();

		message.setFromUid(uid);
		message.setToUid(touid);
		message.setContent(content);
		message.setStatus(0);
		message.setCreated(new Date());
		message.setType(type);
		Profile profile = messageManager.getByClassId(Profile.class, uid);
		message.setProfile(profile);
		messageManager.saveMessage(message);
		List<Message> beans = messageManager.getMessageDetails(uid, touid, 0,
				20);
		model.addAttribute("beans", beans);
		model.addAttribute("uid", uid);
		model.addAttribute("touid", touid);
		return "member/message/chat";
	}

	/**
	 * 发送图片
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
		return uploader.imImg(file);
	}

	/**
	 * 查看大图
	 * 
	 * @param model
	 * @param uid
	 * @param touid
	 * @return
	 */
	@RequestMapping("/bigimg")
	public String bigimg(Model model, Integer uid, Integer touid, String img_url) {
		if (uid == null) {
			uid = 0;
		}
		if (touid == null) {
			touid = 0;
		}
		model.addAttribute("uid", uid);
		model.addAttribute("touid", touid);
		model.addAttribute("img_url", img_url);
		return "member/message/bigimg";
	}

	/**
	 * 查看聊天详情
	 * 
	 * @param model
	 * @param uid
	 * @param touid
	 * @param img_url
	 * @return
	 */
	@RequestMapping("/chatcontent")
	public String more(Model model, Integer uid, Integer touid, Integer id) {
		if (uid == null) {
			uid = 0;
		}
		if (touid == null) {
			touid = 0;
		}
		Message message = messageManager.getByClassId(Message.class, id);
		model.addAttribute("uid", uid);
		model.addAttribute("touid", touid);
		model.addAttribute("message", message);
		return "member/message/chatcontent";
	}

}

package com.k9sv.server.controller;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.k9sv.domain.dto.ResDto;
import com.k9sv.domain.pojo.Feedback;
import com.k9sv.service.IUserManager;
import com.k9sv.util.JsonUtil;

@Controller
@Scope("prototype")
@RequestMapping("/server/feedback")
public class ServerFeedbackController {

	@Autowired
	private IUserManager userManager;

	/**
	 * 头条列表
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody String save(HttpServletRequest request, String json, String sid, Model model) {
		ResDto res = new ResDto();
		try {
			JSONObject jsonD = JsonUtil.getJson(request, json);
			Integer type = JsonUtil.getInt(jsonD, "type");
			int relationId = JsonUtil.getInt(jsonD, "reationid");
			String content = JsonUtil.getString(jsonD, "content");
			Feedback feedback = new Feedback();
			feedback.setContent(content);
			feedback.setType(type);
			feedback.setRelationId(relationId);
			feedback.setContent(content);
			userManager.save(feedback);
			res.setState(0);
			return res.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

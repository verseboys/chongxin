package com.k9sv.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.k9sv.cache.Kefu;
import com.k9sv.domain.dto.MessageDto;
import com.k9sv.domain.dto.ResDto;
import com.k9sv.domain.pojo.Message;
import com.k9sv.service.IMessageManager;
import com.k9sv.util.JsonUtil;

@Controller
@Scope("prototype")
@RequestMapping("/kefu/")
public class KefuController {

	@Autowired
	private IMessageManager messageManager;

	@RequestMapping("")
	public String kefu(HttpServletRequest request, HttpServletResponse response, String pppp, Model model)
			throws Exception {
		model.addAttribute("msgs", Kefu.Messages);
		model.addAttribute("users", Kefu.IMUsers);
		return "kefu/index";
	}

	@RequestMapping(value = "/msgs", method = RequestMethod.POST)
	public @ResponseBody String msgs(HttpServletRequest request, String json, String sid, Model model) {
		ResDto res = new ResDto();
		try {
			JSONObject jsonD = JsonUtil.getJson(request, json);
			Integer uid = JsonUtil.getInt(jsonD, "uid");
			List<Message> _list = messageManager.getIM(1, uid, 1, 20);
			List<MessageDto> msgDtos = new ArrayList<MessageDto>();
			for (Message msg : _list) {
				MessageDto msgDto = new MessageDto(msg);
				msgDtos.add(msgDto);
			}
			res.setState(0);
			res.setData(msgDtos);
			return res.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

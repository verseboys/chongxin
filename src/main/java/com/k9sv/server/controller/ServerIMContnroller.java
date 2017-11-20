package com.k9sv.server.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.k9sv.Config;
import com.k9sv.Config2;
import com.k9sv.domain.dto.MessageDto;
import com.k9sv.domain.dto.ResDto;
import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.Message;
import com.k9sv.service.IMessageManager;
import com.k9sv.service.IUserManager;
import com.k9sv.util.JsonUtil;

@Controller
@Scope("prototype")
@RequestMapping(value = "/server/im")
public class ServerIMContnroller {
	@Autowired
	private IUserManager userManager;
	@Autowired
	private IMessageManager messageManager;

	/**
	 * 聊天
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public String index(HttpServletRequest request, String json, String sid,
			String clientId, Model model) throws Exception {
		ResDto res = new ResDto();
		try {
			// 用户
			Account _account = userManager.getOnlineUser(sid);
			// 请求数据
			JSONObject jsonD = JsonUtil.getJson(request, json);
			String content = JsonUtil.getString(jsonD, "content");
			int type = JsonUtil.getInt(jsonD, "type");
			int touid = JsonUtil.getInt(jsonD, "touid");
			if (type == Config.ContentTypeImg) {
				content = Config2.LocalImgServer + content;
			}
			Message message = new Message();
			message.setFromUid(_account.getId());
			message.setToUid(touid);
			message.setType(type);
			message.setContent(content);
			message.setStatus(0);
			message.setCreated(new Date());
			message.setProfile(_account.getProfile());
			messageManager.saveMessage(message);
			MessageDto msgDto = new MessageDto(message);
			res.setState(0);
			res.setData(msgDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res.toString();
	}
	
	@ResponseBody
	@RequestMapping(value = "/load", method = RequestMethod.POST)
	public String load(HttpServletRequest request, String json, String sid,
			String clientId, Model model) throws Exception {
		ResDto res = new ResDto();
		try {
			// 用户
			Account _account = userManager.getOnlineUser(sid);
			// 请求数据
			JSONObject jsonD = JsonUtil.getJson(request, json);
			int maxid = JsonUtil.getInt(jsonD, "maxid");
			List<Message> _list = messageManager.getMessage(_account.getId(), maxid, 30);
			List<MessageDto> dtos = new ArrayList<MessageDto>();
			if(_list != null && _list.size() > 0){
				for(Message msg : _list){
					MessageDto msgDto = new MessageDto(msg);
					dtos.add(msgDto);
				}
			}
			res.setState(0);
			res.setData(dtos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res.toString();
	}
}

package com.k9sv.server.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.k9sv.domain.dto.ResDto;
import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.ActivityRecord;
import com.k9sv.service.IActivityManager;
import com.k9sv.service.IUserManager;
import com.k9sv.util.JsonUtil;

/**
 * 活动记录
 * 
 * @author mcp
 * 
 */
@Controller
@Scope("prototype")
@RequestMapping("/server/activity")
public class ServerActivityController {

	@Autowired
	IUserManager userManager;
	@Autowired
	private IActivityManager activityManager;

	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(HttpServletRequest request, String json, String sid) {
		ResDto res = new ResDto();
		try {
			Account _account = userManager.getOnlineUser(sid);
			JSONObject jsonD = JsonUtil.getJson(request, json);
			String mobile = JsonUtil.getString(jsonD, "mobile");
			int aid = JsonUtil.getInt(jsonD, "aid");
			ActivityRecord _record = activityManager.getActivityRecord(
					_account.getId(), 1);
			if (_record == null) {
				_record = new ActivityRecord();
				_record.setUid(_account.getId());
				_record.setAid(aid);
				_record.setMobile(mobile);
				_record.setCreated(new Date());
				activityManager.save(_record);
				Account _a = userManager.getAccount(mobile);
				if (_a == null) {
					_account.setUsername(mobile);
					userManager.update(_account);
				}
			}
			res.setState(0);
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

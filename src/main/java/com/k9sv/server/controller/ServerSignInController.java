package com.k9sv.server.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.k9sv.Config;
import com.k9sv.domain.dto.ResDto;
import com.k9sv.domain.pojo.Account;
import com.k9sv.service.IGoldManager;
import com.k9sv.service.IUserManager;

/**
 * 每日任务
 * 
 * @author mcp
 * 
 */
@Controller
@Scope("prototype")
@RequestMapping("/server/dailytask")
public class ServerSignInController {

	@Autowired
	IUserManager userManager;
	@Autowired
	IGoldManager goldManager;

	/**
	 * 签到
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public String save(HttpServletRequest request, String json, String sid) {
		ResDto res = new ResDto();
		try {
			Account _account = userManager.getOnlineUser(sid);
			// 获取要添加的金币数
			int gold = goldManager.getGold(_account.getId(), Config.SignIn,
					Config.SignInGold);
			if (gold != 0) {
				// 添加领取记录
				goldManager.addGold(_account.getProfile(), Config.SignIn, gold);
			}
			res.setState(0);
			res.setGold(gold);
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

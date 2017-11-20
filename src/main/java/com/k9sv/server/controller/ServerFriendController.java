package com.k9sv.server.controller;

import java.util.ArrayList;
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

import com.k9sv.Errorcode;
import com.k9sv.cache.UserCache;
import com.k9sv.domain.dto.ResDto;
import com.k9sv.domain.dto.UserDto;
import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.Profile;
import com.k9sv.service.IFriendManager;
import com.k9sv.service.IUserManager;
import com.k9sv.util.JsonUtil;
import com.k9sv.util.StringUtil;

@Controller
@Scope("prototype")
@RequestMapping("/server/friend")
public class ServerFriendController {

	@Autowired
	private IUserManager userManager;
	@Autowired
	private IFriendManager friendManager;

	/**
	 * 好友关注
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody
	String add(HttpServletRequest request, String json, String sid, Model model) {
		ResDto res = new ResDto();
		try {
			Account _account = userManager.getOnlineUser(sid);
			JSONObject jsonD = JsonUtil.getJson(request, json);
			int fid = JsonUtil.getInt(jsonD, "uid");
			int type = JsonUtil.getInt(jsonD, "type");
			int friendship = friendManager.getFriendShip(_account.getId(), fid);// 好友关系
			if (type == 0) {// 关注
				if (friendship == 1 || friendship == 3) {
					res.setState(1);
					res.setErrorcode(Errorcode.AlreadyAttention.getCode());
					res.setErrormsg(Errorcode.AlreadyAttention.getErrormsg());
				} else {
					friendManager.saveFriend(_account, fid);
					res.setState(0);
					int friendship2 = friendManager.getFriendShip(
							_account.getId(), fid);// 最新好友关系
					UserDto userDto = new UserDto();
					userDto.setFriendship(friendship2);
					res.setData(userDto);
				}
			} else {// 取消关注
				if (friendship == 1 || friendship == 3) {
					friendManager.deleteFriend(_account, fid);
					res.setState(0);
					int friendship2 = friendManager.getFriendShip(
							_account.getId(), fid);// 最新好友关系
					UserDto userDto = new UserDto();
					userDto.setFriendship(friendship2);
					res.setData(userDto);
				} else {
					res.setState(1);
					res.setErrorcode(Errorcode.NOTAttention.getCode());
					res.setErrormsg(Errorcode.NOTAttention.getErrormsg());
				}
			}
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 关注我的人
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/attentionme", method = RequestMethod.POST)
	public String attentionme(HttpServletRequest request, String json,
			String sid, Model model) {
		ResDto res = new ResDto();
		try {
			Account _account = userManager.getOnlineUser(sid);
			JSONObject jsonD = JsonUtil.getJson(request, json);
			int page = JsonUtil.getPage(jsonD, "page");
			int size = JsonUtil.getSize(jsonD, "size");
			int uid = JsonUtil.getInt(jsonD, "uid");
			Account _account2 = _account;
			if (uid > 0) {
				_account2 = userManager.getByClassId(Account.class, uid);
			}
			List<Profile> profiles = UserCache.AttentionMe.get(_account2
					.getId());
			if (profiles == null) {
				profiles = new ArrayList<Profile>();
			}
			int end = page * size;
			if (end > profiles.size()) {
				end = profiles.size();
			}
			List<UserDto> dtos = new ArrayList<UserDto>();
			for (int i = (page - 1) * size; i < end; i++) {
				Profile profile = profiles.get(i);
				// 这里的好友关系只可能是两种2或3
				int friendship = friendManager.getFriendShip(_account.getId(),
						profile.getId());// 好友关系
				UserDto userDto = new UserDto(profile, friendship);
				dtos.add(userDto);
			}
			res.setState(0);
			res.setData(dtos);
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 我关注的人
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/meattention", method = RequestMethod.POST)
	public String meattention(HttpServletRequest request, String json,
			String sid, Model model) {
		ResDto res = new ResDto();
		try {
			Account _account = userManager.getOnlineUser(sid);
			JSONObject jsonD = JsonUtil.getJson(request, json);
			String nickname = JsonUtil.getString(jsonD, "nickname");// 好友名称
			int page = JsonUtil.getPage(jsonD, "page");
			int size = JsonUtil.getSize(jsonD, "size");
			int uid = JsonUtil.getInt(jsonD, "uid");
			Account _account2 = _account;
			if (uid > 0) {
				_account2 = userManager.getByClassId(Account.class, uid);
			}
			List<Profile> profiles = UserCache.MeAttention.get(_account2
					.getId());
			if (profiles == null) {
				profiles = new ArrayList<Profile>();
			}
			List<UserDto> dtos = new ArrayList<UserDto>();
			if (StringUtil.isEmpty(nickname)) {
				for (Profile profile : profiles) {
					if (profile != null) {
						int friendship = friendManager.getFriendShip(
								_account.getId(), profile.getId());// 好友关系
						UserDto userDto = new UserDto(profile, friendship);
						dtos.add(userDto);
					}

				}
			} else {// 搜索
				for (Profile profile : profiles) {
					if (profile != null) {
						String friend = profile.getNickName();
						if (friend.contains(nickname)) {
							int friendship = friendManager.getFriendShip(
									_account.getId(), profile.getId());// 好友关系
							UserDto userDto = new UserDto(profile, friendship);
							dtos.add(userDto);

						}
					}
				}
			}
			int end = page * size;
			if (end > dtos.size()) {
				end = dtos.size();
			}
			List<UserDto> dtos2 = new ArrayList<UserDto>();
			for (int i = (page - 1) * size; i < end; i++) {
				UserDto userDto = dtos.get(i);
				dtos2.add(userDto);
			}
			res.setState(0);
			res.setData(dtos2);
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 互相关注
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/eachother", method = RequestMethod.POST)
	public String eachOtherAttention(HttpServletRequest request, String json,
			String sid, Model model) {
		ResDto res = new ResDto();
		try {
			Account _account = userManager.getOnlineUser(sid);
			// 我关注的人
			List<Profile> profiles = UserCache.MeAttention
					.get(_account.getId());
			if (profiles == null) {
				profiles = new ArrayList<Profile>();
			}
			List<UserDto> dtos = new ArrayList<UserDto>();
			for (Profile profile : profiles) {
				if (profile != null) {
					int friendship = friendManager.getFriendShip(
							_account.getId(), profile.getId());// 好友关系
					// 互相关注的人
					if (friendship == 3) {
						UserDto userDto = new UserDto(profile, friendship);
						dtos.add(userDto);
					}
				}
			}
			res.setState(0);
			res.setData(dtos);
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
}

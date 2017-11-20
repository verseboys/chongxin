package com.k9sv.server.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.k9sv.Config;
import com.k9sv.Errorcode;
import com.k9sv.cache.UserCache;
import com.k9sv.domain.dto.ProfitLogDto;
import com.k9sv.domain.dto.RecommendDto;
import com.k9sv.domain.dto.ResDto;
import com.k9sv.domain.dto.UserDto;
import com.k9sv.domain.dto.UserInfoDto;
import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.Profile;
import com.k9sv.domain.pojo.ProfitLog;
import com.k9sv.domain.pojo.UserInfo;
import com.k9sv.service.IFriendManager;
import com.k9sv.service.IProfitManager;
import com.k9sv.service.IUserManager;
import com.k9sv.util.JsonUtil;
import com.k9sv.util.QRCodeEvents;
import com.k9sv.util.StringUtil;
import com.qcloud.PicCloud;
import com.qcloud.UploadResult;

/**
 * 钱包
 * 
 * @author mcp
 * 
 */
@Controller
@Scope("prototype")
@RequestMapping("/server/profit")
public class ServerProfitController {

	@Autowired
	IUserManager userManager;
	@Autowired
	IProfitManager profitManager;
	@Autowired
	IFriendManager friendManager;

	Logger LOG = Logger.getLogger(ServerProfitController.class);

	/**
	 * 我的资产
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/load", method = RequestMethod.POST)
	public String load(HttpServletRequest request, String json, String sid) {
		ResDto res = new ResDto();
		try {
			Account _account = userManager.getOnlineUser(sid);
			UserInfo userInfo = profitManager.getByClassId(UserInfo.class, _account.getId());
			if (userInfo == null) {
				userInfo = new UserInfo();
				userInfo.setId(_account.getId());
				userInfo.setProfile(_account.getProfile());
				profitManager.save(userInfo);
			}
			res.setState(0);
			res.setData(new UserInfoDto(userInfo));
			LOG.info(res.toString());
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查看明细
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/detail", method = RequestMethod.POST)
	public String detail(HttpServletRequest request, String json, String sid) {
		ResDto res = new ResDto();
		try {
			Account _account = userManager.getOnlineUser(sid);
			JSONObject jsonD = JsonUtil.getJson(request, json);
			int start = JsonUtil.getInt(jsonD, "start");
			int size = JsonUtil.getSize(jsonD, "size");
			List<ProfitLog> logs = profitManager.getProfitLogs(_account.getId(), start, size);
			List<ProfitLogDto> dtos = new ArrayList<ProfitLogDto>();
			for (ProfitLog profitLog : logs) {
				ProfitLogDto dto = new ProfitLogDto(profitLog);
				dtos.add(dto);
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
	 * 推荐有礼
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/recommend/gift", method = RequestMethod.POST)
	public String recommendGift(HttpServletRequest request, String json, String sid) {
		ResDto res = new ResDto();
		try {
			Account _account = userManager.getOnlineUser(sid);
			int recommendCount = profitManager.getRecommendCount(_account.getId());
			UserInfo userInfo = profitManager.getByClassId(UserInfo.class, _account.getId());
			RecommendDto dto = new RecommendDto(recommendCount, userInfo);
			res.setState(0);
			res.setData(dto);
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 推荐人
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/referee", method = RequestMethod.POST)
	public String referee(HttpServletRequest request, String json, String sid) {
		ResDto res = new ResDto();
		try {
			Account _account = userManager.getOnlineUser(sid);
			int fromId = _account.getProfile().getFromId();
			int friendship = friendManager.getFriendShip(_account.getId(), fromId);// 好友关系
			Profile from = userManager.getByClassId(Profile.class, fromId);
			res.setState(0);
			res.setData(new UserDto(from, friendship));
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 兑换推荐码
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/exchange", method = RequestMethod.POST)
	public String exchange(HttpServletRequest request, String json, String sid) {
		ResDto res = new ResDto();
		try {
			Account _account = userManager.getOnlineUser(sid);
			JSONObject jsonD = JsonUtil.getJson(request, json);
			String mobile = JsonUtil.getString(jsonD, "mobile");
			if (_account.getProfile().getFromId() != 0) {
				res.setState(1);
				res.setErrorcode(Errorcode.Recommend.getCode());
				res.setErrormsg(Errorcode.Recommend.getErrormsg());
				return res.toString();
			}
			Account _friend = userManager.getCheckedAccount(mobile);
			if (_friend == null) {
				res.setState(1);
				res.setErrorcode(Errorcode.RecommendMobile.getCode());
				res.setErrormsg(Errorcode.RecommendMobile.getErrormsg());
				return res.toString();
			}
			_account.getProfile().setFromId(_friend.getId());
			userManager.updateAccount(_account);
			res.setState(0);
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 我推荐的人
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/recommend/list", method = RequestMethod.POST)
	public String recommendList(HttpServletRequest request, String json, String sid) {
		ResDto res = new ResDto();
		try {
			Account _account = userManager.getOnlineUser(sid);
			JSONObject jsonD = JsonUtil.getJson(request, json);
			int page = JsonUtil.getPage(jsonD, "page");
			int size = JsonUtil.getSize(jsonD, "size");
			List<Profile> profiles = UserCache.MyRecommend.get(_account.getId());
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
				int friendship = friendManager.getFriendShip(_account.getId(), profile.getId());// 好友关系
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
	 * 立即推荐
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/recommend", method = RequestMethod.POST)
	public String recommend(HttpServletRequest request, String json, String sid) {
		ResDto res = new ResDto();
		try {
			Account _account = userManager.getOnlineUser(sid);
			UserInfo userInfo = profitManager.getByClassId(UserInfo.class, _account.getId());
			if (userInfo == null) {
				userInfo = new UserInfo();
				userInfo.setId(_account.getId());
				profitManager.save(userInfo);
			}
			if (StringUtil.isEmpty(userInfo.getQrCode())) {
				userInfo = createQrCode(request, userInfo);
			}
			res.setState(0);
			res.setData(new UserInfoDto(userInfo));
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 生成推荐二维码
	 * 
	 * @param request
	 * @param userInfo
	 * @return
	 */
	private UserInfo createQrCode(HttpServletRequest request, UserInfo userInfo) {
		// 生成二维码
		String text = Config.ShareUrl + Config.RecommendUrl + userInfo.getId();
		String imgPath = request.getSession().getServletContext().getRealPath("/attachments/profit/recommend.png");
		QRCodeEvents.createPic(text, imgPath);
		// 上传腾讯云
		PicCloud pc = new PicCloud(Config.projectID, Config.secretID, Config.secretKey, Config.projectName);
		UploadResult result = new UploadResult();
		pc.upload(imgPath, result);
		userInfo.setQrCode(result.downloadUrl);
		profitManager.update(userInfo);
		return userInfo;
	}

	/**
	 * 提现
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/cash", method = RequestMethod.POST)
	public String cash(HttpServletRequest request, String json, String sid) {
		ResDto res = new ResDto();
		try {
			Account _account = userManager.getOnlineUser(sid);
			JSONObject jsonD = JsonUtil.getJson(request, json);
			String alipay = JsonUtil.getString(jsonD, "alipay");
			String name = JsonUtil.getString(jsonD, "name");
			String profit = JsonUtil.getString(jsonD, "profit");
			float _profit = Float.parseFloat(profit);
			UserInfo info = profitManager.getByClassId(UserInfo.class, _account.getId());
			if (info == null) {
				info = new UserInfo();
				info.setId(_account.getId());
				profitManager.save(info);
			}
			if (info.getProfit() < _profit) {
				res.setState(1);
				res.setErrorcode(Errorcode.Cash.getCode());
				res.setErrormsg(Errorcode.Cash.getErrormsg());
				return res.toString();
			}
			if (StringUtil.isEmpty(info.getAlipay())) {
				info.setAlipay(alipay);
			}
			if (StringUtil.isEmpty(info.getName())) {
				info.setName(name);
			}
			profitManager.updateInfo(info, _profit);
			res.setState(0);
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

package com.k9sv.server.controller;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.k9sv.Config;
import com.k9sv.Config2;
import com.k9sv.Errorcode;
import com.k9sv.cache.UserCache;
import com.k9sv.domain.dto.AppDto;
import com.k9sv.domain.dto.CheckCode;
import com.k9sv.domain.dto.FeedPicDto;
import com.k9sv.domain.dto.PhotoZanDto;
import com.k9sv.domain.dto.ProfileDto;
import com.k9sv.domain.dto.ResDto;
import com.k9sv.domain.dto.StateDto;
import com.k9sv.domain.dto.ZanCountDto;
import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.City;
import com.k9sv.domain.pojo.Company;
import com.k9sv.domain.pojo.Feed;
import com.k9sv.domain.pojo.FeedComment;
import com.k9sv.domain.pojo.FeedPic;
import com.k9sv.domain.pojo.OnlineUser;
import com.k9sv.domain.pojo.Profile;
import com.k9sv.schedule.Cache;
import com.k9sv.service.ICityManager;
import com.k9sv.service.ICompanyManager;
import com.k9sv.service.IDogManager;
import com.k9sv.service.IFeedManager;
import com.k9sv.service.IFriendManager;
import com.k9sv.service.ITouTiaoManager;
import com.k9sv.service.IUserManager;
import com.k9sv.service.IVoucherManager;
import com.k9sv.util.AppUtil;
import com.k9sv.util.DateUtil;
import com.k9sv.util.JsonUtil;
import com.k9sv.util.MD5;
import com.k9sv.util.QiniuFileUtil;
import com.k9sv.util.StringUtil;
import com.k9sv.util.UploadUtil;

/**
 * 用户中注册登录
 * 
 * @author mcp
 * 
 */
@Controller
@Scope("prototype")
@RequestMapping("/server/user/")
public class ServerUserController {

	private static final Logger LOG = Logger.getLogger(ServerIndexController.class);

	@Autowired
	private IUserManager userManager;
	@Autowired
	private IDogManager dogManager;
	@Autowired
	private IFeedManager feedManager;
	@Autowired
	private ITouTiaoManager touTiaoManager;
	@Autowired
	private IFriendManager friendManager;
	@Autowired
	private ICityManager cityManager;
	@Autowired
	private IVoucherManager voucherManager;
	@Autowired
	private ICompanyManager companyManager;

	/**
	 * 登陆
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public @ResponseBody String login(HttpServletRequest request, String json, String sid, Model model) {
		ResDto res = new ResDto();
		try {
			JSONObject jsonD = JsonUtil.getJson(request, json);

			String username = JsonUtil.getString(jsonD, "username");
			String password = JsonUtil.getString(jsonD, "password");
			String platform = JsonUtil.getString(jsonD, "platform");
			String clientid = JsonUtil.getString(jsonD, "clientid");

			if (password.length() < 10) {
				password = MD5.md5(password + Config.pwdExtention);
			}
			Account _account = userManager.getCheckedAccount(username);
			if (_account == null || _account.getDeleted() == 1) {
				res.setState(1);
				res.setErrorcode(Errorcode.notRegister.getCode());
				res.setErrormsg(Errorcode.notRegister.getErrormsg());
				return res.toString();
			}
			if (!_account.getPassword().equals(password)) {
				res.setState(1);
				res.setErrorcode(Errorcode.passwordError.getCode());
				res.setErrormsg(Errorcode.passwordError.getErrormsg());
				return res.toString();
			}
			userManager.updateClientid(clientid);
			_account.setClientid(clientid);
			_account.setPlatform(platform);
			String sessionid = this.getSessionId(_account.getId());
			userManager.login(_account, sessionid, Config.LoginPlatformMoble);

			res.setState(0);
			res.setToken(sessionid);
			Profile _profile = _account.getProfile();
			res.setData(new ProfileDto(_profile, zanCount(_account.getId())));
			return res.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 第三方登录
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "thirdlogin", method = RequestMethod.POST)
	public @ResponseBody String thirdlogin(HttpServletRequest request, String json, String sid, Model model) {
		ResDto res = new ResDto();
		try {
			JSONObject jsonD = JsonUtil.getJson(request, json);

			String token = JsonUtil.getString(jsonD, "token");// 作为username
			String nickname = JsonUtil.getString(jsonD, "nickname");
			String avatar = JsonUtil.getString(jsonD, "avatar");
			String type = JsonUtil.getString(jsonD, "type");
			String platform = JsonUtil.getString(jsonD, "platform");
			String usid = JsonUtil.getString(jsonD, "usid");// weixin--unionid或qq--openid
			String clientid = JsonUtil.getString(jsonD, "clientid");
			LOG.info("client-unionid:" + usid);
			Account _account = null;
			if ("weixin".equals(type)) {
				_account = userManager.getAccountByUnionid(usid);
			} else if ("qq".equals(type)) {
				_account = userManager.getAccountByOpneId(usid);
			}

			if (_account == null) {// 没有记录 插入
				_account = new Account();
				_account.setUsername(token);
				_account.setPassword(MD5.md5(Config.DefaultPassword + Config.pwdExtention));
				_account.setCreated(new Date());
				_account.setPlatform(platform);
				_account.setChecked(0);
				Profile _p = new Profile();
				_p.setNickName(nickname);
				_p.setGoldCount(0);
				_p.setSex(1);
				_p.setFeedsCount(0);
				_p.setFriendsCount(0);
				_p.setAvatar(avatar);
				_p.setLevel(1);
				_p.setScore(50);
				_p.setPrestige(0);
				_p.setViewCount(0);
				_account.setProfile(_p);
				_p.setAccount(_account);
			}

			if ("weixin".equals(type)) {
				_account.setUnionid(usid);
			} else if ("qq".equals(type)) {
				_account.setOpenId(usid);
			}
			userManager.updateClientid(clientid);
			_account.setClientid(clientid);
			_account.setPlatform(platform);
			userManager.saveOrUpdate(_account);
			String sessionid = this.getSessionId(_account.getId());
			userManager.login(_account, sessionid, Config.LoginPlatformMoble);

			res.setState(0);
			res.setToken(sessionid);
			Profile _profile = _account.getProfile();
			res.setData(new ProfileDto(_profile, zanCount(_account.getId())));
			return res.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 取用户信息
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "load", method = RequestMethod.POST)
	public @ResponseBody String load(HttpServletRequest request, String json, String sid, Model model) {
		ResDto res = new ResDto();
		try {
			JSONObject jsonD = JsonUtil.getJson(request, json);
			Account _account = userManager.getOnlineUser(sid);
			Integer myid = 0;
			if (_account != null) {
				myid = _account.getId();
			}
			int uid = JsonUtil.getInt(jsonD, "uid");
			if (uid > 0) {
				_account = userManager.getByClassId(Account.class, uid);
			}
			if (_account != null) {
				Integer friend = _account.getId();
				int friendship = friendManager.getFriendShip(myid, friend);// 好友关系
				int voucher = voucherManager.getVoucherCount(myid);// 代金券数

				res.setState(0);
				Company _company = null;
				Profile _profile = _account.getProfile();
				if (_profile.getRoleId() == 2 || _profile.getRoleId() == 3) { // 认证犬舍和宠物店
					_company = companyManager.getCompany(uid);
				}
				res.setData(new ProfileDto(_profile, zanCount(_profile.getId()), friendship, voucher, _company));
			} else {
				res.setState(1);
				res.setErrorcode(Errorcode.UserNotExists.getCode());
				res.setErrormsg(Errorcode.UserNotExists.getErrormsg());
			}
			LOG.info(res.toString());
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 图片、关注、粉丝和赞的总数
	 * 
	 * @param id
	 */
	private ZanCountDto zanCount(Integer uid) {
		List<Feed> feeds = UserCache.MeFeed.get(uid);
		List<Profile> fans = UserCache.AttentionMe.get(uid);
		List<Profile> friends = UserCache.MeAttention.get(uid);
		ZanCountDto countDto = new ZanCountDto();
		if (fans != null) {
			countDto.setFansCount(fans.size());
		}
		if (friends != null) {
			countDto.setFriendCount(friends.size());
		}
		int picCount = 0;
		int zanCount = 0;
		if (feeds != null) {
			countDto.setFeedCount(feeds.size());
			for (Feed feed : feeds) {
				Set<FeedPic> feedPics = feed.getFeedPics();
				if (feedPics != null) {
					picCount += feedPics.size();
				}
				zanCount += feed.getGoodCount();
			}
		}
		int petCount = dogManager.getPetCount(uid, 0);
		countDto.setPetCount(petCount);
		countDto.setPicCount(picCount);
		countDto.setZanCount(zanCount);
		return countDto;
	}

	/**
	 * 修改用户信息
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public @ResponseBody String update(HttpServletRequest request, String json, String sid, Model model) {
		ResDto res = new ResDto();
		try {
			JSONObject jsonD = JsonUtil.getJson(request, json);
			Account _account = userManager.getOnlineUser(sid);

			String nickname = JsonUtil.getString(jsonD, "nickname");
			String mobile = JsonUtil.getString(jsonD, "mobile");//
			String birthday = JsonUtil.getString(jsonD, "birthday");
			int sex = JsonUtil.getInt(jsonD, "sex");
			String address = JsonUtil.getString(jsonD, "address");
			String intro = JsonUtil.getString(jsonD, "intro");
			int cityid = JsonUtil.getInt(jsonD, "cityid");
			// String province = JsonUtil.getString(jsonD, "province");
			String city = JsonUtil.getString(jsonD, "city");

			String avatar = JsonUtil.getString(jsonD, "avatar");
			Integer isFlash = 0;
			Profile _profile = _account.getProfile();
			if (nickname != null) {
				_profile.setNickName(nickname);
			}
			if (mobile != null) {
				_profile.setMobile(mobile);
			}
			if (sex != 0) {
				_profile.setSex(sex);
			}
			if (address != null) {
				_profile.setAddress(address);
			}
			if (intro != null) {
				_profile.setIntro(intro);
			}
			if (cityid > 0) {
				_profile.setCityId(cityid);
				City city2 = cityManager.getByClassId(City.class, cityid);
				_profile.setCity(city2);
			} else if (StringUtil.isNotEmpty(city)) {
				int type = 3;
				if (Config.Municipality.contains(city)) {
					type = 1;
				}
				City _city = cityManager.getCity(city, type);// city默认都有
				if (_city == null) {// 城市尚无定位
					res.setState(1);
					res.setErrorcode(Errorcode.NoCity.getCode());
					res.setErrormsg(Errorcode.NoCity.getErrormsg());
					return res.toString();
				}
				_profile.setCityId(_city.getId());
				City city2 = cityManager.getByClassId(City.class, _city.getId());
				_profile.setCity(city2);
			}
			if (birthday != null) {
				if (DateUtil.parse(birthday, null) != null) {
					_profile.setBirthday(DateUtil.parse(birthday, null));
				}

			}
			if (avatar != null && !avatar.equals(_profile.getAvatar())) {
				isFlash = 1;
				UploadUtil.uploadAvatar(avatar, _profile.getAvatar());
				_profile.setAvatar(avatar);
			}
			userManager.update(_profile);
			res.setState(0);
			res.setData(new ProfileDto(_profile, isFlash));
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 修改用户地理位置 经纬度
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "updatelocation", method = RequestMethod.POST)
	public @ResponseBody String updatelocation(HttpServletRequest request, String json, String sid, Model model) {
		LOG.info("updatelocation:" + json);
		ResDto res = new ResDto();
		try {
			JSONObject jsonD = JsonUtil.getJson(request, json);
			Account _account = userManager.getOnlineUser(sid);

			Profile _profile = _account.getProfile();
			String latitude = JsonUtil.getString(jsonD, "latitude");
			String longtitude = JsonUtil.getString(jsonD, "longtitude");
			if (latitude != null && longtitude != null) {
				_profile.setLatitude(Float.parseFloat(latitude));
				_profile.setLongtitude(Float.parseFloat(longtitude));
				userManager.update(_profile);
			}
			res.setState(0);
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 绑定
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "bind", method = RequestMethod.POST)
	public @ResponseBody String bind(HttpServletRequest request, String json, String sid, Model model) {
		ResDto resDto = new ResDto();

		try {
			Account _account = userManager.getOnlineUser(sid);
			JSONObject jsonD = JsonUtil.getJson(request, json);
			String bindid = JsonUtil.getString(jsonD, "bindid");
			String type = JsonUtil.getString(jsonD, "type");
			String platform = JsonUtil.getString(jsonD, "platform");
			if (platform != null) {
				_account.setPlatform(platform);
			}
			if (type != null && bindid != null) {
				if ("getui".equals(type)) {
					_account.setClientid(bindid);
				} else if ("weixin".equals(type)) {
					_account.setUnionid(bindid);
				}
				userManager.update(_account);
				resDto.setState(0);
			} else {
				resDto.setErrorcode(Errorcode.JsonError.getCode());
				resDto.setErrormsg(Errorcode.JsonError.getErrormsg());
			}
			return resDto.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 版本更新
	 * 
	 * @param data
	 * @param sessionId
	 * @return
	 */
	public String updateclient(String data, String sessionId) {
		JSONObject json = new JSONObject();
		try {
			json.put("state", 0);
			JSONObject jsonD = new JSONObject(data);
			String platform = JsonUtil.getString(jsonD, "platform");
			String version = JsonUtil.getString(jsonD, "version");

			JSONObject jsonR = new JSONObject();

			AppDto app = AppUtil.checkVersion(platform, version);
			if (app == null) {
				jsonR.put("flag", 0);// 没更新
			} else {
				jsonR.put("flag", 1);// 有更新
				jsonR.put("url", app.getUrl());
				jsonR.put("msg", app.getMsg());
				jsonR.put("update", Integer.parseInt(app.getUpdated()));
			}
			json.put("data", jsonR);
			return json.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 退出登录
	 * 
	 * @param request
	 * @param sid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "logout", method = RequestMethod.POST)
	public @ResponseBody String logout(HttpServletRequest request, String sid, Model model) {
		ResDto res = new ResDto();
		try {
			OnlineUser _onlineUser = userManager.getByClassId(OnlineUser.class, sid);
			Account _account = userManager.getByClassId(Account.class, _onlineUser.getUid());
			if (_account != null) {
				_account.setClientid(null);
				userManager.update(_account);
			}
			userManager.onlineUserSet(_onlineUser, 0);
			userManager.delete(_onlineUser);
			res.setState(0);
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取手机验证码
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "getcode", method = RequestMethod.POST)
	public @ResponseBody String getcode(HttpServletRequest request, String json, Model model, String sid) {
		ResDto res = new ResDto();
		try {
			JSONObject jsonD = JsonUtil.getJson(request, json);
			String mobile = JsonUtil.getString(jsonD, "mobile");
			String type = JsonUtil.getString(jsonD, "type");
			String sessionId = this.getSessionId(0);
			if ("bind".equals(type)) {
				sessionId = sid;
			}
			String code = "";
			if ("register".equals(type)) {
				Account _a = userManager.getCheckedAccount(mobile);
				if (_a != null) {
					res.setState(1);
					res.setErrorcode(Errorcode.Registered.getCode());
					res.setErrormsg(Errorcode.Registered.getErrormsg());
					return res.toString();
				} else {
					code = userManager.sendCheckCode(type, mobile, sessionId);
				}
			} else if ("forgetpassword".equals(type)) {
				Account _a = userManager.getAccount(mobile);
				if (_a != null && _a.getChecked() == 1) {
					code = userManager.sendCheckCode(type, mobile, sessionId);
				} else {
					res.setState(1);
					res.setErrorcode(Errorcode.notRegister.getCode());
					res.setErrormsg(Errorcode.notRegister.getErrormsg());
					return res.toString();
				}
			} else if ("bind".equals(type)) {
				Account _a = userManager.getCheckedAccount(mobile);
				if (_a == null) {
					Account _account = userManager.getOnlineUser(sid);
					if (_account != null) {
						code = userManager.sendCheckCode(type, mobile, sessionId);
					} else {
						res.setState(1);
						res.setErrorcode(Errorcode.notLogin.getCode());
						res.setErrormsg(Errorcode.notLogin.getErrormsg());
						return res.toString();
					}
				} else {
					res.setState(1);
					res.setErrorcode(Errorcode.Registered.getCode());
					res.setErrormsg(Errorcode.Registered.getErrormsg());
					return res.toString();
				}

			} else if ("cash".equals(type)) {
				Account _a = userManager.getAccount(mobile);
				if (_a != null && _a.getChecked() == 1) {
					code = userManager.sendCheckCode(type, mobile, sessionId);
				} else {
					res.setState(1);
					res.setErrorcode(Errorcode.notRegister.getCode());
					res.setErrormsg(Errorcode.notRegister.getErrormsg());
					return res.toString();
				}
			}
			res.setState(0);
			CheckCode _c = new CheckCode(type, code, mobile, sessionId);
			res.setData(_c);
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 验证验证码
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "confirmcode", method = RequestMethod.POST)
	public String confirmcode(HttpServletRequest request, String json, String sid) {
		ResDto res = new ResDto();
		try {
			JSONObject jsonD = JsonUtil.getJson(request, json);
			String code = JsonUtil.getString(jsonD, "code");
			LOG.info("sid==" + sid + "--code==" + code);
			CheckCode _checkCode = Cache.CheckcodeMap.get(sid);
			if (_checkCode != null && _checkCode.getStatus() == 0 && _checkCode.getCode().equals(code)) {
				_checkCode.setStatus(1);
				res.setState(0);
				if ("bind".equals(_checkCode.getType())) {
					Account _a = userManager.getOnlineUser(sid);
					if (_a != null) {
						Account _temp = userManager.getCheckedAccount(_checkCode.getMobile());
						if (_temp == null) {
							this.updateAccount(_a, _checkCode);
						} else if (!_temp.equals(_a)) {
							_temp.setChecked(0);
							userManager.update(_temp);
							this.updateAccount(_a, _checkCode);
						}
					}
				}
			} else {
				res.setState(1);
				res.setErrorcode(Errorcode.CheckCodeError.getCode());
				res.setErrormsg(Errorcode.CheckCodeError.getErrormsg());
			}
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 绑定更新用户
	 * 
	 * @param _a
	 */
	private void updateAccount(Account _a, CheckCode _checkCode) {
		_a.setUsername(_checkCode.getMobile());
		_a.setChecked(1);
		userManager.update(_a);
		Profile _profile = _a.getProfile();
		if (_profile.getMobile() == null) {
			_profile.setMobile(_checkCode.getMobile());
			userManager.update(_profile);
		}
	}

	/**
	 * 修改密码
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "chanagepassword", method = RequestMethod.POST)
	public @ResponseBody String chanagePassword(HttpServletRequest request, String json, String sid, Model model) {
		ResDto resDto = new ResDto();
		try {
			JSONObject jsonD = JsonUtil.getJson(request, json);
			Account _account = userManager.getOnlineUser(sid);
			String token = JsonUtil.getString(jsonD, "token");
			String mobile = JsonUtil.getString(jsonD, "mobile");
			String password = JsonUtil.getString(jsonD, "password");// 加密过的
			if (_account == null) {
				_account = userManager.getCheckedAccount(mobile);
			}
			if (_account == null) {
				resDto.setState(1);
				resDto.setErrorcode(Errorcode.notRegister.getCode());
				resDto.setErrormsg(Errorcode.notRegister.getErrormsg());
				return resDto.toString();
			}
			_account.setPassword(password);
			// userManager.update(_account);
			userManager.login(_account, token, Config.LoginPlatformMoble);
			resDto.setState(0);
			return resDto.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 修改密码
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "changepwd", method = RequestMethod.POST)
	public @ResponseBody String changepwd(HttpServletRequest request, String json, String sid, Model model) {
		ResDto resDto = new ResDto();
		try {
			JSONObject jsonD = JsonUtil.getJson(request, json);
			Account _account = userManager.getOnlineUser(sid);
			String password = JsonUtil.getString(jsonD, "password");
			String newpassword = JsonUtil.getString(jsonD, "newpassword");

			if (_account != null) {
				if (_account.getPassword().equals(password)) {
					_account.setPassword(newpassword);
					userManager.update(_account);
				} else {
					resDto.setState(1);
					resDto.setErrorcode(Errorcode.oldPasswordError.getCode());
					resDto.setErrormsg(Errorcode.oldPasswordError.getErrormsg());
					return resDto.toString();
				}
			} else {
				resDto.setState(1);
				resDto.setErrorcode(Errorcode.notRegister.getCode());
				resDto.setErrormsg(Errorcode.notRegister.getErrormsg());
				return resDto.toString();
			}
			userManager.login(_account, sid, Config.LoginPlatformMoble);
			resDto.setState(0);
			return resDto.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 注册
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public @ResponseBody String register(HttpServletRequest request, String json, String sid) {
		ResDto res = new ResDto();
		try {
			JSONObject jsonD = JsonUtil.getJson(request, json);
			String code = JsonUtil.getString(jsonD, "code");
			String password = JsonUtil.getString(jsonD, "password");
			String platform = JsonUtil.getString(jsonD, "platform");
			String version = JsonUtil.getString(jsonD, "version");
			String clientid = JsonUtil.getString(jsonD, "clientid");
			String systemversion = JsonUtil.getString(jsonD, "systemversion");
			if (!userManager.checkCode(sid, code)) {
				res.setState(1);
				res.setErrorcode(Errorcode.CheckCodeError.getCode());
				res.setErrormsg(Errorcode.CheckCodeError.getErrormsg());
			} else {
				CheckCode _ceckCode = Cache.CheckcodeMap.get(sid);

				res.setState(0);
				Account _account = new Account();
				Date _now = new Date();
				_account.setUsername(_ceckCode.getMobile());
				if (password.length() < 15) {
					password = MD5.md5(password + Config.pwdExtention);
				}
				userManager.updateClientid(clientid);
				_account.setPassword(password);
				_account.setClientid(clientid);
				_account.setCreated(_now);
				_account.setSystemversion(systemversion);
				_account.setPlatform(platform);
				_account.setVersion(version);
				_account.setChecked(1);
				Profile _p = new Profile();
				_p.setMobile(_account.getUsername());
				_p.setNickName(_account.getUsername());
				_p.setSex(1);
				_p.setGoldCount(0);
				_p.setFeedsCount(0);
				_p.setFriendsCount(0);
				_p.setLevel(1);
				_p.setScore(50);
				_p.setPrestige(0);
				_p.setViewCount(0);
				_account.setProfile(_p);
				_p.setAccount(_account);
				userManager.save(_account);
				userManager.login(_account, sid, Config.LoginPlatformMoble);
				res.setData(new ProfileDto(_p));
			}

			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 微信公众号登录
	 * 
	 * @param request
	 * @param json
	 * @param platform
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "wxregister", method = RequestMethod.POST)
	public @ResponseBody String wxregister(HttpServletRequest request, String json, String platform, Model model) {
		ResDto res = new ResDto();
		try {
			JSONObject jsonD = JsonUtil.getJson(request, json);
			String unionid = JsonUtil.getString(jsonD, "unionid");
			String mobile = JsonUtil.getString(jsonD, "mobile");
			String nickname = JsonUtil.getString(jsonD, "nickname");
			int sex = JsonUtil.getInt(jsonD, "sex");
			String avatar = JsonUtil.getString(jsonD, "avatar");
			Account _account = userManager.getCheckedAccount(mobile);
			if (_account != null) {
				res.setState(1);
				return res.toString();
			}
			res.setState(0);
			_account = new Account();
			Date _now = new Date();
			_account.setUsername(mobile);
			_account.setUnionid(unionid);
			_account.setPassword("weixin");
			_account.setClientid(null);
			_account.setCreated(_now);
			_account.setSystemversion(null);
			_account.setPlatform("weixin");
			_account.setVersion(null);
			_account.setChecked(0);
			Profile _p = new Profile();
			_p.setMobile(_account.getUsername());
			_p.setGoldCount(0);
			_p.setFeedsCount(0);
			_p.setFriendsCount(0);
			_p.setLevel(1);
			_p.setScore(50);
			_p.setPrestige(0);
			_p.setViewCount(0);
			_p.setSex(sex);
			_p.setNickName(nickname);
			_p.setAvatar(avatar);
			_account.setProfile(_p);
			_p.setAccount(_account);
			userManager.save(_account);
			if (platform == null) {
				platform = Config.LoginPlatformMoble;
			}
			String sid = this.getSessionId(_p.getId());
			userManager.login(_account, sid, platform);
			res.setToken(sid);
			res.setData(new ProfileDto(_p));

			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 状态标注（要不要显示更新标志）
	 * 
	 * @param request
	 * @param response
	 * @param json
	 * @param sid
	 * @return
	 */
	@RequestMapping(value = "stateupdate", method = RequestMethod.POST)
	@ResponseBody
	public String stateUpdate(HttpServletRequest request, HttpServletResponse response, String json, String sid) {

		ResDto res = new ResDto();
		StateDto dto = new StateDto();
		try {
			// JSONObject jsonD = JsonUtil.getJson(request, json);
			// Integer maxfeedid = JsonUtil.getInt(jsonD, "maxfeedid");
			// Integer maxtoutiaoid = JsonUtil.getInt(jsonD, "maxtoutiaoid");
			// 动态
			// Integer maxFeed = feedManager.getMaxFeed(maxfeedid);
			Integer maxFeed = feedManager.getMaxFeed(0);
			dto.setFeedid(maxFeed);
			// 消息（头条）
			// Integer maxToutiao = touTiaoManager.getMaxToutiao(maxtoutiaoid);
			Integer maxToutiao = touTiaoManager.getMaxToutiao(0);
			dto.setToutiaoid(maxToutiao);
			res.setState(0);
			res.setData(dto);
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 更换主题背景图
	 * 
	 * @param request
	 * @param response
	 * @param json
	 * @param sid
	 * @return
	 */
	@RequestMapping(value = "updatetopic", method = RequestMethod.POST)
	@ResponseBody
	public String updatetopic(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("files") MultipartFile file, String json, String sid) {

		ResDto res = new ResDto();
		try {
			Account _account = userManager.getOnlineUser(sid);
			BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
			int width = bufferedImage.getWidth();
			int height = bufferedImage.getHeight();
			int width1 = 0;
			int height1 = 0;
			if (width > Config.MaxTopicPicWidth || height > Config.MaxTopicPicHeight) {
				if (width >= height) {
					height1 = (int) (height * Config.MaxTopicPicWidth / width);
					width1 = Config.MaxTopicPicWidth;
				} else {
					height1 = Config.MaxTopicPicHeight;
					width1 = (int) (width * Config.MaxTopicPicHeight / height);
				}
			} else {
				width1 = width;
				height1 = height;
			}
			String extention = ".jpg";
			String _fileName = "topic" + "-" + getSavedFileName() + "_" + width1 + "-" + height1 + extention;
			resizeImage(file.getBytes(), Config2.saveDir + _fileName, width1, height1);
			QiniuFileUtil.uploadTopic(_fileName);
			_account.getProfile().setTopicPic(_fileName);
			userManager.update(_account);
			res.setState(0);
			res.setData(new ProfileDto(_fileName));
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private String getSessionId(int id) {
		Long i = System.currentTimeMillis();
		String sessionId = MD5.md5(i + " " + id);
		return sessionId;
	}

	private synchronized String getSavedFileName() {
		SimpleDateFormat _df = new SimpleDateFormat("yyyyMMddHHmmssSS");
		String _name = _df.format(new Date());
		String _rand = Integer.toString(((int) ((Math.random() + 2) * 100))).substring(1);
		return _name + _rand;
	}

	private void resizeImage(byte[] bytes, String filePath, int width, int height) throws IOException {

		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		BufferedImage bi = ImageIO.read(bais);
		BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = result.createGraphics();
		g.drawImage(bi, 0, 0, width, height, null);
		g.dispose();
		result.getGraphics().drawImage(bi.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
		writeToDisk(result, filePath);
	}

	private static boolean writeToDisk(BufferedImage im, String fileName) {

		String fileType = fileName.split("\\.")[fileName.split("\\.").length - 1];
		File f = new File(fileName);
		if (fileType == null)
			return false;
		try {
			ImageIO.write(im, fileType, f);
			im.flush();
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	/**
	 * 照片和赞
	 * 
	 * @param request
	 * @param response
	 * @param json
	 * @param sid
	 * @return
	 */
	@RequestMapping(value = "photozan", method = RequestMethod.POST)
	@ResponseBody
	public String photoAndZan(HttpServletRequest request, HttpServletResponse response, String json, String sid) {
		ResDto res = new ResDto();
		try {
			Account _account = userManager.getOnlineUser(sid);
			JSONObject jsonD = JsonUtil.getJson(request, json);
			int type = JsonUtil.getInt(jsonD, "type");// 0为照片 1为赞
			int page = JsonUtil.getPage(jsonD, "page");
			int size = JsonUtil.getSize(jsonD, "size");
			int uid = JsonUtil.getInt(jsonD, "uid");
			int trueUid = _account.getId();
			if (uid != 0) {
				trueUid = uid;
			}
			List<FeedPic> _feedPics = new ArrayList<FeedPic>();// 全部照片
			List<FeedPicDto> _feedPicList = new ArrayList<FeedPicDto>();// 分页照片
			List<FeedComment> comments = new ArrayList<FeedComment>();// 我被赞的动态
			if (type == 0) {// 照片
				List<Feed> feeds = UserCache.MeFeed.get(trueUid);// 我的动态
				if (feeds != null) {
					for (Feed feed : feeds) {
						Set<FeedPic> feedPics = feed.getFeedPics();
						if (feedPics != null) {
							_feedPics.addAll(feedPics);
						}
					}
				}
				int end = page * size;
				if (end > _feedPics.size()) {
					end = _feedPics.size();
				}
				for (int i = (page - 1) * size; i < end; i++) {
					FeedPic feedPic = _feedPics.get(i);
					FeedPicDto feedPicDto = new FeedPicDto(feedPic);
					_feedPicList.add(feedPicDto);
				}
			} else {// 赞
				comments = feedManager.getComments(trueUid, page, size);
			}
			PhotoZanDto dto = new PhotoZanDto(type, _feedPicList, comments);
			res.setState(0);
			res.setData(dto);
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

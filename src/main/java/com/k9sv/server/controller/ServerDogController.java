package com.k9sv.server.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.k9sv.Errorcode;
import com.k9sv.cache.UserCache;
import com.k9sv.domain.dto.DogDto;
import com.k9sv.domain.dto.FeedCommentDto;
import com.k9sv.domain.dto.FeedDto;
import com.k9sv.domain.dto.ResDto;
import com.k9sv.domain.dto.UserDto;
import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.Dog;
import com.k9sv.domain.pojo.Feed;
import com.k9sv.domain.pojo.FeedComment;
import com.k9sv.service.IDogManager;
import com.k9sv.service.IFeedManager;
import com.k9sv.service.IFriendManager;
import com.k9sv.service.IRecordManager;
import com.k9sv.service.ITransManager;
import com.k9sv.service.IUserManager;
import com.k9sv.util.DateUtil;
import com.k9sv.util.JsonUtil;
import com.k9sv.util.StringUtil;
import com.k9sv.util.UploadUtil;

@Controller
@Scope("prototype")
@RequestMapping("/server/dog")
public class ServerDogController {

	@Autowired
	IDogManager dogManager;
	@Autowired
	IUserManager userManager;
	@Autowired
	IFeedManager feedManager;
	@Autowired
	IFriendManager friendManager;
	@Autowired
	IRecordManager recordManager;
	@Autowired
	ITransManager transManager;

	/**
	 * 用户的宠物列表
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public String list(HttpServletRequest request, String json, String sid) {
		ResDto res = new ResDto();
		try {
			Account _account = userManager.getOnlineUser(sid);
			JSONObject jsonD = JsonUtil.getJson(request, json);
			int page = JsonUtil.getPage(jsonD, "page");
			int size = JsonUtil.getSize(jsonD, "size");
			int uid = JsonUtil.getInt(jsonD, "uid");
			if (uid == 0) {
				uid = _account.getId();
			}
			List<Dog> dogs = dogManager.getUserDogs(uid, page, size);
			List<DogDto> dogDtos = new ArrayList<DogDto>();
			for (Dog dog : dogs) {
				DogDto dogDto = new DogDto(dog);
				dogDtos.add(dogDto);
			}
			res.setState(0);
			res.setData(dogDtos);
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 宠物动态
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/feeds", method = RequestMethod.POST)
	public String feeds(HttpServletRequest request, String json, String sid) {
		ResDto res = new ResDto();
		try {
			Account _account = userManager.getOnlineUser(sid);
			JSONObject jsObject = JsonUtil.getJson(request, json);
			Integer petid = JsonUtil.getInt(jsObject, "petid");
			Integer start = JsonUtil.getInt(jsObject, "start");
			Integer size = JsonUtil.getSize(jsObject, "size");
			Dog dog = dogManager.getByClassId(Dog.class, petid);
			List<Feed> feeds = UserCache.UserZanFeeds.get(_account.getId());
			List<FeedDto> _list = new ArrayList<FeedDto>();
			List<Feed> _feeds = feedManager.getDogFeeds(petid, start, size);
			int friendship = friendManager.getFriendShip(_account.getId(),
					dog.getOwnerId());// 好友关系
			for (Feed _feed : _feeds) {
				int isZan = isZan(feeds, _feed);
				FeedDto _dto = new FeedDto(_feed, friendship, isZan);
				_dto.setComments(this.getFeedComments(_feed.getId(), 0, 5));
				_dto.setZanusers(this.getFeedZans(_feed.getId(), 1, 10));
				_list.add(_dto);
			}
			res.setState(0);
			res.setData(_list);
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 宠物详情
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
			JSONObject jsObject = JsonUtil.getJson(request, json);
			Integer petid = JsonUtil.getInt(jsObject, "petid");
			Dog dog = dogManager.getByClassId(Dog.class, petid);
			DogDto dogDto = new DogDto(dog);
			res.setState(0);
			res.setData(dogDto);
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private int isZan(List<Feed> feeds, Feed _feed) {
		int isZan = 0;
		if (feeds != null && feeds.contains(_feed)) {
			isZan = 1;// 已赞
		}
		return isZan;
	}

	/**
	 * 新增或修改编辑宠物信息
	 * 
	 * @param request
	 * @param json
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(HttpServletRequest request, String json, String sid) {
		ResDto res = new ResDto();
		try {
			Account _account = userManager.getOnlineUser(sid);
			JSONObject jsObject = JsonUtil.getJson(request, json);
			int petid = JsonUtil.getInt(jsObject, "petid");
			String avatar = JsonUtil.getString(jsObject, "avatar");
			Integer sex = JsonUtil.getInteger(jsObject, "sex");
			String name = JsonUtil.getString(jsObject, "name");
			String birthdayStr = JsonUtil.getString(jsObject, "birthday");
			String chip = JsonUtil.getString(jsObject, "chip");
			Integer classify = JsonUtil.getInteger(jsObject, "classify");
			// 相同用户不允许有相同名字的宠物
			Dog _dog = dogManager.checkDogName(_account, name, petid);
			if (_dog != null) {// 宠物名已存在
				res.setState(1);
				res.setErrorcode(Errorcode.haveDogName.getCode());
				res.setErrormsg(Errorcode.haveDogName.getErrormsg());
				return res.toString();
			}
			Dog dog = null;
			Integer isFlash = 0;// 图片是否改变
			if (petid == 0) {// 新添
				dog = new Dog();
				dog.setZan(0);
				dog.setStatus(0);
				if (birthdayStr == null) {
					dog.setBirthday(new Date());
				}
				if (StringUtil.isNotEmpty(avatar)) {
					dog.setAvatar(avatar);
					UploadUtil.uploadAvatar(avatar, null);
					isFlash = 1;
				}
			} else {// 修改
				dog = dogManager.getByClassId(Dog.class, petid);
				if (_account.getId() != dog.getOwnerId()) {
					res.setState(1);
					res.setErrorcode(Errorcode.updateDog.getCode());
					res.setErrormsg(Errorcode.updateDog.getErrormsg());
					return res.toString();
				}
				if (StringUtil.isNotEmpty(avatar)
						&& !avatar.equals(dog.getAvatar())) {// 换头像
					UploadUtil.uploadAvatar(avatar, dog.getAvatar());
					dog.setAvatar(avatar);
					isFlash = 1;
				}
			}
			if (dog.getStatus() == 0) {// 芯片号没有确认过 信息都可以修改
				if (sex != null) {
					dog.setSex(sex);
				}
				if (birthdayStr != null) {
					dog.setBirthday(DateUtil.getDateObj(birthdayStr, "-"));
				}
				if (StringUtil.isNotEmpty(chip)) {
					dog.setBlood(chip);
				}
				if (classify != null) {
					dog.setClassify(classify);
				}
			}
			if (name != null) {
				dog.setName(name);
			}
			dog.setOwnerId(_account.getId());
			dogManager.saveOrUpdate(dog);
			res.setState(0);
			res.setData(new DogDto(dog, isFlash));
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 删除一个宠物信息
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(HttpServletRequest request, String json, String sid) {

		ResDto res = new ResDto();
		try {
			Account _account = userManager.getOnlineUser(sid);
			JSONObject jsObject = JsonUtil.getJson(request, json);
			Integer petid = JsonUtil.getInt(jsObject, "petid");
			Integer result = dogManager.delete(petid, _account);
			if (result.intValue() == 1) {
				res.setState(0);
			} else {
				res.setState(1);// 没有权限
				res.setErrorcode(Errorcode.deleteDog.getCode());
				res.setErrormsg(Errorcode.deleteDog.getErrormsg());
			}
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 转让
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/trans", method = RequestMethod.POST)
	public String trans(HttpServletRequest request, String json, String sid) {

		ResDto res = new ResDto();
		try {
			Account _account = userManager.getOnlineUser(sid);
			JSONObject jsObject = JsonUtil.getJson(request, json);
			Integer petid = JsonUtil.getInt(jsObject, "petid");
			Integer touid = JsonUtil.getInt(jsObject, "touid");
			Dog result = dogManager.checkDog(petid, _account, touid);
			if (result != null) {
				res.setState(0);
			} else {
				res.setState(1);// 没有权限
				res.setErrorcode(Errorcode.transDog.getCode());
				res.setErrormsg(Errorcode.transDog.getErrormsg());
			}
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private List<FeedCommentDto> getFeedComments(int fid, int page, int size) {
		List<FeedCommentDto> _temp = new ArrayList<FeedCommentDto>();
		try {
			List<FeedComment> _list = feedManager.getFeedComments(fid, page,
					size);
			for (FeedComment fc : _list) {
				FeedCommentDto _dto = new FeedCommentDto(fc);
				_temp.add(_dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return _temp;
	}

	private List<UserDto> getFeedZans(int fid, int page, int size) {
		List<UserDto> _temp = new ArrayList<UserDto>();
		try {
			List<FeedComment> _list = feedManager.getFeedZans(fid, page, size);
			for (FeedComment fc : _list) {
				_temp.add(new UserDto(fc.getProfile()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return _temp;
	}

	/**
	 * 芯片号搜索
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(HttpServletRequest request, String json, String sid) {

		ResDto res = new ResDto();
		try {
			JSONObject jsObject = JsonUtil.getJson(request, json);
			String blood = JsonUtil.getString(jsObject, "blood");
			Dog dog = dogManager.getDogBuyBlood(blood);
			// int feedCount = feedManager.getDogFeedCount(dog);
			DogDto dogDto = new DogDto(dog);
			res.setState(0);
			res.setData(dogDto);
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

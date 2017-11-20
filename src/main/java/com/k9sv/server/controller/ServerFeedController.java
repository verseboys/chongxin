package com.k9sv.server.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.k9sv.Config;
import com.k9sv.Errorcode;
import com.k9sv.cache.UserCache;
import com.k9sv.domain.dto.CardDto;
import com.k9sv.domain.dto.FeedCommentDto;
import com.k9sv.domain.dto.FeedDto;
import com.k9sv.domain.dto.ResDto;
import com.k9sv.domain.dto.UserDto;
import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.Dog;
import com.k9sv.domain.pojo.Feed;
import com.k9sv.domain.pojo.FeedComment;
import com.k9sv.domain.pojo.FeedPic;
import com.k9sv.domain.pojo.Profile;
import com.k9sv.domain.pojo.Record;
import com.k9sv.service.IBaseManager;
import com.k9sv.service.IFeedManager;
import com.k9sv.service.IFriendManager;
import com.k9sv.service.IGoldManager;
import com.k9sv.service.IUserManager;
import com.k9sv.util.JsonUtil;
import com.k9sv.util.StringUtil;

@Controller
@Scope("prototype")
@RequestMapping("/server/feed/")
public class ServerFeedController {

	private static final Logger LOG = Logger.getLogger(ServerFeedController.class);

	@Autowired
	private IUserManager userManager;
	@Autowired
	private IFeedManager feedManager;
	@Autowired
	private IFriendManager friendManager;
	@Autowired
	private IGoldManager goldManager;
	@Autowired
	private IBaseManager baseManager;

	/**
	 * 发布动态
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody String save(HttpServletRequest request, String json, String sid, Model model) {
		ResDto resDto = new ResDto();
		try {
			Date _now = new Date();

			Account _account = userManager.getOnlineUser(sid);
			JSONObject jsonD = JsonUtil.getJson(request, json);

			String content = JsonUtil.getString(jsonD, "content");
			String address = JsonUtil.getString(jsonD, "address");
			String latitude = JsonUtil.getString(jsonD, "latitude");
			String longtitude = JsonUtil.getString(jsonD, "longtitude");
			Integer relationId = JsonUtil.getInteger(jsonD, "petid");
			int type = JsonUtil.getInt(jsonD, "type");
			Feed _feed = new Feed();
			Set<FeedPic> _feedPics = new HashSet<FeedPic>();
			if (type == 1) {// 卡片（先做分享宠物）
				JSONObject _jsonCard = JsonUtil.getJSONObject(jsonD, "card");
				// int _type = JsonUtil.getInt(_jsonCard, "type");// 0是宠物 1是网页
				int _relation = JsonUtil.getInt(_jsonCard, "relation");// 宠物id
				String _content = JsonUtil.getString(_jsonCard, "content");// 分享说明
				Dog _dog = baseManager.getByClassId(Dog.class, _relation);
				CardDto _cardDto = new CardDto(_dog, _content);
				String _cardString = _cardDto.toString();
				_feed.setCard(_cardString);
			} else {
				JSONArray _jsonPhotoList = JsonUtil.getJSONArray(jsonD, "photos");
				if (_jsonPhotoList != null) {
					for (int i = 0; i < _jsonPhotoList.length(); i++) {
						JSONObject _jsonPhoto = (JSONObject) _jsonPhotoList.get(i);
						String _photo = _jsonPhoto.getString("photo");
						if (_photo != null) {
							FeedPic _fp = new FeedPic();
							_fp.setUrl(_photo);
							_fp.setUid(_account.getId());
							_fp.setCreated(_now);
							_feedPics.add(_fp);
						}

					}
				}
				// ///////判断是否重复发布动态///////////
				Feed lastFeed = UserCache.lastFeed.get(_account.getId());
				if (lastFeed != null) {
					Set<FeedPic> lastFeedPics = lastFeed.getFeedPics();
					if ((StringUtil.isNotEmpty(content) && StringUtil.isNotEmpty(lastFeed.getContent())
							&& content.equals(lastFeed.getContent()) && isSetEqual(lastFeedPics, _feedPics))
							|| (StringUtil.isEmpty(content) && StringUtil.isEmpty(lastFeed.getContent())
									&& isSetEqual(lastFeedPics, _feedPics))) {// 判断是不是重复发布
						resDto.setState(1);
						resDto.setErrorcode(Errorcode.ReAddFeedComment.getCode());
						resDto.setErrormsg(Errorcode.ReAddFeedComment.getErrormsg());
						return resDto.toString();
					}
				}
			}

			Record record = null;
			_feed.setCreated(_now);
			_feed.setType(type);
			_feed.setUid(_account.getId());
			_feed.setProfile(_account.getProfile());
			if (content != null) {
				_feed.setContent(content);
			}
			if (address != null) {
				_feed.setAddress(address);
			}
			if (relationId != null && relationId != 0) {
				_feed.setRelationId(relationId);
				record = new Record();
				record.setDogid(relationId);
			}
			if (latitude != null) {
				_feed.setLatitude(Double.parseDouble(latitude));
			} else {
				_feed.setLatitude(0);
			}

			if (longtitude != null) {
				_feed.setLongtitude(Double.parseDouble(longtitude));
			} else {
				_feed.setLongtitude(0);
			}
			_feed.setFeedPics(_feedPics);
			feedManager.saveFeed(_feed);
			if (record != null) {// 有宠物时才插记录
				record.setFeedid(_feed.getId());
				record.setCreated(_now);
				record.setRecordTime(_now);
				record.setUid(_account.getId());
				feedManager.save(record);
			}
			resDto.setState(0);
			// resDto.setData(new FeedDto(_feed));
			resDto.setData(new FeedDto(_feed, 0, 0));
			return resDto.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 判断set是否相等
	 * 
	 * @param set1
	 * @param set2
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean isSetEqual(Set set1, Set set2) {

		if (set1 == null && set2 != null) {
			return false;
		} else if (set1 != null && set2 == null) {
			return false;
		} else if (set1 == null && set2 == null) {
			return true;
		}
		if (set1.size() != set2.size()) {
			return false;
		}
		set1 = new TreeSet<>(set1);
		set2 = new TreeSet<>(set2);
		Iterator ite1 = set1.iterator();
		Iterator ite2 = set2.iterator();
		while (ite1.hasNext()) {
			FeedPic feedPic1 = (FeedPic) ite1.next();
			FeedPic feedPic2 = (FeedPic) ite2.next();
			if (!feedPic1.equals(feedPic2)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 取动态列表
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public @ResponseBody String list(HttpServletRequest request, String json, String sid, Model model) {
		ResDto res = new ResDto();
		try {
			Account _account = userManager.getOnlineUser(sid);
			int uid = 0;
			int roleid = 0;
			if (_account != null) {
				uid = _account.getId();
				roleid = _account.getProfile().getRoleId();
			}
			JSONObject jsonD = JsonUtil.getJson(request, json);

			int start = JsonUtil.getInt(jsonD, "start");
			int size = JsonUtil.getSize(jsonD, "size");
			int type = JsonUtil.getInt(jsonD, "type");// 1的时候取好友的动态

			List<FeedDto> _list = new ArrayList<FeedDto>();
			List<Feed> _feeds = feedManager.getFeeds(uid, roleid, type, start, size);
			List<Feed> feeds = UserCache.UserZanFeeds.get(uid);
			for (Feed _feed : _feeds) {

				int friendship = friendManager.getFriendShip(uid, _feed.getUid());// 好友关系
				int isZan = isZan(feeds, _feed);

				FeedDto _dto = new FeedDto(_feed, friendship, isZan);
				_dto.setComments(this.getFeedComments(_feed.getId(), 0, 3));
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
	 * 取某个人的动态
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "user", method = RequestMethod.POST)
	public @ResponseBody String loadUser(HttpServletRequest request, String json, String sid, Model model) {
		ResDto res = new ResDto();
		try {
			Account _account = userManager.getOnlineUser(sid);

			JSONObject jsonD = JsonUtil.getJson(request, json);
			int start = JsonUtil.getInt(jsonD, "start");
			int size = JsonUtil.getInt(jsonD, "size");
			int uid = JsonUtil.getInt(jsonD, "uid");
			if (uid == 0) {
				uid = _account.getId();
			}
			int friendship = friendManager.getFriendShip(_account.getId(), uid);// 好友关系
			List<Feed> feeds = UserCache.UserZanFeeds.get(_account.getId());
			List<FeedDto> _list = new ArrayList<FeedDto>();
			List<Feed> _feeds = feedManager.getUserFeeds(uid, start, size);
			for (Feed _feed : _feeds) {
				int isZan = isZan(feeds, _feed);
				FeedDto _dto = new FeedDto(_feed, friendship, isZan);
				_dto.setComments(this.getFeedComments(_feed.getId(), 0, 3));
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

	private int isZan(List<Feed> feeds, Feed _feed) {
		int isZan = 0;
		if (feeds != null && feeds.contains(_feed)) {
			isZan = 1;// 已赞
		}
		return isZan;
	}

	/**
	 * 取动态详情
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
			Account _account = userManager.getOnlineUser(sid);
			int uid = 0;
			if (_account != null) {
				uid = _account.getId();
			}
			JSONObject jsonD = JsonUtil.getJson(request, json);
			int fid = JsonUtil.getInt(jsonD, "fid");
			Feed _feed = feedManager.getByClassId(Feed.class, fid);
			List<Feed> feeds = UserCache.UserZanFeeds.get(uid);
			int friendship = friendManager.getFriendShip(uid, _feed.getUid());// 好友关系
			int isZan = isZan(feeds, _feed);
			FeedDto _dto = new FeedDto(_feed, friendship, isZan);
			_dto.setComments(this.getFeedComments(_feed.getId(), 0, 30));
			_dto.setZanusers(this.getFeedZans(_feed.getId(), 1, 10));
			res.setState(0);
			res.setData(_dto);
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 删除1条动态
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public @ResponseBody String delete(HttpServletRequest request, String json, String sid, Model model) {
		ResDto resDto = new ResDto();
		try {
			Account _account = userManager.getOnlineUser(sid);
			JSONObject jsonD = JsonUtil.getJson(request, json);

			int fid = JsonUtil.getInt(jsonD, "fid");
			int result = feedManager.deleteFeed(fid, _account);
			if (result == 1) {
				resDto.setState(0);
			} else {
				resDto.setState(1);
				resDto.setErrorcode(Errorcode.deleteUserFeed.getCode());
				resDto.setErrormsg(Errorcode.deleteUserFeed.getErrormsg());
			}
			return resDto.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 发布动态评论
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "comment", method = RequestMethod.POST)
	public @ResponseBody String comment(HttpServletRequest request, String json, String sid, Model model) {
		ResDto resDto = new ResDto();
		try {
			Account _account = userManager.getOnlineUser(sid);
			JSONObject jsonD = JsonUtil.getJson(request, json);

			int fid = JsonUtil.getInt(jsonD, "fid");
			String comment = JsonUtil.getString(jsonD, "content");
			int touid = JsonUtil.getInt(jsonD, "touid");
			FeedComment _feedComment = new FeedComment();
			_feedComment.setFid(fid);
			Feed feed = null;
			if (fid != 0) {
				feed = userManager.getByClassId(Feed.class, fid);
			}
			_feedComment.setFeed(feed);
			_feedComment.setComment(comment);
			_feedComment.setUid(_account.getId());
			_feedComment.setZan(0);
			_feedComment.setToUid(touid);
			_feedComment.setProfile(_account.getProfile());
			if (touid != 0) {
				Profile toProfile = userManager.getByClassId(Profile.class, touid);// 回复了谁
				_feedComment.setToProfile(toProfile);
			}
			feedManager.saveFeedComment(_feedComment);
			if (feed == null) {
				resDto.setState(1);
				resDto.setErrorcode(Errorcode.AddFeedComment.getCode());
				resDto.setErrormsg(Errorcode.AddFeedComment.getErrormsg());
			} else {
				// 获取要添加的金币数
				int gold = goldManager.getGold(_account.getId(), feed.getId(), Config.Comment, Config.CommentGold);
				if (gold != 0) {
					// 添加领取记录
					goldManager.addGold(_account.getProfile(), feed.getId(), Config.Comment, gold);
				}
				resDto.setState(0);
				resDto.setGold(gold);
				resDto.setData(new FeedCommentDto(_feedComment));
			}
			return resDto.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 动态赞
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "zan", method = RequestMethod.POST)
	public @ResponseBody String zan(HttpServletRequest request, String json, String sid, Model model) {
		ResDto res = new ResDto();
		try {
			Account _account = userManager.getOnlineUser(sid);
			JSONObject jsonD = JsonUtil.getJson(request, json);
			int fid = JsonUtil.getInt(jsonD, "fid");
			int type = JsonUtil.getInt(jsonD, "type");
			LOG.info("fid==" + fid + " type==" + type);
			FeedComment _feedComment = feedManager.getFeedZan(_account.getId(), fid);
			if (type == 0) {
				if (_feedComment == null) {
					_feedComment = new FeedComment();
					_feedComment.setFid(fid);
					_feedComment.setComment(null);
					_feedComment.setUid(_account.getId());
					_feedComment.setZan(1);
					Feed feed = feedManager.getByClassId(Feed.class, fid);
					_feedComment.setFeed(feed);
					_feedComment.setProfile(_account.getProfile());
					////////////////////
					_feedComment.setToUid(feed.getUid());
					Profile toprofile = userManager.getByClassId(Profile.class, feed.getUid());
					_feedComment.setToProfile(toprofile);
					/////////////////////
					_feedComment = feedManager.saveFeedComment(_feedComment);
					// 获取要添加的金币数
					int gold = goldManager.getGold(_account.getId(), feed.getId(), Config.Zan, Config.ZanGole);
					if (gold != 0) {
						// 添加领取记录
						goldManager.addGold(_account.getProfile(), feed.getId(), Config.Zan, gold);
					}
					res.setState(0);
					res.setGold(gold);
				} else {
					res.setState(1);
					res.setErrorcode(Errorcode.AlreadyZan.getCode());
					res.setErrormsg(Errorcode.AlreadyZan.getErrormsg());
				}
			} else {
				if (_feedComment != null) {
					feedManager.deletedFeedComment(_feedComment.getId(), _account);
					res.setState(0);
				} else {
					res.setState(1);
					res.setErrorcode(Errorcode.NOTZan.getCode());
					res.setErrormsg(Errorcode.NOTZan.getErrormsg());
				}
			}
			LOG.info(res.toString());
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 删除动态评论
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "deletecomment", method = RequestMethod.POST)
	public @ResponseBody String deleteComment(HttpServletRequest request, String json, String sid, Model model) {
		ResDto resDto = new ResDto();
		try {
			Account _account = userManager.getOnlineUser(sid);
			JSONObject jsonD = JsonUtil.getJson(request, json);
			int commentid = JsonUtil.getInt(jsonD, "commentid");
			if (commentid != 0) {
				int result = feedManager.deletedFeedComment(commentid, _account);
				if (result == 0) {
					resDto.setState(1);
					resDto.setErrorcode(Errorcode.deleteFeedComment.getCode());
					resDto.setErrormsg(Errorcode.deleteFeedComment.getErrormsg());
				} else {
					resDto.setState(0);
				}
			} else {
				resDto.setState(1);
				resDto.setErrorcode(Errorcode.NOTFeedComment.getCode());
				resDto.setErrormsg(Errorcode.NOTFeedComment.getErrormsg());
			}
			return resDto.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 取动态评论
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "comments", method = RequestMethod.POST)
	public @ResponseBody String comments(HttpServletRequest request, String json, String sid, Model model) {
		ResDto resDto = new ResDto();
		try {
			JSONObject jsonD = JsonUtil.getJson(request, json);
			int fid = JsonUtil.getInt(jsonD, "fid");
			int start = JsonUtil.getInt(jsonD, "start");
			int size = JsonUtil.getInt(jsonD, "size");
			// 为什么添加id<?条件
			List<FeedComment> _list = feedManager.getFeedComments(fid, start, size);
			List<FeedCommentDto> commentDtos = new ArrayList<FeedCommentDto>();
			for (FeedComment fc : _list) {
				FeedCommentDto commentDto = new FeedCommentDto(fc);
				commentDtos.add(commentDto);
			}
			resDto.setState(0);
			resDto.setData(commentDtos);
			return resDto.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 取动态赞的用户列表
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "zans", method = RequestMethod.POST)
	public @ResponseBody String zans(HttpServletRequest request, String json, String sid, Model model) {
		ResDto res = new ResDto();
		try {
			Account _account = userManager.getOnlineUser(sid);
			JSONObject jsonD = JsonUtil.getJson(request, json);
			int fid = JsonUtil.getInt(jsonD, "fid");
			int page = JsonUtil.getInt(jsonD, "page");
			int size = JsonUtil.getInt(jsonD, "size");
			res.setState(0);
			res.setData(getFeedZans(_account, fid, page, size));
			return res.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private List<FeedCommentDto> getFeedComments(int fid, int page, int size) {
		List<FeedCommentDto> _temp = new ArrayList<FeedCommentDto>();
		try {
			List<FeedComment> _list = feedManager.getFeedComments(fid, page, size);
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
			if (_list == null) {
				_list = new ArrayList<FeedComment>();
			}
			for (FeedComment fc : _list) {
				_temp.add(new UserDto(fc.getProfile()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return _temp;
	}

	private List<UserDto> getFeedZans(Account account, int fid, int page, int size) {
		int uid = 0;
		if (account != null) {
			uid = account.getId();
		}
		List<UserDto> _temp = new ArrayList<UserDto>();
		try {
			List<FeedComment> _list = feedManager.getFeedZans(fid, page, size);
			if (_list == null) {
				_list = new ArrayList<FeedComment>();
			}
			for (FeedComment fc : _list) {
				Profile profile = fc.getProfile();
				int friendid = 0;
				if (profile != null) {
					friendid = profile.getId();
				}
				int friendship = friendManager.getFriendShip(uid, friendid);// 好友关系
				_temp.add(new UserDto(profile, friendship));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return _temp;
	}
}

package com.k9sv.server.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.k9sv.Errorcode;
import com.k9sv.cache.UserCache;
import com.k9sv.domain.dto.ClassifyDto;
import com.k9sv.domain.dto.FeedCommentDto;
import com.k9sv.domain.dto.RecordDto;
import com.k9sv.domain.dto.ResDto;
import com.k9sv.domain.dto.UserDto;
import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.Classify;
import com.k9sv.domain.pojo.Feed;
import com.k9sv.domain.pojo.FeedComment;
import com.k9sv.domain.pojo.Record;
import com.k9sv.domain.pojo.RecordPic;
import com.k9sv.service.IClassifyManager;
import com.k9sv.service.IFeedManager;
import com.k9sv.service.IFriendManager;
import com.k9sv.service.IRecordManager;
import com.k9sv.service.IUserManager;
import com.k9sv.util.DateUtil;
import com.k9sv.util.JsonUtil;

/**
 * 宠物经历
 * 
 * @author mcp
 * 
 */
@Controller
@Scope("prototype")
@RequestMapping("/server/record")
public class ServerRecordController {

	@Autowired
	IUserManager userManager;
	@Autowired
	IRecordManager recordManager;
	@Autowired
	IClassifyManager classifyManager;
	@Autowired
	IFriendManager friendManager;
	@Autowired
	private IFeedManager feedManager;

	/**
	 * 记录类型
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/type", method = RequestMethod.POST)
	public String type(HttpServletRequest request, String json, String sid) {
		ResDto res = new ResDto();
		try {
			List<Classify> classifies = classifyManager.getRecordType();
			List<ClassifyDto> classifyDtos = new ArrayList<ClassifyDto>();
			for (Classify classify : classifies) {
				ClassifyDto classifyDto = new ClassifyDto(classify);
				classifyDtos.add(classifyDto);
			}
			res.setState(0);
			res.setData(classifyDtos);
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 添加或修改
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(HttpServletRequest request, String json, String sid) {
		ResDto res = new ResDto();
		Date _now = new Date();
		try {
			Account _account = userManager.getOnlineUser(sid);
			JSONObject jsObject = JsonUtil.getJson(request, json);
			int recordid = JsonUtil.getInt(jsObject, "recordid");
			Integer classifyid = JsonUtil.getInteger(jsObject, "typeid");// 记录类型
			String recordTimeStr = JsonUtil.getString(jsObject, "recordtime");// 疫苗时间
			Double weight = JsonUtil.getDouble(jsObject, "weight");
			Double height = JsonUtil.getDouble(jsObject, "height");
			String remark = JsonUtil.getString(jsObject, "remark");
			Integer dogid = JsonUtil.getInteger(jsObject, "dogid");
			JSONArray _deletePhotoList = JsonUtil.getJSONArray(jsObject,
					"deletephotos");// 删除的图片
			JSONArray _addPhotoList = JsonUtil.getJSONArray(jsObject,
					"addphotos");// 新添的图片
			Set<RecordPic> _deleteRecordPics = new HashSet<RecordPic>();
			Set<RecordPic> _addRecordPics = new HashSet<RecordPic>();
			if (_deletePhotoList != null) {
				for (int i = 0; i < _deletePhotoList.length(); i++) {
					JSONObject _jsonPhoto = (JSONObject) _deletePhotoList
							.get(i);
					String _photo = JsonUtil.getString(_jsonPhoto, "photo");
					_photo = "record-" + _photo.split("record-")[1];
					int _picId = JsonUtil.getInt(_jsonPhoto, "picid");
					if (_photo != null) {
						RecordPic _rp = new RecordPic();
						_rp.setId(_picId);
						_rp.setUrl(_photo);
						_rp.setUid(_account.getId());
						_rp.setCreated(_now);
						_deleteRecordPics.add(_rp);
					}
				}
			}
			if (_addPhotoList != null) {
				for (int i = 0; i < _addPhotoList.length(); i++) {
					JSONObject _jsonPhoto = (JSONObject) _addPhotoList.get(i);
					String _photo = _jsonPhoto.getString("photo");
					if (_photo != null) {
						RecordPic _rp = new RecordPic();
						_rp.setUrl(_photo);
						_rp.setUid(_account.getId());
						_rp.setCreated(_now);
						_addRecordPics.add(_rp);
					}

				}
			}
			Record record = null;
			if (recordid == 0) {// 新建
				record = new Record();
				record.setCreated(new Date());
				if (weight != null) {
					record.setWeight(weight);
				} else {
					record.setWeight(new Double(0));
				}
				if (height != null) {
					record.setHeight(height);
				} else {
					record.setHeight(new Double(0));
				}
			} else {
				record = recordManager.getByClassId(Record.class, recordid);
				if (_account.getId() != record.getUid()) {
					res.setState(1);
					res.setErrorcode(Errorcode.recordUpdate.getCode());
					res.setErrormsg(Errorcode.recordUpdate.getErrormsg());
					return res.toString();
				}
				if (weight != null) {
					record.setWeight(weight);
				}
				if (height != null) {
					record.setHeight(height);
				}
			}
			record.setUid(_account.getId());
			if (classifyid != null) {
				record.setClassifyid(classifyid);
				Classify classify = recordManager.getByClassId(Classify.class,
						classifyid);
				record.setClassify(classify);
			}
			if (recordTimeStr != null) {
				record.setRecordTime(DateUtil.getDateObj(recordTimeStr, "-"));
			}
			if (remark != null) {
				record.setRemark(remark);
			}
			if (dogid != null) {
				record.setDogid(dogid);
			}
			// 删除图片时必须要用到图片id，但保存图片有要用到记录id，接着保存记录又要图片列表
			Record _record = recordManager.saveOrUpdate(record,
					_deleteRecordPics, _addRecordPics);
			res.setState(0);
			res.setData(new RecordDto(_record, 0, 0, null, null));
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 删除记录
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
			int recordid = JsonUtil.getInt(jsObject, "recordid");
			int result = recordManager.delete(recordid, _account);
			if (result == 1) {
				res.setState(0);
			} else {
				res.setState(1);// 没有权限
				res.setErrorcode(Errorcode.recordDelete.getCode());
				res.setErrormsg(Errorcode.recordDelete.getErrormsg());
			}
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 宠物记录
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public String records(HttpServletRequest request, String json, String sid) {
		ResDto res = new ResDto();
		try {
			Account _account = userManager.getOnlineUser(sid);
			int uid = _account.getId();
			JSONObject jsObject = JsonUtil.getJson(request, json);
			Integer petid = JsonUtil.getInt(jsObject, "petid");
			Integer start = JsonUtil.getInt(jsObject, "start");
			Integer size = JsonUtil.getSize(jsObject, "size");
			List<Record> _records = recordManager.getDogRecords(petid, start,
					size);
			List<RecordDto> recordDtos = new ArrayList<RecordDto>();
			List<Feed> feeds = UserCache.UserZanFeeds.get(uid);
			for (Record record : _records) {
				Feed _feed = record.getFeed();
				int friendship = 0;
				int isZan = 0;
				List<FeedCommentDto> feedDtos = null;
				List<UserDto> userDtos = null;
				if (_feed != null) {
					friendship = friendManager.getFriendShip(uid,
							_feed.getUid());// 好友关系
					isZan = isZan(feeds, _feed);
					feedDtos = this.getFeedComments(_feed.getId(), 0, 3);
					userDtos = this.getFeedZans(_feed.getId(), 1, 10);
				}
				RecordDto recordDto = new RecordDto(record, friendship, isZan,
						feedDtos, userDtos);
				recordDtos.add(recordDto);
			}
			res.setState(0);
			res.setData(recordDtos);
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 记录详情
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
			int uid = _account.getId();
			JSONObject jsObject = JsonUtil.getJson(request, json);
			int recordid = JsonUtil.getInt(jsObject, "recordid");
			Record record = recordManager.getByClassId(Record.class, recordid);
			List<Feed> feeds = UserCache.UserZanFeeds.get(uid);
			Feed _feed = record.getFeed();
			int friendship = 0;
			int isZan = 0;
			List<FeedCommentDto> feedDtos = null;
			List<UserDto> userDtos = null;
			if (_feed != null) {
				friendship = friendManager.getFriendShip(uid, _feed.getUid());// 好友关系
				isZan = isZan(feeds, _feed);
				feedDtos = this.getFeedComments(_feed.getId(), 0, 30);
				userDtos = this.getFeedZans(_feed.getId(), 1, 10);
			}
			res.setState(0);
			res.setData(new RecordDto(record, friendship, isZan, feedDtos,
					userDtos));
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

}

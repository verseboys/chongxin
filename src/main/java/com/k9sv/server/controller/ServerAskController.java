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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.k9sv.Errorcode;
import com.k9sv.domain.dto.AnswerDto;
import com.k9sv.domain.dto.AskDto;
import com.k9sv.domain.dto.ResDto;
import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.Answer;
import com.k9sv.domain.pojo.AnswerPic;
import com.k9sv.domain.pojo.Ask;
import com.k9sv.domain.pojo.AskPic;
import com.k9sv.service.IAskManager;
import com.k9sv.service.IUserManager;
import com.k9sv.util.DateUtil;
import com.k9sv.util.JsonUtil;

@Controller
@Scope("prototype")
@RequestMapping("/server/ask/")
public class ServerAskController {

	@Autowired
	private IUserManager userManager;

	@Autowired
	private IAskManager askManager;

	/**
	 * 发布、修改问题
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody String save(HttpServletRequest request, String json, String sid, Model model) {
		ResDto res = new ResDto();
		try {
			// 用户
			Account _account = userManager.getOnlineUser(sid);
			// 请求数据
			JSONObject jsonD = JsonUtil.getJson(request, json);
			String content = JsonUtil.getString(jsonD, "content");
			int score = JsonUtil.getInt(jsonD, "score");
			int aid = JsonUtil.getInt(jsonD, "aid");
			// 问题图片的处理
			Set<AskPic> _pics = new HashSet<AskPic>();
			Ask _ask;
			if (aid > 0) {// 修改（删除）
				_ask = askManager.getAsk(aid);
				_pics = _ask.getAskPics();
			} else {
				_ask = new Ask();
				_ask.setDeleted(0);
				_ask.setStatus(0);
				_ask.setTotal(0);
			}
			_ask.setContent(content);
			_ask.setScore(score);
			_ask.setUid(_account.getId());
			_ask.setCreated(DateUtil.getFormatDateTime(new Date()));
			Set<AskPic> _list = new HashSet<AskPic>();
			JSONArray _photos = JsonUtil.getJSONArray(jsonD, "photos");
			if (_photos != null) {
				for (int i = 0; i < _photos.length(); i++) {
					JSONObject _photo = (JSONObject) _photos.get(i);
					String url = JsonUtil.getString(_photo, "photo");
					int id = JsonUtil.getInt(_photo, "pid");
					AskPic _askPic = new AskPic();
					_askPic.setPid(id);
					_askPic.setPhoto(url);
					_list.add(_askPic);
				}
			}
			if (_pics != null && _pics.size() > 0) {
				for (AskPic pic : _pics) {
					if (!_list.contains(pic)) {
						askManager.delete(pic);
					}
				}
			}
			_ask.setAskPics(_list);
			askManager.saveOrUpdateAsk(_ask);
			Ask ask = askManager.getByClassId(Ask.class, _ask.getId());
			res.setState(0);
			res.setData(new AskDto(ask));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res.toString();
	}

	/**
	 * 取问题列表
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
			List<AskDto> _temp = new ArrayList<AskDto>();
			JSONObject jsonD = JsonUtil.getJson(request, json);
			int start = JsonUtil.getInt(jsonD, "start");
			List<Ask> _list = askManager.loadAsk(0, start, 30);
			if (_list != null && _list.size() > 0) {
				for (Ask ask : _list) {
					_temp.add(new AskDto(ask));
				}
			}
			res.setData(_temp);
			res.setState(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res.toString();
	}

	/**
	 * 删除问题
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public @ResponseBody String delete(HttpServletRequest request, String json, String sid, Model model) {
		ResDto res = new ResDto();
		try {
			Account _account = userManager.getOnlineUser(sid);
			JSONObject jsonD = JsonUtil.getJson(request, json);
			int aid = JsonUtil.getInt(jsonD, "aid");
			int result = askManager.deleteAsk(aid, _account);
			if (result == 1) {
				res.setState(0);
			} else {
				res.setState(1);// 没有权限
				res.setErrorcode(Errorcode.deleteUserAsk.getCode());
				res.setErrormsg(Errorcode.deleteUserAsk.getErrormsg());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res.toString();
	}

	/**
	 * 添加回答
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "answer", method = RequestMethod.POST)
	public @ResponseBody String answer(HttpServletRequest request, String json, String sid, Model model) {
		ResDto res = new ResDto();
		try {
			// 用户
			Account _account = userManager.getOnlineUser(sid);
			// 请求数据
			JSONObject jsonD = JsonUtil.getJson(request, json);
			int aid = JsonUtil.getInt(jsonD, "aid");
			String content = JsonUtil.getString(jsonD, "content");
			int toUid = JsonUtil.getInt(jsonD, "touid");
			Answer _answer = new Answer();
			// 对哪个问题的回答
			Ask _ask = askManager.getByClassId(Ask.class, aid);
			JSONArray _photos = JsonUtil.getJSONArray(jsonD, "photos");
			// 回答中的图片
			Set<AnswerPic> pics = new HashSet<AnswerPic>();
			if (_ask != null) {
				if (_photos != null) {
					for (int i = 0; i < _photos.length(); i++) {
						JSONObject _photo = (JSONObject) _photos.get(i);
						String url = _photo.getString("photo");
						AnswerPic answerPic = new AnswerPic();
						answerPic.setPhoto(url);
						pics.add(answerPic);
					}
				}
				_answer.setAskId(aid);
				_answer.setContent(content);
				_answer.setUid(_account.getId());
				_answer.setToUid(toUid);
				_answer.setCreated(DateUtil.getFormatDateTime(new Date()));
				_answer.setAnswerPics(pics);
				// askManager.answer(_answer);
				askManager.saveOrUpdateAnswer(_answer);
			}
			Answer answer = askManager.getByClassId(Answer.class, _answer.getId());
			res.setState(0);
			res.setData(new AnswerDto(answer));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res.toString();
	}

	/**
	 * 删除回答
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "deleteanswer", method = RequestMethod.POST)
	public @ResponseBody String deleteAnswer(HttpServletRequest request, String json, String sid, Model model) {
		ResDto res = new ResDto();
		try {
			// 用户
			Account _account = userManager.getOnlineUser(sid);
			// 请求数据
			JSONObject jsonD = JsonUtil.getJson(request, json);
			int aid = JsonUtil.getInt(jsonD, "answerid");
			int result = askManager.deleteAnswer(aid, _account);
			if (result == 1) {
				res.setState(0);
			} else {
				res.setState(1);// 没有权限
				res.setErrorcode(Errorcode.deleteUserAnswer.getCode());
				res.setErrormsg(Errorcode.deleteUserAnswer.getErrormsg());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res.toString();
	}

	/**
	 * 取答案列表
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "answers", method = RequestMethod.POST)
	public @ResponseBody String answers(HttpServletRequest request, String json, String sid, Model model) {
		ResDto res = new ResDto();
		try {
			List<AnswerDto> _temp = new ArrayList<AnswerDto>();
			JSONObject jsonD = JsonUtil.getJson(request, json);
			Integer aid = JsonUtil.getInt(jsonD, "aid");
			int page = JsonUtil.getPage(jsonD, "page");
			int size = JsonUtil.getSize(jsonD, "size");
			List<Answer> _list = askManager.loadAnswer(aid, page, size);
			if (_list != null && _list.size() > 0) {
				for (Answer answer : _list) {
					_temp.add(new AnswerDto(answer));
				}
			}
			res.setData(_temp);
			res.setState(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res.toString();
	}
}

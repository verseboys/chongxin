package com.k9sv.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.Answer;
import com.k9sv.domain.pojo.AnswerPic;
import com.k9sv.domain.pojo.Ask;
import com.k9sv.domain.pojo.AskPic;
import com.k9sv.service.IAskManager;
import com.k9sv.util.DateUtil;
import com.k9sv.util.QiniuFileUtil;
import com.k9sv.util.StringUtil;

@Service("askManager")
public class AskManager extends BaseManager implements IAskManager {

	@Override
	public List<Ask> loadAsk(int deleted, int start, int size) {
		if (start == 0) {
			return super.query("from Ask where deleted = 0 order by id desc",
					null, 1, size);
		} else {
			return super.query(
					"from Ask where deleted = 0 and id < ? order by id desc",
					new Object[] { start }, 1, size);
		}

	}

	@Override
	public List<Ask> loadAsk(int uid, int deleted, int page, int size) {
		return super.query(
				"from Ask where uid=? and deleted = ? order by id desc",
				new Object[] { uid, deleted }, page, size);
	}

	@Override
	public List<Answer> loadAnswer(int askId, int page, int size) {
		return super.query("from Answer where askId=? and deleted = 0 order by id desc",
				new Object[] { askId }, page, size);
	}

	@Override
	public void ask(Ask ask) {
		String now = DateUtil.getFormatDateTime(new Date());
		ask.setCreated(now);
		super.save(now);

	}

	@Override
	public void answer(Answer answer) {
		String now = DateUtil.getFormatDateTime(new Date());
		answer.setCreated(now);
		// super.saveOrUpdate(answer);
		super.save(answer);

		Ask _ask = super.getByClassId(Ask.class, answer.getAskId());
		_ask.setTotal(_ask.getTotal() + 1);
		// super.saveOrUpdate(answer);
		super.update(_ask);

	}

	@Override
	public void deleteAnswer(int answerId) {
		Answer _answer = super.getByClassId(Answer.class, answerId);
		Ask _ask = super.getByClassId(Ask.class, _answer.getAskId());
		_ask.setTotal(_ask.getTotal() - 1);
		super.update(_ask);
		super.delete(_answer);

	}

	@Override
	public Ask getAsk(int id) {
		Ask _ask = super.getByClassId(Ask.class, id);
		if (_ask == null) {
			return null;
		}
		/*
		 * List<AskPic> _pics = super.query(
		 * "from AskPic where aid=? order by id desc", new Object[] { id }, 1,
		 * 10); _ask.setPics(_pics);
		 */
		return _ask;
	}

	@Override
	public int deleteAnswer(int aid, Account account) {
		Answer answer = super.getByClassId(Answer.class, aid);
		if (answer.getUid() == account.getId()) {
			answer.setDeleted(1);
			Ask _ask = super.getByClassId(Ask.class, answer.getAskId());
			_ask.setTotal(_ask.getTotal() - 1);
			super.update(answer);
			super.update(_ask);
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int deleteAsk(int aid, Account account) {
		Ask ask = super.getByClassId(Ask.class, aid);
		if (ask.getUid() == account.getId()) {
			ask.setDeleted(1);
			super.update(ask);
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public void saveOrUpdateAsk(Ask _ask) {

		saveOrUpdate(_ask);
		Set<AskPic> _list = _ask.getAskPics();
		if (_list != null) {
			for (AskPic pic : _list) {
				if (pic.getPid() == 0) {
					pic.setAid(_ask.getId());
					super.save(pic);
					QiniuFileUtil.uploadFile(pic.getPhoto());
					// FileUtil.uploadImgToOSS(fp.getUrl());
				}
			}
		}
	}

	@Override
	public void saveOrUpdateAnswer(Answer _answer) {
		_answer.setDeleted(0);
		_answer.setStatus(0);
		saveOrUpdate(_answer);
		Set<AnswerPic> _list = _answer.getAnswerPics();
		if (_list != null) {
			for (AnswerPic pic : _list) {
				pic.setAid(_answer.getId());
				super.saveOrUpdate(pic);
				QiniuFileUtil.uploadFile(pic.getPhoto());
				// FileUtil.uploadImgToOSS(fp.getUrl());
			}
		}
		Ask _ask = super.getByClassId(Ask.class, _answer.getAskId());
		_ask.setTotal(_ask.getTotal() + 1);
		super.saveOrUpdate(_ask);
	}

	@Override
	public Integer getCount(String title) {
		if (StringUtil.isNotEmpty(title)) {
			Long total = (Long) super
					.getCount(
							"select count(id) from Ask where deleted = 0 and content like ?",
							new Object[] { "%" + title + "%" });
			return total.intValue();
		} else {
			Long total = (Long) super.getCount(
					"select count(id) from Ask where deleted = 0", null);
			return total.intValue();
		}
	}

	@Override
	public List<Ask> getAsks(String title, Integer pageNum, Integer numPerPage) {
		if (StringUtil.isNotEmpty(title)) {
			return super
					.query("from Ask where deleted = 0 and content like ? order by created desc",
							new Object[] { "%" + title + "%" }, pageNum,
							numPerPage);
		} else {
			return super.query(
					"from Ask where deleted = 0 order by created desc", null,
					pageNum, numPerPage);
		}
	}

	@Override
	public Integer getCount(String title, Integer id) {
		if (StringUtil.isNotEmpty(title)) {
			Long total = (Long) super
					.getCount(
							"select count(id) from Answer where deleted = 0 and askId = ? and content like ?",
							new Object[] { id, "%" + title + "%" });
			return total.intValue();
		} else {
			Long total = (Long) super
					.getCount(
							"select count(id) from Answer where deleted = 0 and askId = ? ",
							new Object[] { id });
			return total.intValue();
		}
	}

	@Override
	public List<Answer> getAnswers(String title, Integer id, Integer pageNum,
			Integer numPerPage) {
		if (StringUtil.isNotEmpty(title)) {
			return super
					.query("from Answer where deleted = 0 and askId = ? and content like ? order by created desc",
							new Object[] { id, "%" + title + "%" }, pageNum,
							numPerPage);
		} else {
			return super
					.query("from Answer where deleted = 0 and askId = ? order by created desc",
							new Object[] { id }, pageNum, numPerPage);
		}
	}

}

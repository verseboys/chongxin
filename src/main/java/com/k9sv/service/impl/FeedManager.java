package com.k9sv.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.k9sv.Config;
import com.k9sv.cache.UserCache;
import com.k9sv.cache.server.CacheServer;
import com.k9sv.domain.dto.PushCommentDto;
import com.k9sv.domain.dto.PushDto;
import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.Dog;
import com.k9sv.domain.pojo.Feed;
import com.k9sv.domain.pojo.FeedComment;
import com.k9sv.domain.pojo.FeedPic;
import com.k9sv.domain.pojo.Friend;
import com.k9sv.domain.pojo.Goldrecord;
import com.k9sv.domain.pojo.Profile;
import com.k9sv.domain.pojo.Record;
import com.k9sv.service.IFeedManager;
import com.k9sv.service.IUserManager;
import com.k9sv.util.GetuiPush;
import com.k9sv.util.QiniuFileUtil;
import com.k9sv.util.StringUtil;

@SuppressWarnings("unchecked")
@Service("feedManager")
public class FeedManager extends BaseManager implements IFeedManager {

	@Autowired
	IUserManager userManager;

	@Override
	public List<Feed> getFeeds(int uid, int roleid, int type, int start, int size) {
		if (type == 0) {
			return this.getFeeds(roleid, start, size);
		}
		return this.getFriendFeeds(uid, roleid, start, size);
	}

	public List<Feed> getFeeds(int roleid, int start, int pagesize) {
		if (start == 0) {
			String hql = "from Feed where deleted = 0 order by id desc";
			if (!Config.TaintedUser.equals(roleid)) {
				hql = "from Feed where deleted = 0 and profile.roleId !=" + Config.TaintedUser + " order by id desc";
			}
			return super.queryStart(hql, null, start, pagesize);

		} else {
			String hql = "from Feed where deleted = 0 and id < ? order by id desc";
			if (!Config.TaintedUser.equals(roleid)) {
				hql = "from Feed where deleted = 0 and profile.roleId !=" + Config.TaintedUser
						+ " and id < ? order by id desc";
			}
			return super.queryStart(hql, new Object[] { start }, 0, pagesize);
		}

	}

	public List<Feed> getFriendFeeds(int uid, int roleid, int start, int size) {
		List<Integer> friendId = UserCache.FriendShip.get(uid);
		if (friendId == null || friendId.size() == 0) {
			return new ArrayList<Feed>();
		}
		Set<Integer> friendId2 = new HashSet<Integer>(friendId);
		Object[] objects = friendId2.toArray();
		if (start == 0) {
			String hql = "from Feed where deleted = 0 and uid in(:ids) order by id desc";
			if (!Config.TaintedUser.equals(roleid)) {
				hql = "from Feed where deleted = 0 and profile.roleId !=" + Config.TaintedUser
						+ " and uid in(:ids) order by id desc";
			}
			return getObjects(hql, objects, null, start, size);
		} else {
			String hql = "from Feed where deleted = 0 and id < ? and uid in(:ids) order by id desc";
			if (!Config.TaintedUser.equals(roleid)) {
				hql = "from Feed where deleted = 0 and profile.roleId !=" + Config.TaintedUser
						+ " and id < ? and uid in(:ids) order by id desc";
			}
			return getObjects(hql, objects, new Object[] { start }, 0, size);
		}
	}

	public List<Feed> getFriendFeeds2(int uid, int pageIndex, int pageSize) {
		List<Friend> _list = query("from Friend where uid = ? and status > 0", new Object[] { uid }, 1, 1000);
		StringBuffer str = new StringBuffer("(");
		if (_list != null && _list.size() > 0) {
			int i = 0;
			for (Friend f : _list) {
				i++;
				str.append(f.getFid());
				if (i < _list.size()) {
					str.append(",");
				}
			}
			str.append(")");
			return query("from Feed where uid in " + str.toString() + " order by id desc", null, pageIndex, pageSize);
		} else {
			return null;
		}

	}

	@Override
	public int saveFeed(Feed feed) {
		int fid = feed.getUid();
		if (fid == 0) {
			return 0;
		}
		feed.setDeleted(0);
		feed.setReplyCount(0);
		feed.setGoodCount(0);
		super.save(feed);
		Set<FeedPic> _list = feed.getFeedPics();
		if (_list != null) {
			for (FeedPic fp : _list) {
				fp.setFid(feed.getId());
				super.save(fp);
				QiniuFileUtil.uploadFile(fp.getUrl());
				// FileUtil.uploadImgToOSS(fp.getUrl());
			}
		}
		// 维护我的动态
		List<Feed> feeds = UserCache.MeFeed.get(fid);
		if (feeds == null) {
			feeds = new ArrayList<Feed>();
		}
		feeds.add(feed);
		UserCache.MeFeed.put(fid, feeds);
		UserCache.lastFeed.put(fid, feed);
		Profile _p = load(Profile.class, fid);
		_p.setFeedsCount(_p.getFeedsCount() + 1);
		update(_p);
		return 1;

	}

	@Override
	public List<Feed> getUserFeeds(int uid, int start, int pageSize) {
		if (start > 0) {
			return query("from Feed where uid=? and deleted = 0 and id < ? order by id desc",
					new Object[] { uid, start }, 1, pageSize);
		} else {
			return query("from Feed where uid=? and deleted = 0 order by id desc", new Object[] { uid, }, 1, pageSize);
		}

	}

	@Override
	public int deleteFeed(int id, Account account) {
		Feed _feed = super.getByClassId(Feed.class, id);
		if (_feed.getUid() == account.getId()) {
			Record record = getRecordByFid(_feed.getId());
			if (record != null) {
				delete(record);
			}
			_feed.setDeleted(1);
			super.update(_feed);
			// 维护我的动态
			List<Feed> feeds = UserCache.MeFeed.get(account.getId());
			if (feeds == null) {
				feeds = new ArrayList<Feed>();
			}
			feeds.remove(_feed);
			UserCache.MeFeed.put(account.getId(), feeds);
			return 1;
		} else {
			return 0;
		}

	}

	private Record getRecordByFid(Integer feedid) {
		List<Record> records = super.find("from Record where feedid = ? order by created desc",
				new Object[] { feedid });
		Record record = null;
		if (records != null && records.size() > 0) {
			record = records.get(0);
		}
		return record;
	}

	@Override
	public FeedComment saveFeedComment(FeedComment feedComment) {
		Date _now = new Date();
		Feed _feed = feedComment.getFeed();
		if (_feed == null) {
			return null;
		}
		if (feedComment.getZan() == 1) {
			_feed.setGoodCount(_feed.getGoodCount() + 1);
			// 维护我的动态
			List<Feed> feeds = UserCache.MeFeed.get(_feed.getUid());
			if (feeds == null) {
				feeds = new ArrayList<Feed>();
			}
			feeds.remove(_feed);
			feeds.add(_feed);
			UserCache.MeFeed.put(_feed.getUid(), feeds);
			// 更新宠物被赞次数
			Integer dogid = _feed.getRelationId();
			if (dogid != null && dogid.intValue() > 0) {
				Dog dog = super.getByClassId(Dog.class, dogid);
				dog.setZan(dog.getZan() + 1);
				super.update(dog);
			}
		} else {
			_feed.setReplyCount(_feed.getReplyCount() + 1);
		}
		feedComment.setCreated(_now);
		super.save(feedComment);
		super.update(_feed);
		CacheServer.addComment(_feed, feedComment);
		if ((feedComment.getZan() != 1) || (feedComment.getZan() == 1
				&& this.getGoldRecord(feedComment.getUid(), feedComment.getFid(), Config.Zan) == null)) {
			this.pushComments(feedComment, _feed);
		}
		return feedComment;
	}

	private Goldrecord getGoldRecord(Integer uid, Integer fid, Integer type) {
		List<Goldrecord> records = super.find(
				"from Goldrecord where uid = ? and fid = ? and type = ? order by createdtime desc",
				new Object[] { uid, fid, type });
		Goldrecord record = null;
		if (records != null && records.size() > 0) {
			record = records.get(0);
		}
		return record;
	}

	/**
	 * 
	 * @param feedComment
	 *            推送是评论
	 * @param comments
	 *            要推送的人
	 */
	private void pushComments(final FeedComment feedComment, final Feed _feed) {
		Thread t = new Thread(new Runnable() {

			public void run() {
				try {
					PushCommentDto messageDto = new PushCommentDto(feedComment);
					PushDto pushDto = new PushDto();
					Profile fromProfile = feedComment.getProfile();
					pushDto.setData(messageDto);
					if (feedComment.getZan() == 1) {// 点赞只推动态
						if (fromProfile.getId() != _feed.getUid()) {// 赞自己不推
							String alter = fromProfile.getNickName() + "赞了你";
							Profile toProfile = _feed.getProfile();
							pushDto.setType(Config.PushFeedZan);
							GetuiPush.push(alter, pushDto, toProfile);
						}
					} else {
						List<Account> accounts = getAccounts(_feed, feedComment.getUid());
						String alter = feedComment.getComment();
						Profile toProfile = feedComment.getToProfile();
						if (alter.length() > 20) {
							alter = alter.substring(0, 19) + "...";
						}
						alter = fromProfile.getNickName() + " 评论 " + toProfile.getNickName() + ":" + alter;
						pushDto.setType(Config.PushFeedComment);
						GetuiPush.pushListMessage(fromProfile, pushDto, accounts, alter);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		t.start();
	}

	private List<Account> getAccounts(Feed _feed, Integer uid) {
		Integer fid = _feed.getId();
		// 与此动态有关的用户（不包括发此条评论的人）
		String hql = "select (uid) from FeedComment where fid = ? and uid != ?";
		List<Integer> uids = super.find(hql, new Object[] { fid, uid });
		if (_feed.getUid() != uid) {// 发评论的人和发动态的人不是同一个
			uids.add(_feed.getUid());
		}
		Set<Integer> comments = new HashSet<Integer>(uids);
		Object[] ids = comments.toArray();
		String hql2 = "from Account a where a.id in(:ids)";
		List<Account> accounts = baseDao.getObjects(hql2, ids);
		return accounts;
	}

	@Override
	public int deletedFeedComment(int id, Account account) {
		FeedComment feedComment = super.getByClassId(FeedComment.class, id);
		Feed _feed = super.getByClassId(Feed.class, feedComment.getFid());
		if (feedComment.getUid() != account.getId()) {
			return 0;
		}
		if (feedComment.getZan() == 1) {
			if (_feed.getGoodCount() > 0) {
				_feed.setGoodCount(_feed.getGoodCount() - 1);
			}

			// 维护我的动态
			List<Feed> feeds = UserCache.MeFeed.get(_feed.getUid());
			if (feeds == null) {
				feeds = new ArrayList<Feed>();
			}
			feeds.remove(_feed);
			feeds.add(_feed);
			UserCache.MeFeed.put(_feed.getUid(), feeds);
			// 更新宠物被赞次数
			Integer dogid = _feed.getRelationId();
			if (dogid != null && dogid.intValue() > 0) {
				Dog dog = super.getByClassId(Dog.class, dogid);
				int zan = dog.getZan() - 1;
				if (zan < 0) {
					zan = 0;
				}
				dog.setZan(zan);
				super.update(dog);
			}
		} else {
			_feed.setReplyCount(_feed.getReplyCount() - 1);
		}
		super.update(_feed);
		CacheServer.deleteComment(_feed, feedComment);
		super.delete(feedComment);

		return 1;
	}

	@Override
	public FeedComment getFeedZan(int uid, int fid) {
		List<FeedComment> _list = super.queryStart("from FeedComment where fid = ? and uid = ? and zan = 1",
				new Object[] { fid, uid }, 0, 1);
		if (_list == null) {
			return null;
		}
		return _list.size() == 1 ? _list.get(0) : null;
	}

	@Override
	public List<FeedComment> getFeedComments(int fid, int start, int pageSize) {
		if (start == 0) {
			start = 1000000000;
		}
		List<FeedComment> _list = super.query("from FeedComment where fid = ? and zan = 0 and id < ? order by id desc",
				new Object[] { fid, start }, 1, pageSize);
		return _list;
	}

	@Override
	public List<FeedComment> getFeedZans(int fid, int pageIndex, int pageSize) {
		List<FeedComment> _list = super.query("from FeedComment where fid = ? and zan = 1 order by id desc",
				new Object[] { fid }, pageIndex, pageSize);
		return _list;
	}

	@Override
	public Integer getCount(String title) {
		if (StringUtil.isNotEmpty(title)) {
			Long total = (Long) super.getCount("select count(id) from Feed where deleted = 0 and content like ?",
					new Object[] { "%" + title + "%" });
			return total.intValue();
		} else {
			Long total = (Long) super.getCount("select count(id) from Feed where deleted = 0", null);
			return total.intValue();
		}
	}

	@Override
	public List<Feed> getFeeds(String title, Integer pageNum, Integer numPerPage) {
		if (StringUtil.isNotEmpty(title)) {
			return super.query("from Feed where deleted = 0 and content like ? order by created desc",
					new Object[] { "%" + title + "%" }, pageNum, numPerPage);
		} else {
			return super.query("from Feed where deleted = 0 order by created desc", null, pageNum, numPerPage);
		}
	}

	@Override
	public void deleteFeed(Integer id) {
		super.executeSQL("delete from Feed where id = ?", new Object[] { id });
		super.executeSQL("delete from FeedComment where fid = ?", new Object[] { id });
	}

	@Override
	public void deleteFeedComment(Integer id) {
		super.executeSQL("delete from FeedComment where id = ?", new Object[] { id });
	}

	@Override
	public Integer getCount(String title, Integer id) {
		if (StringUtil.isNotEmpty(title)) {
			Long total = (Long) super.getCount(
					"select count(id) from FeedComment where zan = 0 and fid = ? and comment like ?",
					new Object[] { id, "%" + title + "%" });
			return total.intValue();
		} else {
			Long total = (Long) super.getCount("select count(id) from FeedComment where zan = 0 and fid = ? ",
					new Object[] { id });
			return total.intValue();
		}
	}

	@Override
	public List<FeedComment> getFeedComments(String title, Integer id, Integer pageNum, Integer numPerPage) {
		if (StringUtil.isNotEmpty(title)) {
			return super.query("from FeedComment where zan = 0 and fid = ? and comment like ? order by created desc",
					new Object[] { id, "%" + title + "%" }, pageNum, numPerPage);
		} else {
			return super.query("from FeedComment where zan = 0 and fid = ? order by created desc", new Object[] { id },
					pageNum, numPerPage);
		}
	}

	@Override
	public List<Feed> getDogFeeds(Integer petid, Integer start, Integer pagesize) {
		if (start == 0) {
			return super.queryStart("from Feed where deleted = 0 and relationId = ? order by id desc",
					new Object[] { petid }, start, pagesize);
		} else {
			return super.queryStart("from Feed where deleted = 0 and id < ? and relationId = ? order by id desc",
					new Object[] { start, petid }, 0, pagesize);
		}
	}

	@Override
	public Integer getMaxFeed(Integer maxfeedid) {
		List<Integer> _list = queryStart("select id from Feed where id >= ? and deleted = 0 order by id desc",
				new Object[] { maxfeedid }, 0, 1);
		if (_list == null) {
			return null;
		}
		return _list.size() == 1 ? _list.get(0) : null;
	}

	@Override
	public List<FeedComment> getComments(int trueUid, int page, int size) {
		return super.query("from FeedComment where feed.deleted = 0 and feed.uid = ? order by created desc",
				new Object[] { trueUid }, page, size);
	}

	@Override
	public Integer getCount(Integer accountId, String title) {
		if (StringUtil.isNotEmpty(title)) {
			Long total = (Long) super.getCount(
					"select count(id) from Feed where deleted = 0 and content like ? and uid = ?",
					new Object[] { "%" + title + "%", accountId });
			return total.intValue();
		} else {
			Long total = (Long) super.getCount("select count(id) from Feed where deleted = 0 and uid = ?",
					new Object[] { accountId });
			return total.intValue();
		}
	}

	@Override
	public List<Feed> getFeeds(Integer accountId, String title, Integer pageNum, Integer numPerPage) {
		if (StringUtil.isNotEmpty(title)) {
			return super.query("from Feed where deleted = 0 and content like ? and uid = ? order by created desc",
					new Object[] { "%" + title + "%", accountId }, pageNum, numPerPage);
		} else {
			return super.query("from Feed where deleted = 0 and uid = ? order by created desc",
					new Object[] { accountId }, pageNum, numPerPage);
		}
	}

	@Override
	public Integer getCount2(int accountId, String title) {
		if (accountId == 0) {
			return this.getCount(title);
		}
		return this.getCount(accountId, title);
	}

	@Override
	public List<Feed> getFeeds2(int accountId, String title, Integer pageNum, Integer numPerPage) {
		if (accountId == 0) {
			return this.getFeeds(title, pageNum, numPerPage);
		}
		return this.getFeeds(accountId, title, pageNum, numPerPage);
	}

	@Override
	public int getDogFeedCount(Dog dog) {
		if (dog != null) {
			Long total = (Long) super.getCount("select count(id) from Feed where deleted = 0 and relationId = ?",
					new Object[] { dog.getBlood() });
			if (total == null) {
				return 0;
			}
			return total.intValue();
		}
		return 0;
	}

}

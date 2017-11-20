package com.k9sv.schedule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.k9sv.cache.UserCache;
import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.Feed;
import com.k9sv.domain.pojo.FeedComment;
import com.k9sv.domain.pojo.Friend;
import com.k9sv.domain.pojo.OnlineUser;
import com.k9sv.domain.pojo.Profile;
import com.k9sv.service.IDogManager;
import com.k9sv.service.IFeedManager;
import com.k9sv.service.IUserManager;

@Component
@SuppressWarnings("unchecked")
public class UserSchedule {

	private static final Logger LOG = Logger.getLogger(UserSchedule.class);

	@Autowired
	IUserManager userManager;
	@Autowired
	IFeedManager feedManager;
	@Autowired
	IDogManager dogManager;
	private boolean isRun = true;

	@Scheduled(fixedRate = 1000 * 60 * 60 * 1)
	public void scheduleRun() {
		if (isRun) {
			isRun = false;
			this.init();
			this.setOnlineUsers();
			this.setUserZanFeeds();
			this.setFeedComments();
			this.setFeedZanUsers();
			this.setFriendShip();
			this.setMeAttention();
			this.setAttentionMe();
			this.setMeFeed();
			this.setMyRecommend();

			LOG.info("加载User完成.........");
		}
	}

	/**
	 * 好友关系
	 */
	private void setFriendShip() {
		List<Account> accounts = UserCache.accounts;
		for (Account account : accounts) {
			Integer aid = account.getId();
			String hql = "select (fid) from Friend where uid = ?";
			List<Integer> friendids = userManager.find(hql,
					new Object[] { aid });
			// 判断好友关系
			UserCache.FriendShip.put(aid, friendids);
		}
	}

	/**
	 * 我关注的人
	 */
	private void setMeAttention() {
		List<Account> accounts = UserCache.accounts;
		for (Account account : accounts) {
			Integer aid = account.getId();
			String hql = "from Friend where uid = ? order by created desc";
			List<Friend> friendids = userManager
					.find(hql, new Object[] { aid });
			if (friendids.size() == 0) {
				continue;
			}
			List<Profile> profiles = new ArrayList<Profile>();
			for (Friend friend : friendids) {
				Profile profile = friend.getfProfile();
				profiles.add(profile);
			}
			// 我关注的人
			UserCache.MeAttention.put(aid, profiles);
		}
	}

	/**
	 * 关注我的人
	 */
	private void setAttentionMe() {
		List<Account> accounts = UserCache.accounts;
		for (Account account : accounts) {
			Integer aid = account.getId();
			String hql = "from Friend where fid = ? order by created desc";
			List<Friend> friendids = userManager
					.find(hql, new Object[] { aid });
			if (friendids.size() == 0) {
				continue;
			}
			List<Profile> profiles = new ArrayList<Profile>();
			for (Friend friend : friendids) {
				Profile profile = friend.getProfile();
				profiles.add(profile);
			}
			// 关注我的人
			UserCache.AttentionMe.put(aid, profiles);
		}
	}

	/**
	 * 我推荐的人
	 */
	private void setMyRecommend() {
		List<Account> accounts = UserCache.accounts;
		for (Account account : accounts) {
			Integer aid = account.getId();
			String hql = "from Profile where account.deleted = 0 and fromId = ? order by id";
			List<Profile> recommends = userManager.find(hql,
					new Object[] { aid });
			// 我推荐的人
			UserCache.MyRecommend.put(aid, recommends);
		}
	}

	/**
	 * 初始化数据
	 */
	private void init() {
		String hql = "from Account where deleted = 0";
		UserCache.accounts = userManager.find(hql, null);
		String hql2 = "from Feed where deleted = 0";
		UserCache.feeds = feedManager.find(hql2, null);
	}

	/**
	 * 用户赞过的动态
	 */
	private void setUserZanFeeds() {
		List<Account> accounts = UserCache.accounts;
		for (Account account : accounts) {
			Integer aid = account.getId();
			String hql = "select (fid) from FeedComment where uid = ? and zan = 1";
			List<Integer> commentFid = feedManager.find(hql,
					new Object[] { aid });
			if (commentFid.size() == 0) {
				continue;
			}
			Object[] objects = commentFid.toArray();
			String hql2 = "from Feed f where f.id in(:ids)";
			List<Feed> feeds = feedManager.getObjects(hql2, objects);
			UserCache.UserZanFeeds.put(aid, feeds);
		}
	}

	/**
	 * 动态 的评论
	 */
	private void setFeedComments() {
		List<Feed> feeds = UserCache.feeds;
		for (Feed feed : feeds) {
			Integer fid = feed.getId();
			String hql = "from FeedComment where fid = ? and zan = 0";
			List<FeedComment> comments = feedManager.find(hql,
					new Object[] { fid });
			UserCache.FeedComments.put(fid, comments);
		}
	}

	/**
	 * 动态 赞的用户
	 */
	private void setFeedZanUsers() {
		List<Feed> feeds = UserCache.feeds;
		for (Feed feed : feeds) {
			Integer fid = feed.getId();
			String hql = "select (uid) from FeedComment where fid = ? and zan = 1";
			List<Integer> commentUid = feedManager.find(hql,
					new Object[] { fid });
			if (commentUid.size() == 0) {
				continue;
			}
			Set<Integer> commentUid2 = new HashSet<Integer>(commentUid);
			Object[] objects = commentUid2.toArray();
			String hql2 = "from Profile p where p.id in(:ids)";
			List<Profile> profiles = userManager.getObjects(hql2, objects);
			UserCache.FeedZanUsers.put(fid, profiles);
		}
	}

	/**
	 * 我的动态
	 */
	private void setMeFeed() {
		List<Account> accounts = UserCache.accounts;
		for (Account account : accounts) {
			Integer aid = account.getId();
			String hql = "from Feed where uid = ? and deleted = 0 order by created desc";
			List<Feed> feeds = feedManager.find(hql, new Object[] { aid });
			UserCache.MeFeed.put(aid, feeds);
			if (feeds != null && feeds.size() > 0) {
				UserCache.lastFeed.put(aid, feeds.get(0));
			}
		}
	}

	/**
	 * 在线用户
	 */
	private void setOnlineUsers() {
		String hql = "from OnlineUser";
		List<OnlineUser> onlineUsers = userManager.find(hql, null);
		for (OnlineUser onlineUser : onlineUsers) {
			String onlineUserid = onlineUser.getId();
			UserCache.OnlineUsers.add(onlineUserid);
			UserCache.OnlineUsersMap.put(onlineUserid, onlineUser);
		}
		// UserCache.OnlineUsers = userManager.getOnlineUser();
	}

}

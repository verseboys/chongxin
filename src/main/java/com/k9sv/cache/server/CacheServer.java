package com.k9sv.cache.server;

import java.util.ArrayList;
import java.util.List;

import com.k9sv.cache.UserCache;
import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.Feed;
import com.k9sv.domain.pojo.FeedComment;
import com.k9sv.domain.pojo.Profile;

/**
 * 缓存管理
 * 
 * @author mcp
 * 
 */
public class CacheServer {
	/**
	 * 添加赞或评论
	 * 
	 * @param _feed
	 * @param comment
	 */
	public static void addComment(Feed _feed, FeedComment comment) {

		Integer fid = comment.getFid();
		if (comment.getZan() == 1) {
			Integer uid = comment.getUid();
			List<Feed> feeds = UserCache.UserZanFeeds.get(uid);
			if (feeds == null) {
				feeds = new ArrayList<Feed>();
			}
			feeds.add(_feed);
			UserCache.UserZanFeeds.put(uid, feeds);

			List<Profile> profiles = UserCache.FeedZanUsers.get(fid);
			if (profiles == null) {
				profiles = new ArrayList<Profile>();
			}
			Profile profile = comment.getProfile();
			profiles.add(profile);
			UserCache.FeedZanUsers.put(fid, profiles);
		} else {
			List<FeedComment> comments = UserCache.FeedComments.get(fid);
			if (comments == null) {
				comments = new ArrayList<FeedComment>();
			}
			comments.add(comment);
			UserCache.FeedComments.put(fid, comments);
		}
	}

	/**
	 * 删除评论或取消赞
	 * 
	 * @param _feed
	 * @param comment
	 */
	public static void deleteComment(Feed _feed, FeedComment comment) {
		if (comment.getZan() == 1) {
			Integer uid = comment.getUid();
			List<Feed> feeds = UserCache.UserZanFeeds.get(uid);
			feeds.remove(_feed);
			UserCache.UserZanFeeds.put(uid, feeds);

			Integer fid = comment.getFid();
			List<Profile> profiles = UserCache.FeedZanUsers.get(fid);
			if(profiles != null){
				Profile profile = comment.getProfile();
				profiles.remove(profile);
			}
			//UserCache.FeedZanUsers.put(fid, profiles);
		} else {
			Integer fid = comment.getFid();
			List<FeedComment> comments = UserCache.FeedComments.get(fid);
			comments.remove(comment);
			UserCache.FeedComments.put(fid, comments);
		}
	}

	/**
	 * 添加好友
	 * 
	 * @param uid
	 * @param profile
	 */
	public static void addFriend(Account _account, Profile profile) {
		int uid = _account.getId();
		// 维护我关注的人的id 用于判断好友关系
		List<Integer> friends = UserCache.FriendShip.get(uid);
		if (friends == null) {
			friends = new ArrayList<Integer>();
		}
		friends.add(profile.getId());
		UserCache.FriendShip.put(uid, friends);
		// 维护我关注的人 用于取我关注的人列表
		List<Profile> profiles = UserCache.MeAttention.get(uid);
		if (profiles == null) {
			profiles = new ArrayList<Profile>();
		}
		profiles.add(0, profile);
		UserCache.MeAttention.put(uid, profiles);
		// 维护关注我的人
		List<Profile> profiles2 = UserCache.AttentionMe.get(profile.getId());
		if (profiles2 == null) {
			profiles2 = new ArrayList<Profile>();
		}
		profiles2.add(0, _account.getProfile());
		UserCache.AttentionMe.put(profile.getId(), profiles2);
	}

	/**
	 * 删除好友
	 * 
	 * @param uid
	 * @param fid
	 */
	public static void deleteFriend(Account _account, Profile profile) {
		int uid = _account.getId();
		int fid = profile.getId();
		// 维护我关注的人的id 用于判断好友关系
		List<Integer> friends = UserCache.FriendShip.get(uid);
		friends.remove(new Integer(fid));
		UserCache.FriendShip.put(uid, friends);
		// 维护我关注的人 用于取我关注的人列表
		List<Profile> profiles = UserCache.MeAttention.get(uid);
		profiles.remove(profile);
		UserCache.MeAttention.put(uid, profiles);
		// 维护关注我的人
		List<Profile> profiles2 = UserCache.AttentionMe.get(fid);
		profiles2.remove(_account.getProfile());
		UserCache.AttentionMe.put(fid, profiles2);
	}
}

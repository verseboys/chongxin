package com.k9sv.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.Feed;
import com.k9sv.domain.pojo.FeedComment;
import com.k9sv.domain.pojo.OnlineUser;
import com.k9sv.domain.pojo.Profile;

public class UserCache {

	/**
	 * 用户赞过的动态
	 */
	public static Map<Integer, List<Feed>> UserZanFeeds = new ConcurrentHashMap<Integer, List<Feed>>();
	/**
	 * 动态的评论
	 */
	public static Map<Integer, List<FeedComment>> FeedComments = new ConcurrentHashMap<Integer, List<FeedComment>>();
	/**
	 * 动态赞的User
	 */
	public static Map<Integer, List<Profile>> FeedZanUsers = new ConcurrentHashMap<Integer, List<Profile>>();
	/**
	 * 关注的人的id 用于判断好友关系
	 */
	public static Map<Integer, List<Integer>> FriendShip = new ConcurrentHashMap<Integer, List<Integer>>();
	/**
	 * 我关注的人
	 */
	public static Map<Integer, List<Profile>> MeAttention = new ConcurrentHashMap<Integer, List<Profile>>();
	/**
	 * 关注我的人
	 */
	public static Map<Integer, List<Profile>> AttentionMe = new ConcurrentHashMap<Integer, List<Profile>>();
	/**
	 * 我的动态
	 */
	public static Map<Integer, List<Feed>> MeFeed = new ConcurrentHashMap<Integer, List<Feed>>();
	/**
	 * 用户
	 */
	public static List<Account> accounts = new ArrayList<Account>();
	/**
	 * 动态
	 */
	public static List<Feed> feeds = new ArrayList<Feed>();
	/**
	 * 在线用户
	 */
	public static List<String> OnlineUsers = new ArrayList<String>();
	public static Map<String, OnlineUser> OnlineUsersMap = new ConcurrentHashMap<String, OnlineUser>();
	/**
	 * 最新发布的Feed
	 */
	public static Map<Integer, Feed> lastFeed = new ConcurrentHashMap<Integer, Feed>();
	/**
	 * 我推荐的人
	 */
	public static Map<Integer, List<Profile>> MyRecommend = new ConcurrentHashMap<Integer, List<Profile>>();

}

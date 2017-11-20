package com.k9sv.service;

import java.util.List;

import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.Dog;
import com.k9sv.domain.pojo.Feed;
import com.k9sv.domain.pojo.FeedComment;

public interface IFeedManager extends IBaseManager {

	// List<Feed> getFeeds(int roid,int start, int pageSize);

	int saveFeed(Feed feed);

	int deleteFeed(int id, Account account);

	FeedComment saveFeedComment(FeedComment feedComment);

	int deletedFeedComment(int id, Account account);

	FeedComment getFeedZan(int uid, int fid);

	List<Feed> getUserFeeds(int uid, int start, int pageSize);

	// List<Feed> getFriendFeeds(int uid, int pageIndex, int pageSize);

	List<FeedComment> getFeedComments(int fid, int start, int pageSize);

	List<FeedComment> getFeedZans(int fid, int pageIndex, int pageSize);

	/**
	 * 动态条数
	 * 
	 * @param title
	 * @return
	 */
	Integer getCount(String title);

	/**
	 * 动态列表
	 * 
	 * @param title
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 */
	List<Feed> getFeeds(String title, Integer pageNum, Integer numPerPage);

	/**
	 * 删除违规动态
	 * 
	 * @param id
	 */
	void deleteFeed(Integer id);

	void deleteFeedComment(Integer id);

	/**
	 * 评论条数
	 * 
	 * @param title
	 * @param id
	 * @return
	 */
	Integer getCount(String title, Integer id);

	/**
	 * 评论列表
	 * 
	 * @param title
	 * @param id
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 */
	List<FeedComment> getFeedComments(String title, Integer id,
			Integer pageNum, Integer numPerPage);

	/**
	 * 好友最新动态
	 * 
	 * @param _account
	 * @param page
	 * @param size
	 * @return
	 */

	List<Feed> getFeeds(int uid, int roleid, int type, int start, int size);

	/**
	 * 宠物动态
	 * 
	 * @param petid
	 * @param start
	 * @param size
	 * @return
	 */
	List<Feed> getDogFeeds(Integer petid, Integer start, Integer size);

	Integer getMaxFeed(Integer maxfeedid);

	/**
	 * 赞我的评论
	 * 
	 * @param trueUid
	 * @param size
	 * @param page
	 * @return
	 */
	List<FeedComment> getComments(int trueUid, int page, int size);

	/**
	 * 用户动态
	 * 
	 * @param accountId
	 * @param title
	 * @return
	 */
	Integer getCount(Integer accountId, String title);

	List<Feed> getFeeds(Integer accountId, String title, Integer pageNum,
			Integer numPerPage);

	Integer getCount2(int accountId, String title);

	List<Feed> getFeeds2(int accountId, String title, Integer pageNum,
			Integer numPerPage);

	/**
	 * 宠物动态数
	 * 
	 * @param dog
	 * @return
	 */
	int getDogFeedCount(Dog dog);

}

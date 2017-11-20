package com.k9sv.service;

import java.util.List;

import com.k9sv.domain.pojo.Forum;
import com.k9sv.domain.pojo.Topic;

public interface IForumManager extends IBaseManager {
	
	Forum getForum(int id);
	
	Topic getTopic(int id);
	
	void saveOrUpdateTopic(Topic topic);
	
	void saveOrUpdateReply(Topic topic);
	
	int deleteTopic(int id);
	
	int topestTopic(int id,int value);
	
	int bestTopic(int id,int value);

	List<Forum> getForums(int pid);
	
	List<Forum> getForums();
	
	List<Topic> getTopics(int fid,int deleted,int pageIndex, int pageSie);
	
	List<Topic> getReplies(int pid,int deleted,int pageIndex, int pageSie);
	
	List<Topic> getMyTopics(int uid,int deleted,int pageIndex, int pageSie);
	
	boolean isManager(int fid,int uid);
	
	List<Topic> getNewTopics(int fid,int pageIndex, int pageSie);
	
	List<Topic> getHotTopics(int fid,int pageIndex, int pageSie);
}

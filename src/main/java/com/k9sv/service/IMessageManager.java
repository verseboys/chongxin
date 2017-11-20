package com.k9sv.service;

import java.util.List;

import com.k9sv.domain.pojo.Message;

public interface IMessageManager extends IBaseManager {

	int sendMessage(Message message);

	Message readMessage(int id);

	List<Message> getMessages(int uid, int status, int pageIndex, int pageSize);

	int getMessageCount(int uid, int status);

	List<Message> getSendedMessages(int uid, int status, int pageIndex,
			int pageSize);

	int getSendedMessageCount(int uid, int status);

	int deleteMessage(int id);

	List<Message> getIM(int uid, int fromUid, int pageIndex, int pageSize);

	int getIMCount(int uid, int fromUid);

	List<Message> getMessage(int uid, int fromUid, int startId, int size);

	List<Message> getMessage(int uid, int startId, int size);

	void saveMessage(Message message);

	List<Message> getMessages(Integer uid, Integer pageNum, Integer numPerPage);

	Integer getCount(Integer uid);

	/**
	 * 取两人间的聊天记录
	 * 
	 * @param uid
	 * @param touid
	 * @param start
	 * @param size
	 * @return
	 */
	List<Message> getMessageDetails(Integer uid, Integer touid, int start,
			int size);
}

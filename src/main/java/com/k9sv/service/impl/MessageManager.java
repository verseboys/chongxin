package com.k9sv.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.k9sv.Config;
import com.k9sv.domain.dto.MessageDto;
import com.k9sv.domain.dto.PushDto;
import com.k9sv.domain.pojo.Message;
import com.k9sv.domain.pojo.Profile;
import com.k9sv.service.IMessageManager;
import com.k9sv.util.DateUtil;
import com.k9sv.util.GetuiPush;

@Service("messageManager")
public class MessageManager extends BaseManager implements IMessageManager {

	@Override
	public int sendMessage(Message message) {
		if (message.getFromUid() == 0 || message.getToUid() == 0) {
			return 0;
		}
		message.setCreated(DateUtil.getNow());
		message.setStatus(0);
		save(message);
		return 1;
	}

	@Override
	public Message readMessage(int id) {
		Message message = getByClassId(Message.class, id);
		if (message.getStatus() == 0) {
			message.setStatus(0);
			update(message);
		}
		return message;

	}

	@Override
	public List<Message> getMessages(int uid, int status, int pageIndex,
			int pageSize) {
		List<Message> _list = query(
				"from Message where toUid=? and status <= ? order by status asc, id desc",
				new Object[] { uid, status }, pageIndex, pageSize);
		return _list;
	}

	@Override
	public int getMessageCount(int uid, int status) {
		Long o = (Long) getCount(
				"select count(id) from Message where toUid=? and status<=?",
				new Object[] { uid, status });
		return o.intValue();
	}

	@Override
	public List<Message> getSendedMessages(int uid, int status, int pageIndex,
			int pageSize) {
		List<Message> _list = query(
				"from Message where fromUid=? order by id desc",
				new Object[] { uid }, pageIndex, pageSize);
		return _list;
	}

	@Override
	public int getSendedMessageCount(int uid, int status) {
		Long o = (Long) getCount(
				"select count(id) from Message where fromUid=? order by id desc",
				new Object[] { uid });
		return o.intValue();
	}

	@Override
	public int deleteMessage(int id) {
		Message message = getByClassId(Message.class, id);
		message.setStatus(2);
		update(message);
		return 1;
	}

	@Override
	public List<Message> getIM(int uid, int fromUid, int pageIndex, int pageSize) {
		List<Message> _list = query(
				"from Message where ((toUid=? and fromUid=?) or (toUid=? and fromUid=?)) and status <=1 order by id desc",
				new Object[] { uid, fromUid, fromUid, uid }, pageIndex,
				pageSize);
		return _list;
	}

	@Override
	public int getIMCount(int uid, int fromUid) {
		Long o = (Long) getCount(
				"select count(id) from Message where ((toUid=? and fromUid=?) or (toUid=? and fromUid=?)) and status <=1",
				new Object[] { uid, fromUid, fromUid, uid });
		return o.intValue();
	}

	@Override
	public List<Message> getMessage(int uid, int fromUid, int startId, int size) {
		List<Message> _list = query(
				"from Message where toUid=? and fromUid=? and status <=1 and id > ? order by id desc",
				new Object[] { uid, fromUid, startId }, 1, size);
		return _list;

	}

	@Override
	public List<Message> getMessage(int uid, int startId, int size) {
		List<Message> _list = query(
				"from Message where toUid=? and status <=1 and id > ? order by id desc",
				new Object[] { uid, startId }, 1, size);
		return _list;

	}

	@Override
	public void saveMessage(Message message) {
		save(message);
		this.pushMessage(message);
	}

	public void pushMessage(final Message message) {
		Thread t = new Thread(new Runnable() {

			public void run() {
				try {
					Profile fromProfile = message.getProfile();
					MessageDto messageDto = new MessageDto(message);
					PushDto pushDto = new PushDto();
					pushDto.setType(Config.PushFeedIM);
					pushDto.setData(messageDto);
					String content = message.getContent();
					String alter = "";
					int type = message.getType();
					if (type == 0) {
						if (content.length() > 20) {
							content = content.substring(0, 19) + "...";
						}
						alter = fromProfile.getNickName() + ":" + content;
					} else if (type == 1) {
						alter = fromProfile.getNickName() + ":[图片]";
					}
					Profile toProfile = getByClassId(Profile.class,
							message.getToUid());
					GetuiPush.push(alter, pushDto, toProfile);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		t.start();
	}

	@Override
	public List<Message> getMessages(Integer uid, Integer pageNum,
			Integer numPerPage) {
		List<Message> _list = query(
				"from Message where toUid=? order by id desc",
				new Object[] { uid }, pageNum, numPerPage);
		return _list;
	}

	@Override
	public Integer getCount(Integer uid) {
		Long total = (Long) super.getCount(
				"select count(id) from Message where toUid = ?",
				new Object[] { uid });
		return total.intValue();
	}

	@Override
	public List<Message> getMessageDetails(Integer uid, Integer touid,
			int start, int size) {
		if (start == 0) {
			return super
					.queryStart(
							"from Message where (toUid = ? and fromUid = ?) or (toUid = ? and fromUid = ?) order by id desc",
							new Object[] { touid, uid, uid, touid }, start,
							size);
		} else {
			return super
					.queryStart(
							"from Message where id < ? and ((toUid = ? and fromUid = ?) or (toUid = ? and fromUid = ?)) order by id desc",
							new Object[] { start, touid, uid, uid, touid }, 0,
							size);
		}
	}

}

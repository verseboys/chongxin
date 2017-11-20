package com.k9sv.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.k9sv.Config;
import com.k9sv.cache.UserCache;
import com.k9sv.cache.server.CacheServer;
import com.k9sv.domain.dto.PushDto;
import com.k9sv.domain.dto.UserDto;
import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.Friend;
import com.k9sv.domain.pojo.Profile;
import com.k9sv.service.IFriendManager;
import com.k9sv.util.DateUtil;
import com.k9sv.util.GetuiPush;

@Service("friendManager")
public class FriendManager extends BaseManager implements IFriendManager {

	@Override
	public void saveFriend(Account _account, int fid) {
		int uid = _account.getId();
		int i = isFriend(uid, fid);
		if (i > 0) {
			return;
		}
		Friend f = new Friend();
		f.setUid(uid);
		f.setFid(fid);
		f.setStatus(0);
		f.setCreated(new Date());
		List<Friend> _list = query("from Friend where uid=? and fid=?",
				new Object[] { fid, uid }, 1, 1);
		if (_list != null && _list.size() == 1) {
			Friend _friend = _list.get(0);
			_friend.setStatus(1);
			super.update(_friend);
			f.setStatus(1);
		}
		Profile _profile = super.getByClassId(Profile.class, uid);
		Profile fProfile = super.getByClassId(Profile.class, fid);
		_profile.setFriendsCount(_profile.getFriendsCount() + 1);
		update(_profile);
		save(f);
		CacheServer.addFriend(_account, fProfile);
		this.pushFriend(_profile, fProfile);
	}

	private void pushFriend(final Profile _profile, final Profile fProfile) {
		Thread t = new Thread(new Runnable() {

			public void run() {
				try {
					String alter = _profile.getNickName() + "关注了你";
					PushDto pushDto = new PushDto();
					pushDto.setType(Config.PushFriend);
					pushDto.setTime(DateUtil.getFormatDateTime(new Date()));
					int friend = getFriendShip(fProfile.getId(),
							_profile.getId());
					pushDto.setData(new UserDto(_profile, friend));
					GetuiPush.push(alter, pushDto, fProfile);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		t.start();

	}

	@Override
	public List<Friend> getFriends(int uid, int pageIndex, int pageSize) {
		List<Friend> _list = query("from Friend where uid=? ",
				new Object[] { uid }, pageIndex, pageSize);
		return _list;
	}

	@Override
	public Integer getFriendShip(int uid, int fid) {
		List<Integer> friendIds1 = UserCache.FriendShip.get(uid);
		List<Integer> friendIds2 = UserCache.FriendShip.get(fid);
		Integer friendship = 0;// 没关系
		if (friendIds1 != null && friendIds2 != null) {
			if (friendIds1.contains(fid) && friendIds2.contains(uid)) {
				friendship = 3;// 互为好友
			} else if (friendIds1.contains(fid)) {
				friendship = 1;// 是我的好友
			} else if (friendIds2.contains(uid)) {
				friendship = 2;// 我是别人好友
			}
		} else if (friendIds1 != null) {
			if (friendIds1.contains(fid)) {
				friendship = 1;// 是我的好友
			}
		} else if (friendIds2 != null) {
			if (friendIds2.contains(uid)) {
				friendship = 2;// 我是别人好友
			}
		}
		return friendship;
	}

	public int isFriend(int uid, int fid) {
		Long i = (Long) getCount(
				"select count(id) from Friend where uid=? and fid=?",
				new Object[] { uid, fid });

		return i.intValue();
	}

	@Override
	public void deleteFriend(Account _account, Integer fid) {
		int uid = _account.getId();
		int i = isFriend(uid, fid);
		if (i == 0) {
			return;
		}
		List<Friend> _list1 = query("from Friend where uid=? and fid=?",
				new Object[] { uid, fid }, 1, 1);
		List<Friend> _list2 = query("from Friend where uid=? and fid=?",
				new Object[] { fid, uid }, 1, 1);
		if (_list2 != null && _list2.size() == 1) {
			Friend _friend = _list2.get(0);
			_friend.setStatus(0);
			super.update(_friend);
		}
		super.delete(_list1.get(0));
		Profile _profile = super.getByClassId(Profile.class, uid);
		Profile fProfile = super.getByClassId(Profile.class, fid);
		_profile.setFriendsCount(_profile.getFriendsCount() - 1);
		update(_profile);
		CacheServer.deleteFriend(_account, fProfile);
	}
}

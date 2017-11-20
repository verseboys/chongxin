package com.k9sv.service;

import java.util.List;

import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.Friend;

public interface IFriendManager extends IBaseManager {

	void saveFriend(Account _account, int fid);

	List<Friend> getFriends(int uid, int pageIndex, int pageSize);

	public Integer getFriendShip(int uid, int fid);

	void deleteFriend(Account _account, Integer fid);
}

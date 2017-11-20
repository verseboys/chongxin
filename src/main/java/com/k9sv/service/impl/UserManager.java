package com.k9sv.service.impl;

import com.k9sv.Config;
import com.k9sv.util.FloatOperation;
import com.k9sv.util.SMS;
import com.k9sv.util.StringUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.k9sv.cache.UserCache;
import com.k9sv.dao.IUserDao;
import com.k9sv.domain.dto.Area;
import com.k9sv.domain.dto.CheckCode;
import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.Address;
import com.k9sv.domain.pojo.City;
import com.k9sv.domain.pojo.OnlineUser;
import com.k9sv.domain.pojo.Profile;
import com.k9sv.domain.pojo.ProfitLog;
import com.k9sv.domain.pojo.Role;
import com.k9sv.domain.pojo.UserInfo;
import com.k9sv.schedule.Cache;
import com.k9sv.service.IBaiduManager;
import com.k9sv.service.IUserManager;

@Service("userManager")
public class UserManager extends BaseManager implements IUserManager {

	@Autowired
	private IBaiduManager baiduManager;

	@Autowired
	private IUserDao userDao;

	private static final Logger LOG = Logger.getLogger(UserManager.class);

	@Override
	public Account getCheckedAccount(String username) {
		List<Account> _list = queryStart(
				"from Account where username=? and checked =1",
				new Object[] { username }, 0, 1);
		if (_list == null) {
			return null;
		}
		return _list.size() == 1 ? _list.get(0) : null;
	}

	@Override
	public Account getAccount(String username) {
		List<Account> _list = queryStart(
				"from Account where username=? order by checked desc",
				new Object[] { username }, 0, 1);
		if (_list == null) {
			return null;
		}
		return _list.size() == 1 ? _list.get(0) : null;
	}

	@Override
	public void login(Account account, String sessionId, String platform) {
		List<OnlineUser> _list = super.query(
				"from OnlineUser where uid=? and platform = ?", new Object[] {
						account.getId(), platform }, 1, 100);
		if (_list != null && _list.size() > 0) {
			for (OnlineUser u : _list) {
				super.delete(u);
				this.onlineUserSet(u, 0);
			}
		}
		OnlineUser onlineUser = new OnlineUser();
		onlineUser.setId(sessionId);
		onlineUser.setUid(account.getId());
		onlineUser.setUpdated(new Date());
		onlineUser.setPlatform(platform);
		onlineUser.setAccount(account);
		save(onlineUser);
		this.onlineUserSet(onlineUser, 1);
		account.setLastLogin(new Date());
		update(account);
	}

	public void onlineUserSet(OnlineUser onlineUser, int type) {
		String sessionId = onlineUser.getId();
		List<String> list = UserCache.OnlineUsers;
		Map<String, OnlineUser> map = UserCache.OnlineUsersMap;
		if (list == null) {
			list = new ArrayList<String>();
		}
		if (map == null) {
			map = new ConcurrentHashMap<String, OnlineUser>();
		}
		if (type == 1) {
			list.add(sessionId);
			map.put(sessionId, onlineUser);
		} else {
			list.remove(sessionId);
			Iterator<Entry<String, OnlineUser>> it = map.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, OnlineUser> entry = it.next();
				String key = entry.getKey();
				if (sessionId.equals(key)) {
					it.remove(); // OK
				}
			}
		}
		UserCache.OnlineUsers = list;
		UserCache.OnlineUsersMap = map;
	}

	@Override
	public List<Profile> getProfilesByDog(int catid, int start, int size) {
		return null;
	}

	@Override
	public List<Profile> getProfiles(int pageIndex, int pageSize) {

		return super.query("from Profile order by id desc", null, pageIndex,
				pageSize);
	}

	@Override
	public int getProfileCount() {
		Long l = (Long) super.getCount("select count(id) from Profile", null);
		return l.intValue();
	}

	@Override
	public List<Role> getRoles() {
		return super.query("from Role order by id desc", null, 1, 100);
	}

	@Override
	public List<Profile> getProfileByRole(int roleId, int start, int size) {
		return super.queryStart("from Profile where roleId=? order by id desc",
				new Object[] { roleId }, start, size);
	}

	@Override
	public List<Profile> getProfiles(int cityId, int roleId, int start, int size) {
		return super
				.queryStart(
						"from Profile where (cityId = ? or city.pid=?) and roleId=? order by id desc",
						new Object[] { cityId, cityId, roleId }, start, size);
	}

	@Override
	public Account getAccountByOpneId(String openId) {
		List<Account> _list = queryStart(
				"from Account where openId=? and deleted = 0",
				new Object[] { openId }, 0, 1);
		if (_list == null) {
			return null;
		}
		return _list.size() == 1 ? _list.get(0) : null;
	}

	public void updateUserArea(final Profile profile, final String ip) {
		if (profile.getIsCity() == 1) {
			return;
		}
		LOG.info("update Ip:" + ip);
		Thread _t = new Thread(new Runnable() {
			public void run() {
				LOG.info("thread Ip:" + ip);
				Area area = baiduManager.getArea(ip);
				if (area == null) {
					return;
				}
				profile.setLatitude(area.getX());
				profile.setLongtitude(area.getY());
				profile.setCityId(area.getCityId());
				profile.setScore(profile.getScore() + 1);
				update(profile);
				City _city = Cache.cityList.get(area.getCityId());
				if (_city == null) {
					_city = new City();
					_city.setId(area.getCityId());
					_city.setName(area.getCity());
					_city.setType(3);
					City _province = Cache.provinceMap.get(area.getProvince()
							.substring(0, 1));
					if (_province != null) {
						_city.setPid(_province.getId());
					}
					Cache.cityList.put(_city.getId(), _city);
					save(_city);
				}

			}
		});
		_t.start();
	}

	@Override
	public Account getOnlineUser(String sid) {
		if (sid == null) {
			return null;
		}
		OnlineUser _a = super.getByClassId(OnlineUser.class, sid);
		if (_a != null) {
			return _a.getAccount();
		}
		return null;
	}

	@Override
	public Account getAccountByUnionid(String unionid) {
		List<Account> _list = queryStart(
				"from Account where unionid=? and deleted = 0",
				new Object[] { unionid }, 0, 1);
		if (_list == null) {
			return null;
		}
		return _list.size() == 1 ? _list.get(0) : null;
	}

	public String sendCheckCode(String type, String phone, String sessionId) {
		Random rm = new Random();
		Double pross = (1 + rm.nextDouble()) * Math.pow(10, 6);
		String fixLenthString = String.valueOf(pross);
		String code = fixLenthString.substring(1, 5);
		CheckCode _checkCode = new CheckCode(type, code, phone, sessionId);
		String msg = "【宠信app】验证码:" + code;
		SMS.sendSMS(phone, msg);
		LOG.info("mobile==" + phone + "--type==" + type + "--sessionId=="
				+ sessionId + "--code==" + code);
		Cache.CheckcodeMap.put(sessionId, _checkCode);
		// System.out.println("checkCode:" + code);
		return code;
	}

	@Override
	public boolean checkCode(String sessionId, String code) {
		CheckCode _checkCode = Cache.CheckcodeMap.get(sessionId);
		if (_checkCode != null && _checkCode.getStatus() == 0
				&& _checkCode.getCode().equals(code)) {
			_checkCode.setStatus(1);
			return true;
		}
		return false;
	}

	public Integer getCountByUsername(String username) {
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("username", username);
		return getTotalSize(Account.class, searchMap);
	}

	public Account getAccountByUsername(String username) {

		return userDao.getAccountByUsername(username);
	}

	@Override
	public Account getAccountById(Integer getcId) {
		return userDao.getAccountById(getcId);
	}

	@Override
	public Integer getTotalBeans(String nickname, String userName) {
		String hql = "select count(id) from Account where deleted = 0 ";
		List<String> list = new ArrayList<String>();
		Object[] objects = null;
		if (StringUtil.isNotEmpty(nickname)) {
			hql = hql + "and profile.nickName like ? ";
			list.add("%" + nickname + "%");
		}
		if (StringUtil.isNotEmpty(userName)) {
			hql = hql + "and username like ? ";
			list.add("%" + userName + "%");
		}
		int size = list.size();
		if (size > 0) {
			objects = new Object[size];
			for (int i = 0; i < size; i++) {
				objects[i] = list.get(i);
			}
		}
		Long total = (Long) super.getCount(hql, objects);
		return total.intValue();
	}

	@Override
	public List<Account> getBeans(String nickname, String userName,
			Integer pageNum, Integer numPerPage) {
		String hql = "from Account where deleted = 0 ";
		List<String> list = new ArrayList<String>();
		Object[] objects = null;
		if (StringUtil.isNotEmpty(nickname)) {
			hql = hql + "and profile.nickName like ? ";
			list.add("%" + nickname + "%");
		}
		if (StringUtil.isNotEmpty(userName)) {
			hql = hql + "and username like ? ";
			list.add("%" + userName + "%");
		}
		hql = hql + "order by created desc";
		int size = list.size();
		if (size > 0) {
			objects = new Object[size];
			for (int i = 0; i < size; i++) {
				objects[i] = list.get(i);
			}
		}
		return super.query(hql, objects, pageNum, numPerPage);
	}

	@Override
	public void update(Profile _profile) {
		super.update(_profile);
		int uid = _profile.getId();
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("uid", uid);
		int count = super.getTotalSize(Address.class, searchMap);
		if (count == 0 && StringUtil.isNotEmpty(_profile.getAddress())) {
			Address address = new Address();
			address.setAddress(_profile.getAddress());
			address.setUid(_profile.getId());
			address.setDeleted(0);
			address.setName(_profile.getNickName());
			address.setState(1);
			address.setTelephone(_profile.getMobile());
			super.save(address);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getOnlineUser() {
		return super.find("select id from OnlineUser", null);
	}

	@Override
	public List<Profile> getProfiles(int cityId, int start, int size) {
		return null;
	}

	@Override
	public void updateClientid(String clientid) {
		super.executeSQL(
				"update Account set clientid = null where clientid = ?",
				new Object[] { clientid });
	}

	@Override
	public Integer getTotalBeans(String nickname, String userName,
			Integer taintedUser) {
		String hql = "select count(id) from Account where deleted = 0 and profile.roleId ="
				+ taintedUser + " ";
		List<String> list = new ArrayList<String>();
		Object[] objects = null;
		if (StringUtil.isNotEmpty(nickname)) {
			hql = hql + "and profile.nickName like ? ";
			list.add("%" + nickname + "%");
		}
		if (StringUtil.isNotEmpty(userName)) {
			hql = hql + "and username like ? ";
			list.add("%" + userName + "%");
		}
		int size = list.size();
		if (size > 0) {
			objects = new Object[size];
			for (int i = 0; i < size; i++) {
				objects[i] = list.get(i);
			}
		}
		Long total = (Long) super.getCount(hql, objects);
		return total.intValue();
	}

	@Override
	public List<Account> getBeans(String nickname, String userName,
			Integer taintedUser, Integer pageNum, Integer numPerPage) {
		String hql = "from Account where deleted = 0 and profile.roleId ="
				+ taintedUser + " ";
		List<String> list = new ArrayList<String>();
		Object[] objects = null;
		if (StringUtil.isNotEmpty(nickname)) {
			hql = hql + "and profile.nickName like ? ";
			list.add("%" + nickname + "%");
		}
		if (StringUtil.isNotEmpty(userName)) {
			hql = hql + "and username like ? ";
			list.add("%" + userName + "%");
		}
		hql = hql + "order by created desc";
		int size = list.size();
		if (size > 0) {
			objects = new Object[size];
			for (int i = 0; i < size; i++) {
				objects[i] = list.get(i);
			}
		}
		return super.query(hql, objects, pageNum, numPerPage);
	}

	@Override
	public Integer getDeletedTotal(String nickname, String userName) {
		String hql = "select count(id) from Account where deleted = 1 ";
		List<String> list = new ArrayList<String>();
		Object[] objects = null;
		if (StringUtil.isNotEmpty(nickname)) {
			hql = hql + "and profile.nickName like ? ";
			list.add("%" + nickname + "%");
		}
		if (StringUtil.isNotEmpty(userName)) {
			hql = hql + "and username like ? ";
			list.add("%" + userName + "%");
		}
		int size = list.size();
		if (size > 0) {
			objects = new Object[size];
			for (int i = 0; i < size; i++) {
				objects[i] = list.get(i);
			}
		}
		Long total = (Long) super.getCount(hql, objects);
		return total.intValue();
	}

	@Override
	public List<Account> getDeletedBeans(String nickname, String userName,
			Integer pageNum, Integer numPerPage) {
		String hql = "from Account where deleted = 1 ";
		List<String> list = new ArrayList<String>();
		Object[] objects = null;
		if (StringUtil.isNotEmpty(nickname)) {
			hql = hql + "and profile.nickName like ? ";
			list.add("%" + nickname + "%");
		}
		if (StringUtil.isNotEmpty(userName)) {
			hql = hql + "and username like ? ";
			list.add("%" + userName + "%");
		}
		hql = hql + "order by created desc";
		int size = list.size();
		if (size > 0) {
			objects = new Object[size];
			for (int i = 0; i < size; i++) {
				objects[i] = list.get(i);
			}
		}
		return super.query(hql, objects, pageNum, numPerPage);
	}

	@Override
	public Integer getTotalBeans2(String nickname, String userName, int roleid) {
		if (roleid == 0) {
			return this.getTotalBeans(nickname, userName);
		}
		return this.getTotalBeans(nickname, userName, roleid);
	}

	@Override
	public List<Account> getBeans2(String nickname, String userName,
			int roleid, Integer pageNum, Integer numPerPage) {
		if (roleid == 0) {
			return this.getBeans(nickname, userName, pageNum, numPerPage);
		}
		return this.getBeans(nickname, userName, roleid, pageNum, numPerPage);
	}

	@Override
	public void saveOrUpdate(Account _account) {
		int id = _account.getId();
		if (id == 0) {
			super.save(_account);
		} else {
			super.update(_account);
		}
	}

	@Override
	public void updateAccount(Account _account) {
		super.update(_account);
		int fromId = _account.getProfile().getFromId();
		if (fromId != 0) {
			List<Profile> profiles = UserCache.MyRecommend.get(fromId);
			if (profiles == null) {
				profiles = new ArrayList<Profile>();
			}
			profiles.add(_account.getProfile());
			UserCache.MyRecommend.put(fromId, profiles);
			ProfitLog log = new ProfitLog();
			log.setCreated(new Date());
			log.setFid(_account.getId());
			log.setType(0);
			log.setUid(fromId);
			log.setProfit(Config.Recommend);
			log.setStatus(2);
			super.save(log);
			UserInfo userInfo = super.getByClassId(UserInfo.class, fromId);
			if (userInfo == null) {
				userInfo = new UserInfo();
				userInfo.setId(fromId);
				userInfo.setNoProfit(Config.Recommend);
				super.save(userInfo);
			} else {
				userInfo.setNoProfit(FloatOperation.add(userInfo.getNoProfit(),
						Config.Recommend));
				super.update(userInfo);
			}
		}
	}

}

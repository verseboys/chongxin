package com.k9sv.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.OnlineUser;
import com.k9sv.domain.pojo.Profile;
import com.k9sv.domain.pojo.Role;

@Service("userManager")
public interface IUserManager extends IBaseManager {

	Account getCheckedAccount(String username);

	Account getAccount(String username);

	Account getAccountByOpneId(String openId);

	Account getOnlineUser(String sid);

	Account getAccountByUnionid(String unionid);

	void login(Account account, String sessionId, String platform);

	List<Profile> getProfiles(int cityId, int start, int size);

	List<Profile> getProfilesByDog(int catid, int start, int size);

	List<Profile> getProfiles(int start, int size);

	List<Profile> getProfiles(int cityId, int roleId, int start, int size);

	List<Profile> getProfileByRole(int roleId, int start, int size);

	int getProfileCount();

	List<Role> getRoles();

	void updateUserArea(final Profile profile, final String ip);

	String sendCheckCode(String type, String phone, String sessionId);

	boolean checkCode(String sessionId, String code);

	public Integer getCountByUsername(String username);

	public Account getAccountByUsername(String username);

	public Account getAccountById(Integer getcId);

	Integer getTotalBeans(String nickname, String userName);

	List<Account> getBeans(String nickname, String userName, Integer pageNum, Integer numPerPage);

	void update(Profile _profile);

	List<String> getOnlineUser();

	void updateClientid(String clientid);

	void onlineUserSet(OnlineUser onlineUser, int type);

	/**
	 * 取角色下用户
	 * 
	 * @param nickname
	 * @param userName
	 * @param taintedUser
	 * @return
	 */
	Integer getTotalBeans(String nickname, String userName, Integer taintedUser);

	List<Account> getBeans(String nickname, String userName, Integer taintedUser, Integer pageNum, Integer numPerPage);

	/**
	 * 已经删除的用户
	 * 
	 * @param nickname
	 * @param userName
	 * @return
	 */
	Integer getDeletedTotal(String nickname, String userName);

	List<Account> getDeletedBeans(String nickname, String userName, Integer pageNum, Integer numPerPage);

	/**
	 * 用户列表
	 * 
	 * @param nickname
	 * @param userName
	 * @param roleid
	 * @return
	 */
	Integer getTotalBeans2(String nickname, String userName, int roleid);

	List<Account> getBeans2(String nickname, String userName, int roleid, Integer pageNum, Integer numPerPage);

	void saveOrUpdate(Account _account);

	/**
	 * 更新推荐
	 * 
	 * @param _account
	 */
	void updateAccount(Account _account);

}

package com.k9sv.service;

import java.util.List;

import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.Server;

public interface IServerManager extends IBaseManager {

	/**
	 * 服务总条数
	 * 
	 * @param title
	 * @return
	 */
	Integer getCount(String title);

	/**
	 * 服务列表
	 * 
	 * @param title
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 */
	List<Server> getServers(String title, Integer pageNum, Integer numPerPage);

	List<Server> find(Integer id, Integer id2);

	boolean find(Server server);

	Integer getCount(String title, Account account);

	List<Server> getServers(String title,int uid, Integer pageNum,
			Integer numPerPage);

	Integer getCount2(String title, Account account);

	List<Server> getServers2(String title, int accountId, Integer pageNum,
			Integer numPerPage);

}

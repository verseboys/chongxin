package com.k9sv.service;

import com.k9sv.domain.pojo.Account;

public interface IShareNotesManager extends IBaseManager {
	/**
	 * 
	 * @param account 分享给谁
	 * @param account2 谁分享的
	 * @param type 分享的什么
	 */
	void insert(Account account, Account account2, String type);
	void insert(Integer uid, Integer fromuid, String state);

}

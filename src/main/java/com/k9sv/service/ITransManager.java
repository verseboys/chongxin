package com.k9sv.service;

import java.util.List;

import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.TransRecord;

public interface ITransManager extends IBaseManager {

	/**
	 * 
	 * 转让记录
	 * 
	 * @param title
	 * @param id
	 * @return
	 */
	Integer getCount(String title, Integer id);

	/**
	 * 
	 * 转让记录
	 * 
	 * @param title
	 * @param id
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 */
	List<TransRecord> getTransRecords(String title, Integer id,
			Integer pageNum, Integer numPerPage);

	/**
	 * 我的转让记录
	 * 
	 * @param title
	 * @param account
	 * @return
	 */
	Integer getCount(String title, Account account);

	/**
	 * 我的转让记录
	 * 
	 * @param title
	 * @param account
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 */
	List<TransRecord> getTransRecords(String title, Account account,
			Integer pageNum, Integer numPerPage);

}

package com.k9sv.service;

import java.util.List;

import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.Company;

public interface ICompanyManager extends IBaseManager {

	void saveCompnany(Company company, int uid);

	/**
	 * 犬舍或宠物店列表
	 * 
	 * @param type
	 *            类型（犬舍或宠物店）
	 * @param name
	 *            犬舍名字（用于查询）
	 * @param latitude
	 *            纬度
	 * @param longtitude
	 *            经度
	 * @param page
	 * @param size
	 * @return
	 */
	List<Company> getCompanys(int type, String name, String latitude,
			String longtitude, int page, int size);

	Integer getCount(String title, Integer type);

	List<Company> getCompanys(String title, Integer type, Integer pageNum,
			Integer numPerPage);

	Integer getCount(String title, Account account, Integer type);

	List<Company> getCompanys(String title, Account account, Integer type,
			Integer pageNum, Integer numPerPage);

	Integer getTotalBeans(String companyName);

	List<Company> getBeans(String companyName, Integer pageNum,
			Integer numPerPage);

	List<Company> find();

	/**
	 * 用户的店铺
	 * 
	 * @param account
	 * @return
	 */
	Company getCompany(Integer accountid);

	Integer getTotalBeans(String companyName, Account account);

	List<Company> getBeans(String companyName, Account account,
			Integer pageNum, Integer numPerPage);

}

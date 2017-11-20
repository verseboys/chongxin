package com.k9sv.service;

import java.util.List;

import com.k9sv.domain.pojo.Warranty;
import com.k9sv.domain.pojo.WarrantyOperation;

public interface IWarrantyManager extends IBaseManager {
	/**
	 * 保单数
	 * 
	 * @param title
	 *            宠物芯片号
	 * @param uid
	 *            下单人id
	 * @return
	 */
	Integer getCount(String title, int uid);

	/**
	 * 保单列表
	 * 
	 * @param title
	 *            宠物芯片号
	 * @param uid
	 *            下单人id
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 */
	List<Warranty> getWarrantys(String title, int uid, Integer pageNum,
			Integer numPerPage);

	/**
	 * 保单数
	 * 
	 * @param title
	 *            宠物芯片号
	 * @param title2
	 *            保单号
	 * @return
	 */
	Integer getCount(String title, String title2, int checked);

	/**
	 * 保单列表
	 * 
	 * @param title
	 *            宠物芯片号
	 * @param title2
	 *            保单号
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 */
	List<Warranty> getWarrantys(String title, String title2, int checked,
			Integer pageNum, Integer numPerPage);

	/**
	 * 记录总数
	 * 
	 * @param title
	 * @return
	 */
	Integer getOperationCount(String title);

	/**
	 * 记录列表
	 * 
	 * @param title
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 */
	List<WarrantyOperation> getOperations(String title, Integer pageNum,
			Integer numPerPage);

	/**
	 * 取宠物失效时间最晚的保单
	 * 
	 * @param blood
	 * @return
	 */
	Warranty getLatestWarranty(String blood);

}

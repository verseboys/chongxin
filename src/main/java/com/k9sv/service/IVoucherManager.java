package com.k9sv.service;

import java.util.List;

import com.k9sv.domain.pojo.Voucher;
import com.k9sv.domain.pojo.VoucherType;

public interface IVoucherManager extends IBaseManager {
	/**
	 * 
	 * @param uid
	 *            用户id
	 * @param page
	 * @param size
	 * @return
	 */
	List<Voucher> getVouchers(Integer uid, int page, int size);

	/**
	 * 我的代金券
	 * 
	 * @param myid
	 *            用户id
	 * @return
	 */
	int getVoucherCount(Integer myid);

	/**
	 * 代金券类型
	 * 
	 * @param title
	 * @return
	 */
	Integer getTypeCount(String title);

	/**
	 * 代金券类型
	 * 
	 * @param title
	 *            代金券名称
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 */
	List<VoucherType> getTypes(String title, Integer pageNum, Integer numPerPage);

	/**
	 * 第一个代金券类型
	 * 
	 * @return
	 */
	VoucherType getVoucher();

	/**
	 * 代金券记录
	 * 
	 * @param type
	 *            代金券类型
	 * @param title
	 *            用户昵称
	 * @return
	 */
	Integer getCount(Integer type, String title);

	/**
	 * 代金券记录
	 * 
	 * @param type
	 *            代金券类型
	 * @param title
	 *            用户昵称
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 */
	List<Voucher> getVoucherRecords(Integer type, String title,
			Integer pageNum, Integer numPerPage);

	/**
	 * 加载代金券类型
	 * 
	 * @return
	 */
	List<VoucherType> findType();

}

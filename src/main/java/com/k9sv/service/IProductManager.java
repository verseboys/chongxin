package com.k9sv.service;

import java.util.List;

import com.k9sv.domain.pojo.Buy;
import com.k9sv.domain.pojo.Product;

public interface IProductManager extends IBaseManager {
	/**
	 * 获取产品
	 * 
	 * @param start
	 * @param size
	 * @return
	 */
	List<Product> getProducts(Integer page, Integer size);

	/**
	 * 购买产品
	 * 
	 * @param buy
	 */
	Buy save(Buy buy);

	/**
	 * 产品数
	 * 
	 * @param title
	 * @return
	 */
	Integer getCount(String title);

	/**
	 * 产品列表
	 * 
	 * @param title
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 */
	List<Product> getProducts(String title, Integer pageNum, Integer numPerPage);

	/**
	 * 我购买的产品
	 * 
	 * @param id
	 * @param page
	 * @param size
	 * @return
	 */
	List<Buy> getMeBuys(Integer id, int page, int size);

	/**
	 * 取商城产品列表
	 * 
	 * @param start
	 * @param size
	 * @return
	 */
	List<Product> getMallProducts(int page, int size);

}

package com.k9sv.service;

import java.util.List;

import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.Orders;

public interface IOrderManager extends IBaseManager {
	
	Orders checkUserOrder(int uid);

	List<Orders> getUserOrders(int id, int start, int size);

	Integer delete(String id, Account _account);

	Integer getCount(String title);

	List<Orders> getOrders(String title, Integer pageNum, Integer numPerPage);

	public void pushOrder(Orders orders);
}

package com.k9sv.service;

import java.util.List;

import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.Address;

public interface IAddressManager extends IBaseManager {

	List<Address> getUserAddresss(int id, int start, int size);

	Integer delete(Integer addrid, Account _account);

	void updateAddress(Integer uid);

}

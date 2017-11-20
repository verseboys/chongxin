package com.k9sv.dao;

import com.k9sv.domain.pojo.Account;

public interface IUserDao extends IBaseDao {

	Account getAccountByUsername(String username);
	
	Account getAccountById(Integer id);	
}

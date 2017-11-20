package com.k9sv.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.Address;
import com.k9sv.service.IAddressManager;

@Service("addressManager")
public class AddressManager extends BaseManager implements IAddressManager {

	Logger LOG = Logger.getLogger(AddressManager.class);

	@Override
	public List<Address> getUserAddresss(int uid, int pageIndex, int pageSize) {
		try {
			return query(
					"from Address where uid=? and deleted = 0 order by id desc",
					new Object[] { uid }, pageIndex, pageSize);

		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Integer delete(Integer id, Account _account) {
		List<Address> _list = super.queryStart(
				"from Address where uid =? and id=?",
				new Object[] { _account.getId(), id }, 0, 1);
		if (_list != null && _list.size() == 1) {
			Address address = _list.get(0);
			address.setDeleted(1);
			update(address);
			return 1;
		}
		return 0;
	}

	@Override
	public void updateAddress(Integer uid) {
		String hql = "update Address set state = 0 where state = 1 and uid = ?";
		super.executeSQL(hql, new Object[] { uid });

	}

}
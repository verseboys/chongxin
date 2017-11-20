package com.k9sv.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Service;

import com.k9sv.dao.IUserDao;
import com.k9sv.domain.pojo.Account;

@SuppressWarnings("unchecked")
@Service("adminDao")
public class UserDao extends BaseDao<Object> implements IUserDao {

	@Override
	public Account getAccountByUsername(final String username) {

		return (Account) getHibernateTemplate().execute(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException {
				List<?> _list = session
						.createQuery(
								"from Account where deleted = 0 and username=?")
						.setString(0, username).list();
				if (_list != null && _list.size() > 0) {
					return _list.get(0);
				} else {
					return null;
				}
			}
		});
	}

	@Override
	public Account getAccountById(final Integer id) {
		return (Account) getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				List<?> _list = session
						.createQuery("from Account where deleted = 0 and id=?")
						.setLong(0, id).list();
				if (_list != null && _list.size() > 0) {
					return _list.get(0);
				} else {
					return null;
				}
			}
		});
	}

}

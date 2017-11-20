package com.k9sv.dao.impl;

import java.io.Serializable;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;

import com.k9sv.dao.IBaseDao;
import com.k9sv.util.Constants;
import com.k9sv.util.SortColumn;

@SuppressWarnings({ "unchecked", "rawtypes", "unused", "hiding" })
@Service("baseDao")
public class BaseDao<T> extends HibernateDaoSupport implements IBaseDao {

	@Resource(name = "sessionFactory")
	public void setSF(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public <T> T save(final T paramT) throws Exception {
		return (T) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				session.save(paramT);
				session.flush();
				return paramT;
			}
		});
	}

	@Override
	public <T> void saveOrUpdate(final T paramT) throws Exception {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				session.saveOrUpdate(paramT);
				session.flush();
				return paramT;
			}
		});
	}

	@Override
	public <T> void delete(final T entity) throws Exception {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				session.delete(entity);
				session.flush();
				return null;
			}
		});
	}

	@Override
	public <T> T merge(T entity) throws Exception {
		return getHibernateTemplate().merge(entity);

	}

	@Override
	public <T> void update(final T entity) throws Exception {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				session.update(entity);
				session.flush();
				return entity;
			}
		});

	}

	@Override
	public Object load(final Class entity, final Serializable id) {
		return getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Object o = session.load(entity, id);
				session.flush();
				return o;
			}
		});
		// return getHibernateTemplate().load(entity, id);
	}

	@Override
	public Object get(Class entity, Serializable id) {

		return getHibernateTemplate().get(entity, id);
	}

	@Override
	public <T> T getByClassId(final Class<? extends T> clazz, final Serializable id) throws Exception {
		return (T) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Object o = session.get(clazz, id);
				session.flush();
				return o;
			}
		});
	}

	@Override
	public <T> List<T> query(final String sql, final Object[] values, final int pageIndex, final int pageSize)
			throws Exception {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				int start = 0;
				if (pageIndex > 1) {
					start = (pageIndex - 1) * pageSize;
				}
				try {
					return queryStart(sql, values, start, pageSize);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		});
	}

	@Override
	public Object getCount(final String where, final Object[] objects) {
		return getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(where);
				if (objects != null) {
					int objectsize = objects.length;
					if (objectsize > 0) {
						for (int i = 0; i < objectsize; i++) {
							query.setParameter(i, objects[i]);
						}
					}
				}
				if (query == null) {
					return null;
				}
				Object obj = query.uniqueResult();
				return obj;
			}
		});
	}

	public <T> List<T> queryStart(final String sql, final Object[] values, final int start, final int size)
			throws Exception {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				// Transaction tx = session.beginTransaction();
				Query query = session.createQuery(sql);
				if (values != null) {
					int objectsize = values.length;
					if (objectsize > 0) {
						for (int i = 0; i < objectsize; i++) {
							query.setParameter(i, values[i]);
						}
					}
				}
				query.setFirstResult(start);
				query.setMaxResults(size);
				query.setCacheable(true);
				List _list = query.list();
				// tx.commit()
				return _list;
			}
		});
	}

	public List find(String query) {
		return getHibernateTemplate().find(query);
	}

	public Integer getTotalSize(final Class clz, final Map<String, Object> searchMap) {
		return (Integer) getHibernateTemplate().execute(new HibernateCallback() {
			public Integer doInHibernate(Session session) throws HibernateException {
				Criteria criteria = session.createCriteria(clz);
				if (searchMap != null) {
					Iterator iter = searchMap.entrySet().iterator();
					List<String> fieldColumns = new ArrayList<String>();
					List searchValues = new ArrayList();
					while (iter.hasNext()) {
						Map.Entry<String, Object> entry = (Map.Entry<String, Object>) iter.next();
						String key = entry.getKey();
						Object val = entry.getValue();
						fieldColumns.add(key);
						searchValues.add(val);
					}
					if (fieldColumns != null && fieldColumns.size() != 0) {
						for (int k = 0; k < fieldColumns.size(); k++) {
							criteria.add(Restrictions.eq((String) fieldColumns.get(k), searchValues.get(k)));
						}
					}
				}

				return getCount(criteria);
			}
		});
	}

	protected Integer getCount(Criteria criteria) {
		criteria.setProjection(Projections.rowCount());
		List list = criteria.list();
		if (list != null && list.size() > 0) {
			return Integer.valueOf(list.get(0).toString());
		} else {
			return 0;
		}
	}

	public List getPaginationObjects(final Class clz, final int pageIndex, final int pageSize,
			final Map<String, Object> searchMap, final Map<String, Object> searchClassMap, final List<SortColumn> scs) {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Criteria criteria = session.createCriteria(clz);

				processSearchCondiction(searchMap, criteria);
				processSearchClassCondiction(searchClassMap, criteria);
				processSortCondiction(scs, criteria);
				criteria.setFirstResult((pageIndex - 1) * pageSize);
				criteria.setMaxResults(pageSize);
				List li = criteria.list();
				return li;
			}
		});
	}

	public Integer getPaginationObjects(final Class clz, final Map<String, Object> searchMap,
			final Map<String, Object> searchClassMap) {
		return (Integer) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Criteria criteria = session.createCriteria(clz);
				processSearchCondiction(searchMap, criteria);
				processSearchClassCondiction(searchClassMap, criteria);
				return getCount(criteria);
			}
		});
	}

	private void processSearchCondiction(Map<String, Object> searchMap, Criteria criteria) {
		if (searchMap == null) {
			return;
		}
		Iterator iter = searchMap.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String, Object> entry = (Map.Entry<String, Object>) iter.next();
			String key = entry.getKey();
			Object val = entry.getValue();
			if (val.getClass().getName().equals("java.lang.String")) {

				String values = (String) val;
				if (values.startsWith(Constants.LIKE_START)) {
					criteria.add(Restrictions.like(key, values.replace(Constants.LIKE_START, ""), MatchMode.START));
				} else if (values.startsWith(Constants.LIKE)) {
					criteria.add(Restrictions.like(key, values.replace(Constants.LIKE, ""), MatchMode.ANYWHERE));
				} else {
					criteria.add(Restrictions.eq(key, val));
				}

			} else if (val.getClass().getName().equals("[Ljava.lang.String;"))
				criteria.add(Restrictions.in(key, (String[]) val));
			else
				criteria.add(Restrictions.eq(key, val));
		}
	}

	private void processSearchClassCondiction(Map<String, Object> searchClassMap, Criteria criteria) {
		if (searchClassMap == null) {
			return;
		}
		Iterator classIter = searchClassMap.entrySet().iterator();
		List<String> fieldClassColumns = new ArrayList<String>();
		List fieldClassValues = new ArrayList();
		while (classIter.hasNext()) {
			Map.Entry<String, Object> entry = (Map.Entry<String, Object>) classIter.next();
			String key = entry.getKey();
			Object val = entry.getValue();
			fieldClassColumns.add(key);
			fieldClassValues.add(val);
		}
		List<String> objs = new ArrayList<String>();
		if (fieldClassColumns != null && fieldClassColumns.size() > 0) {
			for (int k = 0; k < fieldClassColumns.size(); k++) {
				if (String.valueOf(fieldClassColumns.get(k)).contains(".")) {
					String[] strs = (String.valueOf(fieldClassColumns.get(k))).split("\\.");
					if (!objs.contains(strs[0])) {
						criteria.createAlias(strs[0], "object_" + strs[0]);
						objs.add(strs[0]);
					}
					if (fieldClassValues.get(k).toString().startsWith(Constants.LIKE_START)) {
						criteria.add(Restrictions.like("object_" + strs[0] + "." + strs[1],
								fieldClassValues.get(k).toString().replace(Constants.LIKE_START, ""), MatchMode.START));
					} else if (fieldClassValues.get(k).toString().startsWith(Constants.LIKE)) {
						criteria.add(Restrictions.like("object_" + strs[0] + "." + strs[1],
								fieldClassValues.get(k).toString().replace(Constants.LIKE, ""), MatchMode.ANYWHERE));
					} else {
						criteria.add(Restrictions.eq("object_" + strs[0] + "." + strs[1], fieldClassValues.get(k)));
					}

				}
			}
		}
	}

	private void processSortCondiction(List<SortColumn> scs, Criteria criteria) {
		if (scs == null || scs.size() == 0) {
			return;
		}
		for (SortColumn sc : scs) {
			if (!sc.getSortKeys().startsWith("id.")) {
				if (sc.getSortDir().equalsIgnoreCase("ASC")) {
					if (sc.getSortKeys().indexOf(".") != -1) {
						String aliasKey = sc.getSortKeys().substring(0, sc.getSortKeys().indexOf("."));
						criteria.createAlias(aliasKey, aliasKey);
						criteria.addOrder(Order.asc(sc.getSortKeys()));
					} else {
						criteria.addOrder(Order.asc(sc.getSortKeys()));
					}
				} else {

					criteria.addOrder(Order.desc(sc.getSortKeys()));
				}

			} else {
				if (!sc.getSortKeys().startsWith("id.")) {
					if (sc.getSortKeys().indexOf(".") != -1) {
						String aliasKey = sc.getSortKeys().substring(0, sc.getSortKeys().indexOf("."));
						criteria.createAlias(aliasKey, aliasKey);
						criteria.addOrder(Order.desc(sc.getSortKeys()));
					} else
						criteria.addOrder(Order.desc(sc.getSortKeys()));
				} else {

					criteria.addOrder(Order.desc(sc.getSortKeys()));
				}
			}
		}

	}

	public void executeSQL(final String Sql, final Object[] objects) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(Sql);
				if (objects != null) {
					int objectsize = objects.length;
					if (objectsize > 0) {
						for (int i = 0; i < objectsize; i++) {
							query.setParameter(i, objects[i]);
						}
					}
				}
				return query.executeUpdate();
			}
		});
	}

	public Object getObject(Class entity, Serializable id) {
		return getHibernateTemplate().get(entity, id);
	}

	@Override
	public List find(String query, Object[] objects) {
		return getHibernateTemplate().find(query, objects);
	}

	@Override
	public List getObjects(final String hql2, final Object[] ids) {
		return getObjects(hql2, ids, null, null, null);
	}

	@Override
	public List getObjects(final String hql, final Object[] object, final Object[] objects, final Integer start,
			final Integer size) {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql);
				if (object != null) {
					query.setParameterList("ids", object);
				}
				if (objects != null) {
					int objectsize = objects.length;
					if (objectsize > 0) {
						for (int i = 0; i < objectsize; i++) {
							query.setParameter(i, objects[i]);
						}
					}
				}
				if (start != null && size != null) {
					query.setFirstResult(start);
					query.setMaxResults(size);
				}
				query.setCacheable(true);
				List _list = query.list();
				return _list;
			}
		});
	}

	@Override
	public List getObjects(Class clazz) {
		return getHibernateTemplate().loadAll(clazz);
	}

}
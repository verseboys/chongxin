package com.k9sv.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.k9sv.dao.IBaseDao;
import com.k9sv.service.IBaseManager;
import com.k9sv.util.SortColumn;

@SuppressWarnings("rawtypes")
@Service("baseManager")
public class BaseManager implements IBaseManager {

	private static final Logger LOG = Logger.getLogger(BaseManager.class);
	@Autowired
	protected IBaseDao baseDao;

	@Override
	public <T> T save(T paramT) {
		try {
			return baseDao.save(paramT);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public <T> T load(Class<? extends T> clazz, Serializable id) {
		return (T) baseDao.load(clazz, id);
	}

	@Override
	public <T> T getByClassId(Class<? extends T> clazz, Serializable id) {
		try {
			return baseDao.getByClassId(clazz, id);
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return null;
	}

	@Override
	public <T> void delete(T entity) {
		try {
			baseDao.delete(entity);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e.getMessage());
		}
	}

	@Override
	public <T> T merge(T entity) {
		try {
			return baseDao.merge(entity);
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return null;
	}

	@Override
	public <T> void update(T entity) {
		try {
			baseDao.update(entity);
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
	}

	@Override
	public <T> List<T> query(String query, Object[] values, int pageIndex, int pageSize) {
		try {
			return baseDao.query(query, values, pageIndex, pageSize);
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return null;
	}

	@Override
	public <T> List<T> queryStart(String query, Object[] values, int start, int size) {
		try {
			return baseDao.queryStart(query, values, start, size);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Object getCount(String where, Object[] values) {

		try {
			return baseDao.getCount(where, values);
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return null;
	}

	public IBaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(IBaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public <T> void saveOrUpdate(T paramT) {
		try {
			baseDao.saveOrUpdate(paramT);
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}

	}

	@Override
	public Integer getTotalSize(Class clz, Map<String, Object> searchMap) {
		return baseDao.getTotalSize(clz, searchMap);
	}

	@Override
	public List<Object> getPaginationObjects(Class clz, int pageIndex, int pageSize, Map<String, Object> searchMap,
			Map<String, Object> searchClassMap, List<SortColumn> scs) {
		return baseDao.getPaginationObjects(clz, pageIndex, pageSize, searchMap, searchClassMap, scs);
	}

	@Override
	public Integer getPaginationObjects(Class clz, Map<String, Object> searchMap, Map<String, Object> searchClassMap) {
		return baseDao.getPaginationObjects(clz, searchMap, searchClassMap);
	}

	@Override
	public void executeSQL(String Sql, Object[] object) {
		baseDao.executeSQL(Sql, object);
	}

	/**
	 * 通过主键条件查询
	 * 
	 * @see
	 */
	public Object getObject(Class clazz, Serializable id) {
		return baseDao.getObject(clazz, id);
	}

	public List getObjects(Class clazz) {
		return baseDao.getObjects(clazz);
	}

	@Override
	public List find(String hql, Object[] object) {
		return baseDao.find(hql, object);
	}

	@Override
	public List getObjects(String hql, Object[] ids) {
		return baseDao.getObjects(hql, ids);
	}

	@Override
	public List getObjects(String hql, Object[] ids, Object[] objects, Integer start, Integer size) {

		return baseDao.getObjects(hql, ids, objects, start, size);
	}

}

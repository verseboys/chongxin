package com.k9sv.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.k9sv.util.SortColumn;

@SuppressWarnings("rawtypes")
public interface IBaseDao {

	<T> T save(T paramT) throws Exception;

	<T> void saveOrUpdate(T paramT) throws Exception;

	<T> T getByClassId(Class<? extends T> clazz, Serializable id) throws Exception;;

	<T> void delete(T entity) throws Exception;

	<T> T merge(T entity) throws Exception;

	<T> void update(T entity) throws Exception;

	<T> List<T> query(String query, Object[] values, int pageIndex, int pageSize) throws Exception;

	<T> List<T> queryStart(String query, Object[] values, int start, int size) throws Exception;

	Object getCount(String where, Object[] values) throws Exception;

	Object load(Class entity, Serializable id);

	Object get(Class entity, Serializable id);

	/****
	 * 执行HQL语句<br>
	 * 
	 * @param query
	 *            HQL语句
	 * @return 查询结果集
	 */
	public List find(final String query);

	/**
	 * 统计查询<br>
	 * 查询符合条件的数据条数 ，类似select count(*) where ...<br>
	 * eg: searchMap.put("username","xiaoming");<br>
	 * getTotalSize(User.class,searchMap);<br>
	 * 
	 * @param clz
	 *            查询类型
	 * @param searchMap
	 *            查询条件
	 * @return 符合条件的数据条数
	 */
	public Integer getTotalSize(Class clz, Map<String, Object> searchMap);

	/*****
	 * 分页查询 "本表查询条件"指在HQL中类似"username"这样不涉及别其他关联表的查询条件，
	 * "连接查询条件"指在HQL中类似"address.city"这样中间带点的查询条件，否则方法将不能正常获得查询结果，
	 * 并且对于“连接查询条件”不允许类似“user.address.city”两次连接的写法<br>
	 * eg: searchMap.put("username","abc");<br>
	 * searchClassMap.put("address.ctiy","beijin");<br>
	 * scs.add(new SortColumn("id","desc"));<br>
	 * getPaginationObjects(User.class,searchMap,searchClassMap,scs);<br>
	 * eg1:对于模糊查询你可以这样，<br>
	 * searchMap.put("username",Constants.LIKE+"abc%");<br>
	 * searchClassMap.put("address.ctiy","beijin");<br>
	 * scs.add(new SortColumn("id","desc"));<br>
	 * getPaginationObjects(User.class,searchMap,searchClassMap,scs);<br>
	 * 
	 * @param clz
	 *            查询类型
	 * @param pageIndex
	 *            页码
	 * @param pageSize
	 *            每页条数
	 * @param searchMap
	 *            本表查询条件
	 * @param searchClassMap
	 *            连接查询条件
	 * @param scs
	 *            排序
	 * @return 查询结果集
	 */
	public List<Object> getPaginationObjects(Class clz, int pageIndex, int pageSize, Map<String, Object> searchMap,
			Map<String, Object> searchClassMap, List<SortColumn> scs);

	/*****
	 * 统计查询
	 * 
	 * "本表查询条件"指在HQL中类似"username"这样不涉及别其他关联表的查询条件，
	 * "连接查询条件"指在HQL中类似"address.city"这样中间带点的查询条件，否则方法将不能正常获得查询结果，
	 * 并且对于“连接查询条件”不允许类似“user.address.city”两次连接的写法<br>
	 * eg: searchMap.put("username","abc");<br>
	 * searchClassMap.put("address.ctiy","beijin");<br>
	 * getPaginationObjects(User.class,searchMap,searchClassMap);<br>
	 * eg1:对于模糊查询你可以这样，<br>
	 * searchMap.put("username",Constants.LIKE+"abc");<br>
	 * searchClassMap.put("address.ctiy","beijin");<br>
	 * getPaginationObjects(User.class,searchMap,searchClassMap);<br>
	 * 
	 * @param clz
	 *            查询类型
	 * @param searchMap
	 *            本表查询条件
	 * @param searchClassMap
	 *            连接查询条件
	 * @return 查询结果集数量
	 */
	public Integer getPaginationObjects(Class clz, Map<String, Object> searchMap, Map<String, Object> searchClassMap);

	/**
	 * 更新
	 * 
	 * @param Sql
	 */
	public void executeSQL(final String Sql, Object[] object);

	/**
	 * 获得单条数据
	 * 
	 * @param clazz
	 * @param id
	 * @return 结果集，未找到结果将获返回空
	 */
	public Object getObject(Class clazz, Serializable id);

	public List find(String query, Object[] objects);

	List getObjects(String hql2, Object[] ids);

	/**
	 * 获得指定类型的所有数据 谨慎使用，将会加载整个表的数据
	 * 
	 * @param clazz
	 *            数据类型
	 * @return 结果集
	 */
	public List getObjects(Class clazz);

	List getObjects(String hql, Object[] object, Object[] objects, Integer start, Integer size);

}

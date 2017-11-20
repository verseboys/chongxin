package com.k9sv.service;

import java.util.List;

import com.k9sv.domain.pojo.Classify;

public interface IClassifyManager extends IBaseManager {

	/**
	 * 通过父id取分类
	 * 
	 * @return
	 */
	List<Classify> getClassifys(Integer fid);

	/**
	 * 总条数
	 * 
	 * @param pid
	 * @return
	 */
	Integer getCount(int pid);

	/**
	 * 分页取服务类型
	 * 
	 * @param pid
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 */
	List<Classify> getClassifys(int pid, Integer pageNum, Integer numPerPage);

	/**
	 * 取记录类型
	 * 
	 * @return
	 */
	List<Classify> getRecordType();

	/**
	 * 记录类型
	 * 
	 * @param name
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 */
	List<Classify> getRecordTypes(String name, Integer pageNum,
			Integer numPerPage);

	/**
	 * 记录类型总条数
	 * 
	 * @param name
	 * @return
	 */
	Integer getCount(String name);

	/**
	 * 删除指定pid的服务类型
	 * 
	 * @param id
	 */
	void deleteClassify(Integer pid);

	List<Classify> find(Integer id);

}

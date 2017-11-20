package com.k9sv.service;

import java.util.Date;
import java.util.List;

import com.k9sv.domain.pojo.ToutiaoPublish;

public interface ITouTiaoManager extends IBaseManager {
	/**
	 * 素材id获取头条
	 * @param id
	 * @return
	 */
	ToutiaoPublish getObjects(Integer id);
	/**
	 * 删除素材
	 * @param id
	 */
	void deleteSucai(Integer id);
	/**
	 * 删除头条
	 * @param id
	 */
	void deletePublish(Integer id);
	/**
	 * 获取头条
	 * @param start
	 * @param size
	 * @return
	 */
	List<ToutiaoPublish> getTouTiaos(Integer start, Integer size,Date _now);
	
	Integer getMaxToutiao(Integer maxtoutiaoid);
	
	
	
}

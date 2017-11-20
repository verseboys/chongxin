package com.k9sv.service;

import java.util.List;
import java.util.Set;

import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.Record;
import com.k9sv.domain.pojo.RecordPic;

public interface IRecordManager extends IBaseManager {

	/**
	 * 宠物记录
	 * 
	 * @param petid
	 * @param start
	 * @param size
	 * @return
	 */
	List<Record> getDogRecords(Integer petid, Integer start, Integer size);

	int delete(int recordid, Account _account);

	Record getRecordByFid(Integer feedid);

	/**
	 * 取宠物经历（不包括动态）
	 * 
	 * @param type
	 * @param dogid
	 * @return
	 */
	Integer getCount(Integer type, Integer dogid);

	/**
	 * 取宠物经历（不包括动态）
	 * 
	 * @param type
	 * @param dogid
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 */
	List<Record> getRecords(Integer type, Integer dogid, Integer pageNum,
			Integer numPerPage);
	
	void saveOrUpdate(Record record);

	Record saveOrUpdate(Record record, Set<RecordPic> _deleteRecordPics,
			Set<RecordPic> _addRecordPics);
}

package com.k9sv.service;

import java.util.List;

import com.k9sv.domain.pojo.NoticeRecord;

public interface IAnnouncementManager extends IBaseManager {

	/**
	 * 公告总条数
	 * 
	 * @param title
	 * @return
	 */
	Integer getCount(int companyId);

	/**
	 * 公告列表
	 * 
	 * @param title
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 */
	List<NoticeRecord> getNoticeRecords(int companyId, Integer pageNum,
			Integer numPerPage);

}

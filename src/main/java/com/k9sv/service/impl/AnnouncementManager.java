package com.k9sv.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.k9sv.domain.pojo.NoticeRecord;
import com.k9sv.service.IAnnouncementManager;

@Service("announcementManager")
public class AnnouncementManager extends BaseManager implements
		IAnnouncementManager {

	Logger LOG = Logger.getLogger(AnnouncementManager.class);

	@Override
	public Integer getCount(int companyId) {
		Long total = (Long) super.getCount(
				"select count(id) from NoticeRecord where companyid = ? and deleted=0",
				new Object[] { companyId });
		if (total == null) {
			return 0;
		}
		return total.intValue();
	}

	@Override
	public List<NoticeRecord> getNoticeRecords(int companyId, Integer pageNum,
			Integer numPerPage) {
		return super.query(
				"from NoticeRecord where companyid = ? and deleted=0 order by created desc",
				new Object[] { companyId }, pageNum, numPerPage);
	}

}
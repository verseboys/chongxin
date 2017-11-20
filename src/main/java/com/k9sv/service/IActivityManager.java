package com.k9sv.service;

import java.util.List;

import com.k9sv.domain.pojo.Activity;
import com.k9sv.domain.pojo.ActivityRecord;

public interface IActivityManager extends IBaseManager {

	ActivityRecord getActivityRecord(int uid,int aid);

	Integer getCount(Integer isfinished, String title);

	List<Activity> getActivitys(Integer isfinished, String title,
			Integer pageNum, Integer numPerPage);

	Integer getRecordCount(String title);

	List<ActivityRecord> getRecords(String title, Integer pageNum,
			Integer numPerPage);
}

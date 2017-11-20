package com.k9sv.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.k9sv.domain.pojo.Activity;
import com.k9sv.domain.pojo.ActivityRecord;
import com.k9sv.service.IActivityManager;
import com.k9sv.util.StringUtil;

@Service("activityManager")
public class ActivityManager extends BaseManager implements IActivityManager {

	@Override
	public ActivityRecord getActivityRecord(int uid, int aid) {
		List<ActivityRecord> _list = super.query(
				"from ActivityRecord where uid=? and aid=?", new Object[] {
						uid, aid }, 1, 1);
		if (_list == null || _list.size() == 0) {
			return null;
		} else {
			return _list.get(0);
		}
	}

	@Override
	public Integer getCount(Integer isfinished, String title) {
		if (StringUtil.isNotEmpty(title)) {
			Long total = (Long) super
					.getCount(
							"select count(id) from Activity where isfinished = ? and name like ?",
							new Object[] { isfinished, "%" + title + "%" });
			return total.intValue();
		} else {
			Long total = (Long) super.getCount(
					"select count(id) from Activity where isfinished = ?",
					new Object[] { isfinished });
			return total.intValue();
		}
	}

	@Override
	public List<Activity> getActivitys(Integer isfinished, String title,
			Integer pageNum, Integer numPerPage) {
		if (StringUtil.isNotEmpty(title)) {
			return super
					.query("from Activity where isfinished = ? and name like ? order by id desc",
							new Object[] { isfinished, "%" + title + "%" },
							pageNum, numPerPage);
		} else {
			return super.query(
					"from Activity where isfinished = ? order by id desc",
					new Object[] { isfinished }, pageNum, numPerPage);
		}
	}

	@Override
	public Integer getRecordCount(String title) {
		if (StringUtil.isNotEmpty(title)) {
			Long total = (Long) super
					.getCount(
							"select count(id) from ActivityRecord where activity.name like ?",
							new Object[] { "%" + title + "%" });
			return total.intValue();
		} else {
			Long total = (Long) super.getCount(
					"select count(id) from ActivityRecord", null);
			return total.intValue();
		}
	}

	@Override
	public List<ActivityRecord> getRecords(String title, Integer pageNum,
			Integer numPerPage) {
		if (StringUtil.isNotEmpty(title)) {
			return super
					.query("from ActivityRecord where activity.name like ? order by activity.id desc,id desc",
							new Object[] { "%" + title + "%" }, pageNum,
							numPerPage);
		} else {
			return super.query(
					"from ActivityRecord order by activity.id desc,id desc",
					null, pageNum, numPerPage);
		}
	}

}

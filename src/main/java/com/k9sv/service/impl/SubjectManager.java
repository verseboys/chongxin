package com.k9sv.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.k9sv.domain.pojo.Subject;
import com.k9sv.service.ISubjectManager;
import com.k9sv.util.StringUtil;

@Service("subjectManager")
public class SubjectManager extends BaseManager implements ISubjectManager {

	Logger LOG = Logger.getLogger(SubjectManager.class);

	@Override
	public Integer getCount(String title) {
		if (StringUtil.isNotEmpty(title)) {
			Long total = (Long) super
					.getCount(
							"select count(id) from Subject where deleted = 0 and name like ?",
							new Object[] { "%" + title + "%" });
			return total.intValue();
		} else {
			Long total = (Long) super.getCount(
					"select count(id) from Subject where deleted = 0", null);
			return total.intValue();
		}
	}

	@Override
	public List<Subject> getSubjects(String title, Integer pageNum,
			Integer numPerPage) {
		if (StringUtil.isNotEmpty(title)) {
			return super
					.query("from Subject where deleted = 0 and name like ? order by id desc",
							new Object[] { "%" + title + "%" }, pageNum,
							numPerPage);
		} else {
			return super.query(
					"from Subject where deleted = 0 order by id desc", null,
					pageNum, numPerPage);
		}
	}

	@Override
	public Subject getSubject(String domain) {
		List<Subject> subjects = super.query(
				"from Subject where deleted = 0 and domain = ? ",
				new Object[] { domain }, 1, 1);
		if (subjects != null && subjects.size() > 0) {
			return subjects.get(0);
		}
		return null;
	}

}
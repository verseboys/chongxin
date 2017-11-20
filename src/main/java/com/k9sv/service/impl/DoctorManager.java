package com.k9sv.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.k9sv.domain.pojo.Doctor;
import com.k9sv.service.IDoctorManager;
import com.k9sv.util.StringUtil;

@Service("doctorManager")
public class DoctorManager extends BaseManager implements IDoctorManager {

	Logger LOG = Logger.getLogger(DoctorManager.class);

	@Override
	public Integer getCount(String title) {
		if (StringUtil.isNotEmpty(title)) {
			Long total = (Long) super
					.getCount(
							"select count(id) from Doctor where deleted = 0 and name like ?",
							new Object[] { "%" + title + "%" });
			return total.intValue();
		} else {
			Long total = (Long) super.getCount(
					"select count(id) from Doctor where deleted = 0", null);
			return total.intValue();
		}
	}

	@Override
	public List<Doctor> getDoctors(String title, Integer pageNum,
			Integer numPerPage) {
		if (StringUtil.isNotEmpty(title)) {
			return super
					.query("from Doctor where deleted = 0 and name like ? order by id",
							new Object[] { "%" + title + "%" }, pageNum,
							numPerPage);
		} else {
			return super.query("from Doctor where deleted = 0 order by id",
					null, pageNum, numPerPage);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Doctor> find() {
		String hql = "from Doctor where deleted = 0";
		return super.find(hql, null);
	}
}
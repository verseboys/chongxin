package com.k9sv.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.k9sv.domain.pojo.Warranty;
import com.k9sv.domain.pojo.WarrantyOperation;
import com.k9sv.service.IWarrantyManager;
import com.k9sv.util.StringUtil;

@Service("warrantyManager")
public class WarrantyManager extends BaseManager implements IWarrantyManager {

	Logger LOG = Logger.getLogger(WarrantyManager.class);

	@Override
	public Integer getCount(String title, int uid) {
		if (StringUtil.isNotEmpty(title)) {
			Long total = (Long) super
					.getCount(
							"select count(id) from Warranty where deleted = 0 and uid = ? and blood like ?",
							new Object[] { uid, "%" + title + "%" });
			if (total == null) {
				return 0;
			}
			return total.intValue();
		} else {
			Long total = (Long) super
					.getCount(
							"select count(id) from Warranty where deleted = 0 and uid = ?",
							new Object[] { uid });
			if (total == null) {
				return 0;
			}
			return total.intValue();
		}
	}

	@Override
	public List<Warranty> getWarrantys(String title, int uid, Integer pageNum,
			Integer numPerPage) {
		if (StringUtil.isNotEmpty(title)) {
			return super
					.query("from Warranty where deleted = 0 and uid = ? and blood like ? order by created desc",
							new Object[] { uid, "%" + title + "%" }, pageNum,
							numPerPage);
		} else {
			return super
					.query("from Warranty where deleted = 0 and uid = ? order by created desc",
							new Object[] { uid }, pageNum, numPerPage);
		}
	}

	@Override
	public Integer getCount(String title, String title2, int checked) {
		String hql = "select count(id) from Warranty where deleted = 0 ";
		List<Object> list = new ArrayList<Object>();
		Object[] objects = null;
		if (StringUtil.isNotEmpty(title)) {
			hql = hql + "and blood like ? ";
			list.add("%" + title + "%");
		}
		if (StringUtil.isNotEmpty(title2)) {
			hql = hql + "and id like ? ";
			list.add("%" + title2 + "%");
		}
		if (checked != -1) {
			hql = hql + "and checked = ? ";
			list.add(checked);
		}
		int size = list.size();
		if (size > 0) {
			objects = new Object[size];
			for (int i = 0; i < size; i++) {
				objects[i] = list.get(i);
			}
		}
		Long total = (Long) super.getCount(hql, objects);
		return total.intValue();
	}

	@Override
	public List<Warranty> getWarrantys(String title, String title2,
			int checked, Integer pageNum, Integer numPerPage) {
		String hql = "from Warranty where deleted = 0 ";
		List<Object> list = new ArrayList<Object>();
		Object[] objects = null;
		if (StringUtil.isNotEmpty(title)) {
			hql = hql + "and blood like ? ";
			list.add("%" + title + "%");
		}
		if (StringUtil.isNotEmpty(title2)) {
			hql = hql + "and id like ? ";
			list.add("%" + title2 + "%");
		}
		if (checked != -1) {
			hql = hql + "and checked = ? ";
			list.add(checked);
		}
		hql = hql + "order by created desc";
		int size = list.size();
		if (size > 0) {
			objects = new Object[size];
			for (int i = 0; i < size; i++) {
				objects[i] = list.get(i);
			}
		}
		return super.query(hql, objects, pageNum, numPerPage);
	}

	@Override
	public Integer getOperationCount(String title) {
		if (StringUtil.isNotEmpty(title)) {
			Long total = (Long) super.getCount(
					"select count(id) from WarrantyOperation where wid like ?",
					new Object[] { "%" + title + "%" });
			return total.intValue();
		} else {
			Long total = (Long) super.getCount(
					"select count(id) from WarrantyOperation ", null);
			return total.intValue();
		}
	}

	@Override
	public List<WarrantyOperation> getOperations(String title, Integer pageNum,
			Integer numPerPage) {
		if (StringUtil.isNotEmpty(title)) {
			return super.query(
					"from WarrantyOperation where wid like ? order by id desc",
					new Object[] { "%" + title + "%" }, pageNum, numPerPage);
		} else {
			return super.query("from WarrantyOperation order by id desc", null,
					pageNum, numPerPage);
		}
	}

	@Override
	public Warranty getLatestWarranty(String blood) {
		List<Warranty> list = super
				.query("from Warranty where checked = 1 and blood = ? order by endtime desc",
						new Object[] { blood }, 1, 1);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
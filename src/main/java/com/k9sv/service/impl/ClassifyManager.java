package com.k9sv.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.k9sv.domain.pojo.Classify;
import com.k9sv.service.IClassifyManager;
import com.k9sv.util.StringUtil;

@SuppressWarnings("unchecked")
@Service("classifyManager")
public class ClassifyManager extends BaseManager implements IClassifyManager {

	Logger LOG = Logger.getLogger(ClassifyManager.class);

	@Override
	public List<Classify> getClassifys(Integer fid) {
		return super.find("from Classify where type = 0 and pid = ?", new Object[] { fid });
	}

	@Override
	public Integer getCount(int pid) {
		if (pid != 0) {
			Long total = (Long) super.getCount("select count(id) from Classify where pid = ?", new Object[] { pid });
			return total.intValue();
		} else {
			Long total = (Long) super.getCount("select count(id) from Classify where type = 0", null);
			return total.intValue();
		}
	}

	@Override
	public List<Classify> getClassifys(int pid, Integer pageNum, Integer numPerPage) {
		if (pid != 0) {
			return super.query("from Classify where pid = ? order by id asc,pid desc", new Object[] { pid }, pageNum,
					numPerPage);
		} else {
			return super.query("from Classify where type = 0 order by id asc,pid desc", null, pageNum, numPerPage);
		}
	}

	@Override
	public List<Classify> getRecordType() {
		return super.find("from Classify where type = ? and pid != ? ", new Object[] { 1, 0 });
	}

	@Override
	public Integer getCount(String title) {
		if (StringUtil.isNotEmpty(title)) {
			Long total = (Long) super.getCount(
					"select count(id) from Classify where type = ? and pid != ? and name like ?",
					new Object[] { 1, 0, "%" + title + "%" });
			return total.intValue();
		} else {
			Long total = (Long) super.getCount("select count(id) from Classify where type = ? and pid != ? ",
					new Object[] { 1, 0 });
			return total.intValue();
		}
	}

	@Override
	public List<Classify> getRecordTypes(String title, Integer pageNum, Integer numPerPage) {
		if (StringUtil.isNotEmpty(title)) {
			return super.query("from Classify where type = ? and pid != ? and name like ? order by id desc",
					new Object[] { 1, 0, "%" + title + "%" }, pageNum, numPerPage);
		} else {
			return super.query("from Classify where type = ? and pid != ? order by id desc", new Object[] { 1, 0 },
					pageNum, numPerPage);
		}
	}

	@Override
	public void deleteClassify(Integer pid) {
		super.executeSQL("delete from Classify where pid = ?", new Object[] { pid });
	}

	@Override
	public List<Classify> find(Integer pid) {
		return find("from Classify where pid = ? order by id desc", new Object[] { pid });
	}

}
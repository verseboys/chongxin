package com.k9sv.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;

import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.TransRecord;
import com.k9sv.service.ITransManager;
import com.k9sv.util.StringUtil;

@Service("transManager")
public class TransManager extends BaseManager implements ITransManager {

	@Override
	public Integer getCount(String title, Integer id) {
		if (StringUtil.isNotEmpty(title)) {
			Long total = (Long) super
					.getCount(
							"select count(id) from TransRecord where dogid = ? and comment like ?",
							new Object[] { id, "%" + title + "%" });
			if(total==null){
				return 0;
			}
			return total.intValue();
		} else {
			Long total = (Long) super.getCount(
					"select count(id) from TransRecord where dogid = ? ",
					new Object[] { id });
			if(total==null){
				return 0;
			}
			return total.intValue();
		}
	}

	@Override
	public List<TransRecord> getTransRecords(String title, Integer id,
			Integer pageNum, Integer numPerPage) {
		if (StringUtil.isNotEmpty(title)) {
			return super
					.query("from TransRecord where dogid = ? and comment like ? order by transtime desc",
							new Object[] { id, "%" + title + "%" }, pageNum,
							numPerPage);
		} else {
			return super.query(
					"from TransRecord where dogid = ? order by transtime desc",
					new Object[] { id }, pageNum, numPerPage);
		}
	}

	@Override
	public Integer getCount(String title, Account account) {
		if (StringUtil.isNotEmpty(title)) {
			Long total = (Long) super
					.getCount(
							"select count(id) from TransRecord where fromUid = ? and dog.name like ?",
							new Object[] { account.getId(), "%" + title + "%" });
			return total.intValue();
		} else {
			Long total = (Long) super
					.getCount(
							"select count(id) from TransRecord where fromUid = ?",
							new Object[] { account.getId() });
			return total.intValue();
		}
	}

	@Override
	public List<TransRecord> getTransRecords(String title, Account account,
			Integer pageNum, Integer numPerPage) {
		if (StringUtil.isNotEmpty(title)) {
			return super
					.query("from TransRecord where fromUid = ? and dog.name like ? order by transtime desc",
							new Object[] { account.getId(), "%" + title + "%" },
							pageNum, numPerPage);
		} else {
			return super
					.query("from TransRecord where fromUid = ? order by transtime desc",
							new Object[] { account.getId() }, pageNum,
							numPerPage);
		}
	}

}

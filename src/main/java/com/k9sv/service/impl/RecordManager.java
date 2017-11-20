package com.k9sv.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.Record;
import com.k9sv.domain.pojo.RecordPic;
import com.k9sv.service.IRecordManager;
import com.k9sv.util.QiniuFileUtil;

@SuppressWarnings("unchecked")
@Service("recordManager")
public class RecordManager extends BaseManager implements IRecordManager {

	Logger LOG = Logger.getLogger(RecordManager.class);

	@Override
	public List<Record> getDogRecords(Integer petid, Integer start, Integer pagesize) {
		if (start == 0) {
			return super.queryStart("from Record where dogid = ? order by created desc", new Object[] { petid }, start,
					pagesize);
		} else {
			return super.queryStart("from Record where id < ? and dogid = ? order by created desc",
					new Object[] { start, petid }, 0, pagesize);
		}
	}

	@Override
	public int delete(int recordid, Account _account) {
		Record record = getByClassId(Record.class, recordid);
		if (record != null && (_account.getId() == record.getUid())) {
			delete(record);
			return 1;
		}
		return 0;
	}

	@Override
	public Record getRecordByFid(Integer feedid) {
		List<Record> records = super.find("from Record where feedid = ? order by created desc",
				new Object[] { feedid });
		Record record = null;
		if (records != null && records.size() > 0) {
			record = records.get(0);
		}
		return record;
	}

	@Override
	public Integer getCount(Integer type, Integer dogid) {
		if (type.intValue() == 0) {
			Long total = (Long) super.getCount("select count(id) from Record where dogid = ? and feedid = 0",
					new Object[] { dogid });
			return total.intValue();
		} else {
			Long total = (Long) super.getCount("select count(id) from Record where dogid = ? and classifyid = ? ",
					new Object[] { dogid, type });
			return total.intValue();
		}
	}

	@Override
	public List<Record> getRecords(Integer type, Integer dogid, Integer pageNum, Integer numPerPage) {
		if (type.intValue() == 0) {
			return super.query("from Record where dogid = ? and feedid = 0 order by created desc",
					new Object[] { dogid }, pageNum, numPerPage);
		} else {
			return super.query("from Record where dogid = ? and classifyid = ? order by created desc",
					new Object[] { dogid, type }, pageNum, numPerPage);
		}
	}

	@Override
	public void saveOrUpdate(Record record) {
		int id = record.getId();
		if (id == 0) {
			super.save(record);
		} else {
			super.update(record);
		}
	}

	@Override
	public Record saveOrUpdate(Record record, Set<RecordPic> _deleteRecordPics, Set<RecordPic> _addRecordPics) {

		int id = record.getId();
		if (id == 0) {
			super.save(record);
		} else {
			super.update(record);
		}
		Set<RecordPic> _list = record.getRecordPics();
		if (_list == null) {
			_list = new HashSet<RecordPic>();
		}
		if (_deleteRecordPics != null) {
			for (RecordPic rp : _deleteRecordPics) {
				RecordPic recordPic = super.getByClassId(RecordPic.class, rp.getId());
				super.delete(recordPic);
				_list.remove(rp);
			}
		}
		if (_addRecordPics != null) {
			for (RecordPic rp : _addRecordPics) {
				rp.setRid(record.getId());
				rp.setRecord(record);
				super.save(rp);
				_list.add(rp);
				QiniuFileUtil.uploadFile(rp.getUrl());
			}
		}
		record.setRecordPics(_list);
		super.update(record);
		return record;
	}

}
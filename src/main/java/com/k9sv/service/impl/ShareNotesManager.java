package com.k9sv.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.ShareNotes;
import com.k9sv.service.IShareNotesManager;

@Service("shareNotesManager")
public class ShareNotesManager extends BaseManager implements
		IShareNotesManager {

	Logger LOG = Logger.getLogger(ShareNotesManager.class);

	@Override
	public void insert(Account account, Account account2, String notes) {
		Integer touid = 0;
		Integer fromuid = 0;
		if (account != null) {
			touid = account.getId();
		}
		if (account2 != null) {
			fromuid = account2.getId();
		}
		this.insert(touid, fromuid, notes);
	}

	public ShareNotes getShareNotes(Integer touid, Integer fromuid, String notes) {
		if (touid.equals(fromuid)) {
			return new ShareNotes();
		}
		List<ShareNotes> _list = super
				.queryStart(
						"from ShareNotes where toUid = ? and fromUid = ? and notes = ? order by created desc",
						new Object[] { touid, fromuid, notes }, 0, 1);
		if (_list != null && _list.size() == 1) {
			return _list.get(0);
		}
		return null;
	}

	@Override
	public void insert(Integer uid, Integer fromuid, String notes) {
		ShareNotes shareNotes = this.getShareNotes(uid, fromuid, notes);
		if (shareNotes == null) {// 没有记录时插入
			ShareNotes shareNotes2 = new ShareNotes();
			shareNotes2.setFromUid(fromuid);
			shareNotes2.setToUid(uid);
			shareNotes2.setNotes(notes);
			shareNotes2.setCreated(new Date());
			super.save(shareNotes2);
		}
	}

}
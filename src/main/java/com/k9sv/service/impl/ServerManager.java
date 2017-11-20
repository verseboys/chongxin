package com.k9sv.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.Server;
import com.k9sv.service.IServerManager;
import com.k9sv.util.StringUtil;

@SuppressWarnings("unchecked")
@Service("serverManager")
public class ServerManager extends BaseManager implements IServerManager {

	Logger LOG = Logger.getLogger(ServerManager.class);

	@Override
	public Integer getCount(String title) {
		if (StringUtil.isNotEmpty(title)) {
			Long total = (Long) super.getCount("select count(id) from Server where company.name like ?",
					new Object[] { "%" + title + "%" });
			return total.intValue();
		} else {
			Long total = (Long) super.getCount("select count(id) from Server", null);
			return total.intValue();
		}
	}

	@Override
	public List<Server> getServers(String title, Integer pageNum, Integer numPerPage) {
		if (StringUtil.isNotEmpty(title)) {
			return super.query("from Server where company.name like ? order by companyid desc",
					new Object[] { "%" + title + "%" }, pageNum, numPerPage);
		} else {
			return super.query("from Server order by companyid desc", null, pageNum, numPerPage);
		}
	}

	@Override
	public List<Server> find(Integer companyid, Integer classifyid) {
		return super.find("from Server where companyid = ? and classify.pid = ? order by classify.id desc",
				new Object[] { companyid, classifyid });
	}

	@Override
	public boolean find(Server server) {
		List<Server> servers = find("from Server where companyid = ? and classifyid = ?",
				new Object[] { server.getCompanyid(), server.getClassifyid() });
		if (servers != null && servers.size() > 0) {
			Server server2 = servers.get(0);
			if (server2.getId() == server.getId()) {
				return false;
			}
			return true;
		}
		return false;
	}

	@Override
	public Integer getCount(String title, Account account) {
		if (StringUtil.isNotEmpty(title)) {
			Long total = (Long) super.getCount(
					"select count(id) from Server where company.id = ? and company.name like ?",
					new Object[] { account.getId(), "%" + title + "%" });
			return total.intValue();
		} else {
			Long total = (Long) super.getCount("select count(id) from Server where company.id = ?",
					new Object[] { account.getId() });
			return total.intValue();
		}
	}

	@Override
	public List<Server> getServers(String title, int uid, Integer pageNum, Integer numPerPage) {
		if (StringUtil.isNotEmpty(title)) {
			return super.query("from Server where company.id = ? and company.name like ? order by id desc",
					new Object[] { uid, "%" + title + "%" }, pageNum, numPerPage);
		} else {
			return super.query("from Server where company.id = ? order by id desc", new Object[] { uid }, pageNum,
					numPerPage);
		}
	}

	@Override
	public Integer getCount2(String title, Account account) {
		if (account == null) {
			return this.getCount(title);
		}
		return this.getCount(title, account);
	}

	@Override
	public List<Server> getServers2(String title, int accountId, Integer pageNum, Integer numPerPage) {
		if (accountId == 0) {
			return this.getServers(title, pageNum, numPerPage);
		}
		return this.getServers(title, accountId, pageNum, numPerPage);
	}

}
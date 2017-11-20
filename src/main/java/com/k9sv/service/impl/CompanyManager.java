package com.k9sv.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.k9sv.cache.CompanyCache;
import com.k9sv.domain.dto.Location;
import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.Company;
import com.k9sv.domain.pojo.Profile;
import com.k9sv.service.ICompanyManager;
import com.k9sv.util.BaiDuMapUtil;
import com.k9sv.util.StringUtil;

@Service("companyManager")
public class CompanyManager extends BaseManager implements ICompanyManager {

	Logger LOG = Logger.getLogger(CompanyManager.class);

	@Override
	public Integer getCount(String title, Integer type) {
		if (StringUtil.isNotEmpty(title)) {
			Long total = (Long) super.getCount(
					"select count(id) from Company where deleted = 0 and type = ? and name like ?",
					new Object[] { type, "%" + title + "%" });
			return total.intValue();
		} else {
			Long total = (Long) super.getCount("select count(id) from Company where deleted = 0 and type = ?",
					new Object[] { type });
			return total.intValue();
		}
	}

	@Override
	public List<Company> getCompanys(String title, Integer type, Integer pageNum, Integer numPerPage) {
		if (StringUtil.isNotEmpty(title)) {
			return super.query("from Company where deleted = 0 and type = ? and name like ? order by created desc",
					new Object[] { type, "%" + title + "%" }, pageNum, numPerPage);
		} else {
			return super.query("from Company where deleted = 0 and type = ? order by created desc",
					new Object[] { type }, pageNum, numPerPage);
		}
	}

	@Override
	public Integer getCount(String title, Account account, Integer type) {
		if (StringUtil.isNotEmpty(title)) {
			Long total = (Long) super.getCount(
					"select count(id) from Company where deleted = 0 and uid = ? and type = ? and name like ?",
					new Object[] { account.getId(), type, "%" + title + "%" });
			return total.intValue();
		} else {
			Long total = (Long) super.getCount(
					"select count(id) from Company where deleted = 0 and uid = ? and type = ?",
					new Object[] { account.getId(), type });
			return total.intValue();
		}
	}

	@Override
	public List<Company> getCompanys(String title, Account account, Integer type, Integer pageNum, Integer numPerPage) {
		if (StringUtil.isNotEmpty(title)) {
			return super.query(
					"from Company where deleted = 0 and uid = ? and type = ? and name like ? order by created desc",
					new Object[] { account.getId(), type, "%" + title + "%" }, pageNum, numPerPage);
		} else {
			return super.query("from Company where deleted = 0 and uid = ? and type = ? order by created desc",
					new Object[] { account.getId(), type }, pageNum, numPerPage);
		}
	}

	@Override
	public Integer getTotalBeans(String companyName) {
		if (StringUtil.isNotEmpty(companyName)) {
			Long total = (Long) super.getCount("select count(id) from Company where deleted = 0 and name like ?",
					new Object[] { "%" + companyName + "%" });
			return total.intValue();
		} else {
			Long total = (Long) super.getCount("select count(id) from Company where deleted = 0", null);
			return total.intValue();
		}
	}

	@Override
	public List<Company> getBeans(String companyName, Integer pageNum, Integer numPerPage) {
		if (StringUtil.isNotEmpty(companyName)) {
			return super.query("from Company where deleted = 0 and name like ? order by created desc",
					new Object[] { "%" + companyName + "%" }, pageNum, numPerPage);
		} else {
			return super.query("from Company where deleted = 0 order by created desc", null, pageNum, numPerPage);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Company> find() {
		return super.find("from Company where deleted = ? order by created desc", new Object[] { 0 });
	}

	@Override
	public Company getCompany(Integer accountid) {
		Company _company = super.getByClassId(Company.class, accountid);
		if (_company != null && _company.getDeleted() == 1) {
			_company = null;
		}
		return _company;
	}

	@Override
	public Integer getTotalBeans(String companyName, Account account) {
		if (StringUtil.isNotEmpty(companyName)) {
			Long total = (Long) super.getCount(
					"select count(id) from Company where deleted = 0 and uid = ? and name like ?",
					new Object[] { account.getId(), "%" + companyName + "%" });
			return total.intValue();
		} else {
			Long total = (Long) super.getCount("select count(id) from Company where deleted = 0 and uid = ?",
					new Object[] { account.getId() });
			return total.intValue();
		}
	}

	@Override
	public List<Company> getBeans(String companyName, Account account, Integer pageNum, Integer numPerPage) {
		if (StringUtil.isNotEmpty(companyName)) {
			return super.query("from Company where deleted = 0 and uid = ? and name like ? order by created desc",
					new Object[] { account.getId(), "%" + companyName + "%" }, pageNum, numPerPage);
		} else {
			return super.query("from Company where deleted = 0 and uid = ? order by created desc",
					new Object[] { account.getId() }, pageNum, numPerPage);
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Company> getCompanys(int type, String name, String latitude, String longtitude, int page, int size) {
		List<Company> _list = new ArrayList<Company>();
		int start = (page - 1) * size;
		double _lat = 0;
		double _long = 0;
		Set<Company> _cachSet = CompanyCache.getConpanys(type);

		List<Company> _tempList = new ArrayList<Company>();

		if (latitude != null && longtitude != null) {
			try {
				_lat = Double.parseDouble(latitude);
				_long = Double.parseDouble(longtitude);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Location location = new Location(_lat, _long);

			if (_lat > 0 && _long > 0) {
				for (Company company : _cachSet) {
					company.setDistance(BaiDuMapUtil.getCompanyDistance(company, location));
					_tempList.add(company);
				}
				Collections.sort(_tempList, new CompanySort());
			}
		} else {
			for (Company company : _cachSet) {
				_tempList.add(company);
			}
		}

		if (StringUtil.isNotEmpty(name)) {
			Iterator it = _tempList.iterator();
			while (it.hasNext()) {
				Company company = (Company) it.next();
				String companyName = company.getName();
				if (!companyName.contains(name)) {
					it.remove();
				}
			}
		}

		for (int i = 0; i < _tempList.size(); i++) {
			if (i >= start) {
				_list.add(_tempList.get(i));
			}
			if (_list.size() == size) {
				break;
			}
		}
		return _list;
	}

	@Override
	public void saveCompnany(Company company, int uid) {
		if (company.getId() == 0) {
			company.setId(uid);
			save(company);
		} else {
			// 更新的时候uid直接给0
			update(company);
		}
		Profile _profile = super.getByClassId(Profile.class, uid);
		company.setProfile(_profile);
		CompanyCache.updateCompanyCache(company);
	}

}

class CompanySort implements Comparator<Company> {

	public int compare(Company company, Company company2) {
		if (company.getDistance() < company2.getDistance()) {
			return -1;
		} else if ((company.getDistance() == company2.getDistance())) {
			return 0;
		} else {
			return 1;
		}
	}

}

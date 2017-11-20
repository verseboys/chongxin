package com.k9sv.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.k9sv.domain.pojo.Voucher;
import com.k9sv.domain.pojo.VoucherType;
import com.k9sv.service.IVoucherManager;
import com.k9sv.util.StringUtil;

@Service("voucherManager")
public class VoucherManager extends BaseManager implements IVoucherManager {

	@Override
	public List<Voucher> getVouchers(Integer uid, int page, int size) {
		return super
				.query("from Voucher where uid = ? and beused = 0 order by endtime desc",
						new Object[] { uid }, page, size);
	}

	@Override
	public int getVoucherCount(Integer myid) {
		Long total = (Long) super
				.getCount(
						"select count(id) from Voucher where uid = ? and date(endtime) >= curdate() and beused = 0",
						new Object[] { myid });
		return total.intValue();
	}

	@Override
	public Integer getTypeCount(String title) {
		if (StringUtil.isNotEmpty(title)) {
			Long total = (Long) super.getCount(
					"select count(id) from VoucherType where name like ?",
					new Object[] { "%" + title + "%" });
			return total.intValue();
		} else {
			Long total = (Long) super.getCount(
					"select count(id) from VoucherType", null);
			return total.intValue();
		}
	}

	@Override
	public List<VoucherType> getTypes(String title, Integer pageNum,
			Integer numPerPage) {
		if (StringUtil.isNotEmpty(title)) {
			return super.query(
					"from VoucherType where name like ? order by id",
					new Object[] { "%" + title + "%" }, pageNum, numPerPage);
		} else {
			return super.query("from VoucherType order by id", null, pageNum,
					numPerPage);
		}
	}

	@Override
	public VoucherType getVoucher() {
		List<VoucherType> list = super.query("from VoucherType order by id",
				null, 1, 1);
		VoucherType type = null;
		if (list != null && list.size() > 0) {
			type = list.get(0);
		}
		return type;
	}

	@Override
	public Integer getCount(Integer type, String title) {
		if (type.equals(0)) {
			return null;
		}
		if (StringUtil.isNotEmpty(title)) {
			Long total = (Long) super
					.getCount(
							"select count(id) from Voucher where typeid = ? and profile.nickName like ?",
							new Object[] { type, "%" + title + "%" });
			return total.intValue();
		} else {
			Long total = (Long) super.getCount(
					"select count(id) from Voucher where typeid = ?",
					new Object[] { type });
			return total.intValue();
		}
	}

	@Override
	public List<Voucher> getVoucherRecords(Integer type, String title,
			Integer pageNum, Integer numPerPage) {
		if (type.equals(0)) {
			return null;
		}
		if (StringUtil.isNotEmpty(title)) {
			return super
					.query("from Voucher where typeid = ? and profile.nickName like ? order by beused",
							new Object[] { type, "%" + title + "%" }, pageNum,
							numPerPage);
		} else {
			return super.query("from Voucher where typeid = ? order by beused",
					new Object[] { type }, pageNum, numPerPage);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VoucherType> findType() {
		return super.find("from VoucherType order by id", null);
	}

}

package com.k9sv.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.k9sv.domain.pojo.Buy;
import com.k9sv.domain.pojo.BuyInfo;
import com.k9sv.domain.pojo.Product;
import com.k9sv.domain.pojo.Warranty;
import com.k9sv.service.IProductManager;
import com.k9sv.util.StringUtil;

@Service("productManager")
public class ProductManager extends BaseManager implements IProductManager {

	Logger LOG = Logger.getLogger(ProductManager.class);

	@Override
	public List<Product> getProducts(Integer page, Integer size) {
		try {
			return query(
					"from Product where deleted = 0 order by createdtime desc",
					null, page, size);

		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Buy save(Buy buy) {
		Date start = new Date();
		buy.setBuytime(start);
		super.save(buy);
		Set<BuyInfo> buyInfors = buy.getBuyinfors();
		if (buyInfors != null) {
			for (BuyInfo buyInfor : buyInfors) {
				buyInfor.setBuyid(buy.getId());
				buyInfor.setBuy(buy);
				super.save(buyInfor);
				if (buyInfor.getProductid() == 1) {
					/** 保单 **/
					Warranty warranty = new Warranty();
					warranty.setId(buy.getId());
					warranty.setUid(buy.getUid());
					warranty.setPrice((int) (buyInfor.getNumber() * buyInfor
							.getProduct().getPrice()));
					warranty.setCreated(start);
					super.save(warranty);
				}
			}
		}
		return buy;
	}

	@Override
	public Integer getCount(String title) {
		if (StringUtil.isNotEmpty(title)) {
			Long total = (Long) super
					.getCount(
							"select count(id) from Product where deleted = 0 and product like ?",
							new Object[] { "%" + title + "%" });
			return total.intValue();
		} else {
			Long total = (Long) super.getCount(
					"select count(id) from Product where deleted = 0", null);
			return total.intValue();
		}
	}

	@Override
	public List<Product> getProducts(String title, Integer pageNum,
			Integer numPerPage) {
		if (StringUtil.isNotEmpty(title)) {
			return super
					.query("from Product where deleted = 0 and product like ? order by createdtime desc",
							new Object[] { "%" + title + "%" }, pageNum,
							numPerPage);
		} else {
			return super.query(
					"from Product where deleted = 0 order by createdtime desc",
					null, pageNum, numPerPage);
		}
	}

	@Override
	public List<Buy> getMeBuys(Integer uid, int page, int size) {

		try {
			return query(
					"from Buy where uid = ? and deleted = 0 order by buytime desc",
					new Object[] { uid }, page, size);
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}

		return null;
	}

	@Override
	public List<Product> getMallProducts(int page, int size) {

		try {
			return baseDao.query(
					"from Product where deleted = 0 order by id desc", null,
					page, size);
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return null;
	}

}

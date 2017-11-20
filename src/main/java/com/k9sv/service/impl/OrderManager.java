package com.k9sv.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.k9sv.Config;
import com.k9sv.domain.dto.OrderDto;
import com.k9sv.domain.dto.PushDto;
import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.Orders;
import com.k9sv.domain.pojo.Profile;
import com.k9sv.service.IOrderManager;
import com.k9sv.util.GetuiPush;
import com.k9sv.util.StringUtil;

@Service("orderManager")
public class OrderManager extends BaseManager implements IOrderManager {

	Logger LOG = Logger.getLogger(OrderManager.class);

	@Override
	public List<Orders> getUserOrders(int uid, int pageIndex, int pageSize) {
		try {
			return query("from Orders where uid=? and deleted = 0 order by created desc", new Object[] { uid },
					pageIndex, pageSize);

		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Integer delete(String id, Account _account) {
		Orders _order = super.getByClassId(Orders.class, id);
		if (_order.getDeleted() == 0) { // 服务中的订单不能删除
			if (_order.getState() == Config.OrderStateTaker || _order.getState() == Config.OrderStateService) {
				return -1;
			}
			if (_account.getId() == _order.getUid()) {
				_order.setDeleted(1);
				super.update(_order);
				return 1;
			}
		}

		return 0;
	}

	@Override
	public Integer getCount(String title) {
		if (StringUtil.isNotEmpty(title)) {
			Long total = (Long) super.getCount("select count(id) from Orders where deleted = 0 and id like ?",
					new Object[] { "%" + title + "%" });
			return total.intValue();
		} else {
			Long total = (Long) super.getCount("select count(id) from Orders where deleted = 0", null);
			return total.intValue();
		}
	}

	@Override
	public List<Orders> getOrders(String title, Integer pageNum, Integer numPerPage) {
		if (StringUtil.isNotEmpty(title)) {
			return super.query("from Orders where deleted = 0 and id like ? order by created desc",
					new Object[] { "%" + title + "%" }, pageNum, numPerPage);
		} else {
			return super.query("from Orders where deleted = 0 order by created desc", null, pageNum, numPerPage);
		}
	}

	@Override
	public Orders checkUserOrder(int uid) {
		List<Orders> _list = super.queryStart(
				"from Orders where uid =? and state < ? and deleted = 0 order by created desc",
				new Object[] { uid, Config.OrderStateCancel }, 0, 1);
		if (_list != null && _list.size() == 1) {
			return _list.get(0);
		}
		return null;
	}

	public void pushOrder(final Orders orders) {
		Thread t = new Thread(new Runnable() {

			public void run() {
				try {
					OrderDto dto = new OrderDto(orders);
					PushDto pushDto = new PushDto();
					pushDto.setType(Config.PushOrderState);
					pushDto.setData(dto);
					String alter = "你的预约有更新！";
					Profile toProfile = getByClassId(Profile.class, orders.getUid());
					GetuiPush.push(alter, pushDto, toProfile);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		t.start();
	}

}
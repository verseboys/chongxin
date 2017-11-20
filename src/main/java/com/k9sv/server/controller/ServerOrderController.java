package com.k9sv.server.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.k9sv.Config;
import com.k9sv.Errorcode;
import com.k9sv.domain.dto.OrderDto;
import com.k9sv.domain.dto.ResDto;
import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.Address;
import com.k9sv.domain.pojo.Orders;
import com.k9sv.domain.pojo.Profile;
import com.k9sv.service.IAddressManager;
import com.k9sv.service.IOrderManager;
import com.k9sv.service.IUserManager;
import com.k9sv.util.JsonUtil;
import com.k9sv.util.StringUtil;

@Controller
@Scope("prototype")
@RequestMapping("/server/order")
public class ServerOrderController {
	@Autowired
	IUserManager userManager;
	@Autowired
	IOrderManager orderManager;
	@Autowired
	IAddressManager addressManager;

	Logger LOG = Logger.getLogger(ServerOrderController.class);

	/**
	 * 检测订单，如果上一个订单没有完成则返回订单信息，不能再预约
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public String check(HttpServletRequest request, String json, String sid) {
		ResDto res = new ResDto();
		try {
			Account _account = userManager.getOnlineUser(sid);
			Orders order = orderManager.checkUserOrder(_account.getId());
			if (order != null) {
				res.setState(0);
				// res.setErrorcode(Errorcode.existOrder.getCode());
				// res.setErrormsg(Errorcode.existOrder.getErrormsg());
				res.setData(new OrderDto(order));
				LOG.info(order.getState());
			} else {
				res.setState(0);//
			}
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 新增订单
	 * 
	 * @param request
	 * @param json
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(HttpServletRequest request, String json, String sid) {
		ResDto res = new ResDto();
		try {
			Account _account = userManager.getOnlineUser(sid);
			JSONObject jsObject = JsonUtil.getJson(request, json);
			// Integer state = JsonUtil.getInteger(jsObject, "state");
			int addrid = JsonUtil.getInt(jsObject, "addrid");
			String remark = JsonUtil.getString(jsObject, "remark");
			String name = JsonUtil.getString(jsObject, "name");
			String mobile = JsonUtil.getString(jsObject, "mobile");
			String addr = JsonUtil.getString(jsObject, "address");
			Orders oldorder = orderManager.checkUserOrder(_account.getId());
			if (oldorder != null) {// 存在未完成的订单
				res.setState(1);
				res.setErrorcode(Errorcode.existOrder.getCode());
				res.setErrormsg(Errorcode.existOrder.getErrormsg());
			} else {
				Orders order = new Orders();
				// 生成订单号
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
				Random random = new Random();
				int rand = random.nextInt(9000) + 1000;
				String id = sdf.format(new Date()) + rand;

				order.setId(id);
				order.setUid(_account.getId());
				order.setState(0);
				order.setDeleted(0);
				order.setCreated(new Date());
				order.setState(0);
				if (StringUtil.isNotEmpty(remark)) {
					order.setRemark(remark);
				}
				/*
				 * if (state != null) { order.setState(state); }
				 */
				if (addrid != 0) {
					order.setAddressId(addrid);
					Address address = addressManager.getByClassId(
							Address.class, addrid);
					order.setName(address.getName());
					order.setAddress(address.getAddress());
					order.setTelephone(address.getTelephone());
				} else {
					order.setAddress(addr);
					order.setName(name);
					order.setTelephone(mobile);
					Profile _p = _account.getProfile();
					if (_p.getMobile() == null) {
						_p.setMobile(mobile);
						_p.setAddress(addr);
						userManager.update(_p);
					}
				}
				if (order.getTelephone() != null) {
					orderManager.saveOrUpdate(order);
					res.setState(0);
					res.setData(new OrderDto(order));
				}

			}
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 用户的订单列表
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public String list(HttpServletRequest request, String json, String sid) {
		ResDto res = new ResDto();
		try {
			Account _account = userManager.getOnlineUser(sid);
			JSONObject jsonD = JsonUtil.getJson(request, json);
			int page = JsonUtil.getPage(jsonD, "page");
			int size = JsonUtil.getSize(jsonD, "size");
			List<Orders> orders = orderManager.getUserOrders(_account.getId(),
					page, size);
			List<OrderDto> orderDtos = new ArrayList<OrderDto>();
			for (Orders order : orders) {
				OrderDto orderDto = new OrderDto(order);
				orderDtos.add(orderDto);
			}
			res.setState(0);
			res.setData(orderDtos);
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 订单详情
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/load", method = RequestMethod.POST)
	public String load(HttpServletRequest request, String json, String sid) {
		ResDto res = new ResDto();
		try {
			JSONObject jsObject = JsonUtil.getJson(request, json);
			String id = JsonUtil.getString(jsObject, "orderid");
			Orders order = orderManager.getByClassId(Orders.class, id);
			res.setState(0);
			res.setData(new OrderDto(order));
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 删除订单
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(HttpServletRequest request, String json, String sid) {

		ResDto res = new ResDto();
		try {
			Account _account = userManager.getOnlineUser(sid);
			JSONObject jsObject = JsonUtil.getJson(request, json);
			String id = JsonUtil.getString(jsObject, "orderid");
			Integer result = orderManager.delete(id, _account);
			if (result.intValue() == 1) {
				res.setState(0);
			}else if(result.intValue() ==-1){
				res.setState(1);// 未完成的订单不能删除
				res.setErrorcode(Errorcode.exitOrderNotDeleted.getCode());
				res.setErrormsg(Errorcode.exitOrderNotDeleted.getErrormsg());
			} else {
				res.setState(1);// 没有权限
				res.setErrorcode(Errorcode.deleteOrder.getCode());
				res.setErrormsg(Errorcode.deleteOrder.getErrormsg());
			}
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 取消订单
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/cancel", method = RequestMethod.POST)
	public String cancel(HttpServletRequest request, String json, String sid) {

		ResDto res = new ResDto();
		try {
			Account _account = userManager.getOnlineUser(sid);
			JSONObject jsObject = JsonUtil.getJson(request, json);
			String id = JsonUtil.getString(jsObject, "orderid");
			Orders result = orderManager.getByClassId(Orders.class, id);
			if (result.getUid() == _account.getId()) {
				result.setState(Config.OrderStateCancel);
				orderManager.update(result);
				res.setState(0);
			} else {
				res.setState(1);// 没有权限
				res.setErrorcode(Errorcode.deleteOrder.getCode());
				res.setErrormsg(Errorcode.deleteOrder.getErrormsg());
			}
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

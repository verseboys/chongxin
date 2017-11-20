package com.k9sv.server.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.k9sv.Errorcode;
import com.k9sv.domain.dto.AddressDto;
import com.k9sv.domain.dto.ResDto;
import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.Address;
import com.k9sv.service.IAddressManager;
import com.k9sv.service.IUserManager;
import com.k9sv.util.JsonUtil;
import com.k9sv.util.StringUtil;

/**
 * 地址
 * 
 * @author mcp
 * 
 */
@Controller
@Scope("prototype")
@RequestMapping("/server/address")
public class ServerAddressController {

	@Autowired
	IUserManager userManager;
	@Autowired
	IAddressManager addressManager;

	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(HttpServletRequest request, String json, String sid) {
		ResDto res = new ResDto();
		try {
			Account _account = userManager.getOnlineUser(sid);
			JSONObject jsObject = JsonUtil.getJson(request, json);
			String name = JsonUtil.getString(jsObject, "name");
			String telephone = JsonUtil.getString(jsObject, "telephone");
			String address = JsonUtil.getString(jsObject, "address");
			int addrid = JsonUtil.getInt(jsObject, "addrid");
			int state = JsonUtil.getInt(jsObject, "state");
			Address _address = null;

			if (addrid == 0) {
				_address = new Address();
				_address.setUid(_account.getId());
				_address.setDeleted(0);
			} else {
				_address = addressManager.getByClassId(Address.class, addrid);
			}
			if (StringUtil.isNotEmpty(name)) {
				_address.setName(name);
			}
			if (StringUtil.isNotEmpty(telephone)) {
				_address.setTelephone(telephone);
			}
			if (StringUtil.isNotEmpty(address)) {
				_address.setAddress(address);
			}
			if (state == 1) {
				// 当state=1的时候要把此人其他地址的state设为0
				addressManager.updateAddress(_account.getId());
			}
			_address.setState(state);
			addressManager.saveOrUpdate(_address);
			res.setState(0);
			res.setData(new AddressDto(_address));
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(HttpServletRequest request, String json, String sid) {
		ResDto res = new ResDto();
		try {
			Account _account = userManager.getOnlineUser(sid);
			JSONObject jsObject = JsonUtil.getJson(request, json);
			Integer addrid = JsonUtil.getInt(jsObject, "addrid");
			Integer result = addressManager.delete(addrid, _account);
			if (result.intValue() == 1) {
				res.setState(0);
			} else {
				res.setState(1);// 没有权限
				res.setErrorcode(Errorcode.deleteAddress.getCode());
				res.setErrormsg(Errorcode.deleteAddress.getErrormsg());
			}
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public String list(HttpServletRequest request, String json, String sid) {
		ResDto res = new ResDto();
		try {
			Account _account = userManager.getOnlineUser(sid);
			JSONObject jsonD = JsonUtil.getJson(request, json);
			int page = JsonUtil.getPage(jsonD, "page");
			int size = JsonUtil.getSize(jsonD, "size");
			List<Address> addresses = addressManager.getUserAddresss(
					_account.getId(), page, size);
			List<AddressDto> addressDtos = new ArrayList<AddressDto>();
			for (Address address : addresses) {
				AddressDto addressDto = new AddressDto(address);
				addressDtos.add(addressDto);
			}
			res.setState(0);
			res.setData(addressDtos);
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

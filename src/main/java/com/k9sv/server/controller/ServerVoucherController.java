package com.k9sv.server.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.k9sv.domain.dto.ResDto;
import com.k9sv.domain.dto.VoucherDto;
import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.Voucher;
import com.k9sv.service.IUserManager;
import com.k9sv.service.IVoucherManager;
import com.k9sv.util.JsonUtil;

/**
 * 代金券
 * 
 * @author mcp
 * 
 */
@Controller
@Scope("prototype")
@RequestMapping("/server/voucher")
public class ServerVoucherController {


	@Autowired
	private IUserManager userManager;
	@Autowired
	private IVoucherManager voucherManager;

	@RequestMapping(value = "list", method = RequestMethod.POST)
	public @ResponseBody
	String list(HttpServletRequest request, String json, String sid, Model model) {
		ResDto res = new ResDto();
		try {
			Account _account = userManager.getOnlineUser(sid);
			JSONObject jsonD = JsonUtil.getJson(request, json);
			int page = JsonUtil.getPage(jsonD, "page");
			int size = JsonUtil.getSize(jsonD, "size");
			List<Voucher> _vouchers = voucherManager.getVouchers(
					_account.getId(), page, size);
			List<VoucherDto> _list = new ArrayList<VoucherDto>();
			for (Voucher voucher : _vouchers) {
				VoucherDto dto = new VoucherDto(voucher);
				_list.add(dto);
			}
			res.setState(0);
			res.setData(_list);
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

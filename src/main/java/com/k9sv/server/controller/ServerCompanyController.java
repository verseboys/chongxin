package com.k9sv.server.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.k9sv.Errorcode;
import com.k9sv.domain.dto.CompanyDto;
import com.k9sv.domain.dto.CompanyInfoDto;
import com.k9sv.domain.dto.Location;
import com.k9sv.domain.dto.ResDto;
import com.k9sv.domain.dto.ServerDto;
import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.Classify;
import com.k9sv.domain.pojo.Company;
import com.k9sv.domain.pojo.NoticeRecord;
import com.k9sv.domain.pojo.Server;
import com.k9sv.service.IClassifyManager;
import com.k9sv.service.ICompanyManager;
import com.k9sv.service.IServerManager;
import com.k9sv.service.IUserManager;
import com.k9sv.util.JsonUtil;

@Controller
@Scope("prototype")
@RequestMapping("/server/company")
public class ServerCompanyController {

	private static final Logger LOG = Logger.getLogger(ServerCompanyController.class);

	@Autowired
	private ICompanyManager companyManager;

	@Autowired
	IUserManager userManager;
	@Autowired
	IServerManager serverManager;
	@Autowired
	IClassifyManager classifyManager;

	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public String list(HttpServletRequest request, String json, String sid) {
		ResDto res = new ResDto();
		try {
			JSONObject jsonD = JsonUtil.getJson(request, json);
			String name = JsonUtil.getString(jsonD, "name");// 好友名称
			int page = JsonUtil.getInt(jsonD, "page");
			int size = JsonUtil.getSize(jsonD, "size");
			int type = JsonUtil.getInt(jsonD, "type");// 1 犬舍，2宠物店
			String latitude = JsonUtil.getString(jsonD, "latitude");
			String longtitude = JsonUtil.getString(jsonD, "longtitude");

			List<CompanyDto> _dtos = new ArrayList<CompanyDto>();
			List<Company> _list = companyManager.getCompanys(type, name, latitude, longtitude, page, size);
			for (Company company : _list) {
				_dtos.add(new CompanyDto(company));
			}
			res.setState(0);
			res.setData(_dtos);
			LOG.info(res.toString());
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	public String get(HttpServletRequest request, String json, String sid) {
		ResDto res = new ResDto();
		try {
			JSONObject jsonD = JsonUtil.getJson(request, json);
			int cid = JsonUtil.getInt(jsonD, "cid");
			String latitude = JsonUtil.getString(jsonD, "latitude");
			String longtitude = JsonUtil.getString(jsonD, "longtitude");
			Location _location = null;
			if (latitude != null && longtitude != null) {
				_location = new Location(Double.parseDouble(latitude), Double.parseDouble(longtitude));
			}
			Company _company = companyManager.getByClassId(Company.class, cid);

			res.setState(0);
			res.setData(new CompanyInfoDto(_company, _location));
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 公司服务
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/servers", method = RequestMethod.POST)
	public String servers(HttpServletRequest request, String json, String sid) {
		ResDto res = new ResDto();
		try {
			JSONObject jsonD = JsonUtil.getJson(request, json);
			int uid = JsonUtil.getInt(jsonD, "uid");
			List<ServerDto> _dtos = new ArrayList<ServerDto>();
			List<Server> _servers = serverManager.getServers(null, uid, 1, 100);
			List<Classify> classifies = classifyManager.getClassifys(0);// 一级分类
			if (classifies != null) {
				for (Classify classify : classifies) {
					ServerDto dto = new ServerDto(classify, _servers);
					_dtos.add(dto);
				}
			}
			res.setState(0);
			res.setData(_dtos);
			LOG.info(res.toString());
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 发布公告
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/announcement", method = RequestMethod.POST)
	public String announcement(HttpServletRequest request, String json, String sid) {
		ResDto res = new ResDto();
		try {
			Account _account = userManager.getOnlineUser(sid);
			Company company = companyManager.getByClassId(Company.class, _account.getId());
			JSONObject jsonD = JsonUtil.getJson(request, json);
			String notice = JsonUtil.getString(jsonD, "notice");

			if (company == null) {
				res.setState(0);
				res.setErrorcode(Errorcode.noCompany.getCode());
				res.setErrormsg(Errorcode.noCompany.getErrormsg());
				return res.toString();
			}
			company.setNotice(notice);
			companyManager.saveCompnany(company, 0);
			NoticeRecord record = new NoticeRecord();
			record.setCompanyid(company.getId());
			record.setCreated(new Date());
			record.setDeleted(0);
			record.setNotice(notice);
			companyManager.save(record);

			res.setState(0);
			res.setData(new CompanyInfoDto(company, null));
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 添加或修改
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(HttpServletRequest request, String json, String sid) {
		ResDto res = new ResDto();
		try {
			Account _account = userManager.getOnlineUser(sid);
			Company company = companyManager.getByClassId(Company.class, _account.getId());
			JSONObject jsonD = JsonUtil.getJson(request, json);
			int cid = JsonUtil.getInt(jsonD, "cid");
			String name = JsonUtil.getString(jsonD, "name");
			String telephone = JsonUtil.getString(jsonD, "telephone");
			String opentime = JsonUtil.getString(jsonD, "opentime");
			String address = JsonUtil.getString(jsonD, "address");
			String introduction = JsonUtil.getString(jsonD, "introduction");
			if (company != null && cid == 0) {
				res.setState(1);
				res.setErrorcode(Errorcode.haveCompany.getCode());
				res.setErrormsg(Errorcode.haveCompany.getErrormsg());
				return res.toString();
			}
			if (cid != 0 && company == null) {
				res.setState(0);
				res.setErrorcode(Errorcode.noCompany.getCode());
				res.setErrormsg(Errorcode.noCompany.getErrormsg());
				return res.toString();
			}
			if (cid != 0 && cid != company.getId()) {
				res.setState(1);
				res.setErrorcode(Errorcode.updateCompany.getCode());
				res.setErrormsg(Errorcode.updateCompany.getErrormsg());
				return res.toString();
			}
			if (cid == 0) {
				company = new Company();
			}
			if (name != null) {
				company.setName(name);
			}
			if (telephone != null) {
				company.setTelephone(telephone);
			}
			if (opentime != null) {
				company.setOpen_time(opentime);
			}
			if (address != null) {
				company.setAddress(address);
			}
			if (introduction != null) {
				company.setIntroduction(introduction);
			}
			companyManager.saveCompnany(company, _account.getId());
			res.setState(0);
			res.setData(new CompanyInfoDto(company, null));
			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

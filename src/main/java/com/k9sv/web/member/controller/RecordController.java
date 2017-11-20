package com.k9sv.web.member.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.Classify;
import com.k9sv.domain.pojo.OnlineUser;
import com.k9sv.domain.pojo.Record;
import com.k9sv.service.IBaseManager;
import com.k9sv.service.IClassifyManager;
import com.k9sv.service.IDogManager;
import com.k9sv.service.IRecordManager;
import com.k9sv.service.IUserManager;
import com.k9sv.util.CookieUtil;
import com.k9sv.util.DateUtil;
import com.k9sv.util.PageUtil;
import com.k9sv.util.StringUtil;

@Controller("recordController")
@Scope("prototype")
@RequestMapping("/member/dog/record")
public class RecordController {
	@Autowired
	IDogManager dogManager;
	@Autowired
	IBaseManager baseManager;
	@Autowired
	IUserManager userManager;
	@Autowired
	IClassifyManager classifyManager;
	@Autowired
	IRecordManager recordManager;

	/**
	 * 宠物经历
	 * 
	 * @param model
	 * @param title
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 */
	@RequestMapping("")
	public String index(Model model, Integer type, Integer dogid,
			HttpServletRequest request, Integer pageNum, Integer numPerPage) {
		if (pageNum == null) {
			pageNum = 1;
		}
		if (numPerPage == null) {
			numPerPage = 20;
		}
		if (type == null) {
			type = 0;
		}
		List<Classify> classifies = classifyManager.getRecordType();
		Integer totalCount = recordManager.getCount(type, dogid);
		List<Record> beans = recordManager.getRecords(type, dogid, pageNum,
				numPerPage);
		String page = PageUtil.getPagerNormal(totalCount, numPerPage, pageNum,
				"/member/dog/record?dogid=" + dogid);
		model.addAttribute("page", page);
		model.addAttribute("classifies", classifies);
		model.addAttribute("beans", beans);
		model.addAttribute("type", type);
		model.addAttribute("dogid", dogid);
		return "member/dog/record/list";
	}

	/**
	 * 添加宠物记录
	 * 
	 * @param model
	 * @param dogid
	 * @return
	 */
	@RequestMapping("/add")
	public String add(Model model, Integer dogid) {
		String created = DateUtil.getFormatDateTime(new Date(), "yyyy-MM-dd");
		List<Classify> classifies = classifyManager.getRecordType();
		model.addAttribute("dogid", dogid);
		model.addAttribute("created", created);
		model.addAttribute("classifies", classifies);
		return "member/dog/record/add";
	}

	/**
	 * 修改宠物记录
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/update")
	public String update(Model model, Integer id) {
		Record record = recordManager.getByClassId(Record.class, id);
		List<Classify> classifies = classifyManager.getRecordType();
		model.addAttribute("classifies", classifies);
		model.addAttribute("bean", record);
		return "member/dog/record/update";
	}

	/**
	 * 删除宠物记录
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(Model model, Integer id, HttpServletResponse response) {
		Record record = recordManager.getByClassId(Record.class, id);
		recordManager.delete(record);
		return "1";

	}

	/**
	 * 提交确认
	 * 
	 * @param response
	 * @param buy
	 * @param buytimeStr1
	 * @param starttimeStr1
	 * @param endtimeStr1
	 */
	@RequestMapping("/submit")
	public String submit(HttpServletResponse response,
			HttpServletRequest request, Record record, String createdStr1,
			String recordTime1) {
		String sessionid = CookieUtil.getCookieValue(request, "k9membertoken");
		OnlineUser _onlineUser = userManager.getByClassId(OnlineUser.class,
				sessionid);
		Account _account = _onlineUser.getAccount();
		if (StringUtil.isNotEmpty(createdStr1)) {
			record.setCreated(DateUtil.parse(createdStr1, null));
		}
		if (StringUtil.isNotEmpty(recordTime1)) {
			record.setRecordTime(DateUtil.parse(recordTime1, null));
		}
		record.setUid(_account.getId());
		recordManager.saveOrUpdate(record);
		return "redirect:/member/dog/record?dogid=" + record.getDogid();
	}

}

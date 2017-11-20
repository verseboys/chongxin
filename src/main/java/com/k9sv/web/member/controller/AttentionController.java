package com.k9sv.web.member.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.k9sv.cache.UserCache;
import com.k9sv.domain.dto.ProfileDto;
import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.OnlineUser;
import com.k9sv.domain.pojo.Profile;
import com.k9sv.service.ICompanyManager;
import com.k9sv.service.IUserManager;
import com.k9sv.util.CookieUtil;
import com.k9sv.util.PageUtil;

@Controller
@Scope("prototype")
@RequestMapping("/member/attention")
public class AttentionController {
	@Autowired
	ICompanyManager companyManager;
	@Autowired
	private IUserManager userManager;

	@RequestMapping("")
	public String index(Model model, String title, HttpServletRequest request,
			Integer pageNum, Integer numPerPage) {
		if (pageNum == null) {
			pageNum = 1;
		}
		if (numPerPage == null) {
			numPerPage = 20;
		}
		String sessionid = CookieUtil.getCookieValue(request, "k9membertoken");
		OnlineUser _onlineUser = userManager.getByClassId(OnlineUser.class,
				sessionid);
		Account _account = _onlineUser.getAccount();

		List<Profile> profiles = UserCache.MeAttention.get(_account.getId());
		if (profiles == null) {
			profiles = new ArrayList<Profile>();
		}
		int end = pageNum * numPerPage;
		if (end > profiles.size()) {
			end = profiles.size();
		}
		List<Profile> dtos = new ArrayList<Profile>();
		for (int i = (pageNum - 1) * numPerPage; i < end; i++) {
			Profile profile = profiles.get(i);
			dtos.add(profile);
		}
		String page = PageUtil.getPagerNormal(profiles.size(), numPerPage,
				pageNum, "/member/attention");
		model.addAttribute("page", page);
		model.addAttribute("beans", dtos);
		model.addAttribute("title", title);
		return "member/attention/list";
	}

	@RequestMapping("/details")
	public String details(Model model, Integer id) {
		Account account = userManager.getByClassId(Account.class, id);
		ProfileDto dto = new ProfileDto(account.getProfile());
		model.addAttribute("bean", account);
		model.addAttribute("dto", dto);
		return "member/attention/details";
	}

}

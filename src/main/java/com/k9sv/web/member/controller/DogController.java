package com.k9sv.web.member.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.k9sv.cache.UserCache;
import com.k9sv.domain.dto.DogDto;
import com.k9sv.domain.dto.ProfileDto;
import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.Category;
import com.k9sv.domain.pojo.Dog;
import com.k9sv.domain.pojo.OnlineUser;
import com.k9sv.domain.pojo.Profile;
import com.k9sv.domain.pojo.TransRecord;
import com.k9sv.service.IBaseManager;
import com.k9sv.service.ICategoryManager;
import com.k9sv.service.IDogManager;
import com.k9sv.service.IFriendManager;
import com.k9sv.service.ITransManager;
import com.k9sv.service.IUserManager;
import com.k9sv.util.CookieUtil;
import com.k9sv.util.DateUtil;
import com.k9sv.util.PageUtil;
import com.k9sv.util.QUploader;
import com.k9sv.util.StringUtil;

@Controller("memberDogController")
@Scope("prototype")
@RequestMapping("/member/dog")
public class DogController {
	@Autowired
	IDogManager dogManager;
	@Autowired
	ITransManager transManager;
	@Autowired
	IBaseManager baseManager;
	@Autowired
	IUserManager userManager;
	@Autowired
	ICategoryManager categoryManager;
	@Autowired
	IFriendManager friendManager;

	/**
	 * 宠物列表
	 * 
	 * @param model
	 * @param title
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 */
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
		Account account = _onlineUser.getAccount();

		Integer totalCount = dogManager.getCount(title, account);
		List<Dog> beans = dogManager.getDogs(title, account, pageNum,
				numPerPage);
		String page = PageUtil.getPagerNormal(totalCount, numPerPage, pageNum,
				"/member/dog");
		model.addAttribute("page", page);
		model.addAttribute("beans", beans);
		model.addAttribute("title", title);
		return "member/dog/my/list";
	}

	/**
	 * 添加宠物信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/add")
	public String add(Model model) {
		String created = DateUtil.getFormatDateTime(new Date(), "yyyy-MM-dd");
		List<Category> categories = categoryManager.getCategorys(1);
		model.addAttribute("created", created);
		model.addAttribute("beans", categories);
		return "member/dog/my/add";
	}

	/**
	 * 编辑宠物信息
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/update")
	public String update(Model model, Integer id) {
		Dog dog = dogManager.getByClassId(Dog.class, id);
		DogDto dto = new DogDto(dog);
		List<Category> categories = categoryManager.getCategorys(1);
		String update = DateUtil.getFormatDateTime(new Date(), "yyyy-MM-dd");
		model.addAttribute("update", update);
		model.addAttribute("beans", categories);
		model.addAttribute("bean", dog);
		model.addAttribute("dto", dto);
		return "member/dog/my/update";
	}

	@RequestMapping("/submit")
	public String submit(HttpServletResponse response,
			HttpServletRequest request, Dog dog, String createdStr1,
			String birthday1, String update1, Integer petid, Model model) {
		response.setContentType("text/html;charset=UTF-8");
		String sessionid = CookieUtil.getCookieValue(request, "k9membertoken");
		OnlineUser _onlineUser = userManager.getByClassId(OnlineUser.class,
				sessionid);
		Account account = _onlineUser.getAccount();
		// 相同用户不允许有相同名字的宠物
		Dog _dog = dogManager.checkDogName(account, dog.getName(), petid);
		if (_dog == null) {// 宠物名已存在
			dog.setOwnerId(account.getId());
			dog.setClassify(1);
			if (StringUtil.isNotEmpty(createdStr1)) {
				dog.setCreated(DateUtil.parse(createdStr1, null));
			}
			if (StringUtil.isNotEmpty(birthday1)) {
				dog.setBirthday(DateUtil.parse(birthday1, null));
			}
			if (StringUtil.isNotEmpty(update1)) {
				dog.setUpdated(DateUtil.parse(update1, null));
			}
			if (StringUtil.isEmpty(dog.getFatherBlood())) {
				dog.setFatherBlood(null);
			}
			if (StringUtil.isEmpty(dog.getMatherBlood())) {
				dog.setMatherBlood(null);
			}
			baseManager.saveOrUpdate(dog);
		}
		// 暂未想好怎么提示
		return "redirect:/member/dog";
	}

	/**
	 * 删除宠物信息
	 * 
	 * @param response
	 * @param id
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(HttpServletResponse response, Integer id) {
		response.setContentType("text/html;charset=UTF-8");
		Dog dog = dogManager.getByClassId(Dog.class, id);
		dog.setDeleted(1);
		dogManager.saveOrUpdate(dog);
		return "1";
	}

	/**
	 * 上传宠物头像
	 * 
	 * @param request
	 * @param response
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/ajaxUploadPic", method = RequestMethod.POST)
	public @ResponseBody
	String ajaxUploadPic(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("file") MultipartFile file) {
		response.setContentType("text/html;charset=UTF-8");
		QUploader uploader = new QUploader(request);
		return uploader.upImagToQCloud(file);
	}

	/**
	 * 查询父母证书
	 * 
	 * @param model
	 * @param title
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 */
	@RequestMapping("/querydog")
	public String index(Model model, Integer dogid, String title,
			Integer pageNum, Integer numPerPage, Integer type) {
		if (pageNum == null) {
			pageNum = 1;
		}
		if (numPerPage == null) {
			numPerPage = 20;
		}
		Integer totalCount = dogManager.getCount(title, dogid);
		List<Dog> beans = dogManager.getDogs(title, dogid, pageNum, numPerPage);
		String pageUrl = "/member/dog/querydog?dogid=" + dogid + "&type="
				+ type;
		if (StringUtil.isNotEmpty(title)) {
			pageUrl = pageUrl + "&title=" + title;
		}
		String page = PageUtil.getPagerNormal(totalCount, numPerPage, pageNum,
				pageUrl);
		model.addAttribute("page", page);
		model.addAttribute("beans", beans);
		model.addAttribute("title", title);
		model.addAttribute("dogid", dogid);
		model.addAttribute("type", type);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("numPerPage", numPerPage);
		if (type.intValue() == 2) {
			return "member/dog/my/queryfather";
		}
		return "member/dog/my/querymather";
	}

	/**
	 * 转让好友列表
	 * 
	 * @param model
	 * @param request
	 * @param title
	 * @param dogid
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 */
	@RequestMapping("/queryfriend")
	public String queryfriend(Model model, HttpServletRequest request,
			String title, Integer dogid, Integer pageNum, Integer numPerPage) {

		if (pageNum == null) {
			pageNum = 1;
		}
		if (numPerPage == null) {
			numPerPage = 20;
		}

		String sessionid = CookieUtil.getCookieValue(request, "k9membertoken");
		OnlineUser _onlineUser = userManager.getByClassId(OnlineUser.class,
				sessionid);
		Account account = _onlineUser.getAccount();
		// 我关注的人
		List<Profile> profiles = UserCache.MeAttention.get(account.getId());
		if (profiles == null) {
			profiles = new ArrayList<Profile>();
		}
		List<Profile> dtos = new ArrayList<Profile>();
		if (StringUtil.isEmpty(title)) {
			for (Profile profile : profiles) {
				if (profile != null) {
					int friendship = friendManager.getFriendShip(
							account.getId(), profile.getId());// 好友关系
					// 互相关注的人
					if (friendship == 3) {
						dtos.add(profile);
					}
				}

			}
		} else {// 搜索
			for (Profile profile : profiles) {
				if (profile != null) {
					String friend = profile.getNickName();
					if (!friend.contains(title)) {
						continue;
					}
					int friendship = friendManager.getFriendShip(
							account.getId(), profile.getId());// 好友关系
					// 互相关注的人
					if (friendship == 3) {
						dtos.add(profile);
					}
				}
			}
		}

		int end = pageNum * numPerPage;
		if (end > dtos.size()) {
			end = dtos.size();
		}
		List<Profile> dtos2 = new ArrayList<Profile>();
		for (int i = (pageNum - 1) * numPerPage; i < end; i++) {
			Profile profile = dtos.get(i);
			dtos2.add(profile);
		}
		String pageUrl = "/member/dog/queryfriend?dogid=" + dogid;
		if (StringUtil.isNotEmpty(title)) {
			pageUrl = pageUrl + "&title=" + title;
		}
		String page = PageUtil.getPagerNormal(dtos.size(), numPerPage, pageNum,
				pageUrl);
		model.addAttribute("page", page);
		model.addAttribute("dogid", dogid);
		model.addAttribute("beans", dtos2);
		model.addAttribute("title", title);
		model.addAttribute("numPerPage", numPerPage);
		return "member/dog/my/queryfriend";
	}

	/**
	 * 确认转让
	 * 
	 * @param dogid
	 * @param touid
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping("/trans/submit")
	@ResponseBody
	public String transSubmit(Integer dogid, Integer touid,
			HttpServletResponse response, HttpServletRequest request) {
		String sessionid = CookieUtil.getCookieValue(request, "k9membertoken");
		OnlineUser _onlineUser = userManager.getByClassId(OnlineUser.class,
				sessionid);
		Account _account = _onlineUser.getAccount();
		dogManager.checkDog(dogid, _account, touid);
		// return "redirect:/member/dog";
		return "1";

	}

	/**
	 * 已售宠物
	 * 
	 * @param model
	 * @param title
	 * @param request
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 */
	@RequestMapping("/selled")
	public String selled(Model model, String title, HttpServletRequest request,
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
		Account account = _onlineUser.getAccount();

		Integer totalCount = transManager.getCount(title, account);
		List<TransRecord> beans = transManager.getTransRecords(title, account,
				pageNum, numPerPage);
		String page = PageUtil.getPagerNormal(totalCount, numPerPage, pageNum,
				"/member/dog/selled");
		model.addAttribute("page", page);
		model.addAttribute("beans", beans);
		model.addAttribute("title", title);
		return "member/dog/selled/list";
	}

	/**
	 * 宠物详情
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/view")
	public String view(Model model, Integer id) {
		Dog dog = dogManager.getByClassId(Dog.class, id);
		DogDto dto = new DogDto(dog);
		model.addAttribute("dto", dto);
		model.addAttribute("bean", dog);
		return "member/dog/selled/view";
	}

	/**
	 * 宠物主人
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/owner")
	public String owner(Model model, Integer id) {
		Account account = baseManager.getByClassId(Account.class, id);
		ProfileDto dto = new ProfileDto(account.getProfile());
		model.addAttribute("bean", account);
		model.addAttribute("dto", dto);
		return "member/dog/selled/owner";
	}

}

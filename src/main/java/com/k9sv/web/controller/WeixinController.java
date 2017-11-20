package com.k9sv.web.controller;

import java.io.BufferedReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.k9sv.Config;
import com.k9sv.PayConfig;
import com.k9sv.domain.dto.CompanyDto;
import com.k9sv.domain.dto.DogDto;
import com.k9sv.domain.dto.OrderDto;
import com.k9sv.domain.dto.ProfileDto;
import com.k9sv.domain.dto.WeixinUser;
import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.ActivityRecord;
import com.k9sv.domain.pojo.Category;
import com.k9sv.domain.pojo.Classify;
import com.k9sv.domain.pojo.Company;
import com.k9sv.domain.pojo.Dog;
import com.k9sv.domain.pojo.Orders;
import com.k9sv.domain.pojo.Profile;
import com.k9sv.domain.pojo.Server;
import com.k9sv.service.IActivityManager;
import com.k9sv.service.IBaseManager;
import com.k9sv.service.IClassifyManager;
import com.k9sv.service.ICompanyManager;
import com.k9sv.service.IDogManager;
import com.k9sv.service.IOrderManager;
import com.k9sv.service.IServerManager;
import com.k9sv.service.IUserManager;
import com.k9sv.util.CookieUtil;
import com.k9sv.util.MD5;
import com.k9sv.util.WeixinUtil;

@Scope("prototype")
@Controller
@RequestMapping("/weixin/")
public class WeixinController extends MultiActionController {

	private static final Logger LOG = Logger.getLogger(IndexController.class);

	@Autowired
	private IDogManager dogManager;
	@Autowired
	private IUserManager userManager;
	@Autowired
	private IBaseManager baseManager;
	@Autowired
	private IOrderManager orderManager;
	@Autowired
	private IActivityManager activityManager;
	@Autowired
	private ICompanyManager companyManager;
	@Autowired
	private IClassifyManager classifyManager;
	@Autowired
	private IServerManager serverManager;

	@RequestMapping("index")
	public String index(HttpServletRequest request, String echostr, Model model) throws Exception {

		long i = System.currentTimeMillis();
		String ip = request.getRemoteAddr();// 返回发出请求的IP地址
		LOG.info(ip);
		BufferedReader br = request.getReader();
		String inputLine;
		String str = "";
		while ((inputLine = br.readLine()) != null) {
			str += inputLine;
		}
		br.close();
		LOG.info("callback json:" + str);
		LOG.info(System.currentTimeMillis() - i);

		return "weixin/index";
	}

	@RequestMapping("about")
	public String about(HttpServletRequest request, String echostr, Model model) throws Exception {
		String ip = request.getRemoteAddr();// 返回发出请求的IP地址
		LOG.info(ip);
		return "weixin/about";
	}

	/**
	 * 合作伙伴
	 * 
	 * @param request
	 * @param echostr
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("partner")
	public String partner(HttpServletRequest request, String echostr, Model model) throws Exception {
		List<Company> beans = companyManager.find();
		List<CompanyDto> companyDtos = new ArrayList<CompanyDto>();
		for (Company company : beans) {
			CompanyDto companyDto = new CompanyDto(company);
			companyDtos.add(companyDto);
		}
		model.addAttribute("companyDtos", companyDtos);
		return "weixin/partner";
	}

	/**
	 * 送体检
	 * 
	 * @param request
	 * @param response
	 * @param echostr
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping("songtijian")
	public String songtijian(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

		String token = CookieUtil.getCookieValue(request, "k9token");
		LOG.info("token:" + token);
		// token = "f8f98630140a35fbb722002d01c297e3";
		Account a = userManager.getOnlineUser(token);
		if (a == null) {
			String callbackurl = URLEncoder.encode("http://www.ichongxin.com/weixin/login");
			String state = "songtijian";
			String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + PayConfig.WeixinFuwu
					+ "&redirect_uri=" + callbackurl + "&response_type=code&scope=snsapi_userinfo&state=" + state
					+ "#wechat_redirect";
			response.sendRedirect(url);
		} else {
			ActivityRecord _record = activityManager.getActivityRecord(a.getId(), 1);
			model.addAttribute("activityRecord", _record);
		}

		return "weixin/songtijian";
	}

	@RequestMapping("login")
	public String login(HttpServletRequest request, HttpServletResponse response, String code, String state,
			Model model) throws Exception {

		long i = System.currentTimeMillis();
		LOG.info("weixin callback code:" + code);
		LOG.info("weixin callback state:" + state);
		if (code != null) {
			WeixinUser wxuser = WeixinUtil.getAccessToken(code);
			String unionid = wxuser.getUnionid();
			String openid = wxuser.getOpenid();
			LOG.info("weixin openid:" + openid);
			LOG.info("weixin unionid:" + unionid);
			Account account = userManager.getAccountByUnionid(unionid);

			String sessionid = null;
			if (account != null) {
				model.addAttribute("account", account);
				sessionid = CookieUtil.getSessionId(account.getId());
				userManager.login(account, sessionid, Config.LoginPlatformWeixin);
			} else {
				wxuser = WeixinUtil.getUserinfo(wxuser);
				LOG.info("register:" + wxuser.getNickname());
				Account _a = new Account();
				_a.setUnionid(wxuser.getUnionid());
				_a.setCreated(new Date());
				_a.setUsername(wxuser.getUnionid());
				_a.setPlatform(Config.LoginPlatformWeixin);
				_a.setPassword(MD5.md5("weixin"));
				Profile _p = new Profile();
				_p.setNickName(wxuser.getNickname());
				_p.setAvatar(wxuser.getAvatar());
				_p.setSex(1);
				_p.setAccount(_a);
				_a.setProfile(_p);
				userManager.save(_a);
				sessionid = CookieUtil.getSessionId(_a.getId());
				userManager.login(_a, sessionid, Config.LoginPlatformWeixin);
				model.addAttribute("weixin", wxuser);
			}
			CookieUtil.addCookie(response, "k9token", sessionid, 0);
			if ("yuyue".equals(state)) {
				response.sendRedirect("/weixin/yuyue");
			} else if ("m".equals(state)) {
				response.sendRedirect("/weixin/m");
			} else if ("songtijian".equals(state)) {
				response.sendRedirect("/weixin/songtijian");
			}
		}

		LOG.info(System.currentTimeMillis() - i);

		return "weixin/register";
	}

	@SuppressWarnings("deprecation")
	@RequestMapping("yuyue")
	public String yuyue(HttpServletRequest request, HttpServletResponse response, String code, Model model)
			throws Exception {
		String token = CookieUtil.getCookieValue(request, "k9token");
		LOG.info("token:" + token);
		// if (token == null) {
		// token = "faebaf7660783bd2fd2cf4264074e079";
		// CookieUtil.addCookie(response, "k9token", token, 0);
		// }

		Account a = userManager.getOnlineUser(token);
		if (a == null) {
			String callbackurl = URLEncoder.encode("http://www.ichongxin.com/weixin/login");
			String state = "yuyue";
			String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + PayConfig.WeixinFuwu
					+ "&redirect_uri=" + callbackurl + "&response_type=code&scope=snsapi_userinfo&state=" + state
					+ "#wechat_redirect";
			response.sendRedirect(url);
		} else {
			Orders order = orderManager.checkUserOrder(a.getId());
			if (order != null) {
				model.addAttribute("order", new OrderDto(order));
			}
			model.addAttribute("user", new ProfileDto(a.getProfile()));
		}

		return "weixin/yuyue";
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@RequestMapping("m")
	public String m(HttpServletRequest request, HttpServletResponse response, String code, Model model)
			throws Exception {
		long i = System.currentTimeMillis();
		String token = CookieUtil.getCookieValue(request, "k9token");
		LOG.info("token:" + token);
		// if (token == null) {
		// token = "faebaf7660783bd2fd2cf4264074e079";
		// CookieUtil.addCookie(response, "k9token", token, 0);
		// }

		Account a = userManager.getOnlineUser(token);
		if (a == null) {
			String callbackurl = URLEncoder.encode("http://www.ichongxin.com/weixin/login");
			String state = "m";
			String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + PayConfig.WeixinFuwu
					+ "&redirect_uri=" + callbackurl + "&response_type=code&scope=snsapi_userinfo&state=" + state
					+ "#wechat_redirect";
			response.sendRedirect(url);
		} else {
			List<Category> categories = baseManager.getObjects(Category.class);
			model.addAttribute("beans", categories);
			model.addAttribute("user", new ProfileDto(a.getProfile()));

			LOG.info("weixin m:" + (System.currentTimeMillis() - i));
		}
		return "weixin/m";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/loaddog", method = RequestMethod.POST)
	public String loaddog(HttpServletRequest request, String json, String sid, Integer petid, Model model) {
		try {
			if (petid != null) {
				Dog dog = dogManager.getByClassId(Dog.class, petid);
				DogDto dogDto = new DogDto(dog);
				List<Category> categories = dogManager.getObjects(Category.class);
				Category category = dogManager.getByClassId(Category.class, dog.getClassify());
				model.addAttribute("beans", categories);
				model.addAttribute("dog", dogDto);
				model.addAttribute("category", category);
			}
			return "weixin/doginfor";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/loadpartner", method = RequestMethod.POST)
	public String loadpartner(HttpServletRequest request, String json, String sid, Integer partnerid, Model model) {
		Company company = companyManager.getByClassId(Company.class, partnerid);
		CompanyDto companyDto = new CompanyDto(company);
		List<Classify> classifies = classifyManager.getClassifys(0);// 一级分类
		Map<String, Classify> classymap = new LinkedHashMap<String, Classify>();
		Map<String, List<Server>> servermap = new LinkedHashMap<String, List<Server>>();
		for (int i = 0; i < classifies.size(); i++) {
			char c = (char) ((int) 'a' + i);
			Classify classify = classifies.get(i);
			classymap.put("ui-block-" + c, classify);
			List<Server> beans = serverManager.find(company.getId(), classify.getId());
			servermap.put("divui-block-" + c, beans);
		}
		model.addAttribute("companyDto", companyDto);
		model.addAttribute("classymap", classymap);
		model.addAttribute("servermap", servermap);
		return "weixin/partnerinfor";
	}

	@RequestMapping(value = "/baidumap")
	public String baidumap(HttpServletRequest request, Integer partnerid, Model model) {
		Company company = companyManager.getByClassId(Company.class, partnerid);
		CompanyDto companyDto = new CompanyDto(company);
		model.addAttribute("company", companyDto);
		return "weixin/baidumap";
	}

}

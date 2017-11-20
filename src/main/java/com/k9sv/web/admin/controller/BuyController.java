package com.k9sv.web.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.k9sv.Config;
import com.k9sv.domain.dto.PushBuyDto;
import com.k9sv.domain.dto.PushDto;
import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.Buy;
import com.k9sv.domain.pojo.Dog;
import com.k9sv.domain.pojo.Profile;
import com.k9sv.service.IBuyManager;
import com.k9sv.service.IDogManager;
import com.k9sv.util.ActionUtil;
import com.k9sv.util.GetuiPush;
import com.k9sv.util.StringUtil;

/**
 * 订单管理
 * 
 * @author mcp
 * 
 */
@Controller
@Scope("prototype")
@RequestMapping("/admin/buy")
public class BuyController {
	@Autowired
	IBuyManager buyManager;
	@Autowired
	IDogManager dogManager;

	/**
	 * 订单列表
	 * 
	 * @param model
	 * @param title
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 */
	@RequestMapping("/{statetype}")
	public String index(Model model, String title, Integer pageNum,
			Integer numPerPage, @PathVariable("statetype") int statetype) {
		if (pageNum == null) {
			pageNum = 1;
		}
		if (numPerPage == null) {
			numPerPage = 20;
		}
		Integer totalCount = buyManager.getCount2(title, statetype);
		List<Buy> beans = buyManager.getBuys2(title, statetype, pageNum,
				numPerPage);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("numPerPage", numPerPage);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("beans", beans);
		model.addAttribute("title", title);
		model.addAttribute("statetype", statetype);
		return "admin/buy/list";
	}

	@RequestMapping("/update")
	public String update(Model model, String id, int statetype) {
		Buy bean = buyManager.getByClassId(Buy.class, id);
		model.addAttribute("bean", bean);
		model.addAttribute("statetype", statetype);
		return "admin/buy/update";
	}

	@RequestMapping("/details")
	public String details(Model model, String id) {
		Buy bean = buyManager.getByClassId(Buy.class, id);
		model.addAttribute("bean", bean);
		return "admin/buy/details";
	}

	/**
	 * 查询宠物
	 * 
	 * @param model
	 * @param userName
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 */
	@RequestMapping("/querydog")
	public String querydog(Model model, String dogName, Integer pageNum,
			Integer numPerPage) {

		if (pageNum == null) {
			pageNum = 1;
		}
		if (numPerPage == null) {
			numPerPage = 20;
		}
		if (StringUtil.isNotEmpty(dogName)) {
			model.addAttribute("dogName", dogName);
		}
		Integer totalCount = dogManager.getCount(dogName);
		List<Dog> beans = dogManager.getDogs(dogName, pageNum, numPerPage);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("numPerPage", numPerPage);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("beans", beans);
		return "admin/buy/querydog";
	}

	@RequestMapping("/submit")
	public void submit(HttpServletResponse response, Buy buy, Integer oddstate,
			int statetype) {
		response.setContentType("text/html;charset=UTF-8");
		try {
			Buy _buy = buyManager.getByClassId(Buy.class, buy.getId());
			_buy.setState(buy.getState());
			_buy.setExpress(buy.getExpress());
			_buy.setOddNumbers(buy.getOddNumbers());
			_buy.setName(buy.getName());
			_buy.setTelephone(buy.getTelephone());
			_buy.setAddress(buy.getAddress());
			buyManager.saveOrUpdate(_buy);
			if (buy.getState() == -1 && oddstate.intValue() != -1) {
				Account account = buyManager.getByClassId(Account.class,
						_buy.getUid());
				int goldCount = account.getProfile().getGoldCount();
				int gold = _buy.getGold();
				account.getProfile().setGoldCount(goldCount + gold);
				buyManager.update(account);
			} else if (buy.getState() == 2 && oddstate.intValue() != 2) {
				this.pushBuy(_buy);
			}
			String rel = "rel_list_009001";
			if (statetype == -1) {
				rel = "rel_list_009006";
			} else if (statetype == -2) {
				rel = "rel_list_009001";
			} else {
				int _tem = statetype + 2;
				rel = "rel_list_00900" + _tem;
			}
			response.getWriter().print(
					ActionUtil.getDWZajaxReturn("200", "成功！", rel, "",
							"closeCurrent", ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 推送发货消息
	 * 
	 * @param _buy
	 */
	private void pushBuy(final Buy _buy) {

		Thread t = new Thread(new Runnable() {

			public void run() {
				try {
					PushBuyDto messageDto = new PushBuyDto(_buy);
					PushDto pushDto = new PushDto();
					pushDto.setData(messageDto);

					String alter = "你的订单已发货";
					Profile toProfile = buyManager.getByClassId(Profile.class,
							_buy.getUid());
					pushDto.setType(Config.BuyState);
					GetuiPush.push(alter, pushDto, toProfile);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		t.start();
	}
}

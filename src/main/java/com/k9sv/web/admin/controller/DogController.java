package com.k9sv.web.admin.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.k9sv.domain.dto.DogDto;
import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.Category;
import com.k9sv.domain.pojo.Dog;
import com.k9sv.domain.pojo.TransRecord;
import com.k9sv.service.ICategoryManager;
import com.k9sv.service.IDogManager;
import com.k9sv.service.ITransManager;
import com.k9sv.util.ActionUtil;
import com.k9sv.util.DateUtil;
import com.k9sv.util.QUploader;
import com.k9sv.util.StringUtil;

/**
 * 宠物管理
 * 
 * @author mcp
 * 
 */
@Controller
@Scope("prototype")
@RequestMapping("/admin/dog")
public class DogController {
	@Autowired
	IDogManager dogManager;
	@Autowired
	ITransManager transManager;
	@Autowired
	ICategoryManager categoryManager;

	/**
	 * 宠物列表
	 * 
	 * @param model
	 * @param title
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 */
	@RequestMapping("/{accountId}/{havablood}")
	public String index(Model model, String title, Integer pageNum,
			Integer numPerPage, @PathVariable("accountId") int accountId,
			@PathVariable("havablood") int havablood) {
		if (pageNum == null) {
			pageNum = 1;
		}
		if (numPerPage == null) {
			numPerPage = 20;
		}
		Account account = dogManager.getByClassId(Account.class, accountId);
		Integer totalCount = dogManager.getCount2(title, account, havablood);
		List<Dog> beans = dogManager.getDogs2(title, account, havablood,
				pageNum, numPerPage);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("numPerPage", numPerPage);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("beans", beans);
		model.addAttribute("title", title);
		model.addAttribute("accountId", accountId);
		model.addAttribute("havablood", havablood);
		return "admin/dog/list";
	}

	/**
	 * 修改
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/shencha")
	public String shencha(Model model, Integer id, int accountId, int havablood) {
		Dog dog = dogManager.getByClassId(Dog.class, id);
		DogDto dto = new DogDto(dog);
		List<Category> categories = categoryManager.getCategorys(1);
		String update = DateUtil.getFormatDateTime(new Date(), "yyyy-MM-dd");
		model.addAttribute("update", update);
		model.addAttribute("beans", categories);
		model.addAttribute("dto", dto);
		model.addAttribute("bean", dog);
		model.addAttribute("accountId", accountId);
		model.addAttribute("havablood", havablood);
		return "admin/dog/shencha";
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
			Integer pageNum, Integer numPerPage) {
		if (pageNum == null) {
			pageNum = 1;
		}
		if (numPerPage == null) {
			numPerPage = 20;
		}
		Integer totalCount = dogManager.getCount(title, dogid);
		List<Dog> beans = dogManager.getDogs(title, dogid, pageNum, numPerPage);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("numPerPage", numPerPage);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("beans", beans);
		model.addAttribute("title", title);
		model.addAttribute("dogid", dogid);
		return "admin/dog/querydog";
	}

	/**
	 * ajax上传图片
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
	 * 确认修改
	 * 
	 * @param response
	 * @param account
	 * @param roleid
	 * @param cityid1
	 * @param fcityid
	 */
	@RequestMapping("/updateconfirm")
	public void updateconfirm(HttpServletResponse response, Dog dog,
			Integer accountId, int havablood, String birthdayStr1,
			Integer oldisok) {
		response.setContentType("text/html;charset=UTF-8");
		try {
			int dogid = dog.getId();
			Dog _dog = dogManager.getByClassId(Dog.class, dogid);
			if (StringUtil.isNotEmpty(dog.getAvatar())) {
				_dog.setAvatar(dog.getAvatar());
			}
			if (StringUtil.isNotEmpty(dog.getBlood())) {
				_dog.setBlood(dog.getBlood());
			}
			_dog.setCategoryId(dog.getCategoryId());
			if (StringUtil.isNotEmpty(dog.getName())) {
				_dog.setName(dog.getName());
			}
			_dog.setSex(dog.getSex());
			if (StringUtil.isNotEmpty(dog.getFatherBlood())) {
				_dog.setFatherBlood(dog.getFatherBlood());
			}
			if (StringUtil.isNotEmpty(dog.getMatherBlood())) {
				_dog.setMatherBlood(dog.getMatherBlood());
			}
			if (dog.getHeight() != 0) {
				_dog.setHeight(dog.getHeight());
			}
			if (dog.getWeight() != 0) {
				_dog.setWeight(dog.getWeight());
			}
			if (StringUtil.isNotEmpty(birthdayStr1)) {
				_dog.setBirthday(DateUtil.parse(birthdayStr1, null));
			}
			if (StringUtil.isNotEmpty(dog.getIntro())) {
				_dog.setIntro(dog.getIntro());
			}
			_dog.setIsok(dog.getIsok());
			if (oldisok == 0 && dog.getIsok() == 1) {
				Dog _temp = dogManager.getDogBuyBlood(dog.getBlood());
				if (_temp != null) {
					_temp.setIsok(0);
					dogManager.update(_temp);
				}
			}
			dogManager.update(_dog);
			response.getWriter().print(
					ActionUtil.getDWZajaxReturn("200", "成功！", "", "",
							"forward", "/admin/dog/" + accountId + "/"
									+ havablood));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除违规宠物信息
	 * 
	 * @param response
	 * @param id
	 */
	@RequestMapping("/delete")
	public void delete(HttpServletResponse response, Integer id,
			Integer accountId, int havablood) {
		response.setContentType("text/html;charset=UTF-8");
		Dog dog = dogManager.getByClassId(Dog.class, id);
		dog.setDeleted(1);
		dogManager.saveOrUpdate(dog);
		try {
			response.getWriter().print(
					ActionUtil.getDWZajaxReturn("200", "成功！", "", "",
							"forward", "/admin/dog/" + accountId + "/"
									+ havablood));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 宠物转手记录
	 * 
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping("/transinfor")
	public String transinfor(Model model, String title, Integer pageNum,
			Integer numPerPage, Integer id) {
		if (pageNum == null) {
			pageNum = 1;
		}
		if (numPerPage == null) {
			numPerPage = 20;
		}
		Integer totalCount = transManager.getCount(title, id);
		List<TransRecord> beans = transManager.getTransRecords(title, id,
				pageNum, numPerPage);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("numPerPage", numPerPage);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("beans", beans);
		model.addAttribute("title", title);
		model.addAttribute("fid", id);
		return "admin/dog/transinfor";
	}
}

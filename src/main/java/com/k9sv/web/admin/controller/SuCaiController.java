package com.k9sv.web.admin.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import com.k9sv.Config;
import com.k9sv.domain.pojo.ToutiaoPublish;
import com.k9sv.domain.pojo.ToutiaoSucai;
import com.k9sv.service.IBaseManager;
import com.k9sv.service.ITouTiaoManager;
import com.k9sv.util.ActionUtil;
import com.k9sv.util.Constants;
import com.k9sv.util.DateUtil;
import com.k9sv.util.QRCodeEvents;
import com.k9sv.util.SortColumn;
import com.k9sv.util.StringUtil;
import com.k9sv.util.QUploader;
import com.qcloud.PicCloud;
import com.qcloud.UploadResult;

/**
 * 素材管理
 * 
 * @author mcp
 * 
 */
@Controller
@Scope("prototype")
@RequestMapping("/admin/sucai")
public class SuCaiController {

	@Autowired
	IBaseManager baseManager;
	@Autowired
	ITouTiaoManager touTiaoManager;

	/**
	 * 头条素材列表
	 * 
	 * @param model
	 * @param title
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("")
	public String index(Model model, String title, Integer pageNum, Integer numPerPage) {
		if (pageNum == null) {
			pageNum = 1;
		}
		if (numPerPage == null) {
			numPerPage = 20;
		}
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("deleted", 0);
		if (StringUtil.isNotEmpty(title)) {
			searchMap.put("title", Constants.LIKE + title);
			model.addAttribute("title", title);
		}
		Map<String, Object> searchClassMap = new HashMap<String, Object>();
		List<SortColumn> scs = new ArrayList<SortColumn>();
		scs.add(new SortColumn("created", "desc"));
		Integer totalCount = baseManager.getPaginationObjects(ToutiaoSucai.class, searchMap, searchClassMap);
		List<ToutiaoSucai> beans = baseManager.getPaginationObjects(ToutiaoSucai.class, pageNum, numPerPage, searchMap,
				searchClassMap, scs);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("numPerPage", numPerPage);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("beans", beans);
		return "admin/sucai/list";
	}

	/**
	 * 添加素材
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/add")
	public String add(Model model) {
		String created = DateUtil.getFormatDateTime(DateUtil.getNow(), "yyyy-MM-dd");
		model.addAttribute("created", created);
		return "admin/sucai/add";
	}

	/**
	 * 编辑素材
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/update")
	public String update(Model model, Integer id) {

		ToutiaoSucai toutiaoSucai = (ToutiaoSucai) baseManager.getObject(ToutiaoSucai.class, id);
		model.addAttribute("bean", toutiaoSucai);
		return "admin/sucai/update";
	}

	/**
	 * 删除素材
	 * 
	 * @param model
	 * @param response
	 * @param id
	 */
	@RequestMapping("/delete")
	public void delete(Model model, HttpServletResponse response, Integer id) {
		response.setContentType("text/html;charset=UTF-8");
		// touTiaoManager.deleteSucai(id);
		ToutiaoSucai toutiaoSucai = touTiaoManager.getByClassId(ToutiaoSucai.class, id);
		toutiaoSucai.setDeleted(1);
		touTiaoManager.saveOrUpdate(toutiaoSucai);
		try {
			response.getWriter().print(ActionUtil.getDWZajaxReturn("200", "删除成功！", "rel_list_002001", "", "", ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加或修改素材提交
	 * 
	 * @param response
	 * @param sucai
	 * @param createdStr1
	 */
	@RequestMapping("/submit")
	public void submit(HttpServletResponse response, ToutiaoSucai sucai, String createdStr1) {
		response.setContentType("text/html;charset=UTF-8");
		if (StringUtil.isNotEmpty(createdStr1)) {
			sucai.setCreated(DateUtil.getDateObj(createdStr1, "-"));
		}
		baseManager.saveOrUpdate(sucai);
		try {
			response.getWriter()
					.print(ActionUtil.getDWZajaxReturn("200", "成功！", "rel_list_002001", "", "closeCurrent", ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ajax头条预览
	 * 
	 * @param model
	 * @param toutiaoSucai
	 * @return
	 */
	@RequestMapping("/ajaxpreview")
	public String ajaxpreview(Model model, ToutiaoSucai toutiaoSucai) {
		toutiaoSucai.setCreated(new Date());
		model.addAttribute("bean", toutiaoSucai);
		return "admin/sucai/toutiaocontent";
	}

	/**
	 * 设置素材为头条
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/set")
	public String setToutiao(Model model, Integer id) {
		ToutiaoSucai toutiaoSucai = (ToutiaoSucai) baseManager.getObject(ToutiaoSucai.class, id);
		model.addAttribute("bean", toutiaoSucai);
		return "admin/sucai/set";
	}

	/**
	 * ajax判断是否已设为头条
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/seted")
	@ResponseBody
	public String seted(Model model, Integer sid) {
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("sid", sid);
		Map<String, Object> searchClassMap = new HashMap<String, Object>();
		Integer totalCount = baseManager.getPaginationObjects(ToutiaoPublish.class, searchMap, searchClassMap);
		return totalCount + "";
	}

	/**
	 * 设置头条提交
	 * 
	 * @param response
	 * @param sucai
	 * @param createdStr1
	 */
	@RequestMapping("/submitSet")
	public void submitSet(HttpServletResponse response, HttpServletRequest request, ToutiaoPublish publish,
			String createdStr1) {
		response.setContentType("text/html;charset=UTF-8");
		try {
			if (StringUtil.isNotEmpty(createdStr1)) {
				publish.setPublished(DateUtil.parse(createdStr1, "yyyy-MM-dd HH"));
			}
			baseManager.saveOrUpdate(publish);
			// 二维码
			createQrCode(request, publish);
			response.getWriter()
					.print(ActionUtil.getDWZajaxReturn("200", "设置头条成功！", "rel_list_002001", "", "closeCurrent", ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void createQrCode(HttpServletRequest request, ToutiaoPublish publish) {
		// 生成二维码
		String text = Config.Server + "toutiao/" + publish.getId();
		String imgPath = request.getSession().getServletContext().getRealPath("/attachments/toutiao/share.png");
		QRCodeEvents.createPic(text, imgPath);
		// 上传腾讯云
		PicCloud pc = new PicCloud(Config.projectID, Config.secretID, Config.secretKey, Config.projectName);
		UploadResult result = new UploadResult();
		pc.upload(imgPath, result);
		publish.setQrCode(result.downloadUrl);
		baseManager.update(publish);
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
	public @ResponseBody String ajaxUploadPic(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("file") MultipartFile file) {
		response.setContentType("text/html;charset=UTF-8");
		QUploader uploader = new QUploader(request);
		return uploader.upImagToQCloud(file);
	}

}

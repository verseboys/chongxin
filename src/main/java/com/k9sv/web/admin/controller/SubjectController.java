package com.k9sv.web.admin.controller;

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

import com.k9sv.Config;
import com.k9sv.domain.pojo.Subject;
import com.k9sv.service.ISubjectManager;
import com.k9sv.util.ActionUtil;
import com.k9sv.util.QRCodeEvents;
import com.k9sv.util.QUploader;
import com.k9sv.util.StringUtil;
import com.qcloud.PicCloud;
import com.qcloud.UploadResult;

/**
 * 专题管理
 * 
 * @author mcp
 * 
 */
@Controller
@Scope("prototype")
@RequestMapping("/admin/subject")
public class SubjectController {
	@Autowired
	ISubjectManager subjectManager;

	/**
	 * 专题列表
	 * 
	 * @param model
	 * @param title
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 */
	@RequestMapping("")
	public String index(Model model, String title, Integer pageNum, Integer numPerPage) {
		if (pageNum == null) {
			pageNum = 1;
		}
		if (numPerPage == null) {
			numPerPage = 20;
		}
		Integer totalCount = subjectManager.getCount(title);
		List<Subject> beans = subjectManager.getSubjects(title, pageNum, numPerPage);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("numPerPage", numPerPage);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("beans", beans);
		model.addAttribute("title", title);
		return "admin/subject/list";
	}

	@RequestMapping("/add")
	public String add(Model model) {
		return "admin/subject/add";
	}

	@RequestMapping("/update")
	public String update(Model model, Integer id) {
		Subject bean = subjectManager.getByClassId(Subject.class, id);
		model.addAttribute("bean", bean);
		return "admin/subject/update";
	}

	@RequestMapping("/delete")
	public void delete(Model model, HttpServletResponse response, Integer id) {
		response.setContentType("text/html;charset=UTF-8");
		try {
			Subject bean = subjectManager.getByClassId(Subject.class, id);
			bean.setDeleted(1);
			subjectManager.update(bean);
			response.getWriter().print(ActionUtil.getDWZajaxReturn("200", "删除成功！", "rel_list_011001", "", "", ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/submit")
	public void submit(HttpServletRequest request, HttpServletResponse response, Subject subject, String createdStr,
			String domain1) {
		response.setContentType("text/html;charset=UTF-8");
		try {
			if (!domain1.endsWith(subject.getDomain())) {
				// 二维码
				subject = createQrCode(request, subject);
			}
			subjectManager.saveOrUpdate(subject);
			response.getWriter()
					.print(ActionUtil.getDWZajaxReturn("200", "成功！", "rel_list_011001", "", "closeCurrent", ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Subject createQrCode(HttpServletRequest request, Subject subject) {
		if (StringUtil.isEmpty(subject.getDomain())) {
			return null;
		}
		// 生成二维码
		String text = Config.Server + "subject/" + subject.getDomain();
		String imgPath = request.getSession().getServletContext().getRealPath("/attachments/subject/subject.png");
		QRCodeEvents.createPic(text, imgPath);
		// 上传腾讯云
		PicCloud pc = new PicCloud(Config.projectID, Config.secretID, Config.secretKey, Config.projectName);
		UploadResult result = new UploadResult();
		pc.upload(imgPath, result);
		subject.setQrcode(result.downloadUrl);
		return subject;
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
		return uploader.upImagToQCloud(file, "subject1");
	}

}

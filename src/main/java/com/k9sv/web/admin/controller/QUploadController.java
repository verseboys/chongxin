package com.k9sv.web.admin.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.k9sv.util.QUploader;
/**
 * 图片上传
 * @author mcp
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/upload")
public class QUploadController {
	protected transient final Logger logger = Logger
			.getLogger(QUploadController.class);

	@RequestMapping(value = "/newsImage/upload")
	public void ajaxUploadProduct(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("filedata") MultipartFile file) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		QUploader uploader = new QUploader(request);
		response.getWriter().print(uploader.upImagToQCloud2(file));
	}

	@RequestMapping(value = "/newsImage/upload/{type}")
	public void ajaxUploadProduct2(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("filedata") MultipartFile file,
			@PathVariable("type") String type) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		QUploader uploader = new QUploader(request);
		response.getWriter().print(uploader.upImagToQCloud2(file,type));
	}

}
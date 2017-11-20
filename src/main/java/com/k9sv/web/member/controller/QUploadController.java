package com.k9sv.web.member.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.k9sv.util.QUploader;
/**
 * 图片上传
 * @author mcp
 *
 */
@Controller("memberQUpload")
@Scope("prototype")
@RequestMapping("/upload")
public class QUploadController {
	protected transient final Logger logger = Logger
			.getLogger(QUploadController.class);

	@RequestMapping(value = "/newsImage/member_upload")
	@ResponseBody
	public String ajaxUploadProduct(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("imgFile") MultipartFile file) throws IOException {
		QUploader uploader = new QUploader(request);
		return uploader.upImagToQCloud3(file);
	}

}
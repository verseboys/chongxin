package com.k9sv.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.k9sv.domain.pojo.Company;
import com.k9sv.domain.pojo.Subject;
import com.k9sv.service.ICompanyManager;
import com.k9sv.service.ISubjectManager;

@Controller
@Scope("prototype")
public class IndexController extends MultiActionController {

	@Autowired
	private ISubjectManager subjectManager;
	@Autowired
	private ICompanyManager companyManager;

	private static final Logger LOG = Logger.getLogger(IndexController.class);

	@RequestMapping("")
	public String tt(HttpServletRequest request, HttpServletResponse response,
			String pppp, Model model) throws Exception {
		// Account account = K9Context.getCurrentAccount();
		// model.addAttribute("account", account);
		long i = System.currentTimeMillis();
		String ip = request.getRemoteAddr();// 返回发出请求的IP地址
		LOG.info(ip);

		LOG.info(System.currentTimeMillis() - i);

		return "index";
	}

	@RequestMapping("about")
	public String about(HttpServletRequest request,
			HttpServletResponse response, String pppp, Model model)
			throws Exception {
		return "about";
	}

	/*@RequestMapping("test")
	public void test(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(
				request.getInputStream()));
		String resContent = IOUtils.toString(br);
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(resContent);
			Document document = reader.read(new ByteArrayInputStream(resContent
					.getBytes("UTF-8")));
			Element root = document.getRootElement();
			System.out.println(root.elementText("appid"));
		} catch (DocumentException e) {
			e.printStackTrace();
		}

	}*/

	@RequestMapping("bao")
	public String bao(HttpServletRequest request, HttpServletResponse response,
			String pppp, Model model) throws Exception {
		return "bao";
	}

	@RequestMapping("company")
	public String company(HttpServletRequest request, HttpServletResponse response,
			String pppp, Model model) throws Exception {
		return "company";
	}
	
	@RequestMapping("subject")
	public String subject(HttpServletRequest request,
			HttpServletResponse response, String pppp, Model model)
			throws Exception {
		List<Subject> subjects = subjectManager.getSubjects("", 1, 8);
		List<Company> companies = companyManager.getCompanys("", 1, 1, 10);// 合作犬舍
		model.addAttribute("subjects", subjects);
		model.addAttribute("companies", companies);
		return "subject";
	}
	@RequestMapping("download")
	public String download(HttpServletRequest request, HttpServletResponse response,
			String pppp, Model model) throws Exception {
		return "download";
	}
}

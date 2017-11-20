package com.k9sv.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.k9sv.domain.pojo.Subject;
import com.k9sv.service.ISubjectManager;

@Controller("SubjectController")
@Scope("prototype")
@RequestMapping("/subject")
public class SubjectController {

	@Autowired
	private ISubjectManager subjectManager;

	@RequestMapping("/{domain}")
	public String index(Model model, @PathVariable("domain") String domain)
			throws Exception {
		Subject subject = subjectManager.getSubject(domain);
		model.addAttribute("subject", subject);
		return "subject_infor";
	}
}

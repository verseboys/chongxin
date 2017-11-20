package com.k9sv.server.controller;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.k9sv.domain.pojo.FeedPic;

@Controller
@Scope("prototype")
@RequestMapping("/test")
public class TestController {

	public static void main(String[] args) {
		Set<FeedPic> _feedPics = new HashSet<FeedPic>();
		FeedPic feedPic = new FeedPic();
		feedPic.setUrl("123");
		feedPic.setUid(1);
		_feedPics.add(feedPic);
		FeedPic feedPic2 = new FeedPic();
		feedPic2.setUrl("123");
		_feedPics.remove(feedPic2);
		Set<FeedPic> _feedPics2 = new HashSet<FeedPic>();
		_feedPics2.add(feedPic);
		// System.out.println(new TestController().isTelephone("11.1"));
	}

	public boolean isTelephone(String phonenumber) {
		String phone = "^[1-9]/d*/./d*|0/./d*[1-9]/d*|0?/.0+|0$";
		Pattern p = Pattern.compile(phone);
		Matcher m = p.matcher(phonenumber);
		return m.matches();
	}

	/**
	 * 查询当天记录
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "")
	public @ResponseBody String login(HttpServletRequest request, String json, String sid, Model model) {
		// List<Message> list = messageManager.findMessage(3);
		/*
		 * List<Message> list = messageManager .find(
		 * "from (from Message where toUid = ? ORDER BY created DESC) b GROUP BY b.fromUid"
		 * , new Object[] { 3 });
		 */
		System.out.println();
		/*
		 * List<Account> accounts = userManager.find(
		 * "from Account where date(lastLogin) = curdate()", null);
		 */
		return null;
	}

}

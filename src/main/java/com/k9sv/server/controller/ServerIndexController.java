package com.k9sv.server.controller;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Scope("prototype")
@RequestMapping("/server/index")
public class ServerIndexController {

	private static final Logger LOG = Logger.getLogger(ServerIndexController.class);

	@RequestMapping(value = "", method = RequestMethod.POST)
	public @ResponseBody String index(String json, String sid, Model model) {
		try {
			LOG.info("request：" + sid + " " + json);
			JSONObject jsonObject = new JSONObject(json);
			String type = jsonObject.getString("type");
			// Account account = userManager.getOnlineUser(sid);
			// if(account == null){
			// obj.put("type", type);
			// obj.put("state", 2);
			// obj.put("errorcode", "没登陆");
			// LOG.info("no login:"+obj.toString());
			// return obj.toString();
			// }

			String res = null;
			LOG.info("type:" + type);

			if (res == null) {
				JSONObject _json = new JSONObject();
				_json.put("type", type);
				_json.put("state", -1);
				_json.put("errorcode", "数据异常！");
				res = _json.toString();
			}
			LOG.info("response：" + res);
			return res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

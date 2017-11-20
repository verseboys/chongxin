package com.k9sv.server.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.k9sv.domain.dto.ResDto;
import com.k9sv.domain.dto.TouTiaoDto;
import com.k9sv.domain.pojo.ToutiaoPublish;
import com.k9sv.service.ITouTiaoManager;
import com.k9sv.util.JsonUtil;

@Controller
@Scope("prototype")
@RequestMapping("/server/toutiao")
public class ServerToutiaoController {
	@Autowired
	private ITouTiaoManager touTiaoManager;

	/**
	 * 头条列表
	 * 
	 * @param request
	 * @param json
	 * @param sid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public @ResponseBody
	String list(HttpServletRequest request, String json, String sid, Model model) {
		ResDto res = new ResDto();
		try {
			Date _now = new Date();

			JSONObject jsonD = JsonUtil.getJson(request, json);

			Integer start = JsonUtil.getInt(jsonD, "start");
			Integer size = JsonUtil.getSize(jsonD, "size");
			List<ToutiaoPublish> _list = touTiaoManager.getTouTiaos(start,
					size, _now);
			List<TouTiaoDto> tiaoDtos = new ArrayList<TouTiaoDto>();
			for (ToutiaoPublish toutiaoPublish : _list) {
				TouTiaoDto tiaoDto = new TouTiaoDto(toutiaoPublish);
				tiaoDtos.add(tiaoDto);
			}
			res.setState(0);
			res.setData(tiaoDtos);
			return res.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

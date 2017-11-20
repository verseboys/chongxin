package com.k9sv.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.k9sv.domain.dto.DogDto;
import com.k9sv.domain.dto.RecordDto;
import com.k9sv.domain.pojo.Dog;
import com.k9sv.domain.pojo.Record;
import com.k9sv.service.IDogManager;
import com.k9sv.service.IRecordManager;
import com.k9sv.util.DateUtil;

@Controller("DogController")
@Scope("prototype")
@RequestMapping("/dog")
public class DogController {

	@Autowired
	private IDogManager dogManager;
	@Autowired
	IRecordManager recordManager;

	@RequestMapping("/share")
	public String share(Model model, Integer uid, Integer dogid)
			throws Exception {
		Dog dog = dogManager.getByClassId(Dog.class, dogid);
		DogDto dogDto = new DogDto(dog);
		// String birth = DateUtil.calAge(dog.getBirthday());
		String birth = DateUtil.getHowOld(dog.getBirthdayStr());
		List<Record> _records = recordManager.getDogRecords(dogid, 0, 30);
		List<RecordDto> recordDtos = new ArrayList<RecordDto>();
		for (Record record : _records) {
			RecordDto recordDto = new RecordDto(record, 0, 0, null, null);
			recordDtos.add(recordDto);
		}
		model.addAttribute("uid", uid);
		model.addAttribute("dog", dog);// 宠物详情
		model.addAttribute("dogDto", dogDto);// 宠物详情
		model.addAttribute("birth", birth);// 生日
		model.addAttribute("recordDtos", recordDtos);
		return "h5/dogshare";
	}
}

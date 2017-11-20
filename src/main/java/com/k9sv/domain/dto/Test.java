package com.k9sv.domain.dto;

import com.google.gson.Gson;
import com.k9sv.Errorcode;

public class Test {

	public static void main(String[] args) {
		ResDto dto = new ResDto();
		dto.setState(1);
		dto.setErrorcode(Errorcode.Registered.getCode());
		dto.setErrormsg(Errorcode.Registered.getErrormsg());
		
		Gson gson = new Gson();
        String str = gson.toJson(dto);
        
        System.out.println(str);

	}

}

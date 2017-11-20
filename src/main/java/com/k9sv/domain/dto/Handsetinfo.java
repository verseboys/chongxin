package com.k9sv.domain.dto;

import org.json.JSONException;
import org.json.JSONObject;

import com.k9sv.util.JsonUtil;

public class Handsetinfo {

	private String  platform;
	
	private String version;
	
	private String systemversion;
	
	public static Handsetinfo getHandsetinfo(String json){
		try {
			Handsetinfo handsetinfo = new Handsetinfo();
			JSONObject jsonD = new JSONObject(json);
			String platform = JsonUtil.getString(jsonD,"platform");
			String version = JsonUtil.getString(jsonD,"version");
			String systemversion = JsonUtil.getString(jsonD,"systemversion");
			
			handsetinfo.setPlatform(platform);
			handsetinfo.setVersion(version);
			handsetinfo.setSystemversion(systemversion);
			return handsetinfo;
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	

	public String getPlatform() {
		return platform;
	}



	public void setPlatform(String platform) {
		this.platform = platform;
	}



	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getSystemversion() {
		return systemversion;
	}

	public void setSystemversion(String systemversion) {
		this.systemversion = systemversion;
	}
	
}

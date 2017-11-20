package com.k9sv.domain.dto;

import org.json.JSONException;
import org.json.JSONObject;

import com.k9sv.util.JsonUtil;


public class AccountDto {

	private int id;
	
	private String username;
	
	private String password;
	
	private String clientid;
	
	private String platform;
	
	private String unionid;
	
	private String version;
	
	public static AccountDto getAccountDto(String json){
		try {
			AccountDto account = new AccountDto();
			JSONObject jsonD = new JSONObject(json);
			String username = JsonUtil.getString(jsonD,"username");
			String password = JsonUtil.getString(jsonD,"password");
			String clientid = JsonUtil.getString(jsonD,"clientid");
			String unionid = JsonUtil.getString(jsonD,"unionid");
			
			account.setUsername(username);
			account.setPassword(password);
			account.setUnionid(unionid);
			account.setClientid(clientid);
			
			return account;
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getClientid() {
		return clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	
}

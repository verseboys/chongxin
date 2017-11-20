package com.k9sv.domain.pojo;

import org.springframework.security.core.GrantedAuthority;

public class Role  implements java.io.Serializable,GrantedAuthority  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7149690290330974866L;

	private int id;
	
	private String title;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	public String getAuthority() {
		return getTitle();
	}

}

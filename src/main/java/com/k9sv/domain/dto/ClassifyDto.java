package com.k9sv.domain.dto;

import com.k9sv.domain.pojo.Classify;

/**
 * Classify entity. @author MyEclipse Persistence Tools
 */

public class ClassifyDto implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 5205049058447751781L;
	/**
	 * 
	 */
	private Integer id;
	private String name;

	public ClassifyDto(Classify classify) {
		this.id = classify.getId();
		this.name = classify.getName();
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
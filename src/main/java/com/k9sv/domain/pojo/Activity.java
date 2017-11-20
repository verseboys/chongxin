package com.k9sv.domain.pojo;

/**
 * Activity entity. @author MyEclipse Persistence Tools
 */

public class Activity implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -3334950326914792811L;
	private int id;
	private String name;
	private int isfinished;

	// Constructors

	/** default constructor */
	public Activity() {
	}

	/** full constructor */
	public Activity(String name) {
		this.name = name;
	}

	// Property accessors

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIsfinished() {
		return isfinished;
	}

	public void setIsfinished(int isfinished) {
		this.isfinished = isfinished;
	}

}
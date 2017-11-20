package com.k9sv.domain.pojo;

/**
 * Doctor entity. @author MyEclipse Persistence Tools
 */

public class Doctor implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -6453256974757871887L;
	private int id;
	private String name;
	private String job;
	private String telephone;
	private int deleted;

	// Constructors

	/** default constructor */
	public Doctor() {
	}

	/** full constructor */
	public Doctor(String name, String job, String telephone) {
		this.name = name;
		this.job = job;
		this.telephone = telephone;
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

	public String getJob() {
		return this.job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

}
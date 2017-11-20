package com.k9sv.domain.pojo;

/**
 * Classify entity. @author MyEclipse Persistence Tools
 */

public class Classify implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -3100293835194224270L;
	private int id;
	private String name;
	private int pid;
	private int type;

	private Classify classify;// 父类型

	// Constructors

	/** default constructor */
	public Classify() {
	}

	/** full constructor */
	public Classify(String name, int pid) {
		this.name = name;
		this.pid = pid;
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

	public int getPid() {
		return this.pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public Classify getClassify() {
		return classify;
	}

	public void setClassify(Classify classify) {
		this.classify = classify;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
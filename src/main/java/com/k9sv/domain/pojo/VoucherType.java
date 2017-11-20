package com.k9sv.domain.pojo;

/**
 * VoucherType entity. @author MyEclipse Persistence Tools
 */

public class VoucherType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6791868133807057250L;
	private int id;
	private String name;
	private String intro;
	private int effectivetime;

	// Constructors

	/** default constructor */
	public VoucherType() {
	}

	/** full constructor */
	public VoucherType(String name, String intro, int effectivetime) {
		this.name = name;
		this.intro = intro;
		this.effectivetime = effectivetime;
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

	public String getIntro() {
		return this.intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public int getEffectivetime() {
		return this.effectivetime;
	}

	public void setEffectivetime(int effectivetime) {
		this.effectivetime = effectivetime;
	}

}
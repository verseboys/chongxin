package com.k9sv.domain.pojo;

import java.util.Date;

/**
 * Orderoperation entity. @author MyEclipse Persistence Tools
 */

public class Orderoperation implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String orderid;
	private int statefrom;
	private int stateto;
	private Date time;

	// Constructors

	/** default constructor */
	public Orderoperation() {
	}

	/** full constructor */
	public Orderoperation(String orderid, int statefrom, int stateto,
			Date time) {
		this.orderid = orderid;
		this.statefrom = statefrom;
		this.stateto = stateto;
		this.setTime(time);
	}

	// Property accessors

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderid() {
		return this.orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getStatefrom() {
		return statefrom;
	}

	public void setStatefrom(int statefrom) {
		this.statefrom = statefrom;
	}

	public int getStateto() {
		return stateto;
	}

	public void setStateto(int stateto) {
		this.stateto = stateto;
	}

}
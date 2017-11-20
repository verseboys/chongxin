package com.k9sv.domain.pojo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.k9sv.util.DateUtil;

/**
 * Voucher entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("unused")
public class Voucher implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -8939506949970207061L;
	private int id;
	private int uid;
	private int typeid;
	private int price;
	private int beused;// 已使用
	private Date created;
	private Date endtime;
	private String endtimeStr;
	private int beoverdue;// 已过期

	private VoucherType type;
	private Profile profile;

	// Constructors

	/** default constructor */
	public Voucher() {
	}

	/** full constructor */
	public Voucher(int uid, int typeid, int price, Date created, Date endtime) {
		this.uid = uid;
		this.typeid = typeid;
		this.price = price;
		this.created = created;
		this.endtime = endtime;
	}

	// Property accessors

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUid() {
		return this.uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getTypeid() {
		return this.typeid;
	}

	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getEndtime() {
		return this.endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public VoucherType getType() {
		return type;
	}

	public void setType(VoucherType type) {
		this.type = type;
	}

	public int getBeused() {
		return beused;
	}

	public void setBeused(int beused) {
		this.beused = beused;
	}

	public String getEndtimeStr() {
		try {
			return DateUtil.getFormatDateTime(this.endtime, "yyyy-MM-dd");
		} catch (Exception e) {
			return null;
		}
	}

	public int getBeoverdue() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dt1 = df.parse(DateUtil.getFormatDateTime(new Date(), "yyyy-MM-dd"));
			Date dt2 = df.parse(this.getEndtimeStr());
			if (dt1.getTime() > dt2.getTime()) {
				return 1;// 过期
			}
			return 0;
		} catch (Exception e) {
			return -1;
		}

	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

}
package com.k9sv.domain.pojo;

import java.util.Date;
import java.util.List;

import com.k9sv.util.DateUtil;

/**
 * Company entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("unused")
public class Company implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String banner;
	private String telephone;
	private String address;
	private String district;   //区
	private double latitude;
	private double longtitude;
	
	private String latitudeStr;
	private String longtitudeStr;
	
	private String introduction;
	private Date created;
	private String createdStr;
	private String open_time;
	private int deleted;
	private int type;
	private String notice;//公告
	
	private double distance;
	
	private List<Server> servers;
	
	private Profile profile;
	
	

	// Constructors

	/** default constructor */
	public Company() {
	}

	/** minimal constructor */
	public Company(Date created) {
		this.created = created;
	}

	/** full constructor */
	public Company(String name, String banner, String telephone,
			String address, Float latitude, Float longtitude,
			String introduction, Date created) {
		this.name = name;
		this.banner = banner;
		this.telephone = telephone;
		this.address = address;
		this.latitude = latitude;
		this.longtitude = longtitude;
		this.introduction = introduction;
		this.created = created;
	}

	public boolean equals(Object obj) {  
        if (this == obj)  
            return true;  
        if (obj == null)  
            return false;  
        if (getClass() != obj.getClass())  
            return false;  
        final Company other = (Company) obj;  
        if (this.getId() != other.getId())  
            return false;  
        return true;  
    }  
	public int hashCode(){
		return id;
	}

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

	public String getBanner() {
		return this.banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public double getLongtitude() {
		return this.longtitude;
	}

	public void setLongtitude(Double longtitude) {
		this.longtitude = longtitude;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getOpen_time() {
		return open_time;
	}

	public void setOpen_time(String open_time) {
		this.open_time = open_time;
	}

	public String getCreatedStr() {
		return DateUtil.getFormatDateTime(this.created, "yyyy-MM-dd");
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}



	public String getLatitudeStr() {
		/*if (this.latitude == 0) {
			return null;
		}*/
		if (this.latitude == 0) {
			return "";
		}
		return Double.toString(this.latitude);
	}

	public String getLongtitudeStr() {
		/*if (this.longtitude == 0) {
			return null;
		}*/
		if (this.longtitude == 0) {
			return "";
		}
		return Double.toString(this.longtitude);
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public List<Server> getServers() {
		return servers;
	}

	public void setServers(List<Server> servers) {
		this.servers = servers;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public void setLongtitude(double longtitude) {
		this.longtitude = longtitude;
	}

	public void setLatitudeStr(String latitudeStr) {
		this.latitudeStr = latitudeStr;
	}

	public void setLongtitudeStr(String longtitudeStr) {
		this.longtitudeStr = longtitudeStr;
	}

	public void setCreatedStr(String createdStr) {
		this.createdStr = createdStr;
	}

}

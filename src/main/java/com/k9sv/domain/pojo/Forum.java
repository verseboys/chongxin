package com.k9sv.domain.pojo;

import java.util.List;

public class Forum  implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 879232993440818382L;
	
	private int id;
	
	private String title;
	
	private String intro;
	
	private String logo;
	
	private int pid;
	
	private int topics;
	
	private int viewed;
	
	private int todayTopics;
	
	private boolean enable;
	
	private int orders;
	
	private Topic newTopic;

	private List<Profile> profiles;  //版主
	
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

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public int getTopics() {
		return topics;
	}

	public void setTopics(int topics) {
		this.topics = topics;
	}

	public int getTodayTopics() {
		return todayTopics;
	}

	public void setTodayTopics(int todayTopics) {
		this.todayTopics = todayTopics;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public int getOrders() {
		return orders;
	}

	public void setOrders(int orders) {
		this.orders = orders;
	}

	public List<Profile> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<Profile> profiles) {
		this.profiles = profiles;
	}

	public Topic getNewTopic() {
		return newTopic;
	}

	public void setNewTopic(Topic newTopic) {
		this.newTopic = newTopic;
	}

	public int getViewed() {
		return viewed;
	}

	public void setViewed(int viewed) {
		this.viewed = viewed;
	}
	

}

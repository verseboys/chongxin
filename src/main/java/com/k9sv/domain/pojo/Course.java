package com.k9sv.domain.pojo;

public class Course  implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -254615111862470051L;
	private int id;
	//编号
	private int number;
	//名称
	private String title;
	//简介
	private String content;
	//视频
	private String video;
	//观看次数
	private int viewed;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	public int getViewed() {
		return viewed;
	}
	public void setViewed(int viewed) {
		this.viewed = viewed;
	}
	
	
}

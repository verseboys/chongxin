package com.k9sv.domain.pojo;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.k9sv.util.DateUtil;

@SuppressWarnings("unused")
public class Topic implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2030209185169129953L;

	private int id;

	private String title;

	private String content;
	private int type;// 1 展示，2出售 3 求助 4 新闻
	// 用户
	private int uid;
	// 版块
	private int fid;

	private int pid;
	// 阅读次数
	private int viewed;
	// 回帖数量
	private int replied;

	private Date created;

	private Date updated;
	// 最后回复用户
	private int lastUid;
	// 最后回复时间
	private Date lastReplied;

	// 0 普通，1指定
	private int topest;

	private int best;
	// 是否删除 0 正常 1 回收站，2 删除
	private int deleted;

	private Profile profile;

	private Profile lastReplyProfile;

	private String subContent;

	private String date;

	public String getDate() {
		return DateUtil.getSubTimeString(created);
	}

	public void setDate(String date) {
		this.date = date;
	}

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public int getViewed() {
		return viewed;
	}

	public void setViewed(int viewed) {
		this.viewed = viewed;
	}

	public int getReplied() {
		return replied;
	}

	public void setReplied(int replied) {
		this.replied = replied;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public int getLastUid() {
		return lastUid;
	}

	public void setLastUid(int lastUid) {
		this.lastUid = lastUid;
	}

	public Date getLastReplied() {
		return lastReplied;
	}

	public void setLastReplied(Date lastReplied) {
		this.lastReplied = lastReplied;
	}

	public int getTopest() {
		return topest;
	}

	public void setTopest(int topest) {
		this.topest = topest;
	}

	public int getBest() {
		return best;
	}

	public void setBest(int best) {
		this.best = best;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getSubContent() {
		String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(content);
		String htmlStr = m_html.replaceAll("");
		return htmlStr.trim();
	}

	public void setSubContent(String subContent) {
		this.subContent = subContent;
	}

	public Profile getLastReplyProfile() {
		return lastReplyProfile;
	}

	public void setLastReplyProfile(Profile lastReplyProfile) {
		this.lastReplyProfile = lastReplyProfile;
	}

}

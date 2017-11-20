package com.k9sv.service;

import java.util.List;

import com.k9sv.domain.pojo.Blog;

public interface IBlogManager extends IBaseManager {

	List<Blog> getBlogs(int start,int size);
	
	int getBlogsCount();
	
	List<Blog> getBlogs(int uid,int start,int size);
	
	int getBlogsCount(int uid);
	
	List<Blog> getBlogs(int uid,int type,int start,int size);
	
	int getBlogsCount(int uid,int type);
}

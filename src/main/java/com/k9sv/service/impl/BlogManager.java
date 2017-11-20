package com.k9sv.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.k9sv.domain.pojo.Blog;
import com.k9sv.service.IBlogManager;
@Service("blogManager")
public class BlogManager extends BaseManager implements IBlogManager {

	Logger LOG = Logger.getLogger(BlogManager.class);
	
	@Override
	public List<Blog> getBlogs(int start, int size) {
		try {
			return baseDao.queryStart("from Blog where status >-1 order by created desc", null, start, size);
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return null;
	}

	@Override
	public int getBlogsCount() {
		Long total = (Long)super.getCount("select count(id) from Blog ", null);
		return total.intValue();
	}

	@Override
	public List<Blog> getBlogs(int uid, int start, int size) {
		try {
			return baseDao.queryStart("from Blog where status >-1 and uid = ? order by created desc",  new Object[]{uid}, start, size);
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return null;
	}

	@Override
	public int getBlogsCount(int uid) {
		Long total = (Long)super.getCount("select count(id) from Blog where uid=?", new Object[]{uid});
		return total.intValue();
	}

	@Override
	public List<Blog> getBlogs(int uid, int type, int start, int size) {
		try {
			return baseDao.queryStart("from Blog where status >-1 and uid = ? and type=? order by created desc",  new Object[]{uid,type}, start, size);
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return null;
	}
	

	@Override
	public int getBlogsCount(int uid, int type) {
		Long total = (Long)super.getCount("select count(id) from Blog where uid=? and type = ?", new Object[]{uid,type});
		return total.intValue();
	}

}

package com.k9sv.service;

import java.util.List;

import com.k9sv.domain.pojo.Course;

public interface ICourseManager extends IBaseManager {

	List<Course> getCourses(int pageIndex,int pageSize);
	
	Course getCourse(int id);
}

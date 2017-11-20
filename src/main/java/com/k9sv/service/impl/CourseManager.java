package com.k9sv.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.k9sv.domain.pojo.Course;
import com.k9sv.service.ICourseManager;

@Service("courseManager")
public class CourseManager extends BaseManager implements ICourseManager {

	@Override
	public List<Course> getCourses(int start, int size) {
		return super.queryStart("from Course order by id asc",null, start, size);
	}

	@Override
	public Course getCourse(int id) {
		Course _course = super.load(Course.class, id);
		_course.setViewed(_course.getViewed()+1);
		super.update(_course);
		return _course;
	}

}

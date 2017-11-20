package com.k9sv.service;

import java.util.List;

import com.k9sv.domain.pojo.Subject;

public interface ISubjectManager extends IBaseManager {

	Integer getCount(String title);

	List<Subject> getSubjects(String title, Integer pageNum, Integer numPerPage);

	Subject getSubject(String domain);
	
}

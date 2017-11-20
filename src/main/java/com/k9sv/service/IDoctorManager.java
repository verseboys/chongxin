package com.k9sv.service;

import java.util.List;

import com.k9sv.domain.pojo.Doctor;

public interface IDoctorManager extends IBaseManager {

	Integer getCount(String title);

	List<Doctor> getDoctors(String title, Integer pageNum, Integer numPerPage);

	List<Doctor> find();

}

package com.k9sv.domain.dto;

import com.k9sv.domain.pojo.Doctor;

public class DoctorDto {

	private Integer doctorid;
	private String name;
	private String job;
	private String telephone;

	public DoctorDto(Doctor doctor) {
		this.setDoctorid(doctor.getId());
		this.name = doctor.getName();
		this.job = doctor.getJob();
		this.telephone = doctor.getTelephone();
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJob() {
		return this.job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Integer getDoctorid() {
		return doctorid;
	}

	public void setDoctorid(Integer doctorid) {
		this.doctorid = doctorid;
	}

}
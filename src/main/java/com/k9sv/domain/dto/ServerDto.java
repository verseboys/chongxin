package com.k9sv.domain.dto;

import java.util.ArrayList;
import java.util.List;

import com.k9sv.domain.pojo.Classify;
import com.k9sv.domain.pojo.Server;

public class ServerDto {

	private int pid;

	private String ptitle;

	private List<ServerDetailDto> detail;

	public ServerDto() {

	}

	public ServerDto(Classify classify, List<Server> _servers) {
		this.pid = classify.getId();
		this.ptitle = classify.getName();
		this.detail = this.getDetail(classify, _servers);
	}

	private List<ServerDetailDto> getDetail(Classify classify,
			List<Server> _servers) {
		List<ServerDetailDto> detailDtos = new ArrayList<ServerDetailDto>();
		if (_servers != null) {
			for (Server _server : _servers) {
				if (_server.getClassify().getPid() == classify.getId()) {
					ServerDetailDto detailDto = new ServerDetailDto(_server);
					detailDtos.add(detailDto);
				}
			}
		}
		return detailDtos;
	}

	public String getPtitle() {
		return ptitle;
	}

	public void setPtitle(String ptitle) {
		this.ptitle = ptitle;
	}

	public List<ServerDetailDto> getDetail() {
		return detail;
	}

	public void setDetail(List<ServerDetailDto> detail) {
		this.detail = detail;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

}

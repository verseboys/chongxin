package com.k9sv.util;

public class SortColumn implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sortKeys;//排序字段
	private String sortDir;//排序
	
	public SortColumn(String sortKeys, String sortDir){
		this.sortKeys = sortKeys;
		this.sortDir = sortDir;
	}
	public SortColumn(){
	}

	public String getSortKeys() {
		return sortKeys;
	}

	public void setSortKeys(String sortKeys) {
		this.sortKeys = sortKeys;
	}

	public String getSortDir() {
		return sortDir;
	}

	public void setSortDir(String sortDir) {
		this.sortDir = sortDir;
	}

	
	

}

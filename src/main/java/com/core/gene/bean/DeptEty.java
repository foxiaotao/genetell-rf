package com.core.gene.bean;


public class DeptEty{
	private Long id;	//dept
	private String name;	//name
	/**
	* 得到 dept
	* @return Long
	*/
	public Long getId() {
		return this.id;
	}
	/**
	 * 设置 dept
	 * @param id,  : Long
	*/
	public void setId(Long id) {
		this.id = id;
	}

	/**
	* 得到 name
	* @return String
	*/
	public String getName() {
		return this.name;
	}
	/**
	 * 设置 name
	 * @param name,  : String
	*/
	public void setName(String name) {
		this.name = name;
	}

}
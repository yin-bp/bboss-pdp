/**
 * 
 */
package com.frameworkset.platform.dict.entity;

import com.frameworkset.orm.annotation.PrimaryKey;

/**
 * @author yinbp
 *
 * @Date:2016-12-19 01:23:47
 */
public class DictItem {
	@PrimaryKey
	private String paramId;
	private String name;
	private String value;
	private int rn;
	
	/**
	 * 
	 */
	public DictItem() {
		// TODO Auto-generated constructor stub
	}
	public String getParamId() {
		return paramId;
	}
	public void setParamId(String paramId) {
		this.paramId = paramId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getRn() {
		return rn;
	}
	public void setRn(int rn) {
		this.rn = rn;
	}
	

}

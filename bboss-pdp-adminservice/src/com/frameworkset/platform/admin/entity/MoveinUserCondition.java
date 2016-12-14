/**
 * 
 */
package com.frameworkset.platform.admin.entity;

/**
 * 嵌入用户查询条件
 * @author yinbp
 *
 * @Date:2016-12-14 11:23:48
 */
public class MoveinUserCondition {
	private String departId;
	private String userAttr;
	private String recursive;
	private Integer userIsvalid;	
	private Integer records;
	private String orgtreelevel;
	/**
	 * 
	 */
	public MoveinUserCondition() {
		
	}
	public String getDepartId() {
		return departId;
	}
	public void setDepartId(String departId) {
		this.departId = departId;
	}
	public String getUserAttr() {
		return userAttr;
	}
	public void setUserAttr(String userAttr) {
		this.userAttr = userAttr;
	}
	public String getRecursive() {
		return recursive;
	}
	public void setRecursive(String recursive) {
		this.recursive = recursive;
	}
	public Integer getUserIsvalid() {
		return userIsvalid;
	}
	public void setUserIsvalid(Integer userIsvalid) {
		this.userIsvalid = userIsvalid;
	}
	public Integer getRecords() {
		return records;
	}
	public void setRecords(Integer records) {
		this.records = records;
	}
	public String getOrgtreelevel() {
		return orgtreelevel;
	}
	public void setOrgtreelevel(String orgtreelevel) {
		this.orgtreelevel = orgtreelevel;
	}

}

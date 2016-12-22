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
	private String fromDepartId;
	private String userAttr;
	private String recursive;
	private Integer userIsvalid;	
	private Integer records;
	private String orgtreelevel;
	private String orgtreelevelLike;
	/**
	 * 
	 */
	public MoveinUserCondition() {
		
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

	public String getFromDepartId() {
		return fromDepartId;
	}

	public void setFromDepartId(String fromDepartId) {
		this.fromDepartId = fromDepartId;
	}

	public String getOrgtreelevelLike() {
		return orgtreelevelLike;
	}

	public void setOrgtreelevelLike(String orgtreelevelLike) {
		this.orgtreelevelLike = orgtreelevelLike;
	}

}

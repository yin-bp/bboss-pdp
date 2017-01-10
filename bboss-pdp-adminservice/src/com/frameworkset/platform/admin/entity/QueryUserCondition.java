/**
 * 
 */
package com.frameworkset.platform.admin.entity;

/**
 * @author yinbp
 *
 * @Date:2017-01-10 23:27:51
 */
public class QueryUserCondition {
	private String userAttr;	 
	private Integer userIsvalid;	
	/**
	 * 
	 */
	public QueryUserCondition() {
		// TODO Auto-generated constructor stub
	}
	public String getUserAttr() {
		return userAttr;
	}
	public void setUserAttr(String userAttr) {
		this.userAttr = userAttr;
	}
	public Integer getUserIsvalid() {
		return userIsvalid;
	}
	public void setUserIsvalid(Integer userIsvalid) {
		this.userIsvalid = userIsvalid;
	}

}

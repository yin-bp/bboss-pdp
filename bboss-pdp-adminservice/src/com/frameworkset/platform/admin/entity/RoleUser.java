/**
 * 
 */
package com.frameworkset.platform.admin.entity;

import java.util.Date;

/**
 * @author yinbp
 *
 * @Date:2016-12-31 17:50:17
 */
public class RoleUser extends SmUser {
	private Date startDate;
	private Date endDate;
	/**
	 * 
	 */
	public RoleUser() {
		// TODO Auto-generated constructor stub
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}

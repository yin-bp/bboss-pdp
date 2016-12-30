/**
 * 
 */
package com.frameworkset.platform.admin.entity;

import java.util.Date;

/**
 * @author yinbp
 *
 * @Date:2016-12-25 13:38:13
 */
public class UserRole extends Role{
	private Date startDate;
	private Date endDate;

	/**
	 * 
	 */
	public UserRole() {
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

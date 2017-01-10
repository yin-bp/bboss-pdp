/**
 * 
 */
package com.frameworkset.platform.admin.entity;

import org.frameworkset.util.annotations.RequestParam;

/**
 * @author yinbp
 *
 * @Date:2017-01-08 22:25:19
 */
public class LogSetting {
	private String id;
	@RequestParam(name="status_${id}")
	private Integer status;
	/**
	 * 
	 */
	public LogSetting() {
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

}

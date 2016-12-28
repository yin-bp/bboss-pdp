/**
 * 
 */
package com.frameworkset.platform.admin.entity;

import java.util.Date;
import java.util.List;

/**
 * @author yinbp
 *
 * @Date:2016-12-29 00:03:32
 */
public class AuthOPS implements java.io.Serializable{
	private String resCode;
	private String resName;
	private String opcode  ;	
	private String resourceType ;
	 
	private Date AUTHORIZATION_STIME;
	private Date AUTHORIZATION_ETIME;
	private String urls;
	/**
	 * 
	 */
	public AuthOPS() {
		// TODO Auto-generated constructor stub
	}
	public String getResCode() {
		return resCode;
	}
	public void setResCode(String resCode) {
		this.resCode = resCode;
	}
	public String getOpcode() {
		return opcode;
	}
	public void setOpcode(String opcode) {
		this.opcode = opcode;
	}
	public String getResourceType() {
		return resourceType;
	}
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	public Date getAUTHORIZATION_STIME() {
		return AUTHORIZATION_STIME;
	}
	public void setAUTHORIZATION_STIME(Date aUTHORIZATION_STIME) {
		AUTHORIZATION_STIME = aUTHORIZATION_STIME;
	}
	public Date getAUTHORIZATION_ETIME() {
		return AUTHORIZATION_ETIME;
	}
	public void setAUTHORIZATION_ETIME(Date aUTHORIZATION_ETIME) {
		AUTHORIZATION_ETIME = aUTHORIZATION_ETIME;
	}
	public String getResName() {
		return resName;
	}
	public void setResName(String resName) {
		this.resName = resName;
	}
	public String getUrls() {
		return urls;
	}
	public void setUrls(String urls) {
		this.urls = urls;
	}

}

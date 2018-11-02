package org.frameworkset.platform.security.service.entity;

import com.frameworkset.platform.admin.entity.SmOrganization;
import com.frameworkset.platform.admin.entity.SmUser;

import java.io.Serializable;

public class Result implements Serializable{
	public static final String ok = "success";
	public static final String fail = "failed";
	/**
	 * success
	 * fail
	 */
	private String code;
	private String errormessage;
	private SmUser user;
	private SmOrganization org;
	public SmOrganization getOrg() {
		return org;
	}

	public void setOrg(SmOrganization org) {
		this.org = org;
	}

	private String operationData;

	public Result() {
		// TODO Auto-generated constructor stub
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getErrormessage() {
		return errormessage;
	}

	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}

	public SmUser getUser() {
		return user;
	}

	public void setUser(SmUser user) {
		this.user = user;
	}

	 

	public String getOperationData() {
		return operationData;
	}

	public void setOperationData(String operationData) {
		this.operationData = operationData;
	}

	public void appendErrormessage(String errormessage) {
		if(this.errormessage == null)
			this.errormessage = errormessage;
		else
			this.errormessage = this.errormessage +"\n"+ errormessage;
	}

	 

	 

}

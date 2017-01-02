/**
 * 
 */
package com.frameworkset.platform.admin.util;

/**
 * @author yinbp
 *
 * @Date:2017-01-01 20:06:25
 */
public class OpResult {
	private String result = "failed";
	private String message;
	/**
	 * 
	 */
	public OpResult() {
		// TODO Auto-generated constructor stub
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}

/**
 * 
 */
package com.frameworkset.platform.admin.entity;

import org.frameworkset.util.annotations.RequestParam;

/**
 * @author yinbp
 *
 * @Date:2016-12-27 22:16:27
 */
public class ResOpr {
	private String resCode;
	@RequestParam(name="opcode-${resCode}")
	private String op;
	private String resName;
	/**
	 * 
	 */
	public ResOpr() {
		// TODO Auto-generated constructor stub
	}
	public String getResCode() {
		return resCode;
	}
	public void setResCode(String resCode) {
		this.resCode = resCode;
	}
	public String getOp() {
		return op;
	}
	public void setOp(String op) {
		this.op = op;
	}
	public String getResName() {
		return resName;
	}
	public void setResName(String resName) {
		this.resName = resName;
	}

}

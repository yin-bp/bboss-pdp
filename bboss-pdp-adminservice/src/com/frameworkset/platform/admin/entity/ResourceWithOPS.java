/**
 * 
 */
package com.frameworkset.platform.admin.entity;

import java.util.List;

import org.frameworkset.platform.config.model.Operation;

/**
 * @author yinbp
 *
 * @Date:2016-12-27 20:38:40
 */
public class ResourceWithOPS extends Resource{
	private List<Operation> permissionOPS;
	/**
	 * 
	 */
	public ResourceWithOPS() {
		// TODO Auto-generated constructor stub
	}
	public List<Operation> getPermissionOPS() {
		return permissionOPS;
	}
	public void setPermissionOPS(List<Operation> permissionOPS) {
		this.permissionOPS = permissionOPS;
	}

}

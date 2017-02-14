/**
 *  Copyright 2008-2010 biaoping.yin
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.frameworkset.platform.admin.entity;

/**
 * <p>Title: RoleCondition</p> <p>Description: 角色管理管理查询条件实体类 </p> <p>bboss</p>
 * <p>Copyright (c) 2007</p> @Date 2016-12-15 17:06:09 @author yinbp @version
 * v1.0
 */
public class RoleCondition implements java.io.Serializable {
	/**
	 * 名称
	 */
	private String roleAttr;
	/**
	 * 类型
	 */
	private String roleType;
	private String sortKey;
	private boolean sortDesc;
	private boolean fromAuthmain;
	private boolean cangrantedRole;
	public RoleCondition() {
	}
	 

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setSortKey(String sortKey) {
		this.sortKey = sortKey;
	}

	public String getSortKey() {
		return sortKey;
	}

	public void setSortDesc(boolean sortDesc) {
		this.sortDesc = sortDesc;
	}

	public boolean getSortDesc() {
		return sortDesc;
	}


	public String getRoleAttr() {
		return roleAttr;
	}


	public void setRoleAttr(String roleAttr) {
		this.roleAttr = roleAttr;
	}


	public boolean isFromAuthmain() {
		return fromAuthmain;
	}


	public void setFromAuthmain(boolean fromAuthmain) {
		this.fromAuthmain = fromAuthmain;
	}


	public boolean isCangrantedRole() {
		return cangrantedRole;
	}


	public void setCangrantedRole(boolean cangrantedRole) {
		this.cangrantedRole = cangrantedRole;
	}

}
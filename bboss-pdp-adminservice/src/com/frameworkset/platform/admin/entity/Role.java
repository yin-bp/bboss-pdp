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

import com.frameworkset.orm.annotation.PrimaryKey;
/**
 * <p>Title: Role</p> <p>Description: 角色管理管理服务实体类 </p> <p>bboss</p> <p>Copyright
 * (c) 2007</p> @Date 2016-12-15 17:06:09 @author yinbp @version v1.0
 */
public class Role implements java.io.Serializable {
	/**
	 * 序号
	 */
	@PrimaryKey
	private String roleId;
	/**
	 * 创建人
	 */
	private int ownerId;
	private String ownerName;
	private String ownerAccount;
	
	
	/**
	 * 角色中文名称
	 */
	private String remark1;
	/**
	 * 备注2
	 */
	private String remark2;
	/**
	 * 备注3
	 */
	private String remark3;
	/**
	 * 描述
	 */
	private String roleDesc;
	/**
	 * 名称
	 */
	private String roleName;
	/**
	 * 类型
	 */
	private String roleType;
	private String typeName;
	/**
	 * 用途
	 */
	private String roleUsage;
	public Role() {
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getRemark1() {
		return remark1;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}

	public String getRemark3() {
		return remark3;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleUsage(String roleUsage) {
		this.roleUsage = roleUsage;
	}

	public String getRoleUsage() {
		return roleUsage;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getOwnerAccount() {
		return ownerAccount;
	}
	public void setOwnerAccount(String ownerAccount) {
		this.ownerAccount = ownerAccount;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}
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
 * <p>Title: RoleType</p> <p>Description: 角色类型管理管理服务实体类 </p> <p>bboss</p>
 * <p>Copyright (c) 2007</p> @Date 2016-12-15 23:37:09 @author yinbp @version
 * v1.0
 */
public class RoleType implements java.io.Serializable {
	/**
	 * 序号
	 */
	@PrimaryKey
	private String typeId;
	/**
	 * 创建机构
	 */
	private String creatorOrgId;
	/**
	 * 创建人
	 */
	private String creatorUserId;
	/**
	 * 描述
	 */
	private String typeDesc;
	/**
	 * 类型名称
	 */
	private String typeName;
	public RoleType() {
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setCreatorOrgId(String creatorOrgId) {
		this.creatorOrgId = creatorOrgId;
	}

	public String getCreatorOrgId() {
		return creatorOrgId;
	}

	public void setCreatorUserId(String creatorUserId) {
		this.creatorUserId = creatorUserId;
	}

	public String getCreatorUserId() {
		return creatorUserId;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

	public String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeName() {
		return typeName;
	}

}
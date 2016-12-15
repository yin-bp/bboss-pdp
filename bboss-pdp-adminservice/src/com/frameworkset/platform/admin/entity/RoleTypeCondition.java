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
 * <p>Title: RoleTypeCondition</p> <p>Description: 角色类型管理管理查询条件实体类 </p>
 * <p>bboss</p> <p>Copyright (c) 2007</p> @Date 2016-12-15 23:37:09 @author
 * yinbp @version v1.0
 */
public class RoleTypeCondition implements java.io.Serializable {
	/**
	 * 类型名称
	 */
	private String typeName;
	public RoleTypeCondition() {
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeName() {
		return typeName;
	}

}
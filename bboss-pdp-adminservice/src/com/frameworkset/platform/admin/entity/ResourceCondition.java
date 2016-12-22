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
 * <p>Title: ResourceCondition</p> <p>Description: 资源管理管理查询条件实体类 </p>
 * <p>bboss</p> <p>Copyright (c) 2007</p> @Date 2016-12-21 00:46:37 @author
 * yinbp @version v1.0
 */
public class ResourceCondition implements java.io.Serializable {
	/**
	 * 资源编码
	 */
	private String resCode;
	/**
	 * 资源名称
	 */
	private String resName;
	public ResourceCondition() {
	}
	public void setResCode(String resCode) {
		this.resCode = resCode;
	}

	public String getResCode() {
		return resCode;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public String getResName() {
		return resName;
	}

}
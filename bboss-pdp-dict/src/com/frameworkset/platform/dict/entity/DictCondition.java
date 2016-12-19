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

package com.frameworkset.platform.dict.entity;

/**
 * <p>Title: DictCondition</p> <p>Description: 字典管理管理查询条件实体类 </p> <p>bboss</p>
 * <p>Copyright (c) 2007</p> @Date 2016-12-18 11:29:08 @author yinbp @version
 * v1.0
 */
public class DictCondition implements java.io.Serializable {
	/**
	 * 字典名称
	 */
	private String dictAttr;
	private String handler;
	private String dictStatus;
	public DictCondition() {
	}
	public String getDictAttr() {
		return dictAttr;
	}
	public void setDictAttr(String dictAttr) {
		this.dictAttr = dictAttr;
	}
	public String getHandler() {
		return handler;
	}
	public void setHandler(String handler) {
		this.handler = handler;
	}
	public String getDictStatus() {
		return dictStatus;
	}
	public void setDictStatus(String dictStatus) {
		this.dictStatus = dictStatus;
	}
	

}
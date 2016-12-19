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

import com.frameworkset.orm.annotation.PrimaryKey;
/**
 * <p>Title: Dict</p> <p>Description: 字典管理管理服务实体类 </p> <p>bboss</p> <p>Copyright
 * (c) 2007</p> @Date 2016-12-18 11:29:08 @author yinbp @version v1.0
 */
public class Dict implements java.io.Serializable {
	/**
	 * 序号
	 */
	@PrimaryKey
	private String dictId;
	/**
	 * 启用缓存
	 */
	private String cacheEnable;
	/**
	 * 字典编码
	 */
	private String dictCode;
	
	/**
	 * 字典状态0：删除 1正常
	 */
	private String dictStatus = "1";
	/**
	 * 字典描述
	 */
	private String dictDesc;
	/**
	 * 字典名称
	 */
	private String dictName;
	/**
	 * 字典处理器
	 */
	private String handler;
	/**
	 * 是否树结构
	 */
	private String isTree;
	/**
	 * 创建人
	 */
	private String ownerId;
	private String ownerAccount;
	private String ownerName;
	public Dict() {
	}
	public void setDictId(String dictId) {
		this.dictId = dictId;
	}

	public String getDictId() {
		return dictId;
	}

	public void setCacheEnable(String cacheEnable) {
		this.cacheEnable = cacheEnable;
	}

	public String getCacheEnable() {
		return cacheEnable;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	public String getDictCode() {
		return dictCode;
	}

	public void setDictDesc(String dictDesc) {
		this.dictDesc = dictDesc;
	}

	public String getDictDesc() {
		return dictDesc;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public String getDictName() {
		return dictName;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}

	public String getHandler() {
		return handler;
	}

	public void setIsTree(String isTree) {
		this.isTree = isTree;
	}

	public String getIsTree() {
		return isTree;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getOwnerId() {
		return ownerId;
	}
	public String getOwnerAccount() {
		return ownerAccount;
	}
	public void setOwnerAccount(String ownerAccount) {
		this.ownerAccount = ownerAccount;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getDictStatus() {
		return dictStatus;
	}
	public void setDictStatus(String dictStatus) {
		this.dictStatus = dictStatus;
	}

}
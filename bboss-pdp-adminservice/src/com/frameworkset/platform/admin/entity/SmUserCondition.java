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
 * <p>Title: SmUserCondition</p> <p>Description: 用户管理管理查询条件实体类 </p> <p>bboss</p>
 * <p>Copyright (c) 2007</p> @Date 2016-11-29 21:14:30 @author yinbp @version
 * v1.0
 */
public class SmUserCondition implements java.io.Serializable {
	/**
	 * 身份证
	 */
	private String userIdcard;
	/**
	 * 是否有效
	 */
	private long userIsvalid;
	/**
	 * 手机1
	 */
	private String userMobiletel1;
	/**
	 * 用户账号
	 */
	private String userName;
	/**
	 * 中文名
	 */
	private String userRealname;
	/**
	 * 用户性别
	 */
	private String userSex;
	/**
	 * 用户类型
	 */
	private String userType;
	/**
	 * 工号
	 */
	private String userWorknumber;
	private String sortKey;
	private boolean sortDesc;
	private String departId;
	/**
	 * <option value="0">本机构</option>
	 *   <option value="1">含子机构</option>
	 *   <option value="2">所有机构</option>
     *                                                           
	 */
	private String recursive;
	public SmUserCondition() {
	}
	public void setUserIdcard(String userIdcard) {
		this.userIdcard = userIdcard;
	}

	public String getUserIdcard() {
		return userIdcard;
	}

	public void setUserIsvalid(long userIsvalid) {
		this.userIsvalid = userIsvalid;
	}

	public long getUserIsvalid() {
		return userIsvalid;
	}

	public void setUserMobiletel1(String userMobiletel1) {
		this.userMobiletel1 = userMobiletel1;
	}

	public String getUserMobiletel1() {
		return userMobiletel1;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserRealname(String userRealname) {
		this.userRealname = userRealname;
	}

	public String getUserRealname() {
		return userRealname;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public String getUserSex() {
		return userSex;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserWorknumber(String userWorknumber) {
		this.userWorknumber = userWorknumber;
	}

	public String getUserWorknumber() {
		return userWorknumber;
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
	public String getDepartId() {
		return departId;
	}
	public void setDepartId(String departId) {
		this.departId = departId;
	}
	public String getRecursive() {
		return recursive;
	}
	public void setRecursive(String recursive) {
		this.recursive = recursive;
	}

}
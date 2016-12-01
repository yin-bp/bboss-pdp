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
import java.sql.Timestamp;
/**
 * <p>Title: SmUser</p> <p>Description: 用户管理管理服务实体类 </p> <p>bboss</p>
 * <p>Copyright (c) 2007</p> @Date 2016-11-29 21:14:30 @author yinbp @version
 * v1.0
 */
public class SmUser implements java.io.Serializable {
	/**
	 * 用户ID
	 */
	@PrimaryKey
	private String userId;
	private String certSn;
	/**
	 * 部门ID
	 */
	private String departId;
	/**
	 * 注册时间
	 */
	private String dredgeTime;
	private int istaxmanager;
	/**
	 * 最后登录时间
	 */
	private Timestamp lastloginDate;
	/**
	 * 登录IP
	 */
	private String logonIp;
	/**
	 * 密码有效期
	 */
	private int passwordDualtime;
	/**
	 * 口令明文
	 */
	private String passwordText;
	/**
	 * 口令更新时间
	 */
	private Timestamp passwordUpdatetime;
	/**
	 * 过期时间
	 */
	private Timestamp pastTime;
	/**
	 * 用户策略
	 */
	private String politics;
	/**
	 * 备注1
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
	 * 备注4
	 */
	private String remark4;
	/**
	 * 备注5
	 */
	private String remark5;
	/**
	 * 更新时间
	 */
	private Timestamp updateTime;
	/**
	 * 用户地址
	 */
	private String userAddress;
	/**
	 * 出生日期
	 */
	private Timestamp userBirthday;
	/**
	 * 邮箱地址
	 */
	private String userEmail;
	/**
	 * 传真
	 */
	private String userFax;
	/**
	 * 家庭电话
	 */
	private String userHometel;
	/**
	 * 身份证
	 */
	private String userIdcard;
	/**
	 * 是否有效
	 */
	private int userIsvalid;
	/**
	 * 登录次数
	 */
	private int userLogincount;
	/**
	 * 手机1
	 */
	private String userMobiletel1;
	/**
	 * 手机2
	 */
	private String userMobiletel2;
	/**
	 * 用户账号
	 */
	private String userName;
	/**
	 * QQ号
	 */
	private String userOicq;
	/**
	 * 口令
	 */
	private String userPassword;
	/**
	 * 拼音
	 */
	private String userPinyin;
	/**
	 * 邮编
	 */
	private String userPostalcode;
	/**
	 * 中文名
	 */
	private String userRealname;
	/**
	 * 注册日志
	 */
	private Timestamp userRegdate;
	/**
	 * 用户性别
	 */
	private String userSex;
	/**
	 * 排序号
	 */
	private int userSn;
	/**
	 * 用户类型
	 */
	private String userType;
	/**
	 * 工号
	 */
	private String userWorknumber;
	/**
	 * 工作电话
	 */
	private String userWorktel;
	/**
	 * 工龄
	 */
	private String worklength;
	private String departjobs;
	private String sexName;
	private String userTypeName;
	private String userIsvalidName;
	public SmUser() {
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setCertSn(String certSn) {
		this.certSn = certSn;
	}

	public String getCertSn() {
		return certSn;
	}

	public void setDepartId(String departId) {
		this.departId = departId;
	}

	public String getDepartId() {
		return departId;
	}

	public void setDredgeTime(String dredgeTime) {
		this.dredgeTime = dredgeTime;
	}

	public String getDredgeTime() {
		return dredgeTime;
	}

	public void setIstaxmanager(int istaxmanager) {
		this.istaxmanager = istaxmanager;
	}

	public int getIstaxmanager() {
		return istaxmanager;
	}

	public void setLastloginDate(Timestamp lastloginDate) {
		this.lastloginDate = lastloginDate;
	}

	public Timestamp getLastloginDate() {
		return lastloginDate;
	}

	public void setLogonIp(String logonIp) {
		this.logonIp = logonIp;
	}

	public String getLogonIp() {
		return logonIp;
	}

	public void setPasswordDualtime(int passwordDualtime) {
		this.passwordDualtime = passwordDualtime;
	}

	public int getPasswordDualtime() {
		return passwordDualtime;
	}

	public void setPasswordText(String passwordText) {
		this.passwordText = passwordText;
	}

	public String getPasswordText() {
		return passwordText;
	}

	public void setPasswordUpdatetime(Timestamp passwordUpdatetime) {
		this.passwordUpdatetime = passwordUpdatetime;
	}

	public Timestamp getPasswordUpdatetime() {
		return passwordUpdatetime;
	}

	public void setPastTime(Timestamp pastTime) {
		this.pastTime = pastTime;
	}

	public Timestamp getPastTime() {
		return pastTime;
	}

	public void setPolitics(String politics) {
		this.politics = politics;
	}

	public String getPolitics() {
		return politics;
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

	public void setRemark4(String remark4) {
		this.remark4 = remark4;
	}

	public String getRemark4() {
		return remark4;
	}

	public void setRemark5(String remark5) {
		this.remark5 = remark5;
	}

	public String getRemark5() {
		return remark5;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserBirthday(Timestamp userBirthday) {
		this.userBirthday = userBirthday;
	}

	public Timestamp getUserBirthday() {
		return userBirthday;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserFax(String userFax) {
		this.userFax = userFax;
	}

	public String getUserFax() {
		return userFax;
	}

	public void setUserHometel(String userHometel) {
		this.userHometel = userHometel;
	}

	public String getUserHometel() {
		return userHometel;
	}

	public void setUserIdcard(String userIdcard) {
		this.userIdcard = userIdcard;
	}

	public String getUserIdcard() {
		return userIdcard;
	}

	public void setUserIsvalid(int userIsvalid) {
		this.userIsvalid = userIsvalid;
	}

	public int getUserIsvalid() {
		return userIsvalid;
	}

	public void setUserLogincount(int userLogincount) {
		this.userLogincount = userLogincount;
	}

	public int getUserLogincount() {
		return userLogincount;
	}

	public void setUserMobiletel1(String userMobiletel1) {
		this.userMobiletel1 = userMobiletel1;
	}

	public String getUserMobiletel1() {
		return userMobiletel1;
	}

	public void setUserMobiletel2(String userMobiletel2) {
		this.userMobiletel2 = userMobiletel2;
	}

	public String getUserMobiletel2() {
		return userMobiletel2;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserOicq(String userOicq) {
		this.userOicq = userOicq;
	}

	public String getUserOicq() {
		return userOicq;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPinyin(String userPinyin) {
		this.userPinyin = userPinyin;
	}

	public String getUserPinyin() {
		return userPinyin;
	}

	public void setUserPostalcode(String userPostalcode) {
		this.userPostalcode = userPostalcode;
	}

	public String getUserPostalcode() {
		return userPostalcode;
	}

	public void setUserRealname(String userRealname) {
		this.userRealname = userRealname;
	}

	public String getUserRealname() {
		return userRealname;
	}

	public void setUserRegdate(Timestamp userRegdate) {
		this.userRegdate = userRegdate;
	}

	public Timestamp getUserRegdate() {
		return userRegdate;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public String getUserSex() {
		return userSex;
	}

	public void setUserSn(int userSn) {
		this.userSn = userSn;
	}

	public int getUserSn() {
		return userSn;
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

	public void setUserWorktel(String userWorktel) {
		this.userWorktel = userWorktel;
	}

	public String getUserWorktel() {
		return userWorktel;
	}

	public void setWorklength(String worklength) {
		this.worklength = worklength;
	}

	public String getWorklength() {
		return worklength;
	}
	public String getDepartjobs() {
		return departjobs;
	}
	public void setDepartjobs(String departjobs) {
		this.departjobs = departjobs;
	}
	public String getSexName() {
		return sexName;
	}
	public void setSexName(String sexName) {
		this.sexName = sexName;
	}
	public String getUserTypeName() {
		return userTypeName;
	}
	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}
	public String getUserIsvalidName() {
		return userIsvalidName;
	}
	public void setUserIsvalidName(String userIsvalidName) {
		this.userIsvalidName = userIsvalidName;
	}

}
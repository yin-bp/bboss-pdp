package org.frameworkset.platform.security.authorization;

import com.frameworkset.orm.annotation.Column;

import java.io.Serializable;
import java.util.Date;

/**
 * 权限用户信息
 * @author Administrator
 *
 */
public class AuthUser implements Serializable{
	/**
	 * 用户中文名称
	 */
	@Column(name="userRealname")
	private String userName;
	/**
	 * 口令
	 */
	private transient String userPassword;
	/**
	 * 口令明文
	 */
	private transient String passwordText;
	/**
	 * 用户账号
	 */

	private String userAccount;
	/**
	 * 用户唯一标识
	 */
	@Column(name="userId")
	private String userID;

	private String userJob;

	private String certSn;
	/**
	 * 部门ID
	 */
	private String departId;
	private String departName;
	private String departTreeLevel;

	/**
	 * 部门领导
	 */
	private String leaderid;
	private String leaderaccount;
	private String leadername;
	/**
	 * 注册时间
	 */
	private String dredgeTime;
	private int istaxmanager;
	/**
	 * 最后登录时间
	 */
	private Date lastloginDate;
	/**
	 * 登录IP
	 */
	private String logonIp;
	/**
	 * 密码有效期
	 */
	private int passwordDualtime;

	/**
	 * 口令更新时间
	 */
	private Date passwordUpdatetime;
	/**
	 * 过期时间
	 */
	private Date pastTime;
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
	private Date updateTime;
	/**
	 * 用户地址
	 */
	private String userAddress;

	private Date userBirthday;
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
	 * QQ号
	 */
	private String userOicq;

	/**
	 * 拼音
	 */
	private String userPinyin;
	/**
	 * 邮编
	 */
	private String userPostalcode;

	/**
	 * 注册日志
	 */
	private Date userRegdate;
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



	public String getCertSn() {
		return certSn;
	}

	public void setCertSn(String certSn) {
		this.certSn = certSn;
	}

	public String getDepartId() {
		return departId;
	}

	public void setDepartId(String departId) {
		this.departId = departId;
	}

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public String getDepartTreeLevel() {
		return departTreeLevel;
	}

	public void setDepartTreeLevel(String departTreeLevel) {
		this.departTreeLevel = departTreeLevel;
	}

	public String getLeaderid() {
		return leaderid;
	}

	public void setLeaderid(String leaderid) {
		this.leaderid = leaderid;
	}

	public String getLeaderaccount() {
		return leaderaccount;
	}

	public void setLeaderaccount(String leaderaccount) {
		this.leaderaccount = leaderaccount;
	}

	public String getLeadername() {
		return leadername;
	}

	public void setLeadername(String leadername) {
		this.leadername = leadername;
	}

	public String getDredgeTime() {
		return dredgeTime;
	}

	public void setDredgeTime(String dredgeTime) {
		this.dredgeTime = dredgeTime;
	}

	public int getIstaxmanager() {
		return istaxmanager;
	}

	public void setIstaxmanager(int istaxmanager) {
		this.istaxmanager = istaxmanager;
	}

	public Date getLastloginDate() {
		return lastloginDate;
	}

	public void setLastloginDate(Date lastloginDate) {
		this.lastloginDate = lastloginDate;
	}

	public String getLogonIp() {
		return logonIp;
	}

	public void setLogonIp(String logonIp) {
		this.logonIp = logonIp;
	}

	public int getPasswordDualtime() {
		return passwordDualtime;
	}

	public void setPasswordDualtime(int passwordDualtime) {
		this.passwordDualtime = passwordDualtime;
	}

	public Date getPasswordUpdatetime() {
		return passwordUpdatetime;
	}

	public void setPasswordUpdatetime(Date passwordUpdatetime) {
		this.passwordUpdatetime = passwordUpdatetime;
	}

	public Date getPastTime() {
		return pastTime;
	}

	public void setPastTime(Date pastTime) {
		this.pastTime = pastTime;
	}

	public String getPolitics() {
		return politics;
	}

	public void setPolitics(String politics) {
		this.politics = politics;
	}

	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getRemark3() {
		return remark3;
	}

	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}

	public String getRemark4() {
		return remark4;
	}

	public void setRemark4(String remark4) {
		this.remark4 = remark4;
	}

	public String getRemark5() {
		return remark5;
	}

	public void setRemark5(String remark5) {
		this.remark5 = remark5;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public Date getUserBirthday() {
		return userBirthday;
	}

	public void setUserBirthday(Date userBirthday) {
		this.userBirthday = userBirthday;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserFax() {
		return userFax;
	}

	public void setUserFax(String userFax) {
		this.userFax = userFax;
	}

	public String getUserHometel() {
		return userHometel;
	}

	public void setUserHometel(String userHometel) {
		this.userHometel = userHometel;
	}

	public String getUserIdcard() {
		return userIdcard;
	}

	public void setUserIdcard(String userIdcard) {
		this.userIdcard = userIdcard;
	}

	public int getUserIsvalid() {
		return userIsvalid;
	}

	public void setUserIsvalid(int userIsvalid) {
		this.userIsvalid = userIsvalid;
	}

	public int getUserLogincount() {
		return userLogincount;
	}

	public void setUserLogincount(int userLogincount) {
		this.userLogincount = userLogincount;
	}

	public String getUserMobiletel1() {
		return userMobiletel1;
	}

	public void setUserMobiletel1(String userMobiletel1) {
		this.userMobiletel1 = userMobiletel1;
	}

	public String getUserMobiletel2() {
		return userMobiletel2;
	}

	public void setUserMobiletel2(String userMobiletel2) {
		this.userMobiletel2 = userMobiletel2;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserOicq() {
		return userOicq;
	}

	public void setUserOicq(String userOicq) {
		this.userOicq = userOicq;
	}

	public String getUserPinyin() {
		return userPinyin;
	}

	public void setUserPinyin(String userPinyin) {
		this.userPinyin = userPinyin;
	}

	public String getUserPostalcode() {
		return userPostalcode;
	}

	public void setUserPostalcode(String userPostalcode) {
		this.userPostalcode = userPostalcode;
	}


	public Date getUserRegdate() {
		return userRegdate;
	}

	public void setUserRegdate(Date userRegdate) {
		this.userRegdate = userRegdate;
	}

	public String getUserSex() {
		return userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public int getUserSn() {
		return userSn;
	}

	public void setUserSn(int userSn) {
		this.userSn = userSn;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserWorknumber() {
		return userWorknumber;
	}

	public void setUserWorknumber(String userWorknumber) {
		this.userWorknumber = userWorknumber;
	}

	public String getUserWorktel() {
		return userWorktel;
	}

	public void setUserWorktel(String userWorktel) {
		this.userWorktel = userWorktel;
	}

	public String getWorklength() {
		return worklength;
	}

	public void setWorklength(String worklength) {
		this.worklength = worklength;
	}

	public String getDepartjobs() {
		return departjobs;
	}

	public void setDepartjobs(String departjobs) {
		this.departjobs = departjobs;
	}

	public String getUserJob() {
		return userJob;
	}

	public void setUserJob(String userJob) {
		this.userJob = userJob;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}


	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getPasswordText() {
		return passwordText;
	}

	public void setPasswordText(String passwordText) {
		this.passwordText = passwordText;
	}
}

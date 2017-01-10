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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.frameworkset.util.annotations.RequestParam;

import com.frameworkset.orm.annotation.PrimaryKey;
/**
 * <p>Title: Log</p> <p>Description: 日志管理管理服务实体类 </p> <p>bboss</p> <p>Copyright
 * (c) 2007</p> @Date 2017-01-07 23:53:22 @author yinbp @version v1.0
 */
public class Log implements java.io.Serializable {
    //操作类型：无操作
	public static final int NULL_OPER_TYPE = 0 ;
    //操作类型：新增
	public static final int INSERT_OPER_TYPE = 1 ;
	//操作类型：更新
	public static final int UPDATE_OPER_TYPE = 2 ;
	//操作类型：删除
	public static final int DELETE_OPER_TYPE = 3 ;
	//操作类型：其他
	public static final int OTHER_OPER_TYPE = 4 ;
	public static Map<Integer,String> optypeMap = new HashMap<Integer,String>();
	static{
		optypeMap.put(NULL_OPER_TYPE, "无操作");
		optypeMap.put(INSERT_OPER_TYPE, "新增");
		optypeMap.put(UPDATE_OPER_TYPE, "更新");
		optypeMap.put(DELETE_OPER_TYPE, "删除");
		optypeMap.put(OTHER_OPER_TYPE, "其他");
	}
	@PrimaryKey
	private String logId;
	private String logContent;
	@RequestParam(dateformat = "yyyy-MM-dd")
	private Date logOpertime;
	private Date logArchtime;
	private String logOperuser;
	private String logVisitorial;
	private String opOrgid;
	private String operModule;
	private Integer operType;
	private String remark1;
	public Log() {
	}
	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getLogId() {
		return logId;
	}

	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}

	public String getLogContent() {
		return logContent;
	}

	public void setLogOpertime(Date logOpertime) {
		this.logOpertime = logOpertime;
	}

	public Date getLogOpertime() {
		return logOpertime;
	}

	public void setLogOperuser(String logOperuser) {
		this.logOperuser = logOperuser;
	}

	public String getLogOperuser() {
		return logOperuser;
	}

	public void setLogVisitorial(String logVisitorial) {
		this.logVisitorial = logVisitorial;
	}

	public String getLogVisitorial() {
		return logVisitorial;
	}

	public void setOpOrgid(String opOrgid) {
		this.opOrgid = opOrgid;
	}

	public String getOpOrgid() {
		return opOrgid;
	}

	public void setOperModule(String operModule) {
		this.operModule = operModule;
	}

	public String getOperModule() {
		return operModule;
	}

	public void setOperType(Integer operType) {
		this.operType = operType;
	}

	public Integer getOperType() {
		return operType;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getRemark1() {
		return remark1;
	}
	public Date getLogArchtime() {
		return logArchtime;
	}
	public void setLogArchtime(Date logArchtime) {
		this.logArchtime = logArchtime;
	}

}
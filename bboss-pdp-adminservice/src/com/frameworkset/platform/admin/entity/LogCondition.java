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
import org.frameworkset.util.annotations.RequestParam;
/**
 * <p>Title: LogCondition</p> <p>Description: 日志管理管理查询条件实体类 </p> <p>bboss</p>
 * <p>Copyright (c) 2007</p> @Date 2017-01-07 23:53:22 @author yinbp @version
 * v1.0
 */
public class LogCondition implements java.io.Serializable {
	private String logContent;
	@RequestParam(dateformat = "yyyy-MM-dd")
	private Date logOpertime_start;
	@RequestParam(dateformat = "yyyy-MM-dd")
	private Date logOpertime_end;
	private String logOperuser;
	private String logVisitorial;
	private String operModule;
	public LogCondition() {
	}
	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}

	public String getLogContent() {
		return logContent;
	}

	public void setLogOpertime_start(Date logOpertime_start) {
		this.logOpertime_start = logOpertime_start;
	}

	public Date getLogOpertime_start() {
		return logOpertime_start;
	}
	public void setLogOpertime_end(Date logOpertime_end) {
		this.logOpertime_end = logOpertime_end;
	}

	public Date getLogOpertime_end() {
		return logOpertime_end;
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

	public void setOperModule(String operModule) {
		this.operModule = operModule;
	}

	public String getOperModule() {
		return operModule;
	}

}
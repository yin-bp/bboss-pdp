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

package org.frameworkset.platform.application.action;

import java.io.InputStream;
import java.util.List;

import org.apache.log4j.Logger;
import org.frameworkset.http.FileBlob;
import org.frameworkset.platform.application.entity.Application;
import org.frameworkset.platform.application.entity.ApplicationCondition;
import org.frameworkset.platform.application.service.ApplicationException;
import org.frameworkset.platform.application.service.ApplicationService;
import org.frameworkset.platform.entity.DataGrid;
import org.frameworkset.platform.entity.PageBean;
import org.frameworkset.util.annotations.ResponseBody;
import org.frameworkset.web.auth.AuthorHelper;
import org.frameworkset.web.servlet.ModelMap;

import com.frameworkset.util.ListInfo;
import com.frameworkset.util.SimpleStringUtil;
import com.frameworkset.util.StringUtil;

/**
 * <p>Title: ApplicationController</p> <p>Description: 应用管理管理控制器处理类 </p>
 * <p>bboss</p> <p>Copyright (c) 2007</p> @Date 2016-11-24 15:35:15 @author
 * yinbp @version v1.0
 */
public class ApplicationController {

	private static Logger log = Logger.getLogger(ApplicationController.class);

	private ApplicationService applicationService;
	public @ResponseBody String addApplication(Application application) {
		// 控制器
		try {
			application.setAppId(SimpleStringUtil.getUUID());
			applicationService.addApplication(application);
			return "success";
		} catch (ApplicationException e) {
			log.error("add Application failed:", e);
			return StringUtil.formatBRException(e);
		} catch (Throwable e) {
			log.error("add Application failed:", e);
			return StringUtil.formatBRException(e);
		}

	}
	public @ResponseBody String deleteApplication(String appId) {
		try {
			applicationService.deleteApplication(appId);
			return "success";
		} catch (ApplicationException e) {
			log.error("delete Application failed:", e);
			return StringUtil.formatBRException(e);
		} catch (Throwable e) {
			log.error("delete Application failed:", e);
			return StringUtil.formatBRException(e);
		}

	}
	public @ResponseBody String deleteBatchApplication(String... appIds) {
		try {
			applicationService.deleteBatchApplication(appIds);
			return "success";
		} catch (Throwable e) {
			log.error("delete Batch appIds failed:", e);
			return StringUtil.formatBRException(e);
		}

	}
	public @ResponseBody String updateApplication(Application application) {
		try {
			applicationService.updateApplication(application);
			return "success";
		} catch (Throwable e) {
			log.error("update Application failed:", e);
			return StringUtil.formatBRException(e);
		}

	}
	public String getApplication(String appId, ModelMap model) throws ApplicationException {
		try {
			Application application = applicationService.getApplication(appId);
			model.addAttribute("application", application);
			return "path:getApplication";
		} catch (ApplicationException e) {
			throw e;
		} catch (Throwable e) {
			throw new ApplicationException("get Application failed::appId=" + appId, e);
		}

	}
	public @ResponseBody FileBlob downcafile(String appCode)
    {
    	if(appCode == null || appCode.equals(""))
    		throw new java.lang.NullPointerException("下载证书出错:必须指定应用编码");
    	
		try {
			
			InputStream input =  AuthorHelper.generateCAStream(appCode);
	        FileBlob fb = new FileBlob ("token.certificate",input);//下载文件流
	        return fb;
		} catch (Exception e) {
			throw new java.lang.RuntimeException(e);
		}
    	
    }
	public @ResponseBody DataGrid queryListInfoApplications(ApplicationCondition conditions,
			int currentPage,
			int pageSize, ModelMap model)
					throws ApplicationException {
		// Constant.component_type_actionimpl
		try {
			String appCode = conditions.getAppCode();
			if (appCode != null && !appCode.equals("")) {
				conditions.setAppCode("%" + appCode + "%");
			}
			String appName = conditions.getAppName();
			if (appName != null && !appName.equals("")) {
				conditions.setAppName("%" + appName + "%");
			}

			ListInfo applications = applicationService.queryListInfoApplications(conditions, (currentPage-1) * pageSize, pageSize);
			DataGrid dataGrid = new DataGrid();
			dataGrid.setTbody(applications.getDatas());
			dataGrid.setPage(new PageBean(applications.getTotalPages(), pageSize, currentPage, applications.getTotalSize()));
			return dataGrid;
		} catch (ApplicationException e) {
			throw e;
		} catch (Exception e) {
			throw new ApplicationException("pagine query Application failed:", e);
		}

	}
	public String queryListApplications(ApplicationCondition conditions, ModelMap model) throws ApplicationException {
		try {
			String appCode = conditions.getAppCode();
			if (appCode != null && !appCode.equals("")) {
				conditions.setAppCode("%" + appCode + "%");
			}
			String appName = conditions.getAppName();
			if (appName != null && !appName.equals("")) {
				conditions.setAppName("%" + appName + "%");
			}
			List<Application> applications = applicationService.queryListApplications(conditions);
			model.addAttribute("applications", applications);
			return "path:queryListApplications";
		} catch (ApplicationException e) {
			throw e;
		} catch (Exception e) {
			throw new ApplicationException("query Application failed:", e);
		}

	}
	public String toUpdateApplication(String appId, ModelMap model) throws ApplicationException {
		try {
			Application application = applicationService.getApplication(appId);
			model.addAttribute("application", application);
			return "path:updateApplication";
		} catch (ApplicationException e) {
			throw e;
		} catch (Throwable e) {
			throw new ApplicationException("get Application failed::appId=" + appId, e);
		}

	}
	/** 获取口令 gw_tanx
	 * @param model
	 * @return
	 * 2014年7月29日
	 */
	public @ResponseBody String getSystemSecret(ModelMap model) throws Exception{

		return SimpleStringUtil.getUUID();

	}
	public String toAddApplication() {
		return "path:addApplication";
	}
	public String index() {
		return "path:index";

	}
}
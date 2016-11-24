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

package org.frameworkset.platform.application.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.frameworkset.platform.application.entity.Application;
import org.frameworkset.platform.application.entity.ApplicationCondition;
import org.frameworkset.platform.security.authentication.EncrpyPwd;

import com.frameworkset.common.poolman.ConfigSQLExecutor;
import com.frameworkset.orm.transaction.TransactionManager;
import com.frameworkset.util.ListInfo;

/**
 * <p>Title: ApplicationServiceImpl</p> <p>Description: 应用管理管理业务处理类 </p>
 * <p>bboss</p> <p>Copyright (c) 2007</p> @Date 2016-11-24 15:35:15 @author
 * yinbp @version v1.0
 */
public class ApplicationServiceImpl implements ApplicationService {

	private static Logger log = Logger
			.getLogger(org.frameworkset.platform.application.service.ApplicationServiceImpl.class);

	private ConfigSQLExecutor executor;
	public void addApplication(Application application) throws ApplicationException {
		// 业务组件
		try {
			if(StringUtils.isNotEmpty(application.getAppSecret())){
    			
					application.setAppSecretText(application.getAppSecret());
    			
					application.setAppSecret(EncrpyPwd.encodePassword(application.getAppSecret()));
    		}else if(StringUtils.isNotEmpty(application.getAppSecretText())){
    			
    			application.setAppSecret(EncrpyPwd.encodePassword(application.getAppSecretText()));
    			
    		}
			executor.insertBean("addApplication", application);
		} catch (Throwable e) {
			throw new ApplicationException("add Application failed:", e);
		}

	}
	public void deleteApplication(String appId) throws ApplicationException {
		try {
			executor.delete("deleteByKey", appId);
		} catch (Throwable e) {
			throw new ApplicationException("delete Application failed::appId=" + appId, e);
		}

	}
	public void deleteBatchApplication(String... appIds) throws ApplicationException {
		TransactionManager tm = new TransactionManager();
		try {
			tm.begin();
			executor.deleteByKeys("deleteByKey", appIds);
			tm.commit();
		} catch (Throwable e) {

			throw new ApplicationException("batch delete Application failed::appIds=" + appIds, e);
		} finally {
			tm.release();
		}

	}
	public void updateApplication(Application application) throws ApplicationException {
		try {
			if(StringUtils.isNotEmpty(application.getAppSecret())){
    			
					application.setAppSecretText(application.getAppSecret());
				
					application.setAppSecret(EncrpyPwd.encodePassword(application.getAppSecret()));
			}else if(StringUtils.isNotEmpty(application.getAppSecretText())){
				
				application.setAppSecret(EncrpyPwd.encodePassword(application.getAppSecretText()));
				
			}
			executor.updateBean("updateApplication", application);
		} catch (Throwable e) {
			throw new ApplicationException("update Application failed::", e);
		}

	}
	public Application getApplication(String appId) throws ApplicationException {
		try {
			Application bean = executor.queryObject(Application.class, "selectById", appId);
			return bean;
		} catch (Throwable e) {
			throw new ApplicationException("get Application failed::appId=" + appId, e);
		}

	}
	public ListInfo queryListInfoApplications(ApplicationCondition conditions, long offset, int pagesize)
			throws ApplicationException {
		ListInfo datas = null;
		try {
			datas = executor.queryListInfoBean(Application.class, "queryListApplication", offset, pagesize, conditions);
		} catch (Exception e) {
			throw new ApplicationException("pagine query Application failed:", e);
		}
		return datas;

	}
	public List<Application> queryListApplications(ApplicationCondition conditions) throws ApplicationException {
		try {
			List<Application> beans = executor.queryListBean(Application.class, "queryListApplication", conditions);
			return beans;
		} catch (Exception e) {
			throw new ApplicationException("query Application failed:", e);
		}

	}
}
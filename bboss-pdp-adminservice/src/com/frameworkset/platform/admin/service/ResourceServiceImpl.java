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

package com.frameworkset.platform.admin.service;

import com.frameworkset.platform.admin.entity.*;
import com.frameworkset.util.ListInfo;
import com.frameworkset.common.poolman.ConfigSQLExecutor;
import org.apache.log4j.Logger;
import java.util.List;
import com.frameworkset.orm.transaction.TransactionManager;

/**
 * <p>Title: ResourceServiceImpl</p> <p>Description: 资源管理管理业务处理类 </p>
 * <p>bboss</p> <p>Copyright (c) 2007</p> @Date 2016-12-21 00:46:37 @author
 * yinbp @version v1.0
 */
public class ResourceServiceImpl implements ResourceService {

	private static Logger log = Logger.getLogger(com.frameworkset.platform.admin.service.ResourceServiceImpl.class);

	private ConfigSQLExecutor executor;
	public void addResource(Resource resource) throws ResourceException {
		// 业务组件
		try {
			executor.insertBean("addResource", resource);
		} catch (Throwable e) {
			throw new ResourceException("add Resource failed:", e);
		}

	}
	public void deleteResource(String resId) throws ResourceException {
		try {
			executor.delete("deleteByKey", resId);
		} catch (Throwable e) {
			throw new ResourceException("delete Resource failed::resId=" + resId, e);
		}

	}
	public void deleteBatchResource(String... resIds) throws ResourceException {
		TransactionManager tm = new TransactionManager();
		try {
			tm.begin();
			executor.deleteByKeys("deleteByKey", resIds);
			tm.commit();
		} catch (Throwable e) {

			throw new ResourceException("batch delete Resource failed::resIds=" + resIds, e);
		} finally {
			tm.release();
		}

	}
	public void updateResource(Resource resource) throws ResourceException {
		try {
			executor.updateBean("updateResource", resource);
		} catch (Throwable e) {
			throw new ResourceException("update Resource failed::", e);
		}

	}
	public Resource getResource(String resId) throws ResourceException {
		try {
			Resource bean = executor.queryObject(Resource.class, "selectById", resId);
			return bean;
		} catch (Throwable e) {
			throw new ResourceException("get Resource failed::resId=" + resId, e);
		}

	}
	public ListInfo queryListInfoResources(ResourceCondition conditions, long offset, int pagesize)
			throws ResourceException {
		ListInfo datas = null;
		try {
			datas = executor.queryListInfoBean(Resource.class, "queryListResource", offset, pagesize, conditions);
		} catch (Exception e) {
			throw new ResourceException("pagine query Resource failed:", e);
		}
		return datas;

	}
	public List<Resource> queryListResources(ResourceCondition conditions) throws ResourceException {
		try {
			List<Resource> beans = executor.queryListBean(Resource.class, "queryListResource", conditions);
			return beans;
		} catch (Exception e) {
			throw new ResourceException("query Resource failed:", e);
		}

	}
}
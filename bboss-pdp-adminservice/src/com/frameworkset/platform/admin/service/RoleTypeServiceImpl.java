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
 * <p>Title: RoleTypeServiceImpl</p> <p>Description: 角色类型管理管理业务处理类 </p>
 * <p>bboss</p> <p>Copyright (c) 2007</p> @Date 2016-12-15 23:37:09 @author
 * yinbp @version v1.0
 */
public class RoleTypeServiceImpl implements RoleTypeService {

	private static Logger log = Logger.getLogger(com.frameworkset.platform.admin.service.RoleTypeServiceImpl.class);

	private ConfigSQLExecutor executor;
	public void addRoleType(RoleType roleType) throws RoleTypeException {
		// 业务组件
		try {
			executor.insertBean("addRoleType", roleType);
		} catch (Throwable e) {
			throw new RoleTypeException("add RoleType failed:", e);
		}

	}
	public void deleteRoleType(String typeId) throws RoleTypeException {
		try {
			executor.delete("deleteByKey", typeId);
		} catch (Throwable e) {
			throw new RoleTypeException("delete RoleType failed::typeId=" + typeId, e);
		}

	}
	public void deleteBatchRoleType(String... typeIds) throws RoleTypeException {
		TransactionManager tm = new TransactionManager();
		try {
			tm.begin();
			executor.deleteByKeys("deleteByKey", typeIds);
			tm.commit();
		} catch (Throwable e) {

			throw new RoleTypeException("batch delete RoleType failed::typeIds=" + typeIds, e);
		} finally {
			tm.release();
		}

	}
	public void updateRoleType(RoleType roleType) throws RoleTypeException {
		try {
			executor.updateBean("updateRoleType", roleType);
		} catch (Throwable e) {
			throw new RoleTypeException("update RoleType failed::", e);
		}

	}
	public RoleType getRoleType(String typeId) throws RoleTypeException {
		try {
			RoleType bean = executor.queryObject(RoleType.class, "selectById", typeId);
			return bean;
		} catch (Throwable e) {
			throw new RoleTypeException("get RoleType failed::typeId=" + typeId, e);
		}

	}
	public ListInfo queryListInfoRoleTypes(RoleTypeCondition conditions, long offset, int pagesize)
			throws RoleTypeException {
		ListInfo datas = null;
		try {
			datas = executor.queryListInfoBean(RoleType.class, "queryListRoleType", offset, pagesize, conditions);
		} catch (Exception e) {
			throw new RoleTypeException("pagine query RoleType failed:", e);
		}
		return datas;

	}
	public List<RoleType> queryListRoleTypes(RoleTypeCondition conditions) throws RoleTypeException {
		try {
			List<RoleType> beans =conditions == null? executor.queryList(RoleType.class, "queryAllListRoleType"):
													  executor.queryListBean(RoleType.class, "queryListRoleType",conditions);
			return beans;
		} catch (Exception e) {
			throw new RoleTypeException("query RoleType failed:", e);
		}

	}
}
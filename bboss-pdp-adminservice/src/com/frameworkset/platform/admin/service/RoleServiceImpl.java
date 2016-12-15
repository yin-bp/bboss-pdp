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
 * <p>Title: RoleServiceImpl</p> <p>Description: 角色管理管理业务处理类 </p> <p>bboss</p>
 * <p>Copyright (c) 2007</p> @Date 2016-12-15 17:06:09 @author yinbp @version
 * v1.0
 */
public class RoleServiceImpl implements RoleService {

	private static Logger log = Logger.getLogger(com.frameworkset.platform.admin.service.RoleServiceImpl.class);

	private ConfigSQLExecutor executor;
	public void addRole(Role role) throws RoleException {
		// 业务组件
		try {
			executor.insertBean("addRole", role);
		} catch (Throwable e) {
			throw new RoleException("add Role failed:", e);
		}

	}
	public void deleteRole(String roleId) throws RoleException {
		try {
			executor.delete("deleteByKey", roleId);
		} catch (Throwable e) {
			throw new RoleException("delete Role failed::roleId=" + roleId, e);
		}

	}
	public void deleteBatchRole(String... roleIds) throws RoleException {
		TransactionManager tm = new TransactionManager();
		try {
			tm.begin();
			executor.deleteByKeys("deleteByKey", roleIds);
			tm.commit();
		} catch (Throwable e) {

			throw new RoleException("batch delete Role failed::roleIds=" + roleIds, e);
		} finally {
			tm.release();
		}

	}
	public void updateRole(Role role) throws RoleException {
		try {
			executor.updateBean("updateRole", role);
		} catch (Throwable e) {
			throw new RoleException("update Role failed::", e);
		}

	}
	public Role getRole(String roleId) throws RoleException {
		try {
			Role bean = executor.queryObject(Role.class, "selectById", roleId);
			return bean;
		} catch (Throwable e) {
			throw new RoleException("get Role failed::roleId=" + roleId, e);
		}

	}
	public ListInfo queryListInfoRoles(RoleCondition conditions, long offset, int pagesize) throws RoleException {
		ListInfo datas = null;
		try {
			datas = executor.queryListInfoBean(Role.class, "queryListRole", offset, pagesize, conditions);
		} catch (Exception e) {
			throw new RoleException("pagine query Role failed:", e);
		}
		return datas;

	}
	public List<Role> queryListRoles(RoleCondition conditions) throws RoleException {
		try {
			List<Role> beans = executor.queryListBean(Role.class, "queryListRole", conditions);
			return beans;
		} catch (Exception e) {
			throw new RoleException("query Role failed:", e);
		}

	}
}
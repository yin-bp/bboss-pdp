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
import java.util.List;

/**
 * <p>Title: RoleService</p> <p>Description: 角色管理管理服务接口 </p> <p>bboss</p>
 * <p>Copyright (c) 2015</p> @Date 2016-12-15 17:06:09 @author yinbp @version
 * v1.0
 */
public interface RoleService {
	public void addRole(Role role) throws RoleException;
	public void deleteRole(String roleId) throws RoleException;
	public void deleteBatchRole(String... roleIds) throws RoleException;
	public void updateRole(Role role) throws RoleException;
	public Role getRole(String roleId) throws RoleException;
	public ListInfo queryListInfoRoles(RoleCondition conditions, long offset, int pagesize) throws RoleException;
	public List<Role> queryListRoles(RoleCondition conditions) throws RoleException;
	/**
	 * @param userId
	 * @return
	 */
	public List<UserRole> getUserRoles(String userId) throws RoleException;
}
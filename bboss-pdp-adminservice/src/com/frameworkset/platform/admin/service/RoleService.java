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

import com.frameworkset.common.poolman.handle.RowHandler;
import com.frameworkset.platform.admin.entity.*;
import com.frameworkset.util.ListInfo;
import java.util.List;
import java.util.Map;

import org.frameworkset.platform.security.authorization.AuthRole;

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
	public Role getRoleByName(String roleName) throws RoleException;
	public ListInfo queryListInfoRoles(RoleCondition conditions, long offset, int pagesize) throws RoleException;
	public List<Role> queryListRoles(RoleCondition conditions) throws RoleException;
	/**
	 * @param userId
	 * @return
	 */
	public List<UserRole> getUserRoles(String userId) throws RoleException;
	/**
	 * 获取资源类型下面的，授予角色的全局操作及有效时间
	 * op_id,AUTHORIZATION_STIME,AUTHORIZATION_ETIME
	 * @param globalresourceid
	 * @param resourceType
	 * @param roleId
	 * @param roleType
	 */
	public Map getGrantedGlobalOperations(String globalresourceid, String resourceType, String roleId,
			String roleType,String permissionTable)throws RoleException;
	
	/**
	 * 获取资源类型下面的，授予角色的全局操作及有效时间
	 * op_id,AUTHORIZATION_STIME,AUTHORIZATION_ETIME
	 * @param opcode
	 * @param resourceType
	 * @param roleId
	 * @param roleType
	 */
	public  List  getGrantedOperations(String opcode, String resourceType, String roleId,
			String roleType,String permissionTable,RowHandler  rowHandler,Class poclazz)throws RoleException;
	/**
	 * @param globalresourceid
	 * @param globalopcode
	 * @param resOprs
	 * @param resourceType
	 * @param roleId
	 * @param roleType
	 */
	public void saveRoleAuths(String globalresourceid, String[] globalopcode, List<ResOpr> resOprs, String resourceType,
			String roleId, String roleType,String permissionTable);
	
	/**
	 * @param globalresourceid
	 * @param globalopcode
	 * @param resOprs
	 * @param resourceType
	 * @param roleId
	 * @param roleType
	 */
	public void saveRoleAuths( List<ResOpr> resOprs, String resourceType,
			String roleId, String roleType,String permissionTable);
	/**
	 * 删除角色资源
	 * @param resOprs
	 * @param resourceType
	 * @param roleId
	 * @param roleType
	 * @param permissionTable
	 */
	public void deleteRoleAuthResources(List<ResOpr> resOprs, String resourceType, String roleId, String roleType,
			String permissionTable)  throws RoleException ;
	/**
	 * @param userAttr
	 * @param roleName
	 * @param offset
	 * @param pagesize
	 * @return
	 */
	public ListInfo queryRoleUsers(String userAttr, String roleName, long offset, int pagesize)  throws RoleException ;
	/**
	 * @param rs
	 * @param string
	 */
	public void deleteAllRoleAuthResources(String[] rs, String string)  throws RoleException ;
	/**
	 * @param resource
	 * @param action
	 * @param resourceType
	 * @return
	 */
	public AuthRole[] getRequiredRoles(String resource, String action, String resourceType)  throws RoleException ;
	public String getSimpleRoleName(String roleId)  throws RoleException;
	/**
	 * @param resource
	 * @param resourceType
	 * @return
	 */
	public boolean hasGrantedRoles(String resource, String resourceType)  throws RoleException ;
	/**
	 * @param role
	 * @param resource
	 * @param resourceType
	 * @return
	 */
	public boolean hasGrantRole(AuthRole role, String resource, String resourceType)  throws RoleException ;
	/**
	 * @param roleName
	 * @return
	 */
	public boolean checkroleexist(String roleName) throws RoleException ;
}
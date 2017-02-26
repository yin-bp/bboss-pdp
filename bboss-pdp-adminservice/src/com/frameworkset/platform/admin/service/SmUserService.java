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

import java.util.List;

import org.frameworkset.platform.entity.Leader;

import com.frameworkset.platform.admin.entity.MoveinUserCondition;
import com.frameworkset.platform.admin.entity.QueryUserCondition;
import com.frameworkset.platform.admin.entity.SmUser;
import com.frameworkset.platform.admin.entity.SmUserCondition;
import com.frameworkset.util.ListInfo;

/**
 * <p>Title: SmUserService</p> <p>Description: 用户管理管理服务接口 </p> <p>bboss</p>
 * <p>Copyright (c) 2015</p> @Date 2016-11-29 21:14:30 @author yinbp @version
 * v1.0
 */
public interface SmUserService {
	public void addSmUser(SmUser smUser) throws SmUserException;
	public void deleteSmUser(String userId) throws SmUserException;
	public void deleteBatchSmUser(String[] userIds,String user_deltype) throws SmUserException;
	public void updateSmUser(SmUser smUser) throws SmUserException;
	public SmUser getSmUser(String userId) throws SmUserException;
	public SmUser getSmUserByIDNAMECNName(String userId) throws SmUserException;
	public SmUser getSmUserByName(String userName) throws SmUserException;
	
	public ListInfo queryListInfoSmUsers(SmUserCondition conditions, long offset, int pagesize) throws SmUserException;
	public List<SmUser> queryListSmUsers(SmUserCondition conditions) throws SmUserException;
	public ListInfo getDepartUsers(SmUserCondition conditions, long offset, int pagesize) throws SmUserException ;
	 /**
     * user_updatetype:3 -停用 0 - 逻辑删除 1 - 物理删除  2- 启用用户
     */
	public void updateUserStatus(String userId,String user_updatetype) throws SmUserException;
	/**
	 * @param userId
	 */
	public void resetpassword(String userId) throws SmUserException;
	public  String modifypassword(String userId,String newPassword,String newPasswordSecond,String oldPassword) throws SmUserException;
	/**
	 * @param departid
	 * @return
	 */
	public List<SmUser> getDepartUsers(String departid) throws SmUserException;
	/**
	 * @param userIds
	 */
	public void saveSmUsersOrder(String[] userIds) throws SmUserException;
	/**
	 * @param userIds
	 * @param fromdepartId
	 * @param todepartId
	 */
	public void saveMoveusers(String userIds, String fromdepartId, String todepartId) throws SmUserException;
	/**
	 * @param condition
	 * @return
	 */
	public ListInfo getMoveinUsers(MoveinUserCondition condition,long offset,
			int pagesize) throws SmUserException;
	/**
	 * @param userId
	 * @param roleIds
	 */
	public void saveUserRoles(String userId, String roleIds) throws SmUserException;
	/**
	 * @param userIds
	 * @param roleId
	 */
	public void saveRoleUsers(String userIds, String roleId,boolean needcheckSpecialRole)  throws SmUserException;
	/**
	 * @param roleName
	 * @param userIds
	 */
	public void deleteRoleUsers(String roleId, String userIds,boolean needcheckSpecialRole) throws SmUserException;
	/**
	 * @param roleIds
	 */
	public void deleteRoleUsersOfRoles(String[] roleIds)throws SmUserException;
	
	/**
	 * 删除角色用户关系
	 * @param userIds
	 */
	public void deleteRoleUsersOfUsers(String[] userIds)throws SmUserException;
	/**
	 * @param userWorknumber
	 * @return
	 */
	public boolean checkworknumberexist(String userWorknumber,String userId)throws SmUserException;
	/**
	 * @return
	 */
	public String genworknumber(String userId)throws SmUserException;
	public String getWorknumber(String userId) throws SmUserException ;
	/**
	 * @param userAccount
	 * @return
	 */
	public boolean checkuserexist(String userAccount)throws SmUserException;
	/**
	 * @param condition
	 * @param offset
	 * @param pagesize
	 * @return
	 */
	public ListInfo queryUsers(QueryUserCondition condition, long offset, int pagesize)throws SmUserException;
	/**
	 * @param userAccount
	 * @return
	 */
	public String getChargeOrgId(String userAccount)throws SmUserException;
	/**
	 * @param departId
	 * @return
	 */
	public Leader getLeader(String departTreeLevel)throws SmUserException;
}
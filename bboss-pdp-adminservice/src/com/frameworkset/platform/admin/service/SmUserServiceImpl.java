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

import com.frameworkset.common.poolman.ConfigSQLExecutor;
import com.frameworkset.common.poolman.handle.ResultSetNullRowHandler;
import com.frameworkset.orm.transaction.TransactionManager;
import com.frameworkset.platform.admin.entity.MoveinUserCondition;
import com.frameworkset.platform.admin.entity.QueryUserCondition;
import com.frameworkset.platform.admin.entity.SmUser;
import com.frameworkset.platform.admin.entity.SmUserCondition;
import com.frameworkset.util.ListInfo;
import com.frameworkset.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.frameworkset.platform.common.Constants;
import org.frameworkset.platform.entity.Leader;
import org.frameworkset.platform.security.AccessControl;
import org.frameworkset.platform.security.authentication.EncrpyPwd;
import org.frameworkset.platform.security.authorization.AuthUser;
import org.frameworkset.platform.util.AdminUtil;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: SmUserServiceImpl</p> <p>Description: 用户管理管理业务处理类 </p> <p>bboss</p>
 * <p>Copyright (c) 2007</p> @Date 2016-11-29 21:14:30 @author yinbp @version
 * v1.0
 */
public class SmUserServiceImpl implements SmUserService {

	private static org.slf4j.Logger log = LoggerFactory.getLogger(com.frameworkset.platform.admin.service.SmUserServiceImpl.class);
	private SmOrganizationService smOrganizationService;
	private ConfigSQLExecutor executor;
	private void handlLisan(SmUser smUser){
		if(StringUtil.isEmpty(smUser.getDepartId()) ||smUser.getDepartId().equals(Constants.LISAN_ID))
			smUser.setDepartId(null);
	}
	public void addSmUser(SmUser smUser) throws SmUserException {
		// 业务组件
		try {
			handlLisan(  smUser);
			int user_sn = executor.queryObject(int.class, "selectMaxSNofdepart", smUser.getDepartId());
			smUser.setUserSn(user_sn);
			//对口令进行加密处理
			if(StringUtils.isNotEmpty(smUser.getUserPassword())){
    			
				smUser.setPasswordText(smUser.getUserPassword());
				
				smUser.setUserPassword(EncrpyPwd.encodePassword(smUser.getUserPassword()));
			}else if(StringUtils.isNotEmpty(smUser.getPasswordText())){
				
				smUser.setUserPassword(EncrpyPwd.encodePassword(smUser.getPasswordText()));
				
			}
			executor.insertBean("addSmUser", smUser);
		} catch (Throwable e) {
			throw new SmUserException("add SmUser failed:", e);
		}

	}
	public void deleteSmUser(String userId) throws SmUserException {
		try {
			executor.delete("deleteByKey", userId);
		} catch (Throwable e) {
			throw new SmUserException("delete SmUser failed::userId=" + userId, e);
		}

	}
	public void deleteBatchSmUser(String[] userIds,String user_deltype) throws SmUserException {
		TransactionManager tm = new TransactionManager();
		try {
			tm.begin();
			if(user_deltype == null || user_deltype.equals("0"))//逻辑删除
			{
				executor.updateByKeys("logicDeleteUsers", userIds);
			}
			else
			{
				executor.deleteByKeys("deleteByKey", userIds);//物理删除
				
			}
			tm.commit();
		} catch (Throwable e) {

			throw new SmUserException("batch delete SmUser failed::userIds=" + userIds, e);
		} finally {
			tm.release();
		}

	}
	 /**
     * user_updatetype:3 -停用 0 - 逻辑删除 1 - 物理删除  2- 启用用户
     */
	public void updateUserStatus(String userId,String user_updatetype) throws SmUserException {
		 
		try {
			 
			if( user_updatetype.equals("0"))//逻辑删除
			{
				executor.update("updateUserStatus", Constants.USER_STATUS_DELETE,userId);
			}
			else if( user_updatetype.equals("1"))
			{
				executor.deleteByKeys("deleteByKey", userId);//物理删除
			}
			else if( user_updatetype.equals("2"))
			{
				executor.update("updateUserStatus", Constants.USER_STATUS_NORMAL,userId);//物理删除
			}
			else if( user_updatetype.equals("3"))
			{
				executor.update("updateUserStatus",   Constants.USER_STATUS_STOP,userId);//物理删除
			}
			 
		} catch (Throwable e) {

			throw new SmUserException("修改用户状态失败::userId=" + userId, e);
		} finally {
			 
		}

	}
	public void updateSmUser(SmUser smUser) throws SmUserException {
		try {
			handlLisan(  smUser);
			if(StringUtils.isNotEmpty(smUser.getUserPassword())){
    			
				smUser.setPasswordText(smUser.getUserPassword());
				
				smUser.setUserPassword(EncrpyPwd.encodePassword(smUser.getUserPassword()));
			}else if(StringUtils.isNotEmpty(smUser.getPasswordText())){
				
				smUser.setUserPassword(EncrpyPwd.encodePassword(smUser.getPasswordText()));
				
			}
			if(AccessControl.isDefaultAdmin(smUser.getUserId()))
			{
				smUser.setUserIsvalid(Constants.USER_STATUS_NORMAL);
				smUser.setPasswordDualtime(-1);
			}
			executor.updateBean("updateSmUser", smUser);
		} catch (Throwable e) {
			throw new SmUserException("update SmUser failed::", e);
		}

	}
	public SmUser getSmUser(String userId) throws SmUserException {
		try {
			SmUser bean = executor.queryObject(SmUser.class, "selectById", userId);
			return bean;
		} catch (Throwable e) {
			throw new SmUserException("get SmUser failed::userId=" + userId, e);
		}

	}
	public SmUser getUserByWorknumber(String userWorknumber)throws SmUserException{
		try {
			SmUser bean = executor.queryObject(SmUser.class, "getUserByWorknumber", userWorknumber);
			return bean;
		} catch (Throwable e) {
			throw new SmUserException("get SmUser failed::userWorknumber=" + userWorknumber, e);
		}
	}
	public AuthUser getUser(String account)throws SmUserException{
		try {
			AuthUser bean = executor.queryObject(AuthUser.class, "getSmUserByNAMECNName", account,account, account,account,account);
			return bean;
		} catch (Throwable e) {
			throw new SmUserException("get SmUser failed::IDNAMECNName=" + account, e);
		}
	}
	public SmUser getSmUserByIDNAMECNName(String userId) throws SmUserException {
		try {
			SmUser bean = executor.queryObject(SmUser.class, "getSmUserByNAMECNName", userId,userId, userId,userId,userId);
			return bean;
		} catch (Throwable e) {
			throw new SmUserException("get SmUser failed::IDNAMECNName=" + userId, e);
		}

	}
	
	public SmUser getSmUserByName(String userName) throws SmUserException{
		try {
			SmUser bean = executor.queryObject(SmUser.class, "getSmUserByName", userName);
			return bean;
		} catch (Throwable e) {
			throw new SmUserException("get SmUser failed::userName=" + userName, e);
		}
	}
	public ListInfo queryListInfoSmUsers(SmUserCondition conditions, long offset, int pagesize) throws SmUserException {
		 
		try {
			String departid = conditions.getDepartId();
			
			
			final List<SmUser> users = new ArrayList<SmUser>();
			if(departid != null && departid.equals(Constants.LISAN_ID)){
				
			}
			else if(conditions.getRecursive() != null)
			{
				if(conditions.getRecursive().equals("1")){//含子机构查询
					String orgtreelevel = smOrganizationService.getOrgTreeLevel(conditions.getDepartId());
					conditions.setOrgtreelevel(orgtreelevel);
				}
					
			}
			
			ListInfo datas = executor.queryListInfoBeanByNullRowHandler(new ResultSetNullRowHandler(){

				@Override
				public void handleRow( ResultSet record) throws Exception {
					SmUser rowValue = (SmUser) buildValueObject(record, SmUser.class);
					users.add(rowValue);
					rowValue.setDefaultAdmin(AccessControl.isDefaultAdmin(rowValue.getUserId()));
					
				}
				
			},"queryListInfoSmUsers", offset, pagesize, conditions);
			datas.setDatas(users);
			return datas;
		} catch (Exception e) {
			throw new SmUserException("pagine query SmUser failed:", e);
		}
	 

	}


	
	
	public ListInfo getDepartUsers(SmUserCondition conditions, long offset, int pagesize) throws SmUserException {
		 
		try {
			String departid = conditions.getDepartId();
			
				
			final List<SmUser> users = new ArrayList<SmUser>();
			if(departid != null && departid.equals(Constants.LISAN_ID)){
				
			}
			else if(conditions.getRecursive() != null)
			{
				if(conditions.getRecursive().equals("1")){//含子机构查询
					String orgtreelevel = smOrganizationService.getOrgTreeLevel(conditions.getDepartId());
					conditions.setOrgtreelevel(orgtreelevel);
				}
					
			}
			
			ListInfo datas = executor.queryListInfoBeanByNullRowHandler(new ResultSetNullRowHandler(){

				@Override
				public void handleRow( ResultSet record) throws Exception {
					SmUser rowValue = (SmUser) buildValueObject(record, SmUser.class);
					users.add(rowValue);
					rowValue.setDefaultAdmin(AccessControl.isDefaultAdmin(rowValue.getUserId()));
					
				}
				
			},"getDepartUsers", offset, pagesize, conditions);
			datas.setDatas(users);
			return datas;
		} catch (Exception e) {
			throw new SmUserException("pagine query SmUser failed:", e);
		}
		

	}
	public List<SmUser> queryListSmUsers(SmUserCondition conditions) throws SmUserException {
		try {
			List<SmUser> beans = executor.queryListBean(SmUser.class, "queryListSmUser", conditions);
			return beans;
		} catch (Exception e) {
			throw new SmUserException("query SmUser failed:", e);
		}

	}
	/** (non-Javadoc)
	 * @see com.frameworkset.platform.admin.service.SmUserService#resetpassword(java.lang.String)
	 */
	@Override
	public void resetpassword(String userId) throws SmUserException {
		String newPassword = EncrpyPwd.encodePassword("123456");
		try {
			executor.update("resetpassword", newPassword,userId);
		} catch (Exception e) {
			throw new SmUserException("reset password failed:", e);
		}
		
	}
	public  String modifypassword(String userId,String newPassword,String newPasswordSecond,String oldPassword) throws SmUserException{
		
		
		try {
			if(!newPassword.equals(newPasswordSecond)){
				return "两次输入口令不一致!";
			}
			if(oldPassword != null && !oldPassword.equals(""))	{
				String encrptoldPassword = EncrpyPwd.encodePassword(oldPassword);
				int exist = executor.queryObject(int.class, "oldPasswordright", userId,encrptoldPassword);
				if(exist ==0){
					return "旧口令不正确!";
				}
			}
				
			
				
			String encrptnewPassword = EncrpyPwd.encodePassword(newPassword);
			executor.update("resetpassword", encrptnewPassword,userId);
			return "success";
		} catch (Exception e) {
			throw new SmUserException("reset password failed:", e);
		}
	}
	/** (non-Javadoc)
	 * @see com.frameworkset.platform.admin.service.SmUserService#getDepartUsers(java.lang.String)
	 */
	@Override
	public List<SmUser> getDepartUsers(String departid) throws SmUserException {
		try {
			 if(departid == null || departid.equals(""))
				 throw new SmUserException("没有选择部门");
				
			 List<SmUser> users = null;
			if(departid != null && departid.equals(Constants.LISAN_ID)){
				users = executor.queryList(SmUser.class,"getAllLisanUsers");				
			}
			else
			{
				users = executor.queryList(SmUser.class,"getAllDepartUsers", departid);
			}
			
			return users;
		} 
		catch(SmUserException e)
		{
			throw e;
		}
		catch (Exception e) {
			throw new SmUserException("pagine query SmUser failed:", e);
		}
	}
	/**
	 * 查询需要调入的用户列表
	 * (non-Javadoc)
	 * @see com.frameworkset.platform.admin.service.SmUserService#getMoveinUsers(com.frameworkset.platform.admin.entity.MoveinUserCondition)
	 */
	public ListInfo getMoveinUsers(MoveinUserCondition condition,long offset,
			int pagesize) throws SmUserException{
		if(condition.getRecursive() == null || condition.getRecursive().equals("0") || condition.getRecursive().equals("1")){
			if(condition.getFromDepartId() == null || condition.getFromDepartId().equals(""))
				 throw new SmUserException("没有选择部门");
		}
			
		
		try {
			if(condition.getRecursive() != null)
			{
				if(condition.getRecursive().equals("1")){//含子机构查询
					String orgtreelevel = smOrganizationService.getOrgTreeLevel(condition.getFromDepartId());
					condition.setOrgtreelevel(orgtreelevel);
					condition.setOrgtreelevelLike(orgtreelevel+"|%");
				}
					
			}
//			List<SmUser> users = null;
//			if(condition.getRecords() == null || condition.getRecords() <= 0){
//				users = this.executor.queryListBean(SmUser.class, "getMoveinUsers", condition);
//			}
//			else
			{
				ListInfo _users = this.executor.queryListInfoBean(SmUser.class, "getMoveinUsers", offset, pagesize, condition);
//				users = _users.getDatas();
				return _users;
			}
				
//			return users;
		} 
		catch(SmUserException e)
		{
			throw e;
		}
		catch (Exception e) {
			throw new SmUserException("pagine query SmUser failed:", e);
		}	
		 
		
	}
	public void saveSmUsersOrder(String[] userIds) throws SmUserException{
		if(userIds != null && userIds.length > 0)
		{
			List<Map> datas = new ArrayList<Map>();
			for(int i = 0; i < userIds.length; i ++){
				Map record = new HashMap();
				record.put("userId", userIds[i]);
				record.put("userSn", i);
				datas.add(record);
			}
			
			try {
				this.executor.updateBeans("saveSmUsersOrder", datas);
			} 
			catch(SmUserException e)
			{
				throw e;
			}
			catch (Exception e) {
				throw new SmUserException("pagine query SmUser failed:", e);
			}
		}
	}
	/**
	 * 保存调动用户记录
	 * (non-Javadoc)
	 * @see com.frameworkset.platform.admin.service.SmUserService#saveMoveusers(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void saveMoveusers(String userIds_, String fromdepartId, String todepartId) throws SmUserException{
		String[] userIds = userIds_.split(",");
		if(userIds != null && userIds.length > 0)
		{
			
			
			try {
				if(!todepartId.equals(Constants.LISAN_ID)){
					List<Map> datas = new ArrayList<Map>();
					for(int i = 0; i < userIds.length; i ++){
						Map record = new HashMap();
						record.put("userId", userIds[i]);
						record.put("todepartId", todepartId);
						datas.add(record);
					}
					this.executor.updateBeans("saveMoveusers", datas);
				}
				else
				{
					this.executor.updateByKeys("moveuserstolisan", userIds);
				}
			} 
			catch(SmUserException e)
			{
				throw e;
			}
			catch (Exception e) {
				throw new SmUserException("pagine query SmUser failed:", e);
			}
		}
	}
	/**
	 * @param userIds
	 * @param roleId
	 */
	public void saveRoleUsers(String userIds, String roleId,boolean needcheckSpecialRole)  throws SmUserException{
		
		TransactionManager tm = new TransactionManager();
		try {
			tm.begin();
			List<Map> userRoles = new ArrayList<Map>();
			if(needcheckSpecialRole ){//特殊角色无需手工授予用户
				
				if(!filterGrantSpecialRole(roleId)){//特殊角色无需手工授予用户
					int num = 0;
					if(userIds != null){
						String[] userIds_ = userIds.split(",");
						for(String userId:userIds_){
							num = executor.queryObject(int.class, "existUserRoles", userId,roleId);
							if(num == 0)
							{
								Map data = new HashMap();
								data.put("userId", userId);
								data.put("roleId", roleId);
								userRoles.add(data);
							}
						}
					}
				}
			 
				
			}
			else
			{
				int num = 0;
				if(userIds != null){
					String[] userIds_ = userIds.split(",");
					for(String userId:userIds_){
						num = executor.queryObject(int.class, "existUserRoles", userId,roleId);
						if(num == 0)
						{
							Map data = new HashMap();
							data.put("userId", userId);
							data.put("roleId", roleId);
							userRoles.add(data);
						}
					}
				}
			}
			if(userRoles.size() > 0)
				this.executor.insertBeans("saveUserRoles", userRoles);
			tm.commit();
		} catch (Exception e) {
			throw new SmUserException(e);
		}
		finally
		{
			tm.release();
		}
		
	}
	private boolean filterGrantSpecialRole(String roleId) throws Exception{
		String roleName = this.executor.queryObject(String.class, "getrolename", roleId);
		return AdminUtil.filterGrantSpecialRole(roleName);
	}
	
	private boolean filterDeleteSpecialRole(String roleId) throws Exception{
		String roleName = this.executor.queryObject(String.class, "getrolename", roleId);
		return AdminUtil.filterDeleteSpecialRole(roleName);
	}
	
	
	/** (non-Javadoc)
	 * @see com.frameworkset.platform.admin.service.SmUserService#saveUserRoles(java.lang.String, java.lang.String)
	 */
	@Override
	public void saveUserRoles(String userId, String roleIds) throws SmUserException {
		
		TransactionManager tm = new TransactionManager();
		try {
			tm.begin();
			String roleIds_[] = StringUtil.isEmpty(roleIds)?null: roleIds.split(",");
			List<Map> userRoles = new ArrayList<Map>();
//			int num = 0;
			if(roleIds_ != null){
				for(String roleId:roleIds_){
					if(filterGrantSpecialRole(roleId))
						continue;
	//				num = executor.queryObject(int.class, "existUserRoles", userId,roleId);
	//				if(num == 0)
	//				{
						Map data = new HashMap();
						data.put("userId", userId);
						data.put("roleId", roleId);
						userRoles.add(data);
	//				}
				}
			}
			executor.delete("removeUserRoles", userId);//删除手动为用户设置的角色，缺省的系统级别的角色不能被删除
			if(userRoles.size() > 0)
				this.executor.insertBeans("saveUserRoles", userRoles);
			tm.commit();
		} catch (Exception e) {
			throw new SmUserException(e);
		}
		finally
		{
			tm.release();
		}
		
	}
	/**
	 * @param roleName
	 * @param userIds
	 */
	public void deleteRoleUsers(String roleId, String userIds,boolean needcheckSpecialRole) throws SmUserException{
		
		TransactionManager tm = new TransactionManager();
		try {
			tm.begin();
			String userIds_[] = StringUtil.isEmpty(userIds)?null: userIds.split(",");
			List<Map> userRoles = new ArrayList<Map>();
			if(needcheckSpecialRole){
				if(!filterDeleteSpecialRole(roleId)){//特殊角色不能手工删除
					if(userIds_ != null){
						for(String userId:userIds_){  
							Map data = new HashMap();
							data.put("userId", userId);
							data.put("roleId", roleId);
							userRoles.add(data);
						}
					}
				}
			}
			else
			{
				if(userIds_ != null){
					for(String userId:userIds_){  
						Map data = new HashMap();
						data.put("userId", userId);
						data.put("roleId", roleId);
						userRoles.add(data);
					}
				}
			}
			
			if(userRoles.size() > 0)
				this.executor.deleteBeans("deleteRoleUsers", userRoles);
			tm.commit();
		} catch (Exception e) {
			throw new SmUserException(e);
		}
		finally
		{
			tm.release();
		}
	}
	/**
	 * 删除角色用户关系
	 * @param userIds
	 */
	public void deleteRoleUsersOfUsers(String[] userIds)throws SmUserException{
		if(userIds == null || userIds.length == 0)
			return;
		TransactionManager tm = new TransactionManager();
		try {
			tm.begin();
//			String roleIds_[] = StringUtil.isEmpty(roleIds)?null: roleIds.split(",");
//			
			this.executor.deleteByKeys("deleteRoleUsersOfUsers", userIds);
			tm.commit();
		} catch (Exception e) {
			throw new SmUserException(e);
		}
		finally
		{
			tm.release();
		}
	}
	public void deleteRoleUsersOfRoles(String[] roleIds)throws SmUserException{
		if(roleIds == null || roleIds.length == 0)
			return;
		TransactionManager tm = new TransactionManager();
		try {
			tm.begin();
//			String roleIds_[] = StringUtil.isEmpty(roleIds)?null: roleIds.split(",");
//			
			this.executor.deleteByKeys("deleteRoleUsersOfRoles", roleIds);
			tm.commit();
		} catch (Exception e) {
			throw new SmUserException(e);
		}
		finally
		{
			tm.release();
		}
	}
	/** (non-Javadoc)
	 * @see com.frameworkset.platform.admin.service.SmUserService#checkworknumberexist(java.lang.String)
	 */
	@Override
	public boolean checkworknumberexist(String userWorknumber,String userId) throws SmUserException {
		try {
			if(StringUtil.isEmpty(userId)){
				int num = this.executor.queryObject(int.class, "checkworknumberexist", userWorknumber);
				return num > 0;
			}
			else
			{
				int num = this.executor.queryObject(int.class, "checkworknumberexistofuser", userWorknumber,userId);
				return num > 0;
			}
		} catch (SQLException e) {
			throw new SmUserException(e);
		}
		
	}
	public String getWorknumber(String userId) throws SmUserException {
		try {
			return this.executor.queryObject(String.class, "getWorknumber", userId);
		} catch (SQLException e) {
			throw new SmUserException(e);
		}
	}
	/** (non-Javadoc)
	 * @see com.frameworkset.platform.admin.service.SmUserService#genworknumber()
	 */
	@Override
	public String genworknumber(String userId) throws SmUserException {
		
		try {
			if(StringUtil.isNotEmpty(userId)){
				String worknumber = getWorknumber(  userId);
				if(StringUtil.isNotEmpty(userId)){
					return worknumber;
				}
			}
			String num = this.executor.queryObject(String.class, "genworknumber");
			int _num = Integer.parseInt(num)+1;
			return _num + "";
		} catch (SQLException e) {
			throw new SmUserException(e);
		}
	}
	public boolean checkuserexist(String userAccount)throws SmUserException{
		try {
			int num = this.executor.queryObject(int.class, "checkuserexist", userAccount);
			return num > 0;
		} catch (SQLException e) {
			throw new SmUserException(e);
		}
	}
	
	public ListInfo queryUsers(QueryUserCondition condition, long offset, int pagesize)throws SmUserException{
		
		
		try {
			ListInfo _users = this.executor.queryListInfoBean(SmUser.class, "queryUsers", offset, pagesize, condition);
//			users = _users.getDatas();
			return _users;
		} catch (SQLException e) {
			throw new SmUserException(e);
		}
	}
	/** (non-Javadoc)
	 * @see com.frameworkset.platform.admin.service.SmUserService#getChargeOrgId(java.lang.String)
	 */
	@Override
	public String getChargeOrgId(String userAccount) throws SmUserException {
		try {
			String chargeOrgId = this.executor.queryObject(String.class, "getChargeOrgId",userAccount);
//			users = _users.getDatas();
			if(chargeOrgId == null || chargeOrgId.equals(""))
				return "";
			return chargeOrgId;
		} catch (SQLException e) {
			throw new SmUserException(e);
		}
	}
	/** (non-Javadoc)
	 * @see com.frameworkset.platform.admin.service.SmUserService#getLeader(java.lang.String)
	 */
	@Override
	public Leader getLeader(String departTreeLevel) throws SmUserException {
		String[] departIds = departTreeLevel.split("\\|");
		for(int i = departIds.length - 1; i > 0; i--){
			try {
				Leader leader = executor.queryObject(Leader.class, "getOrgLeader", departIds[i]);
				if(leader != null)
					return leader;
			} catch (SQLException e) {
				throw new SmUserException(e);
			}
		}
		return null;
	}
}
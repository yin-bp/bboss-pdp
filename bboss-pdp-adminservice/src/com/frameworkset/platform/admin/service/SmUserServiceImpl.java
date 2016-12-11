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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.frameworkset.platform.common.Constants;
import org.frameworkset.platform.security.AccessControl;
import org.frameworkset.platform.security.authentication.EncrpyPwd;

import com.frameworkset.common.poolman.ConfigSQLExecutor;
import com.frameworkset.common.poolman.handle.ResultSetNullRowHandler;
import com.frameworkset.orm.transaction.TransactionManager;
import com.frameworkset.platform.admin.entity.SmUser;
import com.frameworkset.platform.admin.entity.SmUserCondition;
import com.frameworkset.util.ListInfo;
import com.frameworkset.util.StringUtil;

/**
 * <p>Title: SmUserServiceImpl</p> <p>Description: 用户管理管理业务处理类 </p> <p>bboss</p>
 * <p>Copyright (c) 2007</p> @Date 2016-11-29 21:14:30 @author yinbp @version
 * v1.0
 */
public class SmUserServiceImpl implements SmUserService {

	private static Logger log = Logger.getLogger(com.frameworkset.platform.admin.service.SmUserServiceImpl.class);
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
	
	public SmUser getSmUserByIDNAMECNName(String userId) throws SmUserException {
		try {
			SmUser bean = executor.queryObject(SmUser.class, "getSmUserByNAMECNName", userId,userId, userId,userId);
			return bean;
		} catch (Throwable e) {
			throw new SmUserException("get SmUser failed::userId=" + userId, e);
		}

	}
	
	public ListInfo queryListInfoSmUsers(SmUserCondition conditions, long offset, int pagesize) throws SmUserException {
		ListInfo datas = null;
		try {
			datas = executor.queryListInfoBean(SmUser.class, "queryListSmUser", offset, pagesize, conditions);
		} catch (Exception e) {
			throw new SmUserException("pagine query SmUser failed:", e);
		}
		return datas;

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
				
			String encrptoldPassword = EncrpyPwd.encodePassword(oldPassword);
			int exist = executor.queryObject(int.class, "oldPasswordright", userId,encrptoldPassword);
			if(exist ==0){
				return "旧口令不正确!";
			}
				
			String encrptnewPassword = EncrpyPwd.encodePassword(newPassword);
			executor.update("resetpassword", encrptnewPassword,userId);
			return "success";
		} catch (Exception e) {
			throw new SmUserException("reset password failed:", e);
		}
	}
}
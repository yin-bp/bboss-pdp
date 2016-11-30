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
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.frameworkset.common.poolman.ConfigSQLExecutor;
import com.frameworkset.common.poolman.handle.ResultSetNullRowHandler;
import com.frameworkset.orm.transaction.TransactionManager;
import com.frameworkset.platform.admin.entity.SmUser;
import com.frameworkset.platform.admin.entity.SmUserCondition;
import com.frameworkset.platform.admin.entity.UIUser;
import com.frameworkset.util.ListInfo;

/**
 * <p>Title: SmUserServiceImpl</p> <p>Description: 用户管理管理业务处理类 </p> <p>bboss</p>
 * <p>Copyright (c) 2007</p> @Date 2016-11-29 21:14:30 @author yinbp @version
 * v1.0
 */
public class SmUserServiceImpl implements SmUserService {

	private static Logger log = Logger.getLogger(com.frameworkset.platform.admin.service.SmUserServiceImpl.class);

	private ConfigSQLExecutor executor;
	public void addSmUser(SmUser smUser) throws SmUserException {
		// 业务组件
		try {
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
	public void deleteBatchSmUser(String... userIds) throws SmUserException {
		TransactionManager tm = new TransactionManager();
		try {
			tm.begin();
			executor.deleteByKeys("deleteByKey", userIds);
			tm.commit();
		} catch (Throwable e) {

			throw new SmUserException("batch delete SmUser failed::userIds=" + userIds, e);
		} finally {
			tm.release();
		}

	}
	public void updateSmUser(SmUser smUser) throws SmUserException {
		try {
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
	public ListInfo queryListInfoSmUsers(SmUserCondition conditions, long offset, int pagesize) throws SmUserException {
		ListInfo datas = null;
		try {
			datas = executor.queryListInfoBean(UIUser.class, "queryListSmUser", offset, pagesize, conditions);
		} catch (Exception e) {
			throw new SmUserException("pagine query SmUser failed:", e);
		}
		return datas;

	}
	
	public ListInfo getDepartUsers(SmUserCondition conditions, long offset, int pagesize) throws SmUserException {
		 
		try {
			final List<UIUser> users = new ArrayList<UIUser>();
			ListInfo datas = executor.queryListInfoBeanByNullRowHandler(new ResultSetNullRowHandler(){

				@Override
				public void handleRow( ResultSet record) throws Exception {
					UIUser rowValue = (UIUser) buildValueObject(record, UIUser.class);
					users.add(rowValue);
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
}
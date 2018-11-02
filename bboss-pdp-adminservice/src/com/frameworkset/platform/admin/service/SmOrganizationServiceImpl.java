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

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.frameworkset.common.poolman.ConfigSQLExecutor;
import com.frameworkset.orm.transaction.TransactionManager;
import com.frameworkset.platform.admin.entity.SmOrganization;
import com.frameworkset.platform.admin.entity.SmOrganizationCondition;
import com.frameworkset.platform.admin.entity.SmUser;
import com.frameworkset.util.ListInfo;
import org.slf4j.LoggerFactory;

/**
 * <p>Title: SmOrganizationServiceImpl</p> <p>Description: 机构管理管理业务处理类 </p>
 * <p>bboss</p> <p>Copyright (c) 2007</p> @Date 2016-11-28 16:33:16 @author
 * yinbp @version v1.0
 */
public class SmOrganizationServiceImpl implements SmOrganizationService {

	private static org.slf4j.Logger log = LoggerFactory
			.getLogger(com.frameworkset.platform.admin.service.SmOrganizationServiceImpl.class);

	private ConfigSQLExecutor executor;

	@Override
	public boolean existOrg(String orgId) {
		try {
			return executor.queryObject(int.class, "existOrg", orgId) > 0;
		}
		catch (Exception e){
			throw new SmOrganizationException("Exist Org Failed:"+orgId,e);
		}
	}

	@Override
	public boolean existOrgCode(String orgCode) {
		try {
			return executor.queryObject(int.class, "existOrgCode", orgCode) > 0;
		}
		catch (Exception e){
			throw new SmOrganizationException("Exist OrgCode Failed:"+orgCode,e);
		}
	}

	public void addSmOrganization(SmOrganization smOrganization) throws SmOrganizationException {
		// 业务组件
		TransactionManager tm = new TransactionManager();
		try {
			tm.begin();
			int org_sn = executor.queryObject(int.class, "selectMaxSNofdepart", smOrganization.getParentId());
			smOrganization.setOrgSn(org_sn);
			executor.insertBean("addSmOrganization", smOrganization);
			String orgtreelevel = null;
			if(smOrganization.getParentId() == null || smOrganization.getParentId().equals("0"))
				orgtreelevel = OrgTreeLevel.TREE_BASE + OrgTreeLevel.CUT_UP + smOrganization.getOrgId();
			else
			{
				orgtreelevel = executor.queryObject(String.class, "getorgtreelevel", smOrganization.getParentId());
				orgtreelevel = orgtreelevel+OrgTreeLevel.CUT_UP + smOrganization.getOrgId();
				
			}
			executor.update("updateorgtreelevel", orgtreelevel,smOrganization.getOrgId());
			tm.commit();
		} catch (Throwable e) {
			throw new SmOrganizationException("add SmOrganization failed:", e);
		} finally {
			tm.release();
		}


	}
	public void deleteSmOrganization(String orgId) throws SmOrganizationException {
		try {
			executor.delete("deleteByKey", orgId);
		} catch (Throwable e) {
			throw new SmOrganizationException("delete SmOrganization failed::orgId=" + orgId, e);
		}

	}
	public void deleteBatchSmOrganization(String... orgIds) throws SmOrganizationException {
		TransactionManager tm = new TransactionManager();
		try {
			tm.begin();
			executor.updateByKeys("moveUsertoDaigang", orgIds);
			executor.deleteByKeys("removeOrgManager", orgIds);
			executor.deleteByKeys("deleteByKey", orgIds);
			tm.commit();
		} catch (Throwable e) {

			throw new SmOrganizationException("batch delete SmOrganization failed::orgIds=" + orgIds, e);
		} finally {
			tm.release();
		}

	}
	public boolean hasSon(String org) throws SmOrganizationException{
		
		try {
			int num = this.executor.queryObject(int.class, "hasSon", org);
			return (num > 0);
		} catch (Throwable e) {
			throw new SmOrganizationException("hasSon SmOrganization failed::orgId=" + org, e);
		}
			
	}
	
	public boolean hasManager(String org) throws SmOrganizationException{
		try {
			int num = this.executor.queryObject(int.class, "hasManager", org);
			return (num > 0);
		} catch (Throwable e) {
			throw new SmOrganizationException("hasManager SmOrganization failed::orgId=" + org, e);
		}
	}
	public void updateSmOrganization(SmOrganization smOrganization) throws SmOrganizationException {
		try {
			executor.updateBean("updateSmOrganization", smOrganization);
		} catch (Throwable e) {
			throw new SmOrganizationException("update SmOrganization failed::", e);
		}

	}
	public SmOrganization getSmOrganization(String orgId) throws SmOrganizationException {
		try {
			SmOrganization bean = executor.queryObject(SmOrganization.class, "selectById", orgId);
			return bean;
		} catch (Throwable e) {
			throw new SmOrganizationException("get SmOrganization failed::orgId=" + orgId, e);
		}

	}
	public ListInfo queryListInfoSmOrganizations(SmOrganizationCondition conditions, long offset, int pagesize)
			throws SmOrganizationException {
		ListInfo datas = null;
		try {
			datas = executor.queryListInfoBean(SmOrganization.class, "queryListSmOrganization", offset, pagesize,
					conditions);
		} catch (Exception e) {
			throw new SmOrganizationException("pagine query SmOrganization failed:", e);
		}
		return datas;

	}
	public List<SmOrganization> queryListSmOrganizations(SmOrganizationCondition conditions)
			throws SmOrganizationException {
		try {
			List<SmOrganization> beans = executor.queryListBean(SmOrganization.class, "queryListSmOrganization",
					conditions);
			return beans;
		} catch (Exception e) {
			throw new SmOrganizationException("query SmOrganization failed:", e);
		}

	}
	/** (non-Javadoc)
	 * @see com.frameworkset.platform.admin.service.SmOrganizationService#getChildres(java.lang.String)
	 */
	@Override
	public List<SmOrganization> getChildren(String parent,boolean choosenormalorg) {
		if(parent.equals("#"))
		{
			parent = "0";
		}
		try {
			List<SmOrganization> beans = executor.queryList(SmOrganization.class, choosenormalorg?"selectChildren": "selectAllChildren",
					parent);
			return beans;
		} catch (Exception e) {
			throw new SmOrganizationException("query Children SmOrganization failed:", e);
		}
	}
	/** (non-Javadoc)
	 * @see com.frameworkset.platform.admin.service.SmOrganizationService#getAllOrgs()
	 */
	@Override
	public List<SmOrganization> getAllOrgs() throws SmOrganizationException {
		List<SmOrganization> beans = null;
		try {
			beans = executor.queryList(SmOrganization.class, "getAllOrgs");
		} catch (Exception e) {
			throw new SmOrganizationException("getAllOrgs failed:", e);
		}
		return beans;
	}
	
	public void buildTreeLevel() throws SmOrganizationException{
		OrgTreeLevel.run(this);
	}
	
	public String getOrgTreeLevel(String orgid) throws SmOrganizationException
	{
		try {
			return executor.queryObject(String.class, "getOrgTreeLevel",orgid);
		} catch (Exception e) {
			throw new SmOrganizationException("getAllOrgs failed:", e);
		}
		
	}
	/** (non-Javadoc)
	 * @see com.frameworkset.platform.admin.service.SmOrganizationService#getOrgmanagers(java.lang.String)
	 */
	@Override
	public List<SmUser> getOrgmanagers(String orgId) throws SmOrganizationException {
		// TODO Auto-generated method stub
		try {
			return this.executor.queryList(SmUser.class, "getOrgmanagers", orgId);
		} catch (SQLException e) {
			throw new SmOrganizationException("getOrgmanagers failed:", e);
		}
	}
	/** (non-Javadoc)
	 * @see com.frameworkset.platform.admin.service.SmOrganizationService#saveorgmanagers(java.lang.String[], java.lang.String)
	 */
	@Override
	public void saveorgmanagers(String[] userIds_, String orgId) throws SmOrganizationException {
		 TransactionManager tm = new TransactionManager();
		 try {			 
			 List params = new ArrayList();
			 for(String userId:userIds_){
				 Map param = new HashMap();
				 param.put("userId", userId);
				 param.put("orgId", orgId); 
				 params.add(param);
			 }
			 tm.begin();
			 this.executor.insertBeans("saveorgmanagers", params);
			 tm.commit();
		} catch (Exception e) {
			throw new SmOrganizationException("saveorgmanagers failed:", e);
		}
		finally{
			tm.release();
		}
		
	}
	/** (non-Javadoc)
	 * @see com.frameworkset.platform.admin.service.SmOrganizationService#existManager(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean existManager(String userId, String orgId) throws SmOrganizationException {
		 
		try {
			int num = this.executor.queryObject(int.class, "existManager", userId,orgId);
			return num > 0;
		} catch (SQLException e) {
			throw new SmOrganizationException("existManager failed:", e);
		}	
	}
	public void removeorgmanager(String[] userIds_) throws SmOrganizationException{
		TransactionManager tm = new TransactionManager();
		 try {			 
			
			 tm.begin();
			 this.executor.deleteByKeys("removeorgmanagerofusers", userIds_);
			 tm.commit();
		} catch (Exception e) {
			throw new SmOrganizationException("removeorgmanagerofusers failed:", e);
		}
		finally{
			tm.release();
		}
	}
	public void removeorgmanager(String[] userIds_, String orgId) throws SmOrganizationException{
		TransactionManager tm = new TransactionManager();
		 try {			 
			 List params = new ArrayList();
			 for(String userId:userIds_){
				 Map param = new HashMap();
				 param.put("userId", userId);
				 param.put("orgId", orgId); 
				 params.add(param);
			 }
			 tm.begin();
			 this.executor.insertBeans("removeorgmanager", params);
			 tm.commit();
		} catch (Exception e) {
			throw new SmOrganizationException("removeorgmanager failed:", e);
		}
		finally{
			tm.release();
		}
	}
	/** (non-Javadoc)
	 * @see com.frameworkset.platform.admin.service.SmOrganizationService#getOrgLeader(java.lang.String)
	 */
	@Override
	public String getOrgLeader(String org) {
		// TODO Auto-generated method stub
		return null;
	}
	/** (non-Javadoc)
	 * @see com.frameworkset.platform.admin.service.SmOrganizationService#isOrganizationManager(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean isOrganizationManager(String userAccount, String orgId) {
		// TODO Auto-generated method stub
		return false;
	}
	/** (non-Javadoc)
	 * @see com.frameworkset.platform.admin.service.SmOrganizationService#isOrgManager(java.lang.String)
	 */
	@Override
	public boolean isOrgManager(String userAccount) {
		// TODO Auto-generated method stub
		return false;
	}
	/** (non-Javadoc)
	 * @see com.frameworkset.platform.admin.service.SmOrganizationService#isSubOrgManager(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean isSubOrgManager(String userAccount, String orgId) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * 获取用户可管理的机构列表
	 * @param userId
	 * @return
	 * @throws Exception 
	 */
	public List<SmOrganization> getManagerOrgs(String userId) throws SmOrganizationException{
		try {
			return this.executor.queryList(SmOrganization.class, "getUserManagerOrgs", userId);
		}
		catch (SQLException e){
			throw new SmOrganizationException(e);
		}
	}

	public boolean existJobReleation(String userId, String orgid) throws SmOrganizationException{
		try {
			return this.executor.queryObject(int.class, "existJobReleation", userId,orgid) > 0 ;
		}
		catch (SQLException e){
			throw new SmOrganizationException(e);
		}
	}

	public void buildUserOrgRelationWithEventTrigger(String userId,String orgid) throws SmOrganizationException{
		try {
			this.executor.update("buildUserOrgRelationWithEventTrigger", orgid,userId) ;
		}
		catch (SQLException e){
			throw new SmOrganizationException(e);
		}
	}
	
	
	
}
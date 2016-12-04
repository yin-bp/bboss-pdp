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

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.frameworkset.platform.security.AccessControl;
import org.frameworkset.platform.security.authentication.EncrpyPwd;

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
	public void updateSmUser(SmUser smUser) throws SmUserException {
		try {
			if(StringUtils.isNotEmpty(smUser.getUserPassword())){
    			
				smUser.setPasswordText(smUser.getUserPassword());
				
				smUser.setUserPassword(EncrpyPwd.encodePassword(smUser.getUserPassword()));
			}else if(StringUtils.isNotEmpty(smUser.getPasswordText())){
				
				smUser.setUserPassword(EncrpyPwd.encodePassword(smUser.getPasswordText()));
				
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
	public ListInfo queryListInfoSmUsers(SmUserCondition conditions, long offset, int pagesize) throws SmUserException {
		ListInfo datas = null;
		try {
			datas = executor.queryListInfoBean(UIUser.class, "queryListSmUser", offset, pagesize, conditions);
		} catch (Exception e) {
			throw new SmUserException("pagine query SmUser failed:", e);
		}
		return datas;

	}
	/**
	 * <option value="2">开通</option>
                                                                <option value="1">申请</option>
                                                                <option value="3">停用</option>
                                                                <option value="0">删除</option>
                                                                
                                                                 array("success" => "Pending"),
    array("info" => "Closed"),
    array("danger" => "On Hold"),
    array("warning" => "Fraud")
	 * @param state
	 * @return
	 */
	private void getState(int state,StringBuilder idcheckbox)
	{
		if(state == 2)
			idcheckbox.append("<span class=\"label label-sm label-").append("success").append("\">").append("开通").append("</span>");
		else if(state == 3)
			idcheckbox.append("<span class=\"label label-sm label-").append("warning").append("\">").append("停用").append("</span>");
		else if(state == 0)
			idcheckbox.append("<span class=\"label label-sm label-").append("danger").append("\">").append("删除").append("</span>");
		else if(state == 1)
			idcheckbox.append("<span class=\"label label-sm label-").append("info").append("\">").append("申请").append("</span>");	
		else
			idcheckbox.append("<span class=\"label label-sm label-").append("warning").append("\">").append("未知").append("</span>");	 
		
	}
	
	private void getOps(String userId,StringBuilder idcheckbox)
	{
		 /**
			idcheckbox.append("<a href=\"javascript:Sysmanager.viewUser('").append(userId).append("');\" class=\"btn btn-sm btn-outline grey-salsa\"><i class=\"fa fa-search\"></i> 查看</a>");	 
			idcheckbox.append("<a href=\"javascript:Sysmanager.modifyUser('").append(userId).append("');\" class=\"btn btn-sm btn-outline grey-salsa\"><i class=\"fa fa-search\"></i> 修改</a>");
			idcheckbox.append("<a href=\"javascript:Sysmanager.delUser('").append(userId).append("');\" class=\"btn btn-sm btn-outline grey-salsa\"><i class=\"fa fa-search\"></i> 删除</a>");	
			idcheckbox.append("<a href=\"javascript:Sysmanager.stopUser('").append(userId).append("');\" class=\"btn btn-sm btn-outline grey-salsa\"><i class=\"fa fa-search\"></i> 停用</a>");
			*/
//		idcheckbox.append("<div class=\"btn-group\">")
//		.append("<a class=\"btn red btn-outline btn-circle\" href=\"javascript:;\" data-toggle=\"dropdown\">")
//		.append("    <i class=\"fa fa-share\"></i>")
//		.append("   <span class=\"hidden-xs\"> 操作 </span>")
//		.append("    <i class=\"fa fa-angle-down\"></i>")
//		.append("</a>")
//		.append("<ul class=\"dropdown-menu pull-right\">")
//		.append("    <li>")
//		.append("        <a href=\"javascript:Sysmanager.viewUser('").append(userId).append("');\"> 查看 </a>")
//		.append("   </li>")
//		.append("    <li>")
//		.append("        <a href=\"javascript:Sysmanager.modifyUser('").append(userId).append("');\"> 修改 </a>")
//		.append("    </li>")
//		.append("    <li>")
//		.append("        <a href=\"javascript:Sysmanager.delUser('").append(userId).append("');\"> 删除 </a>")
//		.append("   </li>")
//		.append("    <li class=\"divider\"> </li>")
//		.append("   <li>")
//		.append("       <a href=\"javascript:Sysmanager.stopUser('").append(userId).append("');\"> 停用 </a>")
//		.append("    </li>")
//		.append("</ul>")
//		.append("</div>");
		
		
		idcheckbox.append("<div class=\"btn-group\">")
		.append("<a class=\"btn btn-xs purple\" href=\"javascript:;\" data-toggle=\"dropdown\">")
		.append("    <i class=\"fa fa-user\"></i> 操作")
		.append("    <i class=\"fa fa-angle-down\"></i>")
		.append("</a>")
		.append("<ul class=\"dropdown-menu\">")
		.append("    <li>")
		.append("        <a href=\"javascript:;\" onclick=\"javascript:SysUser.viewUser('").append(userId).append("');\">")
		.append("            <i class=\"fa fa-bullhorn\"></i> 查看 </a>")
		.append("    </li>");
		if(!AccessControl.isDefaultAdmin(userId))
			idcheckbox.append("    <li>")
				.append("       <a href=\"javascript:;\" onclick=\"javascript:SysUser.tomodifyUser('").append(userId).append("');\">")
				.append("           <i class=\"fa fa-pencil\"></i> 修改 </a>")
				.append("    </li>")
				.append("    <li>")
				.append("       <a href=\"javascript:;\" onclick=\"javascript:SysUser.modifyUser('").append(userId).append("');\">")
				.append("           <i class=\"fa fa-pencil\"></i> 授权 </a>")
				.append("    </li>")
				.append("    <li>")
				.append("        <a href=\"javascript:;\" onclick=\"javascript:SysUser.delUser('").append(userId).append("');\">")
				.append("            <i class=\"fa fa-trash-o\"></i> 删除 </a>")
				.append("    </li>")
				.append("    <li class=\"divider\"> </li>")
				.append("    <li>")
				.append("        <a href=\"javascript:;\" onclick=\"javascript:SysUser.stopUser('").append(userId).append("');\">")
				.append("            <i class=\"fa fa-ban\"></i> 停用 </a>")
				.append("    </li>");
		idcheckbox.append("    <li>")
		.append("       <a href=\"javascript:;\" onclick=\"javascript:SysUser.resetPassword('").append(userId).append("');\">")
		.append("           <i class=\"fa fa-pencil\"></i> 重置口令 </a>")
		.append("    </li>")

		.append("</ul>")
		.append("</div>");
	}
	
	private String getSexName(String sexCode)
	{
		 
			if(sexCode == null || sexCode.equals("-1"))
				return "未知";
			else if( sexCode.equals("F"))
				return "女";
			else if(  sexCode.equals("M"))
				return "男";
			else 
				return "未知";
	}
	/**
	 *  <option value="0">系统用户</option>
                                                                <option value="1">域用户</option>
                                                                <option value="2">第三方用户</option>
	 * @param type
	 * @return
	 */
	private String getTypeName(String type)
	{
		 
			if(type == null || type.equals("0"))
				return "系统用户";
			else if(  type.equals("1"))
				return "域用户";
			else if( type.equals("2"))
				return "第三方用户";
			else 
				return "第三方用户";
	}
	
	
	
	public ListInfo getDepartUsers(SmUserCondition conditions, long offset, int pagesize) throws SmUserException {
		 
		try {
			final List<UIUser> users = new ArrayList<UIUser>();
			ListInfo datas = executor.queryListInfoBeanByNullRowHandler(new ResultSetNullRowHandler(){

				@Override
				public void handleRow( ResultSet record) throws Exception {
					UIUser rowValue = (UIUser) buildValueObject(record, UIUser.class);
					users.add(rowValue);
					StringBuilder idcheckbox = new StringBuilder();
					if(!AccessControl.isDefaultAdmin(rowValue.getUserId()))
					{
						idcheckbox.append("<label class=\"mt-checkbox mt-checkbox-single mt-checkbox-outline\"><input name=\"userId\" type=\"checkbox\" class=\"checkboxes\" value=\"")
						.append(rowValue.getUserId()).append("\"").append("/><span></span></label>");
						rowValue.setCheckbox(idcheckbox.toString());
						idcheckbox.setLength(0);
					}
					getState(rowValue.getUserIsvalid(),  idcheckbox);
					rowValue.setUserIsvalidName(idcheckbox.toString());
					idcheckbox.setLength(0);
					getOps(rowValue.getUserId(),idcheckbox);
					rowValue.setOps(idcheckbox.toString());
					rowValue.setSexName(getSexName(rowValue.getUserSex()));
					rowValue.setUserTypeName(getTypeName(rowValue.getUserType()));
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
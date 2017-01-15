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

package com.frameworkset.platform.admin.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.frameworkset.event.Event;
import org.frameworkset.event.EventHandle;
import org.frameworkset.event.EventImpl;
import org.frameworkset.platform.common.DatagridBean;
import org.frameworkset.platform.common.JSTreeNode;
import org.frameworkset.platform.common.TreeNodeStage;
import org.frameworkset.platform.security.event.ACLEventType;
import org.frameworkset.platform.util.AdminUtil;
import org.frameworkset.util.annotations.ResponseBody;
import org.frameworkset.web.servlet.ModelMap;

import com.frameworkset.orm.transaction.TransactionManager;
import com.frameworkset.platform.admin.entity.SmOrganization;
import com.frameworkset.platform.admin.entity.SmOrganizationCondition;
import com.frameworkset.platform.admin.entity.SmUser;
import com.frameworkset.platform.admin.service.RoleService;
import com.frameworkset.platform.admin.service.SmOrganizationException;
import com.frameworkset.platform.admin.service.SmOrganizationService;
import com.frameworkset.platform.admin.service.SmUserService;
import com.frameworkset.platform.admin.util.OpResult;
import com.frameworkset.util.ListInfo;
import com.frameworkset.util.StringUtil;

/**
 * <p>Title: SmOrganizationController</p> <p>Description: 机构管理管理控制器处理类 </p>
 * <p>bboss</p> <p>Copyright (c) 2007</p> @Date 2016-11-28 16:33:16 @author
 * yinbp @version v1.0
 */
public class SmOrganizationController {

	private static Logger log = Logger.getLogger(SmOrganizationController.class);

	private SmOrganizationService smOrganizationService;
	private SmUserService userService;
	private RoleService roleService;
	public @ResponseBody List<JSTreeNode> getChildrens(String parent,boolean isuser,boolean chooseuser)
	{
		if(StringUtil.isEmpty(parent))
			return null;
		boolean choosenormalorg = isuser || chooseuser;
		List<SmOrganization> orgs = smOrganizationService.getChildren(parent,  choosenormalorg);
		if(!isuser){
			if(orgs == null || orgs.size() == 0)
				return null;
		}
		
		List<JSTreeNode> treeNodes = new ArrayList<JSTreeNode>();
		for(SmOrganization org:orgs)
		{
			treeNodes.add(this.buildJSTreeNode(org));
		}
		if(parent != null && parent.equals("#"))
		{
			JSTreeNode root = new JSTreeNode();
			root.setText("组织机构");
			//root.setId("#");
			root.setChildren(treeNodes);
			TreeNodeStage state = new TreeNodeStage();
			state.setDisabled(false);
			state.setOpened(true);
			state.setSelected(false);
			root.setState(state);
			treeNodes = new ArrayList<JSTreeNode>();
			treeNodes.add(root);
			if(isuser)
			{
				JSTreeNode lisan = new JSTreeNode();
				lisan.setText("待岗用户");
				lisan.setId("lisan");
				lisan.setType("lisan");
				
				//root.setId("#");
				 state = new TreeNodeStage();
				state.setDisabled(false);
				state.setOpened(true);
				state.setSelected(false);
				lisan.setState(state);
				
				treeNodes.add(lisan);
			}
			
			return treeNodes;
		}
		else{
			
			return treeNodes;
		}
		
	}
	private JSTreeNode buildJSTreeNode(SmOrganization org)
	{
		JSTreeNode JSTreeNode = new JSTreeNode();
		JSTreeNode.setId(org.getOrgId());
//		JSTreeNode.setText(new StringBuilder().append("<a href=\"#\" onclick=\"javascript:Sysmanager.showOrgUsers('").append(org.getOrgId()).append("');\">").append(org.getOrgName()).append("</a>").toString());
		JSTreeNode.setText(org.getOrgName());
		JSTreeNode.setIcon(null);
		TreeNodeStage state = new TreeNodeStage();
		state.setDisabled(false);
		state.setOpened(false);
		state.setSelected(false);
		JSTreeNode.setState(state);
		JSTreeNode.setChildren(true);
		return JSTreeNode;
	}
	public @ResponseBody String addSmOrganization(SmOrganization smOrganization) {
		// 控制器
		try {
			smOrganizationService.addSmOrganization(smOrganization);
			return "success";
		} catch (SmOrganizationException e) {
			log.error("add SmOrganization failed:", e);
			return "部门创建失败:"+ e.getMessage();
		} catch (Throwable e) {
			log.error("add SmOrganization failed:", e);
			return "部门创建失败:"+ e.getMessage();
		}

	}
	public @ResponseBody String deleteSmOrganization(String orgId) {
		try {
			smOrganizationService.deleteSmOrganization(orgId);
			return "success";
		} catch (SmOrganizationException e) {
			log.error("delete SmOrganization failed:", e);
			return StringUtil.formatBRException(e);
		} catch (Throwable e) {
			log.error("delete SmOrganization failed:", e);
			return StringUtil.formatBRException(e);
		}

	}
	public @ResponseBody OpResult deleteBatchSmOrganization(String orgIds) {
		OpResult result = new OpResult();
		if(StringUtil.isEmpty(orgIds)){
			result.setMessage("没有选择要删除的部门");
			return result;
		}
		TransactionManager tm = new TransactionManager();
		
		try {
			tm.begin();	 
			
			String orgIds_[] = orgIds.split(",");
			StringBuilder hasnosonOrgs = new StringBuilder();
			StringBuilder hassonOrgs = new StringBuilder();
			//帅选出可以删除的部门
			int i = 0;
			boolean hasManagers = false;
			for(String org:orgIds_){
				
				if(!smOrganizationService.hasSon(org)){
					if(i == 0)
						hasnosonOrgs.append(org);
					else
						hasnosonOrgs.append(",").append(org);
					if(!hasManagers){
						if(smOrganizationService.hasManager(org))
							hasManagers = true;
					}
				}
				else
				{
					if(i == 0)
						hassonOrgs.append(org);
					else
						hassonOrgs.append(",").append(org);
				}
				i ++;
			}
			if(hasnosonOrgs.length() > 0)
				smOrganizationService.deleteBatchSmOrganization(hasnosonOrgs.toString().split(","));
			 
			result.setResult("success");
			if(hassonOrgs.length() > 0)
				result.setMessage("删除部门完毕，忽略删除的部门（有下级的部门）："+hassonOrgs.toString());
			else
				result.setMessage("删除部门完毕!");
			tm.commit();
			if(hasManagers){
				Event event = new EventImpl("",
						ACLEventType.USER_ROLE_INFO_CHANGE);
				EventHandle.sendEvent(event);
			}
			return result;
		} catch (Throwable e) {
			log.error("delete Batch orgIds failed:", e);
			result.setMessage(StringUtil.formatBRException(e));
			return result;
		}
		finally
		{
			tm.release();
		}

	}
	public @ResponseBody String updateSmOrganization(SmOrganization smOrganization) {
		try {
			smOrganizationService.updateSmOrganization(smOrganization);
			return "success";
		} catch (Throwable e) {
			log.error("update SmOrganization failed:", e);
			return StringUtil.formatBRException(e);
		}

	}
	public String getSmOrganization(String orgId, ModelMap model) throws SmOrganizationException {
		try {
			SmOrganization smOrganization = smOrganizationService.getSmOrganization(orgId);
			model.addAttribute("smOrganization", smOrganization);
			return "path:getSmOrganization";
		} catch (SmOrganizationException e) {
			throw e;
		} catch (Throwable e) {
			throw new SmOrganizationException("get SmOrganization failed::orgId=" + orgId, e);
		}

	}
	public @ResponseBody DatagridBean getDeparts(SmOrganizationCondition conditions,int draw,int start,int length,Map<String,String> datas) 
					throws SmOrganizationException {
		// Constant.component_type_actionimpl
		try {
			 
			String orgName = conditions.getOrgName();
			if (orgName != null && !orgName.equals("")) {
				conditions.setOrgName("%" + orgName + "%");
			}

			ListInfo smOrganizations = smOrganizationService.queryListInfoSmOrganizations(conditions, start, length);
			DatagridBean data = new DatagridBean();
			data.setDraw(draw);
			if(smOrganizations != null)
			{
				if(smOrganizations.getDatas() != null)
					data.setData(smOrganizations.getDatas());
				else
					data.setData(new ArrayList(0));
				data.setRecordsFiltered((int)smOrganizations.getTotalSize());
				data.setRecordsTotal((int)smOrganizations.getTotalSize());
			}
			 
			
			return data;
		} catch (SmOrganizationException e) {
			throw e;
		} catch (Exception e) {
			throw new SmOrganizationException("pagine query SmOrganization failed:", e);
		}

	}
	public String queryListSmOrganizations(SmOrganizationCondition conditions, ModelMap model)
			throws SmOrganizationException {
		try {
			String orgName = conditions.getOrgName();
			if (orgName != null && !orgName.equals("")) {
				conditions.setOrgName("%" + orgName + "%");
			}
			List<SmOrganization> smOrganizations = smOrganizationService.queryListSmOrganizations(conditions);
			model.addAttribute("smOrganizations", smOrganizations);
			return "path:queryListSmOrganizations";
		} catch (SmOrganizationException e) {
			throw e;
		} catch (Exception e) {
			throw new SmOrganizationException("query SmOrganization failed:", e);
		}

	}
	public String toUpdateSmOrganization(String orgId, ModelMap model) throws SmOrganizationException {
		try {
			SmOrganization smOrganization = smOrganizationService.getSmOrganization(orgId);
			model.addAttribute("smOrganization", smOrganization);
			return "path:updateSmOrganization";
		} catch (SmOrganizationException e) {
			throw e;
		} catch (Throwable e) {
			throw new SmOrganizationException("get SmOrganization failed::orgId=" + orgId, e);
		}

	}
	public String toAddSmOrganization(String departId,ModelMap model) {
		if(departId == null || departId.equals(""))
			departId = "0";
		model.addAttribute("departId", departId);
		return "path:addSmOrganization";
	}
	public String index() {
		return "path:index";

	}
	public @ResponseBody String buildTreeLevel() throws SmOrganizationException{
		this.smOrganizationService.buildTreeLevel();
		return "success";
	}
	public String orgmanagerset(String orgId,ModelMap model){
		model.addAttribute("orgId", orgId);
		return "path:orgmanagerset";
	}
	public String orgmanagerlist(String orgId,ModelMap model){
		List<SmUser> orgmanagers = this.smOrganizationService.getOrgmanagers(orgId);
		model.addAttribute("orgmanagers", orgmanagers);
		return "path:orgmanagerlist";
	}
	public @ResponseBody String saveorgmanagers(String userIds,String orgId){
		String[] userIds_ = userIds.split(",");
		if(StringUtil.isEmpty(userIds) )
			return "没有选择部门管理员";
		if(StringUtil.isEmpty(orgId) )
			return "没有选择部门";
		StringBuilder users = new StringBuilder();
		for(int i = 0; i < userIds_.length;i ++){
			if(smOrganizationService.existManager(userIds_[i],orgId))
				continue;
			if(users.length() == 0)
				users.append(userIds_[i]);
			else
				users.append(",").append(userIds_[i]);
			
		}
		if(users.length() > 0){
			String users_ = users.toString();
			TransactionManager tm = new TransactionManager();
			try{
				tm.begin();
				this.smOrganizationService.saveorgmanagers(  users_.split(","),  orgId);
				userService.saveRoleUsers(users_, roleService.getRoleByName(AdminUtil.role_orgmanager).getRoleId(),false);
				userService.saveRoleUsers(users_, roleService.getRoleByName(AdminUtil.role_orgmanagerroletemplate).getRoleId(),false);
				tm.commit();
				Event event = new EventImpl("",
						ACLEventType.USER_ROLE_INFO_CHANGE);
				EventHandle.sendEvent(event);
			} catch (Exception e) {
				log.debug("设置部门管理失败：",e);
				return "设置部门管理失败："+e.getMessage();
			}
			finally{
				tm.release();
			}			
		}
		return "success";
	}
	public @ResponseBody String removeorgmanager(String userIds,String orgId){
		String[] userIds_ = userIds.split(",");
		if(StringUtil.isEmpty(userIds) )
			return "没有选择部门管理员";
		if(StringUtil.isEmpty(orgId) )
			return "没有选择部门";
		
		TransactionManager tm = new TransactionManager();
		try{
			tm.begin();
		 	userService.deleteRoleUsers(roleService.getRoleByName(AdminUtil.role_orgmanager).getRoleId(),userIds,false);
			userService.deleteRoleUsers( roleService.getRoleByName(AdminUtil.role_orgmanagerroletemplate).getRoleId(),userIds,false);
			this.smOrganizationService.removeorgmanager( userIds_,  orgId);
			tm.commit();
			Event event = new EventImpl("",
					ACLEventType.USER_ROLE_INFO_CHANGE);
			EventHandle.sendEvent(event);
		} catch (Exception e) {
			log.debug("移除部门管理失败：",e);
			return "移除部门管理失败："+e.getMessage();
		}
		finally{
			tm.release();
		}
		 
		return "success";
	}
	  
}
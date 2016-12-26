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

import org.apache.log4j.Logger;
import org.frameworkset.platform.config.ResourceInfoQueue;
import org.frameworkset.platform.config.model.OperationQueue;
import org.frameworkset.platform.config.model.ResourceInfo;
import org.frameworkset.platform.resource.ResourceManager;
import org.frameworkset.platform.security.AccessControl;
import org.frameworkset.util.annotations.PagerParam;
import org.frameworkset.util.annotations.ResponseBody;
import org.frameworkset.web.servlet.ModelMap;

import com.frameworkset.platform.admin.entity.Resource;
import com.frameworkset.platform.admin.entity.Role;
import com.frameworkset.platform.admin.entity.RoleCondition;
import com.frameworkset.platform.admin.entity.RoleType;
import com.frameworkset.platform.admin.service.ResourceService;
import com.frameworkset.platform.admin.service.RoleException;
import com.frameworkset.platform.admin.service.RoleService;
import com.frameworkset.platform.admin.service.RoleTypeService;
import com.frameworkset.util.ListInfo;
import com.frameworkset.util.StringUtil;

/**
 * <p>Title: RoleController</p> <p>Description: 角色管理管理控制器处理类 </p> <p>bboss</p>
 * <p>Copyright (c) 2007</p> @Date 2016-12-15 17:06:09 @author yinbp @version
 * v1.0
 */
public class RoleController {

	private static Logger log = Logger.getLogger(RoleController.class);
	private RoleTypeService roleTypeService;
	private ResourceService resourceService;
	private ResourceManager resourceManager = new ResourceManager();
	private RoleService roleService;
	public @ResponseBody String addRole(Role role) {
		// 控制器
		try {
			roleService.addRole(role);
			return "success";
		} catch (RoleException e) {
			log.error("add Role failed:", e);
			return StringUtil.formatBRException(e);
		} catch (Throwable e) {
			log.error("add Role failed:", e);
			return StringUtil.formatBRException(e);
		}

	}
	public @ResponseBody String deleteRole(String roleId) {
		try {
			roleService.deleteRole(roleId);
			return "success";
		} catch (RoleException e) {
			log.error("delete Role failed:", e);
			return StringUtil.formatBRException(e);
		} catch (Throwable e) {
			log.error("delete Role failed:", e);
			return StringUtil.formatBRException(e);
		}

	}
	public @ResponseBody String deleteBatchRole(String roleIds) {
		try {
			if(roleIds != null && !roleIds.equals("")){
				String[] rs = roleIds.split(",");
				roleService.deleteBatchRole(rs);
			}
				
			return "success";
		} catch (Throwable e) {
			log.error("delete Batch roleIds failed:", e);
			return StringUtil.formatBRException(e);
		}

	}
	public @ResponseBody String updateRole(Role role) {
		try {
			roleService.updateRole(role);
			return "success";
		} catch (Throwable e) {
			log.error("update Role failed:", e);
			return StringUtil.formatBRException(e);
		}

	}
	public String getRole(String roleId, ModelMap model) throws RoleException {
		try {
			Role role = roleService.getRole(roleId);
			model.addAttribute("role", role);
			return "path:getRole";
		} catch (RoleException e) {
			throw e;
		} catch (Throwable e) {
			throw new RoleException("get Role failed::roleId=" + roleId, e);
		}

	}
	
	public String toroleauthset(String roleId,
			String  roleName,
			String  roleType ,
			String roleCName,ModelMap model){
		String curSystem = AccessControl.getAccessControl().getCurrentSystemID();
		ResourceInfoQueue  resQueue = resourceManager.getResourceInfoQueue();
		List<ResourceInfo> resourceTypes = new ArrayList<ResourceInfo>();
		 for (int i = 0; resQueue != null && i < resQueue.size(); i++) {
             ResourceInfo res = resQueue.getResourceInfo(i);
//             System.out.println("res.isAuto() = " + res.isAuto());
             //判断当前系统模块；
             if (res.isUsed() && res.containSystem(curSystem) ) {
            	 resourceTypes.add(res);
               
             }
         }
		model.addAttribute("resourceTypes", resourceTypes);
		if(resourceTypes.size() > 0)
		{
			model.addAttribute("resourceType", resourceTypes.get(0).getId());
			model.addAttribute("resourceName", resourceTypes.get(0).getName());
		}
		model.addAttribute("roleId", roleId);
		model.addAttribute("roleName", roleName);
		model.addAttribute("roleType", roleType);
		model.addAttribute("roleCName", roleCName);
		
		return "path:toroleauthset";
	}
	
	public String loadResourceOperations(String resourceType,String roleId,String roleType,ModelMap model){
		if(StringUtil.isEmpty(resourceType))
		{
			model.addAttribute("errorMessage", "没有选择资源类型");
			return "path:loadResourceOperations";
		}
			
		
		ResourceInfo resourceInfo = resourceManager.getResourceInfoByType(resourceType);
		if(resourceInfo != null){
			model.addAttribute("resourceInfo", resourceInfo);
			model.addAttribute("resourceType", resourceInfo.getId());
			model.addAttribute("resourceName", resourceInfo.getName());
			model.addAttribute("roleId", roleId);
			model.addAttribute("roleType", roleType);
			boolean maintaindata = !resourceInfo.isAuto() && resourceInfo.maintaindata();
			model.addAttribute("maintaindata", maintaindata);
			if(maintaindata){
				List<Resource> resources = resourceService.queryListResources(resourceType);
				model.addAttribute("resources", resources);
			}
			if(resourceInfo.getGlobalresourceid() != null && !resourceInfo.getGlobalresourceid().equals("")){
				model.addAttribute("hasGlobalresource",true);
				model.addAttribute("globalResourceid",resourceInfo.getGlobalresourceid());
				OperationQueue goperationQueue = resourceInfo.getGlobalOperationQueue();
				if(goperationQueue != null && goperationQueue.size() > 0)
				{
					model.addAttribute("globalOperationQueue",goperationQueue.getList());
				}
			}
			OperationQueue operationQueue = resourceInfo.getOperationQueue();
			if(operationQueue != null && operationQueue.size() > 0)
			{
				model.addAttribute("operationQueue",operationQueue.getList());
			}
			
		}
		return "path:loadResourceOperations";
	}
	
	public String queryListInfoRoles(RoleCondition conditions,
			@PagerParam(name = PagerParam.SORT, defaultvalue = "ROLE_TYPE") String sortKey,
			@PagerParam(name = PagerParam.DESC, defaultvalue = "false") boolean desc,
			@PagerParam(name = PagerParam.OFFSET) long offset,
			@PagerParam(name = PagerParam.PAGE_SIZE, defaultvalue = "10") int pagesize, ModelMap model)
					throws RoleException {
		// Constant.component_type_actionimpl
		try {
			if (sortKey != null && !sortKey.equals("")) {
				conditions.setSortKey(sortKey);
				conditions.setSortDesc(desc);
			}
			String roleAttr = conditions.getRoleAttr();
			if (roleAttr != null && !roleAttr.equals("")) {
				conditions.setRoleAttr("%" + roleAttr + "%");
			}

			ListInfo roles = roleService.queryListInfoRoles(conditions, offset, pagesize);
			
			if(!conditions.isFromAuthmain()){
				model.addAttribute("roles", roles);
				return "path:queryListInfoRoles";
			}
			else
			{
				List<Role> temp = roles.getDatas();//过滤当前用户没有设置权限的角色
				List<Role> rolesetPermissions = new ArrayList<Role>();
				if(temp.size() > 0){
					AccessControl control = AccessControl.getAccessControl();
					for(Role role : temp){
						if(control.checkPermission(role.getRoleName(), "roleset", "role"))
							rolesetPermissions.add(role);
					}
				}
				roles.setDatas(rolesetPermissions);
				model.addAttribute("roles", roles);
				return "path:authmainRoles";
			}
		} catch (RoleException e) {
			throw e;
		} catch (Exception e) {
			throw new RoleException("pagine query Role failed:", e);
		}

	}
	public String queryListRoles(RoleCondition conditions, ModelMap model) throws RoleException {
		try {
			String roleAttr = conditions.getRoleAttr();
			if (roleAttr != null && !roleAttr.equals("")) {
				conditions.setRoleAttr("%" + roleAttr + "%");
			}
			List<Role> roles = roleService.queryListRoles(conditions);
			model.addAttribute("roles", roles);
			return "path:queryListRoles";
			
		} catch (RoleException e) {
			throw e;
		} catch (Exception e) {
			throw new RoleException("query Role failed:", e);
		}

	}
	
	
	public String toUpdateRole(String roleId, ModelMap model) throws RoleException {
		try {
			Role role = roleService.getRole(roleId);
			List<RoleType> roleTypes = roleTypeService.queryListRoleTypes(null);
			model.addAttribute("roleTypes", roleTypes);
			model.addAttribute("role", role);
			return "path:updateRole";
		} catch (RoleException e) {
			throw e;
		} catch (Throwable e) {
			throw new RoleException("get Role failed::roleId=" + roleId, e);
		}

	}
	public String toAddRole(ModelMap model) {
		List<RoleType> roleTypes = roleTypeService.queryListRoleTypes(null);
		model.addAttribute("roleTypes", roleTypes);
		return "path:addRole";
	}
	public String index(ModelMap model) {
		List<RoleType> roleTypes = roleTypeService.queryListRoleTypes(null);
		model.addAttribute("roleTypes", roleTypes);
		return "path:index";

	}
}
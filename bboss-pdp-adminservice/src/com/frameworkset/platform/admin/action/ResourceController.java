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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.frameworkset.platform.config.ResourceInfoQueue;
import org.frameworkset.platform.config.model.ResourceInfo;
import org.frameworkset.platform.resource.ResourceManager;
import org.frameworkset.platform.security.AccessControl;
import org.frameworkset.util.annotations.PagerParam;
import org.frameworkset.util.annotations.ResponseBody;
import org.frameworkset.web.servlet.ModelMap;

import com.frameworkset.platform.admin.entity.Resource;
import com.frameworkset.platform.admin.entity.ResourceCondition;
import com.frameworkset.platform.admin.service.ResourceException;
import com.frameworkset.platform.admin.service.ResourceService;
import com.frameworkset.util.ListInfo;
import com.frameworkset.util.StringUtil;

/**
 * <p>Title: ResourceController</p> <p>Description: 资源管理管理控制器处理类 </p>
 * <p>bboss</p> <p>Copyright (c) 2007</p> @Date 2016-12-21 00:46:37 @author
 * yinbp @version v1.0
 */
public class ResourceController {

	private static Logger log = Logger.getLogger(ResourceController.class);

	private ResourceService resourceService;
	public String loadResourceMaintain(String resourceType){
		
		return "path:loadResourceMaintain";
	}
	public @ResponseBody String addResource(Resource resource) {
		// 控制器
		try {
			resourceService.addResource(resource);
			return "success";
		} catch (ResourceException e) {
			log.error("add Resource failed:", e);
			return StringUtil.formatBRException(e);
		} catch (Throwable e) {
			log.error("add Resource failed:", e);
			return StringUtil.formatBRException(e);
		}

	}
	public @ResponseBody String deleteResource(String resId) {
		try {
			resourceService.deleteResource(resId);
			return "success";
		} catch (ResourceException e) {
			log.error("delete Resource failed:", e);
			return StringUtil.formatBRException(e);
		} catch (Throwable e) {
			log.error("delete Resource failed:", e);
			return StringUtil.formatBRException(e);
		}

	}
	public @ResponseBody String deleteBatchResource(String... resIds) {
		try {
			resourceService.deleteBatchResource(resIds);
			return "success";
		} catch (Throwable e) {
			log.error("delete Batch resIds failed:", e);
			return StringUtil.formatBRException(e);
		}

	}
	public @ResponseBody String updateResource(Resource resource) {
		try {
			resourceService.updateResource(resource);
			return "success";
		} catch (Throwable e) {
			log.error("update Resource failed:", e);
			return StringUtil.formatBRException(e);
		}

	}
	public String getResource(String resId, ModelMap model) throws ResourceException {
		try {
			Resource resource = resourceService.getResource(resId);
			model.addAttribute("resource", resource);
			return "path:getResource";
		} catch (ResourceException e) {
			throw e;
		} catch (Throwable e) {
			throw new ResourceException("get Resource failed::resId=" + resId, e);
		}

	}
	public String queryListInfoResources(ResourceCondition conditions,
			@PagerParam(name = PagerParam.OFFSET) long offset,
			@PagerParam(name = PagerParam.PAGE_SIZE, defaultvalue = "10") int pagesize, ModelMap model)
					throws ResourceException {
		// Constant.component_type_actionimpl
		try {
			String resCode = conditions.getResCode();
			if (resCode != null && !resCode.equals("")) {
				conditions.setResCode("%" + resCode + "%");
			}
			String resName = conditions.getResName();
			if (resName != null && !resName.equals("")) {
				conditions.setResName("%" + resName + "%");
			}

			ListInfo resources = resourceService.queryListInfoResources(conditions, offset, pagesize);
			model.addAttribute("resources", resources);
			return "path:queryListInfoResources";
		} catch (ResourceException e) {
			throw e;
		} catch (Exception e) {
			throw new ResourceException("pagine query Resource failed:", e);
		}

	}
	public String queryListResources(ResourceCondition conditions, ModelMap model) throws ResourceException {
		try {
			String resCode = conditions.getResCode();
			if (resCode != null && !resCode.equals("")) {
				conditions.setResCode("%" + resCode + "%");
			}
			String resName = conditions.getResName();
			if (resName != null && !resName.equals("")) {
				conditions.setResName("%" + resName + "%");
			}
			List<Resource> resources = resourceService.queryListResources(conditions);
			model.addAttribute("resources", resources);
			return "path:queryListResources";
		} catch (ResourceException e) {
			throw e;
		} catch (Exception e) {
			throw new ResourceException("query Resource failed:", e);
		}

	}
	public String toUpdateResource(String resId, ModelMap model) throws ResourceException {
		try {
			Resource resource = resourceService.getResource(resId);
			model.addAttribute("resource", resource);
			return "path:updateResource";
		} catch (ResourceException e) {
			throw e;
		} catch (Throwable e) {
			throw new ResourceException("get Resource failed::resId=" + resId, e);
		}

	}
	public String toAddResource() {
		return "path:addResource";
	}
	public String index(ModelMap model) {
		ResourceManager resourceManager = new ResourceManager();
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
			model.addAttribute("resourceType", resourceTypes.get(0).getId());
		return "path:index";

	}
}
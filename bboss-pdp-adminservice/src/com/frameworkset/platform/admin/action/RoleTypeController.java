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

import com.frameworkset.platform.admin.entity.*;
import com.frameworkset.util.ListInfo;
import org.apache.log4j.Logger;
import java.util.List;
import java.util.Map;
import com.frameworkset.util.StringUtil;
import com.frameworkset.platform.admin.service.*;
import org.frameworkset.util.annotations.ResponseBody;
import org.frameworkset.web.servlet.ModelMap;
import org.frameworkset.util.annotations.PagerParam;
import org.frameworkset.util.annotations.MapKey;

/**
 * <p>Title: RoleTypeController</p> <p>Description: 角色类型管理管理控制器处理类 </p>
 * <p>bboss</p> <p>Copyright (c) 2007</p> @Date 2016-12-15 23:37:09 @author
 * yinbp @version v1.0
 */
public class RoleTypeController {

	private static Logger log = Logger.getLogger(RoleTypeController.class);

	private RoleTypeService roleTypeService;
	public @ResponseBody String addRoleType(RoleType roleType) {
		// 控制器
		try {
			roleTypeService.addRoleType(roleType);
			return "success";
		} catch (RoleTypeException e) {
			log.error("add RoleType failed:", e);
			return StringUtil.formatBRException(e);
		} catch (Throwable e) {
			log.error("add RoleType failed:", e);
			return StringUtil.formatBRException(e);
		}

	}
	public @ResponseBody String deleteRoleType(String typeId) {
		try {
			roleTypeService.deleteRoleType(typeId);
			return "success";
		} catch (RoleTypeException e) {
			log.error("delete RoleType failed:", e);
			return StringUtil.formatBRException(e);
		} catch (Throwable e) {
			log.error("delete RoleType failed:", e);
			return StringUtil.formatBRException(e);
		}

	}
	public @ResponseBody String deleteBatchRoleType(String... typeIds) {
		try {
			roleTypeService.deleteBatchRoleType(typeIds);
			return "success";
		} catch (Throwable e) {
			log.error("delete Batch typeIds failed:", e);
			return StringUtil.formatBRException(e);
		}

	}
	public @ResponseBody String updateRoleType(RoleType roleType) {
		try {
			roleTypeService.updateRoleType(roleType);
			return "success";
		} catch (Throwable e) {
			log.error("update RoleType failed:", e);
			return StringUtil.formatBRException(e);
		}

	}
	public String getRoleType(String typeId, ModelMap model) throws RoleTypeException {
		try {
			RoleType roleType = roleTypeService.getRoleType(typeId);
			model.addAttribute("roleType", roleType);
			return "path:getRoleType";
		} catch (RoleTypeException e) {
			throw e;
		} catch (Throwable e) {
			throw new RoleTypeException("get RoleType failed::typeId=" + typeId, e);
		}

	}
	public String queryListInfoRoleTypes(RoleTypeCondition conditions,
			@PagerParam(name = PagerParam.OFFSET) long offset,
			@PagerParam(name = PagerParam.PAGE_SIZE, defaultvalue = "10") int pagesize, ModelMap model)
					throws RoleTypeException {
		// Constant.component_type_actionimpl
		try {

			ListInfo roleTypes = roleTypeService.queryListInfoRoleTypes(conditions, offset, pagesize);
			model.addAttribute("roleTypes", roleTypes);
			return "path:queryListInfoRoleTypes";
		} catch (RoleTypeException e) {
			throw e;
		} catch (Exception e) {
			throw new RoleTypeException("pagine query RoleType failed:", e);
		}

	}
	public String queryListRoleTypes(RoleTypeCondition conditions, ModelMap model) throws RoleTypeException {
		try {
			List<RoleType> roleTypes = roleTypeService.queryListRoleTypes(conditions);
			model.addAttribute("roleTypes", roleTypes);
			return "path:queryListRoleTypes";
		} catch (RoleTypeException e) {
			throw e;
		} catch (Exception e) {
			throw new RoleTypeException("query RoleType failed:", e);
		}

	}
	public String toUpdateRoleType(String typeId, ModelMap model) throws RoleTypeException {
		try {
			RoleType roleType = roleTypeService.getRoleType(typeId);
			model.addAttribute("roleType", roleType);
			return "path:updateRoleType";
		} catch (RoleTypeException e) {
			throw e;
		} catch (Throwable e) {
			throw new RoleTypeException("get RoleType failed::typeId=" + typeId, e);
		}

	}
	public String toAddRoleType() {
		return "path:addRoleType";
	}
	public String index() {
		return "path:index";

	}
}
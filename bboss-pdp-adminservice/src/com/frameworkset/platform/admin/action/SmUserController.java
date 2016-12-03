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
import org.frameworkset.platform.common.DatagridBean;
import org.frameworkset.util.annotations.PagerParam;
import org.frameworkset.util.annotations.ResponseBody;
import org.frameworkset.web.servlet.ModelMap;

import com.frameworkset.platform.admin.entity.SmUser;
import com.frameworkset.platform.admin.entity.SmUserCondition;
import com.frameworkset.platform.admin.service.SmUserException;
import com.frameworkset.platform.admin.service.SmUserService;
import com.frameworkset.util.ListInfo;
import com.frameworkset.util.StringUtil;

/**
 * <p>Title: SmUserController</p> <p>Description: 用户管理管理控制器处理类 </p> <p>bboss</p>
 * <p>Copyright (c) 2007</p> @Date 2016-11-29 21:14:30 @author yinbp @version
 * v1.0
 */
public class SmUserController {

	private static Logger log = Logger.getLogger(SmUserController.class);

	private SmUserService smUserService;
	public @ResponseBody String addSmUser(SmUser smUser) {
		// 控制器
		try {
			smUserService.addSmUser(smUser);
			return "success";
		} catch (SmUserException e) {
			log.error("add SmUser failed:", e);
			return StringUtil.formatBRException(e);
		} catch (Throwable e) {
			log.error("add SmUser failed:", e);
			return StringUtil.formatBRException(e);
		}

	}
	public @ResponseBody String deleteSmUser(String userId) {
		try {
			smUserService.deleteSmUser(userId);
			return "success";
		} catch (SmUserException e) {
			log.error("delete SmUser failed:", e);
			return StringUtil.formatBRException(e);
		} catch (Throwable e) {
			log.error("delete SmUser failed:", e);
			return StringUtil.formatBRException(e);
		}

	}
	public @ResponseBody String deleteBatchSmUser(String... userIds) {
		try {
			smUserService.deleteBatchSmUser(userIds);
			return "success";
		} catch (Throwable e) {
			log.error("delete Batch userIds failed:", e);
			return StringUtil.formatBRException(e);
		}

	}
	public @ResponseBody String updateSmUser(SmUser smUser) {
		try {
			smUserService.updateSmUser(smUser);
			return "success";
		} catch (Throwable e) {
			log.error("update SmUser failed:", e);
			return StringUtil.formatBRException(e);
		}

	}
	public String getSmUser(String userId, ModelMap model) throws SmUserException {
		try {
			SmUser smUser = smUserService.getSmUser(userId);
			model.addAttribute("smUser", smUser);
			return "path:getSmUser";
		} catch (SmUserException e) {
			throw e;
		} catch (Throwable e) {
			throw new SmUserException("get SmUser failed::userId=" + userId, e);
		}

	}
	public String queryListInfoSmUsers(SmUserCondition conditions,
			@PagerParam(name = PagerParam.SORT, defaultvalue = "USER_NAME") String sortKey,
			@PagerParam(name = PagerParam.DESC, defaultvalue = "false") boolean desc,
			@PagerParam(name = PagerParam.OFFSET) long offset,
			@PagerParam(name = PagerParam.PAGE_SIZE, defaultvalue = "10") int pagesize, ModelMap model)
					throws SmUserException {
		// Constant.component_type_actionimpl
		try {
			if (sortKey != null && !sortKey.equals("")) {
				conditions.setSortKey(sortKey);
				conditions.setSortDesc(desc);
			}
			String userMobiletel1 = conditions.getUserMobiletel1();
			if (userMobiletel1 != null && !userMobiletel1.equals("")) {
				conditions.setUserMobiletel1("%" + userMobiletel1 + "%");
			}
			String userName = conditions.getUserName();
			if (userName != null && !userName.equals("")) {
				conditions.setUserName("%" + userName + "%");
			}
			String userRealname = conditions.getUserRealname();
			if (userRealname != null && !userRealname.equals("")) {
				conditions.setUserRealname("%" + userRealname + "%");
			}
			String userWorknumber = conditions.getUserWorknumber();
			if (userWorknumber != null && !userWorknumber.equals("")) {
				conditions.setUserWorknumber("%" + userWorknumber + "%");
			}

			ListInfo smUsers = smUserService.queryListInfoSmUsers(conditions, offset, pagesize);
			model.addAttribute("smUsers", smUsers);
			return "path:queryListInfoSmUsers";
		} catch (SmUserException e) {
			throw e;
		} catch (Exception e) {
			throw new SmUserException("pagine query SmUser failed:", e);
		}

	}
	
	public @ResponseBody DatagridBean getDepartUsers(SmUserCondition conditions,
			int draw,int start,int length,Map<String,String> datas,ModelMap model)
					throws SmUserException {
		// Constant.component_type_actionimpl
		try {
//			if (sortKey != null && !sortKey.equals("")) {
//				conditions.setSortKey(sortKey);
//				conditions.setSortDesc(desc);
//			}
			String userMobiletel1 = conditions.getUserMobiletel1();
			if (userMobiletel1 != null && !userMobiletel1.equals("")) {
				conditions.setUserMobiletel1("%" + userMobiletel1 + "%");
			}
			String userName = conditions.getUserName();
			if (userName != null && !userName.equals("")) {
				conditions.setUserName("%" + userName + "%");
			}
			String userRealname = conditions.getUserRealname();
			if (userRealname != null && !userRealname.equals("")) {
				conditions.setUserRealname("%" + userRealname + "%");
			}
			String userWorknumber = conditions.getUserWorknumber();
			if (userWorknumber != null && !userWorknumber.equals("")) {
				conditions.setUserWorknumber("%" + userWorknumber + "%");
			}
//
			ListInfo smUsers = smUserService.getDepartUsers(conditions, start , length);
			DatagridBean data = new DatagridBean();
			data.setDraw(draw);
			if(smUsers != null)
			{
				if(smUsers.getDatas() != null)
					data.setData(smUsers.getDatas());
				else
					data.setData(new ArrayList());
				data.setRecordsFiltered((int)smUsers.getTotalSize());
				data.setRecordsTotal((int)smUsers.getTotalSize());
			}
			if(StringUtil.isNotEmpty(conditions.getDepartId()))
			{
				model.addAttribute("departId", conditions.getDepartId());
			}
			
			return data;
		} catch (SmUserException e) {
			throw e;
		} catch (Exception e) {
			throw new SmUserException("pagine query SmUser failed:", e);
		}

	}
	 
	public String queryListSmUsers(SmUserCondition conditions, ModelMap model) throws SmUserException {
		try {
			String userMobiletel1 = conditions.getUserMobiletel1();
			if (userMobiletel1 != null && !userMobiletel1.equals("")) {
				conditions.setUserMobiletel1("%" + userMobiletel1 + "%");
			}
			String userName = conditions.getUserName();
			if (userName != null && !userName.equals("")) {
				conditions.setUserName("%" + userName + "%");
			}
			String userRealname = conditions.getUserRealname();
			if (userRealname != null && !userRealname.equals("")) {
				conditions.setUserRealname("%" + userRealname + "%");
			}
			String userWorknumber = conditions.getUserWorknumber();
			if (userWorknumber != null && !userWorknumber.equals("")) {
				conditions.setUserWorknumber("%" + userWorknumber + "%");
			}
			List<SmUser> smUsers = smUserService.queryListSmUsers(conditions);
			model.addAttribute("smUsers", smUsers);
			return "path:queryListSmUsers";
		} catch (SmUserException e) {
			throw e;
		} catch (Exception e) {
			throw new SmUserException("query SmUser failed:", e);
		}

	}
	public String toUpdateSmUser(String userId, ModelMap model) throws SmUserException {
		try {
			SmUser smUser = smUserService.getSmUser(userId);
			model.addAttribute("smUser", smUser);
			return "path:updateSmUser";
		} catch (SmUserException e) {
			throw e;
		} catch (Throwable e) {
			throw new SmUserException("get SmUser failed::userId=" + userId, e);
		}

	}
	public String toAddSmUser(String departId,ModelMap model) {
		model.addAttribute("departId",departId);
		return "path:addSmUser";
	}
	public String index() {
		return "path:index";

	}
}
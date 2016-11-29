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
import org.frameworkset.platform.common.JSTreeNode;
import org.frameworkset.platform.common.TreeNodeStage;
import org.frameworkset.util.annotations.PagerParam;
import org.frameworkset.util.annotations.ResponseBody;
import org.frameworkset.web.servlet.ModelMap;

import com.frameworkset.platform.admin.entity.SmOrganization;
import com.frameworkset.platform.admin.entity.SmOrganizationCondition;
import com.frameworkset.platform.admin.service.SmOrganizationException;
import com.frameworkset.platform.admin.service.SmOrganizationService;
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
	public @ResponseBody List<JSTreeNode> getChildrens(String parent)
	{
		if(StringUtil.isEmpty(parent))
			return null;
		List<SmOrganization> orgs = smOrganizationService.getChildren(parent);
		
		if(orgs == null || orgs.size() == 0)
			return null;
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
			return StringUtil.formatBRException(e);
		} catch (Throwable e) {
			log.error("add SmOrganization failed:", e);
			return StringUtil.formatBRException(e);
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
	public @ResponseBody String deleteBatchSmOrganization(String... orgIds) {
		try {
			smOrganizationService.deleteBatchSmOrganization(orgIds);
			return "success";
		} catch (Throwable e) {
			log.error("delete Batch orgIds failed:", e);
			return StringUtil.formatBRException(e);
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
	public String queryListInfoSmOrganizations(SmOrganizationCondition conditions,
			@PagerParam(name = PagerParam.SORT, defaultvalue = "ORG_TREE_LEVEL") String sortKey,
			@PagerParam(name = PagerParam.DESC, defaultvalue = "false") boolean desc,
			@PagerParam(name = PagerParam.OFFSET) long offset,
			@PagerParam(name = PagerParam.PAGE_SIZE, defaultvalue = "10") int pagesize, ModelMap model)
					throws SmOrganizationException {
		// Constant.component_type_actionimpl
		try {
			if (sortKey != null && !sortKey.equals("")) {
				conditions.setSortKey(sortKey);
				conditions.setSortDesc(desc);
			}
			String orgName = conditions.getOrgName();
			if (orgName != null && !orgName.equals("")) {
				conditions.setOrgName("%" + orgName + "%");
			}

			ListInfo smOrganizations = smOrganizationService.queryListInfoSmOrganizations(conditions, offset, pagesize);
			model.addAttribute("smOrganizations", smOrganizations);
			return "path:queryListInfoSmOrganizations";
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
	public String toAddSmOrganization() {
		return "path:addSmOrganization";
	}
	public String index() {
		return "path:index";

	}
}
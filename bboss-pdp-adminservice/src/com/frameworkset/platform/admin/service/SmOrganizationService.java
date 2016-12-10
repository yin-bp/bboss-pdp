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

import com.frameworkset.platform.admin.entity.*;
import com.frameworkset.util.ListInfo;
import java.util.List;

/**
 * <p>Title: SmOrganizationService</p> <p>Description: 机构管理管理服务接口 </p>
 * <p>bboss</p> <p>Copyright (c) 2015</p> @Date 2016-11-28 16:33:16 @author
 * yinbp @version v1.0
 */
public interface SmOrganizationService {
	public void addSmOrganization(SmOrganization smOrganization) throws SmOrganizationException;
	public void deleteSmOrganization(String orgId) throws SmOrganizationException;
	public void deleteBatchSmOrganization(String... orgIds) throws SmOrganizationException;
	public void updateSmOrganization(SmOrganization smOrganization) throws SmOrganizationException;
	public SmOrganization getSmOrganization(String orgId) throws SmOrganizationException;
	public ListInfo queryListInfoSmOrganizations(SmOrganizationCondition conditions, long offset, int pagesize)
			throws SmOrganizationException;
	public List<SmOrganization> queryListSmOrganizations(SmOrganizationCondition conditions)
			throws SmOrganizationException;
	/**
	 * @param parent
	 * @return
	 */
	public List<SmOrganization> getChildren(String parent,boolean choosenormalorg)throws SmOrganizationException;
	/**
	 * @return
	 */
	public List<SmOrganization> getAllOrgs() throws SmOrganizationException;
	public void buildTreeLevel() throws SmOrganizationException;
	public String getOrgTreeLevel(String orgid) throws SmOrganizationException;
}
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
 * <p>Title: ResourceService</p> <p>Description: 资源管理管理服务接口 </p> <p>bboss</p>
 * <p>Copyright (c) 2015</p> @Date 2016-12-21 00:46:37 @author yinbp @version
 * v1.0
 */
public interface ResourceService {
	public void addResource(Resource resource) throws ResourceException;
	public void deleteResource(String resId) throws ResourceException;
	public void deleteBatchResource(String... resIds) throws ResourceException;
	public void updateResource(Resource resource) throws ResourceException;
	public Resource getResource(String resId) throws ResourceException;
	public ListInfo queryListInfoResources(ResourceCondition conditions, long offset, int pagesize)
			throws ResourceException;
	public List<Resource> queryListResources(ResourceCondition conditions) throws ResourceException;
	public List<Resource> queryListResources(String resourceType) throws ResourceException;
	public List<ResourceWithOPS> queryListResourceWithOPS(String resourceType) throws ResourceException;
}
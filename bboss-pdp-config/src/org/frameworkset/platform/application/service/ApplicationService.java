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

package org.frameworkset.platform.application.service;

import org.frameworkset.platform.application.entity.*;
import com.frameworkset.util.ListInfo;
import java.util.List;

/**
 * <p>Title: ApplicationService</p> <p>Description: 应用管理管理服务接口 </p> <p>bboss</p>
 * <p>Copyright (c) 2015</p> @Date 2016-11-24 15:35:15 @author yinbp @version
 * v1.0
 */
public interface ApplicationService {
	public void addApplication(Application application) throws ApplicationException;
	public void deleteApplication(String appId) throws ApplicationException;
	public void deleteBatchApplication(String... appIds) throws ApplicationException;
	public void updateApplication(Application application) throws ApplicationException;
	public Application getApplication(String appId) throws ApplicationException;
	public ListInfo queryListInfoApplications(ApplicationCondition conditions, long offset, int pagesize)
			throws ApplicationException;
	public List<Application> queryListApplications(ApplicationCondition conditions) throws ApplicationException;
}
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
 * <p>Title: SmUserService</p> <p>Description: 用户管理管理服务接口 </p> <p>bboss</p>
 * <p>Copyright (c) 2015</p> @Date 2016-11-29 21:14:30 @author yinbp @version
 * v1.0
 */
public interface SmUserService {
	public void addSmUser(SmUser smUser) throws SmUserException;
	public void deleteSmUser(String userId) throws SmUserException;
	public void deleteBatchSmUser(String... userIds) throws SmUserException;
	public void updateSmUser(SmUser smUser) throws SmUserException;
	public SmUser getSmUser(String userId) throws SmUserException;
	public ListInfo queryListInfoSmUsers(SmUserCondition conditions, long offset, int pagesize) throws SmUserException;
	public List<SmUser> queryListSmUsers(SmUserCondition conditions) throws SmUserException;
	public ListInfo getDepartUsers(SmUserCondition conditions, long offset, int pagesize) throws SmUserException ;
}
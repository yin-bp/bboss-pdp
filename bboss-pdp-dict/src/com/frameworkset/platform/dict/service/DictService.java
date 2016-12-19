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

package com.frameworkset.platform.dict.service;

import com.frameworkset.platform.dict.entity.*;
import com.frameworkset.util.ListInfo;
import java.util.List;

/**
 * <p>Title: DictService</p> <p>Description: 字典管理管理服务接口 </p> <p>bboss</p>
 * <p>Copyright (c) 2015</p> @Date 2016-12-18 11:29:08 @author yinbp @version
 * v1.0
 */
public interface DictService {
	public void addDict(Dict dict,List<DictItem> dictItems) throws DictException;
	public void deleteDict(String dictId) throws DictException;
	public void deleteBatchDict(String... dictIds) throws DictException;
	public void updateDict(Dict dict,List<DictItem> dictItems) throws DictException;
	public Dict getDict(String dictId) throws DictException;
	public ListInfo queryListInfoDicts(DictCondition conditions, long offset, int pagesize) throws DictException;
	public List<Dict> queryListDicts(DictCondition conditions) throws DictException;
	/**
	 * @param dict
	 * @param dictItems
	 */
	public void maintaindata(Dict dict, List<DictItem> dictItems)throws DictException;
}
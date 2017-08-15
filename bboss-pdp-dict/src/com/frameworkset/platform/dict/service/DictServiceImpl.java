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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frameworkset.common.poolman.ConfigSQLExecutor;
import com.frameworkset.orm.transaction.TransactionManager;
import com.frameworkset.platform.dict.core.Param;
import com.frameworkset.platform.dict.core.Params;
import com.frameworkset.platform.dict.core.ParamsHandler;
import com.frameworkset.platform.dict.entity.Dict;
import com.frameworkset.platform.dict.entity.DictCondition;
import com.frameworkset.platform.dict.entity.DictItem;
import com.frameworkset.util.ListInfo;

/**
 * <p>Title: DictServiceImpl</p> <p>Description: 字典管理管理业务处理类 </p> <p>bboss</p>
 * <p>Copyright (c) 2007</p> @Date 2016-12-18 11:29:08 @author yinbp @version
 * v1.0
 */
public class DictServiceImpl implements DictService {

	private static Logger log = LoggerFactory.getLogger(com.frameworkset.platform.dict.service.DictServiceImpl.class);

	private ConfigSQLExecutor executor;
	private List<Param> params(Dict dict,List<DictItem> dictItems,String dictId){
		List<Param> params = new ArrayList<Param>();
		int i = 0;
		for(DictItem dictItem:dictItems){
			Param param = new Param();
			param.setDictId(dictId);
			param.setName(dictItem.getName());
			param.setValue(dictItem.getValue());
			param.setDictCode(dict.getDictCode());
			param.setDictName(dict.getDictName());
			param.setRn(dictItem.getRn());
			param.setDataOrder(i);
			params.add(param);
			i ++;
		}
		return params;
	}
	public void addDict(Dict dict,List<DictItem> dictItems) throws DictException {
		TransactionManager tm = new TransactionManager();
		// 业务组件
		try {
			tm.begin();
			executor.insertBean("addDict", dict);
			if(dictItems != null && dictItems.size() > 0)
			{
				Params params = new Params();				
				params.setParams(params( dict,dictItems, dict.getDictId()));
				params.setDictId(dict.getDictId());
				ParamsHandler.getParamsHandler(dict.getHandler()).saveParams(params) ;
			}
			tm.commit();
		} catch (Throwable e) {
			throw new DictException("add Dict failed:", e);
		}
		finally
		{
			tm.release();
		}

	}
	public void deleteDict(String dictId) throws DictException {
		try {
			executor.delete("deleteByKey", dictId);
		} catch (Throwable e) {
			throw new DictException("delete Dict failed::dictId=" + dictId, e);
		}

	}
	 
	public void deleteBatchDict(String... dictIds) throws DictException {
		TransactionManager tm = new TransactionManager();
		try {
			tm.begin();
			
			executor.updateByKeys("updateByKeys", dictIds);
			tm.commit();
		} catch (Throwable e) {

			throw new DictException("batch delete Dict failed::dictIds=" + dictIds, e);
		} finally {
			tm.release();
		}

	}
	/**
	 * @param dict
	 * @param dictItems
	 */
	public void maintaindata(Dict dict, List<DictItem> dictItems)throws DictException{
		Map<String, Serializable> trace = null;
		try {
			
			
			if(dictItems != null && dictItems.size() > 0)
			{
				Params params = new Params();				
				params.setParams(params( dict,dictItems, dict.getDictId()));
				params.setDictId(dict.getDictId());
				params.setDictCode(dict.getDictCode());
				trace =ParamsHandler.getParamsHandler(dict.getHandler()).saveParams(params) ;
			}
			else
			{
				Params params = new Params();				
				
				params.setDictId(dict.getDictId());
				params.setDictCode(dict.getDictCode());
				trace =ParamsHandler.getParamsHandler(dict.getHandler()).saveParams(params) ;
			}
		
			
		} catch (Throwable e) {
	
			throw new DictException("update Dict failed::", e);
		} 
		if(trace != null)
			ParamsHandler.getParamsHandler(dict.getHandler()).sendEvent(trace);

	}
	public void updateDict(Dict dict,List<DictItem> dictItems) throws DictException {
		TransactionManager tm = new TransactionManager();
		Map<String, Serializable> trace = null;
		try {
			tm.begin();
			executor.updateBean("updateDict", dict);
			if(dictItems != null && dictItems.size() > 0)
			{
				Params params = new Params();				
				params.setParams(params( dict,dictItems, dict.getDictId()));
				params.setDictId(dict.getDictId());
				params.setDictCode(dict.getDictCode());
				trace =ParamsHandler.getParamsHandler(dict.getHandler()).saveParams(params) ;
			}
			else
			{
				Params params = new Params();				
				params.setDictId(dict.getDictId());
				params.setDictCode(dict.getDictCode());
				trace =ParamsHandler.getParamsHandler(dict.getHandler()).saveParams(params) ;
			}
		
			tm.commit();
		} catch (Throwable e) {
	
			throw new DictException("update Dict failed::", e);
		} finally {
			tm.release();
		} 
		if(trace != null)
			ParamsHandler.getParamsHandler(dict.getHandler()).sendEvent(trace);

	}
	public Dict getDict(String dictId) throws DictException {
		try {
			Dict bean = executor.queryObject(Dict.class, "selectById", dictId);
			return bean;
		} catch (Throwable e) {
			throw new DictException("get Dict failed::dictId=" + dictId, e);
		}

	}
	public ListInfo queryListInfoDicts(DictCondition conditions, long offset, int pagesize) throws DictException {
		ListInfo datas = null;
		try {
			datas = executor.queryListInfoBean(Dict.class, "queryListDict", offset, pagesize, conditions);
		} catch (Exception e) {
			throw new DictException("pagine query Dict failed:", e);
		}
		return datas;

	}
	public List<Dict> queryListDicts(DictCondition conditions) throws DictException {
		try {
			List<Dict> beans = executor.queryListBean(Dict.class, "queryListDict", conditions);
			return beans;
		} catch (Exception e) {
			throw new DictException("query Dict failed:", e);
		}

	}
}
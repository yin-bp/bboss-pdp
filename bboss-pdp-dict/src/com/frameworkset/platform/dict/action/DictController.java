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

package com.frameworkset.platform.dict.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.frameworkset.util.Params;
import org.frameworkset.util.ParamsHandler;
import org.frameworkset.util.annotations.PagerParam;
import org.frameworkset.util.annotations.ResponseBody;
import org.frameworkset.web.servlet.ModelMap;

import com.frameworkset.platform.dict.entity.Dict;
import com.frameworkset.platform.dict.entity.DictCondition;
import com.frameworkset.platform.dict.entity.DictItem;
import com.frameworkset.platform.dict.service.DictException;
import com.frameworkset.platform.dict.service.DictService;
import com.frameworkset.util.ListInfo;
import com.frameworkset.util.StringUtil;

/**
 * <p>Title: DictController</p> <p>Description: 字典管理管理控制器处理类 </p> <p>bboss</p>
 * <p>Copyright (c) 2007</p> @Date 2016-12-18 11:29:08 @author yinbp @version
 * v1.0
 */
public class DictController {

	private static Logger log = Logger.getLogger(DictController.class);

	private DictService dictService;
	public @ResponseBody String addDict(Dict dict,List<DictItem> dictItems) {
		// 控制器
		try {
			dictService.addDict(dict,  dictItems);
			return "success";
		} catch (DictException e) {
			log.error("add Dict failed:", e);
			return StringUtil.formatException(e);
		} catch (Throwable e) {
			log.error("add Dict failed:", e);
			return StringUtil.formatException(e);
		}

	}
	
	public @ResponseBody String deleteDict(String dictId) {
		try {
			dictService.deleteDict(dictId);
			return "success";
		} catch (DictException e) {
			log.error("delete Dict failed:", e);
			return StringUtil.formatException(e);
		} catch (Throwable e) {
			log.error("delete Dict failed:", e);
			return StringUtil.formatException(e);
		}

	}
	public @ResponseBody String deleteBatchDict(String dictIds) {
		try {
			if(StringUtil.isEmpty(dictIds)){
				return "没有要删除的字典";
			}
			String[] _dictIds = dictIds.split(",");
			dictService.deleteBatchDict(_dictIds);
			return "success";
		} catch (Throwable e) {
			log.error("delete Batch dictIds failed:", e);
			return StringUtil.formatException(e);
		}

	}
	public @ResponseBody String updateDict(Dict dict) {
		try {
			dictService.updateDict(dict);
			return "success";
		} catch (Throwable e) {
			log.error("update Dict failed:", e);
			return StringUtil.formatException(e);
		}

	}
	public String getDict(String dictId, ModelMap model) throws DictException {
		try {
			Dict dict = dictService.getDict(dictId);
			model.addAttribute("dict", dict);
			return "path:getDict";
		} catch (DictException e) {
			throw e;
		} catch (Throwable e) {
			throw new DictException("get Dict failed::dictId=" + dictId, e);
		}

	}
	public String queryListInfoDicts(DictCondition conditions, @PagerParam(name = PagerParam.OFFSET) long offset,
			@PagerParam(name = PagerParam.PAGE_SIZE, defaultvalue = "10") int pagesize, ModelMap model)
					throws DictException {
		// Constant.component_type_actionimpl
		try {
			String dictName = conditions.getDictAttr();
			if (dictName != null && !dictName.equals("")) {
				conditions.setDictAttr("%" + dictName + "%");
			}

			ListInfo dicts = dictService.queryListInfoDicts(conditions, offset, pagesize);
			model.addAttribute("dicts", dicts);
			return "path:queryListInfoDicts";
		} catch (DictException e) {
			throw e;
		} catch (Exception e) {
			throw new DictException("pagine query Dict failed:", e);
		}

	}
	public String queryListDicts(DictCondition conditions, ModelMap model) throws DictException {
		try {
			String dictName = conditions.getDictAttr();
			if (dictName != null && !dictName.equals("")) {
				conditions.setDictAttr("%" + dictName + "%");
			}
			List<Dict> dicts = dictService.queryListDicts(conditions);
			model.addAttribute("dicts", dicts);
			return "path:queryListDicts";
		} catch (DictException e) {
			throw e;
		} catch (Exception e) {
			throw new DictException("query Dict failed:", e);
		}

	}
	public String toUpdateDict(String dictId, ModelMap model) throws DictException {
		try {
			Dict dict = dictService.getDict(dictId);
			model.addAttribute("dict", dict);
			return "path:updateDict";
		} catch (DictException e) {
			throw e;
		} catch (Throwable e) {
			throw new DictException("get Dict failed::dictId=" + dictId, e);
		}

	}
	public String toAddDict() {
		return "path:addDict";
	}
	public String index() {
		return "path:index";

	}
	
	public String dictitemlist(String dictId, String handler,String actiontype,ModelMap model){
		if(!StringUtil.isEmpty(dictId) && !StringUtil.isEmpty(handler)){
			
			Params params = ParamsHandler._getParams(handler, dictId);
			if(params != null)
			{
				model.addAttribute("paramsList", params.getParams());
				if(actiontype != null)
					model.addAttribute("actiontype", actiontype);
			}
		}
		return "path:dictitemlist";
		
	}
}
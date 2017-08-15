/*
 *  Copyright 2008 biaoping.yin
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

package com.frameworkset.platform.dict.core;


import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.frameworkset.event.Event;
import org.frameworkset.event.EventHandle;
import org.frameworkset.event.EventImpl;
import org.frameworkset.event.Listener;
import org.frameworkset.event.NotifiableFactory;
import org.frameworkset.event.SimpleEventType;
import org.frameworkset.spi.BaseApplicationContext;
import org.frameworkset.spi.BaseSPIManager2;
import org.frameworkset.spi.BeanNameAware;
import org.frameworkset.spi.DefaultApplicationContext;
import org.frameworkset.spi.assemble.Pro;
import org.frameworkset.spi.assemble.ProMap;
import org.frameworkset.util.ClassUtil;
import org.frameworkset.util.ClassUtil.ClassInfo;
import org.frameworkset.util.ClassUtil.PropertieDescription;
import org.frameworkset.util.beans.PropertyAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frameworkset.common.poolman.PreparedDBUtil;
import com.frameworkset.common.poolman.Record;
import com.frameworkset.common.poolman.SQLExecutor;
import com.frameworkset.common.poolman.handle.NullRowHandler;
import com.frameworkset.common.poolman.handle.RowHandler;
import com.frameworkset.common.poolman.sql.IdGenerator;
import com.frameworkset.common.poolman.util.SQLManager;
import com.frameworkset.orm.transaction.TransactionManager;
import com.frameworkset.util.SimpleStringUtil;
import com.frameworkset.util.ValueObjectUtil;

/**
 * <p>
 * Title: ParamsHandler.java
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * bboss workgroup
 * </p>
 * <p>
 * Copyright (c) 2008
 * </p>
 * 
 * @Date 2010-1-11 下午03:50:21
 * @author biaoping.yin
 * @version 1.0
 */
public class ParamsHandler implements org.frameworkset.spi.InitializingBean,Listener<Map<String,Serializable>>,BeanNameAware {
	
	private static final Logger log = LoggerFactory.getLogger(ParamsHandler.class);
	private static Map<String,ParamsHandler> handlers = new HashMap<String,ParamsHandler>();
	private String beanName;
	private SimpleEventType eventtype ;
	private static ParamsHandler defaultHandler;
	static{
	 BaseApplicationContext.addShutdownHook(new Runnable() {
			
			public void run()
			{
		
				destory();
				
			}
		});
	}
	public void sendEvent(Map<String,Serializable> trace){
		EventHandle.sendEvent(new EventImpl<Map<String, Serializable>>(trace,this.eventtype));
	}
	/**
	 * 缓存参数数据
	 * paramType:paramID:Params
	 * 参数类型      参数表        参数记录集
	 * 
	 */
	private Map<String,Params> cache = new HashMap<String,Params>();
	

	/**
	 * 参数集合
	 * 	
	 * 
	 */
	
	public boolean cleanCache(String dictCode)
	{
		Params table = this.cache.get(dictCode);
		if(table == null)
			return false;
		synchronized(table)
		{
			Object obj = cache.remove(dictCode);
			if(obj == null)
				return false;
			else
				return true;
		}
		
	}
	public boolean cleanCaches()
	{
		this.cache.clear();
		return true;
	}
	public void _destory()
	{
		this.cache.clear();
		cache = null;
	}
	
	public static boolean cleanAllCache()
	{
		init();
		Iterator<Map.Entry<String, ParamsHandler>> it = handlers.entrySet().iterator();
		while(it.hasNext())
		{
			ParamsHandler handler = it.next().getValue();
			handler.cleanCaches();
		}
		return true;
	}
	
	public static void destory()
	{
		loaded = false;
		if(handlers != null)
		{
			Iterator<Map.Entry<String, ParamsHandler>> it = handlers.entrySet().iterator();
			while(it.hasNext())
			{
				ParamsHandler handler = it.next().getValue();
				handler._destory();
			}
			handlers.clear();
			handlers = null;
		}
		
	}
	
	
	
	
	
	
	public boolean cleanCaches(Map<String,Serializable> keys)
	{
		if(keys == null || keys.size() == 0) return false;
		Iterator<String> it = keys.keySet().iterator();
		while(it.hasNext())
		{
			String v = it.next();
			 
			cleanCache(v);
		}
		return true;
		
		
	}
	
	private void rnParams(List<Param> paramList )
	{
		if(paramList == null || paramList.size() == 0)
			return ;
		Map<String,Integer> rns = new HashMap<String,Integer>();
		for(Param param:paramList)
		{
			String name = param.getName();
			Integer rn = rns.get(name);
			if(rn == null)
			{
				rns.put(name,new Integer(0));
			}
			else
			{
				int newrn = rn.intValue()+1;
				param.setRn(newrn);
				rns.put(name,new Integer(newrn));
			}
		}
	}
	/**
	 * 自定义参数存储
	 * 
	 * @param params
	 * @param paramid
	 * @param paramType
	 * @return
	 */
	public Map<String, Serializable> saveParams(Params params) {
		StringBuilder sql_del = new StringBuilder();
		sql_del.append("delete from ").append(tableName).append(
				" where DICT_ID=?");
		StringBuilder sql_in = new StringBuilder();
//		INSERT INTO td_sm_parameters (PAEAM_ID, NAME, RN, VALUE, DICT_ID) VALUES ('', '', 0, '', '');
		sql_in.append("insert into ").append(tableName).append(
				"(PAEAM_ID, NAME, RN, VALUE, DICT_ID,data_order) values(?,?,?,?,?,?)");

		List<Param> paramList = params.getParams();
		IdGenerator idGenerator = SQLManager.getInstance().getPool(dbname).getIdGenerator();
		PreparedDBUtil dbutil = new PreparedDBUtil();
		java.io.Serializable t = new Integer(1);
		Map<String, Serializable> trace = new HashMap<String, Serializable>();

		TransactionManager tm = new TransactionManager();
		try {
			tm.begin();
			if(SimpleStringUtil.isEmpty(SimpleStringUtil.isEmpty(params.getDictId())))
			{
				rnParams(paramList );
				for (Param param : paramList) {
					String key = param.getDictCode();
					if (trace.containsKey(key))
						continue;
					dbutil.preparedDelete(this.getDbname(), sql_del.toString());
					dbutil.setString(1, param.getDictId());					  
					dbutil.addPreparedBatch();
					trace.put(key, t);
	
				}
			}
			else
			{
				String key = params.getDictCode() ;
				trace.put(key, t);
				rnParams(paramList );
				dbutil.preparedDelete(this.getDbname(), sql_del.toString());
				dbutil.setString(1, params.getDictId());
				 
				dbutil.addPreparedBatch();
			}
			for (Param param : paramList) {
				if(SimpleStringUtil.isEmpty(param.getName()))
					continue;
				dbutil.preparedInsert(this.getDbname(), sql_in.toString());
				dbutil.setString(1, idGenerator.getNextId());
				dbutil.setString(2, param.getName());
				
				
				dbutil.setInt(3, param.getRn());
				dbutil.setString(4, String.valueOf(param.getValue()));
				dbutil.setString(5, param.getDictId());
				dbutil.setInt(6, param.getDataOrder());
				
					
				dbutil.addPreparedBatch();
			}
			dbutil.executePreparedBatch();
			tm.commit();
//			cleanCaches(trace);
//			EventHandle.sendEvent(new EventImpl<Map<String, Serializable>>(trace,this.eventtype));
			
			return trace;
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		finally
		{
			tm.release();
		}
		return trace;
	}

	public static Params getParams(String handler,String dictCode) {
		return getParamsHandler(handler).getParams(dictCode);
	}
	public static Params getDefaultParams(String dictCode) {
		return getParamsHandler("default").getParams(dictCode);
	}
	
	public static String  getStringParam(String paramHandle,String paramid, String paramName,String paramType) {
		return getParamsHandler(paramHandle).getParams(paramid, paramType).getAttributeString(paramName);
	}

	public static <T>  T getParams(String paramHandle,String dictCode,Class<T> dataBean) {
		Params params = getParams( paramHandle, dictCode) ;
		return convertParamsToBean(params,dataBean);
	}
	
	
	private static <T>  T convertParamsToBean(Params params,Class<T> dataBean)
	{
		if(params == null)
			return null;
		
		ClassInfo beanInfo = null;
		try {
			beanInfo = ClassUtil.getClassInfo(dataBean);
		} catch (Exception e) {
			throw new PropertyAccessException( "获取bean ["+dataBean.getName()+"]信息失败",e);
		}
		
		String name = null;
		
		Object value =  null;
		Class type = null;
		T obj = null;
		try {
			obj = dataBean.newInstance();
		} catch (InstantiationException e1) {
			throw new RuntimeException("参数转对象错误,实例化对象失败 ",e1);
		} catch (IllegalAccessException e1) {
			throw new RuntimeException("参数转对象错误,实例化对象失败 ",e1);
		}
		List<PropertieDescription> attributes = beanInfo.getPropertyDescriptors();
		for(PropertieDescription property:attributes)
		{
			if(property.getName().equals("class"))
				continue;
			
			try {
				name = property.getName();
				
				type = property.getPropertyType();
				value =  params.getAttributeObject(name);
				value = ValueObjectUtil.typeCast(value, type);
				property.setValue(obj, value);
				
				
			} catch (SecurityException e) {
				log.error("参数转对象属性设置失败:bean class["+ dataBean.getCanonicalName() +"],param info["+ params.getMetaString()+ "]",e );
				continue;
			} catch (IllegalArgumentException e) {
				log.error("参数转对象属性设置失败:bean class["+ dataBean.getCanonicalName() +"],param info["+ params.getMetaString()+ "]",e );
				continue;
			} catch (IllegalAccessException e) {
				log.error("参数转对象属性设置失败:bean class["+ dataBean.getCanonicalName() +"],param info["+ params.getMetaString()+ "]" ,e);
				continue;
			} catch (InvocationTargetException e) {
				log.error("参数转对象属性设置失败:bean class["+ dataBean.getCanonicalName() +"],param info["+ params.getMetaString()+ "]" ,e);
				continue;
			} 
//			catch (InstantiationException e) {
//				log.error("参数转对象属性设置失败:bean class["+ dataBean.getCanonicalName() +"],param info["+ params.getMetaString()+ "]" ,e);
//				continue;
//			}
			catch (Exception e) {
				log.error("参数转对象属性设置失败:bean class["+ dataBean.getCanonicalName() +"],param info["+ params.getMetaString()+ "]" ,e);
				continue;
			}
		}
		
		return obj;
		
	}
	public Params getParams(String dictCode) {
		Params table  = this.cache.get(dictCode);
		if(table  == null)
		{
			synchronized(cache)
			{
				table = cache.get(dictCode);
				if(table != null)
					return table;
				table = _getParams(dictCode);
				cache.put(dictCode, table);
			}
		}
		
		return table;
	}
	public static Params _getParams(String handler,String dictCode){
		return ParamsHandler.getParamsHandler(handler)._getParams(dictCode);
	}
	public Params _getParams(String dictCode) {
		StringBuilder sql_query = new StringBuilder();
		sql_query.append("select di.*,dct.dict_code,dct.dict_name from ").append(tableName).append(
				" di,td_sm_dict dct where (dct.dict_code=? or dct.dict_id=?) and di.dict_id=dct.dict_id  order by data_order,rn asc");//to_do
		PreparedDBUtil pd = new PreparedDBUtil();
		
		final Params params = new Params();
		try {
			pd.preparedSelect(this.getDbname(),sql_query.toString());
			pd.setString(1, dictCode);	
			pd.setString(2, dictCode);	
			params.setDbname(this.getDbname());			 
			params.setDictCode(dictCode);
			params.setTableName(tableName);
			pd.executePreparedWithRowHandler(new NullRowHandler() {
				@Override
				public void handleRow(Record record) {
					try {
						
						{
							params.setDictId(record.getString("dict_id"));
							params.setDictName(record.getString("dict_name"));
							params.addAttribute(record.getString("dict_code"),
									record.getString("PAEAM_ID"), record
											.getString("NAME"), record
											.getString("VALUE"), record
											.getInt("RN"));
						}
						
					} catch (SQLException e) {
						e.printStackTrace();
					}

				}
			});
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return params;
	}
	
	public Param getParam(final String dictCode, String paramName) {
		StringBuilder sql_query = new StringBuilder();
		sql_query.append("select di.*,dct.dict_code,dct.dict_name from ").append(tableName).append(
				" di,td_sm_dict dct where (dct.dict_code=? or dct.dict_id=?) and name = ? order by rn asc");
		PreparedDBUtil pd = new PreparedDBUtil();
		final Param param = new Param();
		try {
			pd.preparedSelect(this.getDbname(),sql_query.toString());
			pd.setString(1, dictCode);
			pd.setString(2, dictCode);
			pd.setString(3, paramName);
			 
			
			pd.executePreparedWithRowHandler(new NullRowHandler() {
				@Override
				public void handleRow(Record record) {
					try {
						{
							param.setDictCode(dictCode);
							param.setDictName(record
									.getString("dict_name"));
							param.setDictId(record
									.getString("dict_id"));
							param.setName(record
									.getString("NAME"));
							param.setValue(record
									.getString("VALUE"));
							param.setRn(record
									.getInt("RN"));
							
						}
						
					} catch (SQLException e) {
						e.printStackTrace();
					}

				}
			});
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return param;
	}
	
	public List<Param> getMultiValueParam(final String dictCode, String paramName) {
		StringBuilder sql_query = new StringBuilder();
		sql_query.append("select di.*,dct.dict_code,dct.dict_name from ").append(tableName).append(
				" di,td_sm_dict dct where (dct.dict_code=? or dct.dict_id=?) and name = ? order by rn asc");
		PreparedDBUtil pd = new PreparedDBUtil();
		final List<Param> params = new ArrayList<Param>();
		
		try {
			pd.preparedSelect(this.getDbname(),sql_query.toString());
			pd.setString(1, dictCode);
			pd.setString(2, dictCode);
			pd.setString(3, paramName);
			
			pd.executePreparedWithRowHandler(new NullRowHandler() {
				@Override
				public void handleRow(Record record) {
					try {
						int isbigData = record.getInt("ISBIGDATA");
						Param param = new Param();
						param.setDictCode(dictCode);
						param.setDictName(record
								.getString("dict_name"));
						param.setDictId(record
								.getString("dict_id"));
						param.setName(record
								.getString("NAME"));
						param.setValue(record
								.getString("VALUE"));
						param.setRn(record
								.getInt("RN"));
						params.add(param);	
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
				}
			});
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return params;
	}
	
	/**
	 * 没有一个key对应多value
	 * @param paramid
	 * @param paramType
	 * @return
	 */
	public Map<String,Object> getMapParams(final String dictCode, String paramName) {
		StringBuilder sql_query = new StringBuilder();
		sql_query.append("select di.*,dct.dict_code,dct.dict_name from ").append(tableName).append(
				" di,td_sm_dict dct where (dct.dict_code=? or dct.dict_id=?) and name = ? order by rn asc");
		PreparedDBUtil pd = new PreparedDBUtil();
		final Map<String,Object> params = new HashMap<String,Object>();
		try {
			pd.preparedSelect(this.getDbname(),sql_query.toString());
			pd.setString(1, dictCode);
			pd.setString(2, dictCode);
			pd.setString(3, paramName);

			pd.executePreparedWithRowHandler(new NullRowHandler() {
				@Override
				public void handleRow(Record record) {
					try {
						
							params.put(record.getString("NAME"), record.getString("VALUE"));
						
					} catch (SQLException e) {
						e.printStackTrace();
					}

				}
			});
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return params;
	}
	
	public boolean delParams(final String dictId){
		StringBuilder sql_del = new StringBuilder();
		sql_del.append("delete from ").append(tableName).append(
				" where dict_Id=? ");
		PreparedDBUtil pd = new PreparedDBUtil();
		try {
			pd.preparedDelete(this.getDbname(),sql_del.toString());
			pd.setString(1, dictId);
		 
			pd.executePrepared();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean delParam(final String dictId,String paramName){
		StringBuilder sql_del = new StringBuilder();
		sql_del.append("delete from ").append(tableName).append(
				" where dict_Id=?  and name=?");
		PreparedDBUtil pd = new PreparedDBUtil();
		try {
			pd.preparedDelete(this.getDbname(),sql_del.toString());
			pd.setString(1, dictId);
			pd.setString(2, paramName);
			pd.executePrepared();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 客户端节点高级参数类型名称
	 */
	public static final String AMQ_PARAM_TYPE = "amq.connection.params";

	/**
	 * 客户端esb高级参数处理对象名称
	 */
	public static final String CMC_PARAMSHANDLER = "cmc.paramshandler";
	/**
	 * 服务端esb高级参数处理对象名称
	 */
	public static final String SMC_PARAMSHANDLER = "smc.paramshandler";
	/**
	 * 服务端。集群高级参数配置类型名称
	 */
	public static final String MQ_NETWORKCONNECTOR_PARAM = "mq.networkconnector.params";
	
	/**
	 * 服务端。基本参数配置类型名称
	 */
	public static final String MQ_BROKERSERVICE_PARAM = "mq.brokerservice.params";

	private String dbname = null;

	private String tableName = "ESB_PARAMS";



	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}


	public String getDbname() {
		return dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

	  
	/**
	 * 从数据库中获取给定参数名称，配置节点id，参数类型的参数对象
	 * 
	 * @param propertyName
	 *            参数称
	 * @param nodeId
	 *            配置节点id
	 * @@param params_type 参数类型
	 * @return
	 * 
	 */
	public Pro getNodeParamWithProName(String propertyName, String nodeId,
			String params_type) {

		String sql = "select NAME,VALUE from " + tableName
				+ " where NODE_ID = ? and PARAM_TYPE=? and name=?  order by rn asc";

		Pro pro = null;
		PreparedDBUtil dbutil = new PreparedDBUtil();
		try {
			dbutil.preparedSelect(getDbname(), sql);
			dbutil.setString(1, nodeId);
			dbutil.setString(2, params_type);
			dbutil.setString(3, propertyName);
			pro = (Pro) dbutil.executePreparedForObject(Pro.class,
					new RowHandler<Pro>() {

						@Override
						public void handleRow(Pro rowValue, Record record) {
							try {
								rowValue.setName(record.getString("name"));

								rowValue.setValue(record.getString("value"));

							} catch (SQLException e) {
								e.printStackTrace();
							}

						}

					});

			return pro;
		} catch (Exception e) {
			throw new ParamException(e);
		}
	}

	/**
	 * 从数据库中获取已经配置的参数
	 * 
	 * @param propertyMapName
	 *            模板参数map配置的名称
	 * @param nodeId
	 *            配置节点id
	 * @param params_type
	 *            参数类型
	 * @return
	 * 
	 */
	public ProMap<String, Pro> getNodeParams(String nodeId, String params_type) {

		String sql = "select NAME,VALUE from " + tableName
				+ " where NODE_ID = ? and PARAM_TYPE=?  order by name,rn asc";
		final ProMap<String, Pro> params = new ProMap<String, Pro>();

		PreparedDBUtil dbutil = new PreparedDBUtil();
		try {
			dbutil.preparedSelect(getDbname(), sql);
			dbutil.setString(1, nodeId);
			dbutil.setString(2, params_type);
			dbutil.executePreparedWithRowHandler(new NullRowHandler() {

				public void handleRow(Record record) {
					try {
						Pro rowValue = new Pro();
						rowValue.setName(record.getString("name"));

						rowValue.setValue(record.getString("value"));

						params.put(rowValue.getName(), rowValue);
					} catch (SQLException e) {
						e.printStackTrace();
					}

				}

			});

			return params;
		} catch (Exception e) {
			throw new ParamException(e);
		}
	}

	 
 

	/**
	 * 根据节点id和参数类型删除参数配置
	 * 
	 * @param nodeIds
	 * @param paramstype
	 * @return
	 */
	public boolean delNodeParams(String[] nodeIds, String paramstype) {
		String sql_del = "delete from " + tableName
				+ " where NODE_ID=? and param_type= ?";
		PreparedDBUtil dbutil = new PreparedDBUtil();
		try {
			for (String nodeId : nodeIds) {
				dbutil.preparedDelete(getDbname(), sql_del);
				dbutil.setString(1, nodeId);
				dbutil.setString(2, paramstype);
				dbutil.addPreparedBatch();
			}
			dbutil.executePreparedBatch();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static boolean delNodeParams(String paramshandler,
			String[] businessIds, String properMapname) {
		ParamsHandler handle = getParamsHandler(paramshandler);
		String[] properMapnames = properMapname.split(",");

		for (String mapName : properMapnames) {
			Pro t_ = BaseSPIManager2.getProBean(mapName);
			String paramsType = (String) t_.getExtendAttribute("paramstype");
			try {
				handle.delNodeParams(businessIds, paramsType);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	public boolean insertConParams(int NODE_ID, List<Pro> params,
			String paramsType) {
		String sql_del = "delete from " + tableName
				+ " where NODE_ID=? and PARAM_TYPE=?";
		PreparedDBUtil dbutil = new PreparedDBUtil();
		try {
			dbutil.preparedDelete(this.getDbname(), sql_del);
			dbutil.setString(1, String.valueOf(NODE_ID));
			dbutil.setString(2, paramsType);
			dbutil.addPreparedBatch();
			StringBuffer sql = new StringBuffer();
			Pro mq_con_params = null;
			for (int i = 0; i < params.size(); i++) {
				mq_con_params = params.get(i);
				sql.append("insert into ").append(tableName).append(
						"(NODE_ID,NAME,VALUE,PARAM_TYPE) values(?,?,?,?)");
				dbutil.preparedInsert(this.getDbname(), sql.toString());
				dbutil.setString(1, String.valueOf(NODE_ID));
				dbutil.setString(2, mq_con_params.getName());
				dbutil.setString(3, mq_con_params.getString());
				dbutil.setString(4, paramsType);
				dbutil.addPreparedBatch();
				sql.setLength(0);
			}
			dbutil.executePreparedBatch();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	private static boolean loaded;
	private static void init()
	{
		if(loaded)
			return;
		synchronized(ParamsHandler.class)
		{
			if(loaded)
				return;
			BaseApplicationContext context = DefaultApplicationContext.getApplicationContext("org/frameworkset/util/paramhandlers.xml");
			Set<String> keys =context.getPropertyKeys();
			if(keys == null || keys.size() == 0)
			{
				loaded = true;
				return;
			}
			Iterator<String> its = keys.iterator();
			while(its.hasNext())
			{
				String key = its.next();
				Object value = context.getBeanObject(key);
				if(value == null)
					continue;
				if(value instanceof ParamsHandler)
				{
					handlers.put(key, (ParamsHandler)value);
				}
			}
			loaded = true;
		}
	}
	public static ParamsHandler getParamsHandler(String name) {
		
		init();
//		return (ParamsHandler) DefaultApplicationContext.getApplicationContext("org/frameworkset/util/paramhandlers.xml")
//				.getBeanObject(name);
		if(name.equals("default"))
		{
			if(defaultHandler != null)
				return defaultHandler;
			
			ParamsHandler _defaultHandler = new ParamsHandler();
			_defaultHandler.setTableName("TD_SM_PARAMETERS");
			try {
				_defaultHandler.afterPropertiesSet();
				defaultHandler = _defaultHandler;
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return defaultHandler;
		}
		return handlers.get(name);
	}

	public void afterPropertiesSet() throws Exception {
		 
		eventtype = new SimpleEventType("org_frameworkset_util_"+this.beanName);
		NotifiableFactory.addListener(this, eventtype);
	}

	public static void insertParams(Map<String, String> paramsMap,
			String factoryId, String properMapname, String paramshandler) {

	}

	/**
	 * 
	 * 保存参数组，使用远程服务调用
	 * 
	 * @param request
	 * @param factoryId
	 *            业务主键ID
	 * @param properMapname
	 *            参数组名称
	 * @param paramshandler
	 *            参数处理类名字
	 * @param rpcbrokeraddr
	 *            远程组件调用地址
	 * @throws ParamException
	 */
	public static void insertParams(HttpServletRequest request,
			String factoryId, String properMapname, String paramshandler,
			String rpcbrokeraddr) throws ParamException {
		String[] properMapnames = properMapname.split(",");
		if (rpcbrokeraddr == null || "".equals(rpcbrokeraddr))
			rpcbrokeraddr = ParamProperties.PARAM_HANDLER_PRO;
		RpcParamsHandlerInf brokerMan = (RpcParamsHandlerInf) BaseSPIManager2
				.getBeanObject(rpcbrokeraddr);

		String key = null;
		Pro pro = null;
		List<Pro> params = new ArrayList<Pro>();
		String msg = null;
		for (String mapName : properMapnames) {
			// 获取配置文件中的配置参数默认值
			ProMap confMap = (ProMap) BaseSPIManager2.getMapProperty(mapName);
			Pro t_ = BaseSPIManager2.getProBean(mapName);
			String paramsType = (String) t_.getExtendAttribute("paramstype");
			Iterator itParams = confMap.keySet().iterator();
			while (itParams.hasNext()) {
				key = (String) itParams.next();
				pro = new Pro();
				pro.setName(key);
				String value = request.getParameter(key);
				value = (value == null || "".equals(value)) ? "" : value;
				pro.setValue(value);
				params.add(pro);
			}
			try {
				brokerMan.insertConParams(paramshandler, Integer
						.parseInt(factoryId), params, paramsType);
				params.clear();
			} catch (Exception e) {
				throw new ParamException(e);
			}
		}
	}

	/**
	 * 
	 * 保存参数组，本地调用
	 * 
	 * @param request
	 * @param factoryId
	 *            业务主键ID
	 * @param properMapname
	 *            参数组名称
	 * @param paramshandler
	 *            参数处理类名字
	 * @throws ParamException
	 */
	public static void insertParams(HttpServletRequest request,
			String factoryId, String properMapname, String paramshandler)
			throws ParamException {
		insertParams(request, factoryId, properMapname, paramshandler, null);
	}

	/**
	 * 重置参数组，使用配置文件中的默认配置，适用远程服务调用
	 * 
	 * @param factoryId
	 * @param properMapname
	 * @param paramshandler
	 * @param rpcbrokeraddr
	 * @throws ParamException
	 */
	public static void resetParams(String factoryId, String properMapname,
			String paramshandler, String rpcbrokeraddr) throws ParamException {
		String[] properMapnames = properMapname.split(",");
		if (rpcbrokeraddr == null || "".equals(rpcbrokeraddr))
			rpcbrokeraddr = ParamProperties.PARAM_HANDLER_PRO;
		RpcParamsHandlerInf brokerMan = (RpcParamsHandlerInf) BaseSPIManager2
				.getBeanObject(rpcbrokeraddr);

		String key = null;
		Pro pro = null;
		List<Pro> params = new ArrayList<Pro>();
		String msg = null;
		for (String mapName : properMapnames) {
			// 获取配置文件中的配置参数默认值
			ProMap confMap = (ProMap) BaseSPIManager2.getMapProperty(mapName);
			Pro t_ = BaseSPIManager2.getProBean(mapName);
			String paramsType = (String) t_.getExtendAttribute("paramstype");
			Iterator itParams = confMap.keySet().iterator();
			while (itParams.hasNext()) {
				key = (String) itParams.next();
				pro = new Pro();
				pro.setName(key);
				String value = confMap.getString(key);
				pro.setValue(value);
				params.add(pro);
			}
			try {
				brokerMan.insertConParams(paramshandler, Integer
						.parseInt(factoryId), params, paramsType);
				params.clear();
			} catch (Exception e) {
				throw new ParamException(e);
			}
		}
	}

	/**
	 * 重置参数组，使用配置文件中的默认配置，本地调用
	 * 
	 * @param factoryId
	 * @param properMapname
	 * @param paramshandler
	 * @throws ParamException
	 */
	public static void resetParams(String factoryId, String properMapname,
			String paramshandler) throws ParamException {
		resetParams(factoryId, properMapname, paramshandler, null);
	}
	/**
	 * 获取所有对应类型的参数名称为paramName的值，以nodeid为key的map对象
	 * @param paramType
	 * @return
	 */
	public Map<String,String> getStringParamMap(String paramType,String paramName) throws ParamException
	{
		StringBuilder sql_query = new StringBuilder();
		sql_query.append("select * from ").append(tableName).append(
				" where param_type= ? and NAME=? order by rn asc");
		final Map<String,String> params = new HashMap<String,String>();
		try {
			SQLExecutor.queryByNullRowHandler(new NullRowHandler(){

				@Override
				public void handleRow(Record origine) throws Exception {
					
					params.put(origine.getString("NODE_ID"), origine.getString("VALUE"));
				}
				
			}, sql_query.toString(), paramType,paramName);
			return params;
		} catch (Exception e) {
			throw new ParamException(e);
		}
	}
	@Override
	public void handle(Event<Map<String,Serializable>> e) {
		Map<String,Serializable> data = e.getSource();
		this.cleanCaches(data);
		
	}
	@Override
	public void setBeanName(String name) {
		this.beanName = name;
		
	}
}

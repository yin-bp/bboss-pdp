/**
 * 
 */
package com.frameworkset.platform.dict.core;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yinbp
 *
 * @Date:2016-12-18 10:32:18
 */
public  class Params{
	private String dictId; 
	private String dictCode;
	private String dictName;
	private String tableName;
	private String dbname;
	public int size(){
		if (params == null )
			return 0;
		return this.params.size() ;
	}
	public int getSize(String code)
	{
		if (params == null || this.params.size() <= 0)
			return 0;
		int count = 0;
		for (Param param : params) {
			if (param.getName().equals(code))
				count ++;
		}
		return count;	
	}
	public String getMetaString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("dictId=").append(dictId)
		 
		.append(",dbname=")
		.append(dbname)
		.append(",tableName=")
		.append(tableName);
		return builder.toString(); 
	}
	public String toString(){
		if(params == null || params.size() == 0){
			return null;
		}else{
			return super.toString();
		}
	}
	
	public boolean isEmpty(){
		if(params == null || params.size() == 0){
			return true;
		}else{
			return false;
		}
	}

	private List<Param> params = new ArrayList<Param>();

	public void addAttribute(String dictId, String paramid, String name,
			long value, int rn)

	{
		params.add(new Param(  dictId, paramid, name, String
				.valueOf(value), rn));
	}

	public void addAttribute(String dictId, String paramid, String name,
			long value) {
		addAttribute(    dictId,   paramid, name, value, 0);
	}
	public void setParams(List<Param> params)
	{
		this.params = params;
	}

	public void addAttribute(String dictId, String paramid, String name,
			double value, int rn) {
		params.add(new Param(  dictId,   paramid, name, String
				.valueOf(value), rn));
	}
	
	public void addAttribute(String dictId, String paramid, String name,
			Object value) {
		params.add(new Param(  dictId,   paramid, name, String.valueOf(value), 0));
	}
	
	public void addAttribute(String dictId, String paramid, String name,
			Object value, int rn) {
		params.add(new Param(  dictId,   paramid, name, String.valueOf(value), rn));
	}

	public void addAttribute(String dictId, String paramid, String name,
			double value) {
		addAttribute(  dictId,   paramid, name, value, 0);
	}

	public void addAttribute(String dictId, String paramid, String name,
			boolean value) {
		addAttribute(  dictId,   paramid, name, value ? "Y" : "N");
	}

	public void addAttribute(String dictId, String paramid, String name,
			String value, int rn) {
		params.add(new Param(  dictId,   paramid, name, value, rn));
	}

	public void addAttribute(String dictId, String paramid, String name,
			String value) {
		addAttribute(  dictId, paramid, name, value, 0);
	}

	private Object findAttribute(int nr, String code) {
		if (params == null || this.params.size() <= 0)
			return null;
		for (Param param : params) {
			if (param.getRn() == nr && param.getName().equals(code))
				return param.getValue();
		}
		return null;
	}

	public String getAttributeString(int nr, String code) {
		Object value = findAttribute(nr, code);
		if(value == null)
			return null;
		return String.valueOf(value);
	}
	
	public Object getAttributeObject(int nr, String code) {
		return findAttribute(nr, code);
	}

	public boolean getAttributeBoolean(int nr, String code, boolean def) {
		String value = this.getAttributeString(nr, code);
		if (this.isEmpty(value)) {
			return def;
		}
		return this.convertStringToBoolean(value);
	}

	public boolean getAttributeBoolean(int nr, String code) {
		String value = this.getAttributeString(nr, code);
		if (this.isEmpty(value)) {
			return false;
		}
		return this.convertStringToBoolean(value);
	}

	public long getAttributeInteger(String code) {
		return this.getAttributeInteger(0, code);

	}

	public long getAttributeInteger(int nr, String code) {
		String value = this.getAttributeString(nr, code);
		if (this.isEmpty(value)) {
			return 0;
		}
		return Long.parseLong(value);
	}

	public String getAttributeString(String code) {
		return getAttributeString(0, code);
	}

	public Object getAttributeObject(String code) {
		return getAttributeObject(0, code);
	}
	public boolean getAttributeBoolean(String code) {
		return getAttributeBoolean(0, code);
	}
	public Param getParam(int i){
		return this.params.get(i);
	}
	public List<Param> getParams() {
		return params;
	}
	/**
	 * 在内存中更新给定名称的参数值
	 * @param param
	 * @return true 有更新，false为插入参数
	 */
	public boolean updateParam(Param param)
	{
		if(this.params == null)
		{
			params = new ArrayList<Param>();
			params.add(param);
			return false;
		}
		else if(this.params.size() == 0)
		{
			params.add(param);
			return false;
		}
		else
		{
			int i = 0;
			for(Param param_:params)
			{
				if(param_.getName().equals(param.getName()))
				{
					params.remove(i);
					params.add(param);
					return true;
					
				}
				i ++;
			}
			
			params.add(param);	
			return false;
		}
		
	}

	public static Boolean convertStringToBoolean(String string) {
		if (isEmpty(string))
			return null;
		return Boolean.valueOf("Y".equalsIgnoreCase(string)
				|| "TRUE".equalsIgnoreCase(string)
				|| "YES".equalsIgnoreCase(string) || "1".equals(string));
	}

	/**
	 * Check if the string supplied is empty. A String is empty when it is
	 * null or when the length is 0
	 * 
	 * @param string
	 *            The string to check
	 * @return true if the string supplied is empty
	 */
	public static final boolean isEmpty(String string) {
		return string == null || string.length() == 0;
	}

	

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
	public String getDictId() {
		return dictId;
	}
	public void setDictId(String dictId) {
		this.dictId = dictId;
	}
	public String getDictCode() {
		return dictCode;
	}
	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}
	public String getDictName() {
		return dictName;
	}
	public void setDictName(String dictName) {
		this.dictName = dictName;
	}
	private Param getParamByValue(String value){
		if(this.params == null )
			return null;
		for(Param p:params){
			if(p.getValue().equals(value))
				return p;
		}
		return null;
	}
	private Param getParamByName(String name){
		if(this.params == null )
			return null;
		for(Param p:params){
			if(p.getName().equals(name))
				return p;
		}
		return null;
	}
	/**
	 * @param valueOf
	 * @param defaultName
	 * @return
	 */
	public String getItemName(String value, String defaultName) {
		if (params == null){
			if(defaultName == null)
			{
//			throw new ProfessionDataManagerException("id为'" + dataId
//					+ "'的专业数据不能为空");
//			System.out.println("id为'" + dataId + "'的专业数据不能为空");
				return value;
			}
			else
			{
				return defaultName;
			}
		}

		Param item =   getParamByValue( value);
		if (item != null)
			return item.getName();
		// for(int i = 0; i < items.size(); i ++)
		// {
		// Item item = (Item)items.get(i);
		// if(item.getValue().equals(value))
		// return item.getName();
		// }
//		throw new ProfessionDataManagerException("id为'" + dataId
//				+ "'专业数据中不存在值为'" + value + "'的数据项");
		if(defaultName == null)
		{
			return value;
		}
		else
		{
			return defaultName;
		}
	}
	
	public String getItemName(String value)
			 {
		if (params == null){
			 
				return value;
			 
		}

		Param item =   getParamByValue( value);
		if (item != null)
			return item.getName();
		// for(int i = 0; i < items.size(); i ++)
		// {
		// Item item = (Item)items.get(i);
		// if(item.getValue().equals(value))
		// return item.getName();
		// }
//		throw new ProfessionDataManagerException("id为'" + dataId
//				+ "'专业数据中不存在值为'" + value + "'的数据项");
		 
		return value;
		 
	}
	
	
	public String getItemNameByValue(String value)
	{
		if (params == null){			 
			return value;
		}
	
		Param item =   getParamByValue( value);
		if (item != null)
			return item.getName();
	// for(int i = 0; i < items.size(); i ++)
	// {
	// Item item = (Item)items.get(i);
	// if(item.getValue().equals(value))
	// return item.getName();
	// }
//	throw new ProfessionDataManagerException("id为'" + dataId
//			+ "'专业数据中不存在值为'" + value + "'的数据项");
	 
		return value;
	}
	 
	public String getItemValue(String name)
			  {
		if (params == null){
//			throw new ProfessionDataManagerException("id为'" + dataId
//					+ "'的专业数据不能为空");
			return name;
		}
		Param item = getParamByName(name);

		if (item != null)
			return item.getValue();

		// for(int i = 0; i < items.size(); i ++)
		// {
		// Item item = (Item)items.get(i);
		// if(item.getName().equals(name))
		// return item.getValue();
		// }
//		throw new ProfessionDataManagerException("id为'" + dataId
//				+ "'的专业数据中不存在名称为" + name + "数据项");
		return name;
	}
	
	public String getItemValueByName(String name)
			 {
		if (params == null){
//			throw new ProfessionDataManagerException("id为'" + dataId
//					+ "'的专业数据不能为空");
			return name;
		}
		Param item = getParamByName(name);

		if (item != null)
			return item.getValue();

		// for(int i = 0; i < items.size(); i ++)
		// {
		// Item item = (Item)items.get(i);
		// if(item.getName().equals(name))
		// return item.getValue();
		// }
//		throw new ProfessionDataManagerException("id为'" + dataId
//				+ "'的专业数据中不存在名称为" + name + "数据项");
		return null;
	}
	
	public String getItemValue(String name,String defaultValue)
			 {
		if (params == null){
//			throw new ProfessionDataManagerException("id为'" + dataId
//					+ "'的专业数据不能为空");
			if(defaultValue == null)
			{
				return name;
			}
			else
			{
				return defaultValue;
			}
		}
		Param item = getParamByName(name);

		if (item != null)
			return item.getValue();

		// for(int i = 0; i < items.size(); i ++)
		// {
		// Item item = (Item)items.get(i);
		// if(item.getName().equals(name))
		// return item.getValue();
		// }
//		throw new ProfessionDataManagerException("id为'" + dataId
//				+ "'的专业数据中不存在名称为" + name + "数据项");
		if(defaultValue == null)
		{
			return name;
		}
		else
			return defaultValue;
	}
	
	public String getItemValueByName(String name,String defaultValue)
			 {
		if (params == null){
//			throw new ProfessionDataManagerException("id为'" + dataId
//					+ "'的专业数据不能为空");
			if(defaultValue == null)
			{
				return name;
			}
			else
			{
				return defaultValue;
			}
		}
		Param item = getParamByName(name);

		if (item != null)
			return item.getValue();

		// for(int i = 0; i < items.size(); i ++)
		// {
		// Item item = (Item)items.get(i);
		// if(item.getName().equals(name))
		// return item.getValue();
		// }
//		throw new ProfessionDataManagerException("id为'" + dataId
//				+ "'的专业数据中不存在名称为" + name + "数据项");
		if(defaultValue == null)
		{
			return name;
		}
		else
			return defaultValue;
	}

	 

}


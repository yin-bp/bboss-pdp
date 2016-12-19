/**
 * 
 */
package org.frameworkset.util;

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

}


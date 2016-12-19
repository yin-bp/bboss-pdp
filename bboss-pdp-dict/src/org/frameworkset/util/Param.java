/**
 * 
 */
package org.frameworkset.util;

/**
 * @author yinbp
 *
 * @Date:2016-12-18 10:31:10
 */
public  class Param {
	private String paramId;// 参数主键
	private String name;// 参数名称
	private String value;// 参数值
	private int rn;// 一个参数对应多个值时，对应行号
	private String dictId;// 参数分类
	private String dictCode;// 参数分类
	
	private String dictName;// 参数分类
	


	


	public Param()
	{
		
	}
	public Param(String paramId, String dictId, String name,
			String value, int rn) {
		super();
		this.paramId = paramId;
		this.dictId = dictId;
		this.name = name;
		this.value = value;
		this.rn = rn;
		
	}
	
	

	
	
	public int getRn() {
		return rn;
	}

	public void setRn(int rn) {
		this.rn = rn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getParamId() {
		return paramId;
	}
	public void setParamId(String paramId) {
		this.paramId = paramId;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
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

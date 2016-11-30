/**
 * 
 */
package org.frameworkset.platform.common;

import java.util.List;

/**
 * 封装分页对象
 * @author yinbp
 *
 * @Date:2016-11-29 22:54:56
 */
public class DatagridBean {
	private int draw;
	private int recordsTotal;
	private int recordsFiltered;
	private List<Object> data;
	/**
	 * 
	 */
	public DatagridBean() {
		// TODO Auto-generated constructor stub
	}
	public int getDraw() {
		return draw;
	}
	public void setDraw(int draw) {
		this.draw = draw;
	}
	public int getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	public int getRecordsFiltered() {
		return recordsFiltered;
	}
	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	public List<Object> getData() {
		return data;
	}
	public void setData(List<Object> data) {
		this.data = data;
	}

}

package org.frameworkset.platform.entity;

import java.io.Serializable;
import java.util.List;

/**
 * DataGrid返回数据格式
 * 
 * @author <a href="mailto:yin-bp@163.com">xujb</a>
 * @version 1.0
 */
public class DataGrid implements Serializable {
	private static final long serialVersionUID = 2914752879851283376L;

	private List<?> tbody;

	private PageBean page;

	public List<?> getTbody() {
		return tbody;
	}

	public void setTbody(List<?> tbody) {
		this.tbody = tbody;
	}

	public PageBean getPage() {
		return page;
	}

	public void setPage(PageBean page) {
		this.page = page;
	}

}

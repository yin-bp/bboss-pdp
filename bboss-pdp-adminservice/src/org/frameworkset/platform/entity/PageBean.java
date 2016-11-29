package org.frameworkset.platform.entity;

import java.io.Serializable;

/**
 * 分页对象
 * 
 * @author <a href="mailto:yin-bp@163.com">xujb</a>
 * @version 1.0
 */
public class PageBean implements Serializable {
	private static final long serialVersionUID = -104665411959731845L;
	private int pageCount;
	private int pageSize;
	private int currentPage;
	private long recordCount;
	private long lastPageSize;

	public PageBean() {
	}

	public PageBean(int pageCount, int pageSize, int currentPage,
			long recordCount) {
		this.pageCount = pageCount;
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.recordCount = recordCount;
		this.lastPageSize = recordCount - pageSize * (pageCount - 1);
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public long getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(long recordCount) {
		this.recordCount = recordCount;
	}

	public long getLastPageSize() {
		return lastPageSize;
	}

	public void setLastPageSize(long lastPageSize) {
		this.lastPageSize = lastPageSize;
	}

}

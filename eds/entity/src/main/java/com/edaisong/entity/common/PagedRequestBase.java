package com.edaisong.entity.common;

public class PagedRequestBase extends RequestBase{
	private int totalRecord;
	private int totalPage;
	private int pageSize;
	private int currentPage;

	/**
	 * 总记录数
	 * @return
	 */
	public int getTotalRecord() {
		return totalRecord;
	}
   
	/**
	 * 总记录数
	 * @param totalRecord
	 */
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	/**
	 * 总页数
	 * @return
	 */
	public int getTotalPage() {
		return totalPage;
	}
	 /**
     * 总页数
     * @param totalRecord
     */
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	/**
	 * 页容量
	 * @return
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 页容量
	 * @param pageSize
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 当前页码
	 * @return
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * 当前页吗
	 * @param currentPage
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
}

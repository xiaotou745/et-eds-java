package com.edaisong.entity.common;

public class RequestBase {

	private String authorityCityNameListStr;

	private int userType;

	/**
	 * 用户所有权限城市名称集合串
	 * 
	 * @return
	 */
	public String getAuthorityCityNameListStr() {
		return authorityCityNameListStr;
	}

	/**
	 * 用户所有权限城市名称集合串
	 * 
	 * @param authorityCityNameListStr
	 */
	public void setAuthorityCityNameListStr(String authorityCityNameListStr) {
		this.authorityCityNameListStr = authorityCityNameListStr;
	}

	/**
	 * 用户类型（1：全部城市权限 2：部分城市权限）
	 * 
	 * @return
	 */
	public int getUserType() {
		return userType;
	}

	/**
	 * 用户类型（1：全部城市权限 2：部分城市权限）
	 * 
	 * @param userType
	 */
	public void setUserType(int userType) {
		this.userType = userType;
	}

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

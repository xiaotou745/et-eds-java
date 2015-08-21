package com.edaisong.entity.req;

import com.edaisong.entity.common.PagedRequestBase;
/**
 * 交易明细列表 请求参数
 * 2015年8月4日10:23:51
 * 茹化肖
 * 
 * */
public class PagedTransDetailReq extends PagedRequestBase {

	private String startDate;
	private String endDate;
	private String recordType;
	private int businessID;

	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getRecordType() {
		return recordType;
	}
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
	public int getBusinessID() {
		return businessID;
	}
	public void setBusinessID(int businessID) {
		this.businessID = businessID;
	}
}

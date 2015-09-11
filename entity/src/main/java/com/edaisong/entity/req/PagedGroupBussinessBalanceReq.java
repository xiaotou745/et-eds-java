package com.edaisong.entity.req;

import com.edaisong.entity.common.PagedRequestBase;

public class PagedGroupBussinessBalanceReq extends PagedRequestBase{
	
	private String startDate;
	private String endDate;
	private String recordType;
	private int groupbusinessId;
	
	private String businessName;
	private String relationNo;

	public String getRelationNo() {
		return relationNo;
	}
	public void setRelationNo(String relationNo) {
		this.relationNo = relationNo;
	}
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
	public int getGrupBusinessId() {
		return groupbusinessId;
	}
	public void setGroupBusinessId(int groupbusinessId) {
		this.groupbusinessId = groupbusinessId;
	}
	
	public String getBusinessname() {
		return relationNo;
	}
	public void setBusinessname(String businessName) {
		this.businessName = businessName;
	}
}

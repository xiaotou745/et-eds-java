package com.edaisong.entity.req;

import com.edaisong.entity.common.PagedRequestBase;

public class PagedBusinessClientersReq extends PagedRequestBase{
	private int businessId;
	private int workStatus;
	private String search;//页面右上角查询手机或名字字段
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public int getBusinessId() {
		return businessId;
	}
	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}
	public int getWorkStatus() {
		return workStatus;
	}
	public void setWorkStatus(int workStatus) {
		this.workStatus = workStatus;
	} 
}

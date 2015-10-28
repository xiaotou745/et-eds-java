
package com.edaisong.entity.req;

import com.edaisong.entity.common.PagedRequestBase;

public class PagedClienterBalanceRecordReq extends PagedRequestBase{
	
	public Integer getClienterId() {
		return clienterId;
	}
	public void setClienterId(Integer clienterId) {
		this.clienterId = clienterId;
	}
	public int getBusinessId() {
		return businessId;
	}
	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}
	private Integer clienterId;
	private int businessId;
}


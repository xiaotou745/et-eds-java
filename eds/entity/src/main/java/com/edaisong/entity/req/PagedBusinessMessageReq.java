package com.edaisong.entity.req;

import com.edaisong.entity.common.PagedRequestBase;

public class PagedBusinessMessageReq extends PagedRequestBase{
	private int businessId;

	public int getBusinessId() {
		return businessId;
	}

	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}
}

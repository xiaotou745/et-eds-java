package com.edaisong.entity.req;

import com.edaisong.entity.common.PagedRequestBase;

public class PagedTestUserReq extends PagedRequestBase {
	private String phoneNo;

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
}

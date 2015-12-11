package com.edaisong.entity.req;

import com.edaisong.entity.common.PagedRequestBase;

public class PagedQuartzServiceReq extends PagedRequestBase{
	private int status;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}

package com.edaisong.toolsentity.req;

import com.edaisong.toolsentity.common.PagedRequestBase;

public class PagedQuartzServiceReq extends PagedRequestBase{
	private int status;
	private int appSource;
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getAppSource() {
		return appSource;
	}

	public void setAppSource(int appSource) {
		this.appSource = appSource;
	}
}

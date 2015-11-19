package com.edaisong.toolsentity.req;

import com.edaisong.toolsentity.common.PagedRequestBase;

public class PagedAppDbConfigReq extends PagedRequestBase{
	private String appname;
	private Integer configtype;
	public String getAppname() {
		return appname;
	}
	public void setAppname(String appname) {
		this.appname = appname;
	}
	public Integer getConfigtype() {
		return configtype;
	}
	public void setConfigtype(Integer configtype) {
		this.configtype = configtype;
	}
}

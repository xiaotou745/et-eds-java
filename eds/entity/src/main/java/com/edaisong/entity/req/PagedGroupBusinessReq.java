package com.edaisong.entity.req;

import com.edaisong.entity.common.PagedRequestBase;

public class PagedGroupBusinessReq extends PagedRequestBase {
	private String groupBusinessName;
	
	public String getbusinessGroupName() {
		return groupBusinessName;
	}

	 
	public void setgroupBusinessName(String groupBusinessName) {
		this.groupBusinessName = groupBusinessName;
	}
}

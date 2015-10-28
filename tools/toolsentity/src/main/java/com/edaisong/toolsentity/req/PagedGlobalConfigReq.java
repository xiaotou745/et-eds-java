package com.edaisong.toolsentity.req;

import com.edaisong.toolsentity.common.PagedRequestBase;

public class PagedGlobalConfigReq extends PagedRequestBase{
	private int groupId;

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
}

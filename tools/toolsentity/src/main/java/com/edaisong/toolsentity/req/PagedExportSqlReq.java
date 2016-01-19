package com.edaisong.toolsentity.req;

import com.edaisong.toolsentity.common.PagedRequestBase;

public class PagedExportSqlReq extends PagedRequestBase{
private String templetName;

public String getTempletName() {
	return templetName;
}

public void setTempletName(String templetName) {
	this.templetName = templetName;
}
}

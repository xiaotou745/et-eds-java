package com.edaisong.entity.req;

import com.edaisong.entity.common.PagedRequestBase;

public class PagedBusinessSetpReq  extends PagedRequestBase{

	private String title;
	private String beginDate;
	private String endDate;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}

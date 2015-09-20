package com.edaisong.entity.req;

import com.edaisong.entity.common.PagedRequestBase;

public class MarkReq extends PagedRequestBase{
	private String markName;
	private int markType;
	private String startDate;
	private String endDate;
	public String getMarkName() {
		return markName;
	}
	public void setMarkName(String markName) {
		this.markName = markName;
	}
	public int getMarkType() {
		return markType;
	}
	public void setMarkType(int markType) {
		this.markType = markType;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	

}

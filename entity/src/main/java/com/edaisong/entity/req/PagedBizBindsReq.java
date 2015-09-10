package com.edaisong.entity.req;

import com.edaisong.entity.common.PagedRequestBase;

/**
 *
 * @author  pengyi 
 * @date 2015年9月9日 上午10:36:17
 * @version 1.0
 * @parameter
 * @since
 */
public class PagedBizBindsReq extends PagedRequestBase{
	private int groupBusinessId;
	private String bizName;
	private String startDate;
	private String endDate;
	public int getGroupBusinessId() {
		return groupBusinessId;
	}
	public void setGroupBusinessId(int groupBusinessId) {
		this.groupBusinessId = groupBusinessId;
	}
	public String getBizName() {
		return bizName;
	}
	public void setBizName(String bizName) {
		this.bizName = bizName;
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

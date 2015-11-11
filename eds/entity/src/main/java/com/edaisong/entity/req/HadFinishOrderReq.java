package com.edaisong.entity.req;

import com.edaisong.entity.common.PagedRequestBase;

public class HadFinishOrderReq extends PagedRequestBase {
	private String dateInfo;
	private Integer clienterId; 

	/**
	 * 日期 可空
	 * 
	 * @return
	 */
	public String getDateInfo() {
		return dateInfo;
	}

	/**
	 * 日期可空
	 * 
	 * @param dateInfo
	 */
	public void setDateInfo(String dateInfo) {
		this.dateInfo = dateInfo;
	}

	/**
	 * 骑士 id 可空
	 * 
	 * @return
	 */
	public Integer getClienterId() {
		return clienterId;
	}

	/**
	 * 骑士 id 可空
	 * 
	 * @param clienterId
	 */
	public void setClienterId(Integer clienterId) {
		this.clienterId = clienterId;
	} 
	
}
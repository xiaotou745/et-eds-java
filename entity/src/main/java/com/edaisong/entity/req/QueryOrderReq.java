package com.edaisong.entity.req;

import com.edaisong.entity.common.PagedRequestBase;

/**
 * 
 * @author CaoHeYang
 *
 */
public class QueryOrderReq extends PagedRequestBase {
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
	
	private Integer businessId;
	private Integer status;

	/**
	 * 
	 * @return
	 */
	public Integer getBusinessId() {
		return businessId;
	}

	/**
	 * 
	 * @param businessId
	 */
	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}

	/**
	 * 订单状态
	 * 
	 * @return
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 订单状态
	 * 
	 * @param status
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
}

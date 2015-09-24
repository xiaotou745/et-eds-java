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
	private Integer businessId;
	private Integer status;
	private Double longitude;
	private Double latitude;

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

	/**
	 * 骑士经度
	 * 
	 * @return
	 */
	public Double getLongitude() {
		return longitude;
	}

	/**
	 * 骑士经度
	 * 
	 * @param longitude
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	/**
	 * 骑士纬度
	 * 
	 * @return
	 */
	public Double getLatitude() {
		return latitude;
	}

	/**
	 * 骑士纬度
	 * 
	 * @return
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	
	private int orderBy;
/**
 * 0 按照发单时间排序 1 按照完成时间排序
 * @return
 */
	public int getorderBy() {
		return orderBy;
	}
/**
 *  0 按照发单时间排序 1 按照完成时间排序
 * @param orderBy
 */
	public void setorderBy(int orderBy) {
		this.orderBy = orderBy;
	}
	
}

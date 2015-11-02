package com.edaisong.entity.req;

/**
 * 骑士端获取店内任务
 * 
 * @version 3.0
 * @author CaoHeYang
 * @date 20151102
 */
public class InStoreTaskReq {
	private int clienterId;
	private Double longitude;
	private Double latitude;

	/**
	 * 骑士id
	 * 
	 * @return
	 */
	public int getClienterId() {
		return clienterId;
	}

	/**
	 * 骑士id
	 * 
	 * @param clienterId
	 */
	public void setClienterId(int clienterId) {
		this.clienterId = clienterId;
	}

	/**
	 * 经度
	 * 
	 * @return
	 */
	public Double getLongitude() {
		return longitude;
	}

	/**
	 * 经度
	 * 
	 * @param longitude
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	/**
	 * 纬度
	 * 
	 * @return
	 */
	public Double getLatitude() {
		return latitude;
	}

	/**
	 * 纬度
	 * 
	 * @param latitude
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

}

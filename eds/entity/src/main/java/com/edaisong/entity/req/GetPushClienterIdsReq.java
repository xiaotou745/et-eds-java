package com.edaisong.entity.req;

/**
 * 里程计算模式获取推单骑士id
 * 
 * @author CaoHeYang
 *
 */
public class GetPushClienterIdsReq {
	private Double latitude;
	private Double longitude;
	private int minuteInfo;
	private int distance;

	public GetPushClienterIdsReq(){
		
	}
	/**
	 * 
	 * @param latitude
	 * @param longitude
	 * @param minuteInfo
	 * @param distance
	 */
    public GetPushClienterIdsReq(Double latitude,Double longitude,int minuteInfo,int distance){
		this.latitude=latitude;
		this.longitude=longitude;
		this.minuteInfo=minuteInfo;
		this.distance=distance;
	}
	/**
	 * 发单纬度
	 * 
	 * @return
	 */
	public Double getLatitude() {
		return latitude;
	}

	/**
	 * 发单纬度
	 * 
	 * @param latitude
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	/**
	 * 发单经度
	 * 
	 * @return
	 */
	public Double getLongitude() {
		return longitude;
	}

	/**
	 * 发单经度
	 * 
	 * @param longitude
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	/**
	 * 最后N分钟内上传过坐标
	 * 
	 * @return
	 */
	public int getMinuteInfo() {
		return minuteInfo;
	}

	/**
	 * 最后N分钟内上传过坐标
	 * 
	 * @param minuteInfo
	 */
	public void setMinuteInfo(int minuteInfo) {
		this.minuteInfo = minuteInfo;
	}

	/**
	 * N米范围内
	 * 
	 * @return
	 */
	public int getDistance() {
		return distance;
	}

	/**
	 * N米范围内
	 * 
	 * @param distance
	 */
	public void setDistance(int distance) {
		this.distance = distance;
	}

}

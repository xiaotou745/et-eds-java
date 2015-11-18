package com.edaisong.entity.taobao.req;

/**
 * 淘宝 api 调用 返回值数据结构
 * @author CaoHeYang
 * @date 2015年11月13日 14:48:49
 */
public class TaoBaoPickUp {

	/** 
	* 物流订单号
	 */
	private Long deliveryOrderNo;

	/** 
	* 地图坐标：维度
	 */
	private String lat;

	/** 
	* 地图坐标：经度
	 */
	private String lng;

	public Long getDeliveryOrderNo() {
		return deliveryOrderNo;
	}

	public void setDeliveryOrderNo(Long deliveryOrderNo) {
		this.deliveryOrderNo = deliveryOrderNo;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}
	
}

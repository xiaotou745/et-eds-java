package com.edaisong.entity.taobao.req;

/**
 * 淘宝 api 调用 返回值数据结构
 * @author CaoHeYang
 * @date 2015年11月13日 14:48:49
 */
public class TaoBaoLocationUpdate {
	/** 
	* 配送员姓名
	 */
	private String delivererName;

	/** 
	* 配送员手机号码
	 */
	private String delivererPhone;

	/** 
	* 地图坐标：维度
	 */
	private String lat;

	/** 
	* 地图坐标：经度
	 */
	private String lng;

	public String getDelivererName() {
		return delivererName;
	}

	public void setDelivererName(String delivererName) {
		this.delivererName = delivererName;
	}

	public String getDelivererPhone() {
		return delivererPhone;
	}

	public void setDelivererPhone(String delivererPhone) {
		this.delivererPhone = delivererPhone;
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

package com.edaisong.entity.taobao.req;

/**
 * 淘宝 api 调用 返回值数据结构
 * 
 * @author CaoHeYang
 * @date 2015年11月13日 14:48:49
 */
public class TaoBaoUpdate {

	/**
	 * 配送员身份证号
	 */
	private String cardNo;

	/**
	 * 配送员id
	 */
	private String delivererId;

	/**
	 * 配送员姓名
	 */
	private String delivererName;

	/**
	 * 配送员手机号
	 */
	private String delivererPhone;

	/**
	 * 物流单号
	 */
	private Long deliveryOrderNo;

	/**
	 * 配送员位置：维度
	 */
	private String lat;

	/**
	 * 配送员位置：经度
	 */
	private String lng;

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getDelivererId() {
		return delivererId;
	}

	public void setDelivererId(String delivererId) {
		this.delivererId = delivererId;
	}

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

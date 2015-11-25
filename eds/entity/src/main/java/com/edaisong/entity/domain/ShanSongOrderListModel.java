package com.edaisong.entity.domain;

public class ShanSongOrderListModel extends OrderListModel {
	private Double weight;
	private String pickupCode;
	private int formType;

	/**
	 * 重量
	 */
	public Double getWeight() {
		return weight;
	}

	/**
	 * 重量
	 */
	public void setWeight(Double weight) {
		this.weight = weight;
	}

	

	/**
	 * 取货码
	 */
	public String getPickupCode() {
		return pickupCode;
	}

	/**
	 * 取货码
	 */
	public void setPickupCode(String pickupCode) {
		this.pickupCode = pickupCode;
	}

	/**
	 * 来源表 0 订单表 1 草稿箱
	 * 
	 * @return
	 */
	public int getFormType() {
		return formType;
	}

	/**
	 * 来源表 0 订单表 1 草稿箱
	 * 
	 * @param formType
	 */
	public void setFormType(int formType) {
		this.formType = formType;
	}

}

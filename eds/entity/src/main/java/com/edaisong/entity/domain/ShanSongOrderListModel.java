package com.edaisong.entity.domain;

public class ShanSongOrderListModel extends OrderListModel {
	private Double weight;
	private String pickupCode;
	private int formType;
	private String pubName;
	private String pubPhoneNo;
     private String productName;
     private String receiveCode;
     private int isreceivecode;
	public int getIsreceivecode() {
		return isreceivecode;
	}

	public void setIsreceivecode(int isreceivecode) {
		this.isreceivecode = isreceivecode;
	}

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

	/**
	 * 发货人
	 * 
	 * @return
	 */
	public String getPubName() {
		return pubName;
	}

	/**
	 * 发货人
	 * 
	 * @param pubName
	 */
	public void setPubName(String pubName) {
		this.pubName = pubName;
	}

	/**
	 * 发货人电话
	 * 
	 * @return
	 */
	public String getPubPhoneNo() {
		return pubPhoneNo;
	}

	/**
	 * 发货人电话
	 * 
	 * @param pubPhoneNo
	 */
	public void setPubPhoneNo(String pubPhoneNo) {
		this.pubPhoneNo = pubPhoneNo;
	}

	/**
	 * 商品名称
	 * @return
	 */
	public String getProductName() {
		return productName;
	}
    /**
     * 商品名称
     * @param productName
     */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 *  收货码
	 * @return
	 */
	public String getReceiveCode() {
		return receiveCode;
	}

	/**
	 * 收货码
	 * @param receiveCode
	 */
	public void setReceiveCode(String receiveCode) {
		this.receiveCode = receiveCode;
	}
	
}

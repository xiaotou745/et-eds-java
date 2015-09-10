package com.edaisong.entity.domain;

/**
 *  移动端查询订单列表
 * @author CaoHeYang
 * @date 20150910
 */
public class QueryOrder {
	private int orderId;
	private String orderNo;
	private String receviceName;
	private String recevicePhoneNo;
	private int orderCount;
	private Double amount;
	private Double totalAmount;
	private String originalOrderNo;
	private String superManName;
	private String superManPhone;
	private String pickUpName;
	private String pickUpAddress;
	private int status;
	private int orderFrom;
	private String receviceAddress;

	/**
	 * 订单Id
	 */
	public int getOrderId() {
		return orderId;
	}

	/**
	 * 订单Id
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	/**
	 * 订单号
	 */
	public String getOrderNo() {
		return orderNo;
	}

	/**
	 * 订单号
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * 收货人
	 */
	public String getReceviceName() {
		return receviceName;
	}

	/**
	 * 收货人
	 */
	public void setReceviceName(String receviceName) {
		this.receviceName = receviceName;
	}

	/**
	 * 收货电话
	 */
	public String getRecevicePhoneNo() {
		return recevicePhoneNo;
	}

	/**
	 * 收货电话
	 */
	public void setRecevicePhoneNo(String recevicePhoneNo) {
		this.recevicePhoneNo = recevicePhoneNo;
	}

	/**
	 * 数量
	 */
	public int getOrderCount() {
		return orderCount;
	}

	/**
	 * 数量
	 */
	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}

	/**
	 * 金额
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * 金额
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	/**
	 * 订单总金额
	 */
	public Double getTotalAmount() {
		return totalAmount;
	}

	/**
	 * 订单总金额
	 */
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	/**
	 * 第三方订单号
	 */
	public String getOriginalOrderNo() {
		return originalOrderNo;
	}

	/**
	 * 第三方订单号
	 */
	public void setOriginalOrderNo(String originalOrderNo) {
		this.originalOrderNo = originalOrderNo;
	}

	/**
	 * 骑士名称
	 */
	public String getSuperManName() {
		return superManName;
	}

	/**
	 * 骑士名称
	 */
	public void setSuperManName(String superManName) {
		this.superManName = superManName;
	}

	/**
	 * 骑士手机号
	 */
	public String getSuperManPhone() {
		return superManPhone;
	}

	/**
	 * 骑士手机号
	 */
	public void setSuperManPhone(String superManPhone) {
		this.superManPhone = superManPhone;
	}

	/**
     * 
     */
	public String getPickUpName() {
		return pickUpName;
	}

	/**
     * 
     */
	public void setPickUpName(String pickUpName) {
		this.pickUpName = pickUpName;
	}

	/**
	 * 取货地址
	 */
	public String getPickUpAddress() {
		return pickUpAddress;
	}

	/**
	 * 取货地址
	 */
	public void setPickUpAddress(String pickUpAddress) {
		this.pickUpAddress = pickUpAddress;
	}

	/**
	 * 状态
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * 状态
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * 订单来源
	 */
	public int getOrderFrom() {
		return orderFrom;
	}

	/**
	 * 订单来源
	 */
	public void setOrderFrom(int orderFrom) {
		this.orderFrom = orderFrom;
	}

	/**
	 * 收货地址
	 * 
	 * @return
	 */
	public String getReceviceAddress() {
		return receviceAddress;
	}

	/**
	 * 收货地址
	 * 
	 * @param receviceAddress
	 */
	public void setReceviceAddress(String receviceAddress) {
		this.receviceAddress = receviceAddress;
	}

}

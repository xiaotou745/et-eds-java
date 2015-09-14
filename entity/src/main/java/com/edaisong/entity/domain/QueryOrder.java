package com.edaisong.entity.domain;

import java.util.Date;

/**
 * 移动端查询订单列表
 * 
 * @author CaoHeYang
 * @date 20150910
 */
public class QueryOrder {
	private int orderId;
	private String orderNo;
	private String superManName;
	private String superManPhone;
	private String pickUpName;
	private String pickUpAddress;
	private String receviceName;
	private String recevicePhoneNo;
	private String receviceAddress;
	private int orderCount;
	private Double amount;
	private Double totalAmount;
	private String originalOrderNo;
	private int status;
	private int orderFrom;
	private String pubDate;
	private String actualDoneDate;
	private boolean isPay;
	private String remark;
	private String distanceB2R;
	private String orderFromName;
	private int mealsSettleMode;
	private Double income; // 骑士收入
	private String businessName; // 发货人
	private String pickUpCity; // 取货城市
	private String businessPhone;
	private String businessPhone2;
	private String receviceCity;
	private int groupId;
	private int needPickupCode;
	private int hadUploadCount;
	private int orderCommission;
	private Double commissionRate;
	private Double distribSubsidy;
	private Double websiteSubsidy;
	private Double longitude;
	private Double latitude;

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

	/**
	 * * 发布时间
	 * 
	 * @return
	 */
	public String getPubDate() {
		return pubDate;
	}

	/**
	 * 发布时间
	 * 
	 * @param pubDate
	 */
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	/**
	 * 完成时间
	 * 
	 * @return
	 */
	public String getActualDoneDate() {
		return actualDoneDate;
	}

	/**
	 * 完成时间
	 * 
	 * @param actualDoneDate
	 */
	public void setActualDoneDate(String actualDoneDate) {
		this.actualDoneDate = actualDoneDate;
	}

	/**
	 * 是否已付款
	 * 
	 * @return
	 */
	public boolean isPay() {
		return isPay;
	}

	/**
	 * 是否已付款
	 * 
	 * @param isPay
	 */
	public void setPay(boolean isPay) {
		this.isPay = isPay;
	}

	/**
	 * 备注
	 * 
	 * @return
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 备注
	 * 
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 商家到收货人的距离
	 * 
	 * @return
	 */
	public Double getDistanceB2R() {
		return distanceB2R;
	}

	/**
	 * 商家到收货人的距离
	 * 
	 * @param distanceB2R
	 */
	public void setDistanceB2R(Double distanceB2R) {
		this.distanceB2R = distanceB2R;
	}

	/**
	 * 订单来源 名称
	 * 
	 * @return
	 */
	public String getOrderFromName() {
		return orderFromName;
	}

	/**
	 * 订单来源 名称
	 * 
	 * @param orderFromName
	 */
	public void setOrderFromName(String orderFromName) {
		this.orderFromName = orderFromName;
	}

	/**
	 * 结算方式
	 * 
	 * @return
	 */
	public int getMealsSettleMode() {
		return mealsSettleMode;
	}

	/**
	 * 结算方式
	 * 
	 * @param mealsSettleMode
	 */
	public void setMealsSettleMode(int mealsSettleMode) {
		this.mealsSettleMode = mealsSettleMode;
	}

	/**
	 * 骑士收入 / 佣金
	 * 
	 * @return
	 */
	public Double getIncome() {
		return income;
	}

	/**
	 * 骑士收入 / 佣金
	 * 
	 * @param income
	 */
	public void setIncome(Double income) {
		this.income = income;
	}

	/**
	 * 发货人 商家
	 * 
	 * @return
	 */
	public String getBusinessName() {
		return businessName;
	}

	/**
	 * 发货人 商家
	 * 
	 * @param businessName
	 */
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	/**
	 * 取货城市
	 * 
	 * @return
	 */
	public String getPickUpCity() {
		return pickUpCity;
	}

	/**
	 * 取货城市
	 * 
	 * @param pickUpCity
	 */
	public void setPickUpCity(String pickUpCity) {
		this.pickUpCity = pickUpCity;
	}

	/**
	 * 发货电话 商家电话
	 * 
	 * @return
	 */
	public String getBusinessPhone() {
		return businessPhone;
	}

	/**
	 * 发货电话 商家电话
	 * 
	 * @param businessPhone
	 */
	public void setBusinessPhone(String businessPhone) {
		this.businessPhone = businessPhone;
	}

	public String getBusinessPhone2() {
		return businessPhone2;
	}

	public void setBusinessPhone2(String businessPhone2) {
		this.businessPhone2 = businessPhone2;
	}

	/**
	 * 收货人城市
	 * 
	 * @return
	 */
	public String getReceviceCity() {
		return receviceCity;
	}

	/**
	 * 收货人城市
	 * 
	 * @param receviceCity
	 */
	public void setReceviceCity(String receviceCity) {
		this.receviceCity = receviceCity;
	}

	/**
	 * / 集团id
	 * 
	 * @return
	 */
	public int getGroupId() {
		return groupId;
	}

	/**
	 * / 集团id
	 * 
	 * @param groupId
	 */
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	/**
	 * 是否需要做取货码验证 0 不需要 1 需要
	 * 
	 * @return
	 */
	public int getNeedPickupCode() {
		return needPickupCode;
	}

	/**
	 * 是否需要做取货码验证 0 不需要 1 需要
	 * 
	 * @param needPickupCode
	 */
	public void setNeedPickupCode(int needPickupCode) {
		this.needPickupCode = needPickupCode;
	}

	/**
	 * 已经上传的小票数量
	 * 
	 * @return
	 */
	public int getHadUploadCount() {
		return hadUploadCount;
	}

	/**
	 * 已经上传的小票数量
	 * 
	 * @param hadUploadCount
	 */
	public void setHadUploadCount(int hadUploadCount) {
		this.hadUploadCount = hadUploadCount;
	}

	/**
	 * 佣金比例
	 * 
	 * @return
	 */
	public Double getCommissionRate() {
		return commissionRate;
	}

	/**
	 * 佣金比例
	 * 
	 * @param commissionRate
	 */
	public void setCommissionRate(Double commissionRate) {
		this.commissionRate = commissionRate;
	}

	/**
	 * 外送费
	 * 
	 * @return
	 */
	public Double getDistribSubsidy() {
		return distribSubsidy;
	}

	/**
	 * 外送费
	 * 
	 * @param distribSubsidy
	 */
	public void setDistribSubsidy(Double distribSubsidy) {
		this.distribSubsidy = distribSubsidy;
	}

	/**
	 * 网站补贴
	 * 
	 * @return
	 */
	public Double getWebsiteSubsidy() {
		return websiteSubsidy;
	}

	/**
	 * 网站补贴
	 * 
	 * @return
	 */
	public void setWebsiteSubsidy(Double websiteSubsidy) {
		this.websiteSubsidy = websiteSubsidy;
	}

	/**
	 * 商家经度
	 * 
	 * @return
	 */
	public Double getLongitude() {
		return longitude;
	}

	/**
	 * 商家经度
	 * 
	 * @param longitude
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	/**
	 * 商家纬度
	 * 
	 * @return
	 */
	public Double getLatitude() {
		return latitude;
	}

	/**
	 * 商家纬度
	 * 
	 * @param latitude
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	/**
	 * 订单佣金 骑士收入
	 * 
	 * @return
	 */
	public int getOrderCommission() {
		return orderCommission;
	}

	/**
	 * 订单佣金 骑士收入
	 * 
	 * @param orderCommission
	 */
	public void setOrderCommission(int orderCommission) {
		this.orderCommission = orderCommission;
	}

}

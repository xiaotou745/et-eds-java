package com.edaisong.entity.domain;

import java.text.DecimalFormat;
import java.util.Date;

import com.edaisong.core.util.ParseHelper;

/**
 * 移动端查询订单列表
 *  TODO  少个距离 
 * @author CaoHeYang
 * @date 20150910
 */
public class QueryOrder {
	private int orderId;
	private String orderNo;
	private String superManName;
	private String superManPhone;
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
	private String distanceB2R="--";
	private String orderFromName;
	private int mealsSettleMode;
	private Double income; // 骑士收入  弃用 移动端 不删除
	private String businessName; // 发货人
	private String pickUpCity; // 取货城市
	private String businessPhone;
	private String businessPhone2;
	private String receviceCity;
	private int groupId;
	private int needPickupCode; //无实际意义 与app同步 不能删
	private int hadUploadCount;
	private double orderCommission;
	private String distance="--"; 
	private Double distance_OrderBy=9999999.0; 
	private double km;
	private double weight;
	private String pickupCode;
	private int platform;
	private String grabTime;
	private String pubName;
	private double balancePrice;
	private Double amountAndTip;
	 private String receivecode;
	 private String paymentstr;
	 private String platformstr;
		private int isReceiveCode;

	 public int getIsReceiveCode() {
			return isReceiveCode;
		}

		public void setIsReceiveCode(int isReceiveCode) {
			this.isReceiveCode = isReceiveCode;
		}

	public Double getAmountAndTip() {
		return amountAndTip;
	}

	public void setAmountAndTip(Double amountAndTip) {
		this.amountAndTip = amountAndTip;
	}

	public String getReceivecode() {
		return receivecode;
	}

	public void setReceivecode(String receivecode) {
		this.receivecode = receivecode;
	}

	public String getPaymentstr() {
		return paymentstr;
	}

	public void setPaymentstr(String paymentstr) {
		this.paymentstr = paymentstr;
	}

	public String getPlatformstr() {
		return platformstr;
	}

	public void setPlatformstr(String platformstr) {
		this.platformstr = platformstr;
	}


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
	public boolean getIsPay() {
		return isPay;
	}

	/**
	 * 是否已付款
	 * 
	 * @param isPay
	 */
	public void setIsPay(boolean isPay) {
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
	public String getDistanceB2R() {
		return distanceB2R;
	}

	/**
	 * 商家到收货人的距离
	 * 
	 * @param distanceB2R
	 */
	public void setDistanceB2R(String distanceB2R) {
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
	 * 订单佣金 骑士收入
	 * 
	 * @return
	 */
	public double getOrderCommission() {
		return orderCommission;
	}

	/**
	 * 订单佣金 骑士收入
	 * 
	 * @param orderCommission
	 */
	public void setOrderCommission(double orderCommission) {
		this.orderCommission = orderCommission;
	}
   /**
    * 骑士距离门店距离 只有在骑士查询我的任务 ——待取货的任务时有效
    * @return
    */
	public String getDistance() {
		return distance;
	}
/**
 * 骑士距离门店距离 只有在骑士查询我的任务 ——待取货的任务时有效
 * @param distance
 */
	public void setDistance(String distance) {
		this.distance = distance;
	}
/**
 * 骑士距离门店距离用来排序 只有在骑士查询我的任务 ——待取货的任务时有效
 * @return
 */
	public Double getDistance_OrderBy() {
		return distance_OrderBy;
	}
/**
 *  骑士距离门店距离用来排序 只有在骑士查询我的任务 ——待取货的任务时有效
 * @param distance_OrderBy
 */
	public void setDistance_OrderBy(Double distance_OrderBy) {
		this.distance_OrderBy = distance_OrderBy;
	} 

public double getKm() { 
	return	ParseHelper.ToDouble(new DecimalFormat("0.0").format(km),0); 
}

public void setKm(double km) {
	this.km = km;
}

public double getWeight() {
	return weight;
}

public void setWeight(double weight) {
	this.weight = weight;
}

public int getPlatform() {
	return platform;
}

public void setPlatform(int platform) {
	this.platform = platform;
}

public String getPickupCode() {
	return pickupCode;
}

public void setPickupCode(String pickupCode) {
	this.pickupCode = pickupCode;
}

public String getGrabTime() {
	return grabTime;
}

public void setGrabTime(String grabTime) {
	this.grabTime = grabTime;
}

public String getPubName() {
	return pubName;
}

public void setPubName(String pubName) {
	this.pubName = pubName;
}

public double getBalancePrice() {
	return balancePrice;
}

public void setBalancePrice(double balancePrice) {
	this.balancePrice = balancePrice;
}
}

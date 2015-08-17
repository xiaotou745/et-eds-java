package com.edaisong.entity.domain;

public class OrderCommission {
	 /// <summary>
    ///订单金额
    /// </summary>
    public float amount;
    /// <summary>
    /// 外送费
    /// </summary>
    public float distribSubsidy;

    /// <summary>
    /// 订单数量
    /// </summary>
    public int orderCount; 

    /// <summary>
    /// 商户结算比例
    /// </summary>
    public float businessCommission;

    /// <summary>
    /// 结算类型
    /// </summary>
    public int commissionType ;

    /// <summary>
    /// 固定金额
    /// </summary>
    public float commissionFixValue;

    /// <summary>
    /// 商家分组ID
    /// </summary>
    public int businessGroupId;
    /// <summary>
    /// 策略ID
    /// </summary>
    public int strategyId ;

    /// <summary>
    /// 网站补贴
    /// </summary>
    public float orderWebSubsidy;

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public float getDistribSubsidy() {
		return distribSubsidy;
	}

	public void setDistribSubsidy(float distribSubsidy) {
		this.distribSubsidy = distribSubsidy;
	}

	public int getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}

	public float getBusinessCommission() {
		return businessCommission;
	}

	public void setBusinessCommission(float businessCommission) {
		this.businessCommission = businessCommission;
	}

	public int getCommissionType() {
		return commissionType;
	}

	public void setCommissionType(int commissionType) {
		this.commissionType = commissionType;
	}

	public float getCommissionFixValue() {
		return commissionFixValue;
	}

	public void setCommissionFixValue(float commissionFixValue) {
		this.commissionFixValue = commissionFixValue;
	}

	public int getBusinessGroupId() {
		return businessGroupId;
	}

	public void setBusinessGroupId(int businessGroupId) {
		this.businessGroupId = businessGroupId;
	}

	public int getStrategyId() {
		return strategyId;
	}

	public void setStrategyId(int strategyId) {
		this.strategyId = strategyId;
	}

	public float getOrderWebSubsidy() {
		return orderWebSubsidy;
	}

	public void setOrderWebSubsidy(float orderWebSubsidy) {
		this.orderWebSubsidy = orderWebSubsidy;
	}
    
}

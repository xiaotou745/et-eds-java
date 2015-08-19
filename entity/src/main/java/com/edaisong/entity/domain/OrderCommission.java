package com.edaisong.entity.domain;

import java.lang.Double;

/**
 * 订单佣金
 * @author pengyi
 * @date 20150817
 *
 */
public class OrderCommission {
	/**
	 * 订单金额
	 */
    private Double amount;
    /**
     * 外送费
     */
    private Double distribSubsidy;

    /**
     * 订单数量
     */
    private int orderCount; 

    /**
     * 商户结算比例
     */
    private Double businessCommission;

    /**
     * 结算类型
     */
    private int commissionType ;

    /**
     * 固定金额
     */
    private Double commissionFixValue;

    /**
     * 商家分组ID
     */
    private int businessGroupId;
    /**
     * 策略ID
     */
    private int strategyId ;

    /**
     * 网站补贴
     */
    private Double orderWebSubsidy;

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getDistribSubsidy() {
		return distribSubsidy;
	}

	public void setDistribSubsidy(Double distribSubsidy) {
		this.distribSubsidy = distribSubsidy;
	}

	public int getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}

	public Double getBusinessCommission() {
		return businessCommission;
	}

	public void setBusinessCommission(Double businessCommission) {
		this.businessCommission = businessCommission;
	}

	public int getCommissionType() {
		return commissionType;
	}

	public void setCommissionType(int commissionType) {
		this.commissionType = commissionType;
	}

	public Double getCommissionFixValue() {
		return commissionFixValue;
	}

	public void setCommissionFixValue(Double commissionFixValue) {
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

	public Double getOrderWebSubsidy() {
		return orderWebSubsidy;
	}

	public void setOrderWebSubsidy(Double orderWebSubsidy) {
		this.orderWebSubsidy = orderWebSubsidy;
	}
    
}

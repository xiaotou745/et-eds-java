package com.edaisong.entity.domain;

import java.math.BigDecimal;

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
    private BigDecimal amount;
    /**
     * 外送费
     */
    private BigDecimal distribSubsidy;

    /**
     * 订单数量
     */
    private int orderCount; 

    /**
     * 商户结算比例
     */
    private BigDecimal businessCommission;

    /**
     * 结算类型
     */
    private int commissionType ;

    /**
     * 固定金额
     */
    private BigDecimal commissionFixValue;

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
    private BigDecimal orderWebSubsidy;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getDistribSubsidy() {
		return distribSubsidy;
	}

	public void setDistribSubsidy(BigDecimal distribSubsidy) {
		this.distribSubsidy = distribSubsidy;
	}

	public int getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}

	public BigDecimal getBusinessCommission() {
		return businessCommission;
	}

	public void setBusinessCommission(BigDecimal businessCommission) {
		this.businessCommission = businessCommission;
	}

	public int getCommissionType() {
		return commissionType;
	}

	public void setCommissionType(int commissionType) {
		this.commissionType = commissionType;
	}

	public BigDecimal getCommissionFixValue() {
		return commissionFixValue;
	}

	public void setCommissionFixValue(BigDecimal commissionFixValue) {
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

	public BigDecimal getOrderWebSubsidy() {
		return orderWebSubsidy;
	}

	public void setOrderWebSubsidy(BigDecimal orderWebSubsidy) {
		this.orderWebSubsidy = orderWebSubsidy;
	}
    
}

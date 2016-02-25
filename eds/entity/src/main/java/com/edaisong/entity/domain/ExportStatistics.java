package com.edaisong.entity.domain;

/**
 * 集团订单统计 导出
 * @author CaoHeYang
 * @date 20160225
 */
public class ExportStatistics {
	private String monthDate;
	private String businessName;
	private Integer compliteCount;
	private Double totalSettleMoney;
	private Double groupPay;
	private Double businessPay;
	private Double totalAmount;
	private Integer canelCount;
	/**
	 * 日期
	 * @return
	 */
	public String getMonthDate() {
		return monthDate;
	}
	/**
	 * 日期
	 * @param monthDate
	 */
	public void setMonthDate(String monthDate) {
		this.monthDate = monthDate;
	}
	/**
	 * 门店名称
	 * @return
	 */
	public String getBusinessName() {
		return businessName;
	}
	/**
	 * 门店名称
	 * @param businessName
	 */
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	/**
	 * 订单数量
	 * @return
	 */
	public Integer getCompliteCount() {
		return compliteCount;
	}
	/**
	 * 订单数量
	 * @param compliteCount
	 */
	public void setCompliteCount(Integer compliteCount) {
		this.compliteCount = compliteCount;
	}
	/**
	 * 配送费支出
	 * @return
	 */
	public Double getTotalSettleMoney() {
		return totalSettleMoney;
	}
	/**
	 * 配送费支出
	 * @param totalSettleMoney
	 */
	public void setTotalSettleMoney(Double totalSettleMoney) {
		this.totalSettleMoney = totalSettleMoney;
	}
	/**
	 * 消费集团金额
	 * @return
	 */
	public Double getGroupPay() {
		return groupPay;
	}
	/**
	 * 消费集团金额
	 * @param groupPay
	 */
	public void setGroupPay(Double groupPay) {
		this.groupPay = groupPay;
	}
	/**
	 * 门店自费金额
	 * @return
	 */
	public Double getBusinessPay() {
		return businessPay;
	}
	/**
	 * 门店自费金额
	 * @param businessPay
	 */
	public void setBusinessPay(Double businessPay) {
		this.businessPay = businessPay;
	}
	/**
	 * 菜品总金额
	 * @return
	 */
	public Double getTotalAmount() {
		return totalAmount;
	}
	/**
	 * 菜品总金额
	 * @param totalAmount
	 */
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	/**
	 * 取消订单量
	 * @return
	 */
	public Integer getCanelCount() {
		return canelCount;
	}
	/**
	 * 取消订单量
	 * @param canelCount
	 */
	public void setCanelCount(Integer canelCount) {
		this.canelCount = canelCount;
	}
}

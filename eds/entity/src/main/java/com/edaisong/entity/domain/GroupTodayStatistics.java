package com.edaisong.entity.domain;

/**
 * 集团首页 今日统计
 * @author CaoHeYang
 * @date 20160223
 */
public class GroupTodayStatistics {
	private Double balancePrice;
	private Double todayPay;
	private Double todayDishTotal;

	/**
	 * 集团当前余额
	 * 
	 * @return
	 */
	public Double getBalancePrice() {
		return balancePrice;
	}

	/**
	 * 集团当前余额
	 * 
	 * @param balancePrice
	 */
	public void setBalancePrice(Double balancePrice) {
		this.balancePrice = balancePrice;
	}

	/**
	 * 集团今日支出金额
	 * 
	 * @return
	 */
	public Double getTodayPay() {
		return todayPay;
	}

	/**
	 * 集团今日支出金额
	 * 
	 * @param todayPay
	 */
	public void setTodayPay(Double todayPay) {
		this.todayPay = todayPay;
	}

	/**
	 * 今日使用集团余额产生的餐品金额
	 * 
	 * @return
	 */
	public Double getTodayDishTotal() {
		return todayDishTotal;
	}

	/**
	 * 今日使用集团余额产生的餐品金额
	 * 
	 * @param todayDishTotal
	 */
	public void setTodayDishTotal(Double todayDishTotal) {
		this.todayDishTotal = todayDishTotal;
	}

}

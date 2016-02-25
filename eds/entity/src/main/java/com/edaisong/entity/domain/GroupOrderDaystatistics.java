package com.edaisong.entity.domain;

/**
 * 集团订单统计图标 数据载体
 * 
 * @author CaoHeYang
 * @date 20160224
 */
public class GroupOrderDaystatistics {
	private String monthDate;
	private int orderCount;
	private Double settleMoney;
	private Double amount;

	/**
	 * 日期
	 * 
	 * @return
	 */
	public String getMonthDate() {
		return monthDate;
	}

	/**
	 * 日期
	 * 
	 * @param monthDate
	 */
	public void setMonthDate(String monthDate) {
		this.monthDate = monthDate;
	}

	/**
	 * 订单数量
	 * 
	 * @return
	 */
	public int getOrderCount() {
		return orderCount;
	}

	/**
	 * 订单数量
	 * 
	 * @param orderCount
	 */
	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}

	/**
	 * 配送费支出
	 * 
	 * @return
	 */
	public Double getSettleMoney() {
		return settleMoney;
	}

	/**
	 * 配送费支出
	 * 
	 * @param settleMoney
	 */
	public void setSettleMoney(Double settleMoney) {
		this.settleMoney = settleMoney;
	}

	/**
	 * 拒单数量
	 * 
	 * @return
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * 拒单数量
	 * 
	 * @param amount
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}

}

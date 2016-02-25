package com.edaisong.entity.domain;

import java.util.List;

/**
 * 集团订单统计
 * @author CaoHeYang
 * @date 20160224
 */
public class GroupOrderstatistics {
	private int compliteCount;
	private int canelCount;
	private Double totalSettleMoney;
	private Double totalAmount;
	private List<GroupOrderDaystatistics> days;

	/**
	 * 完成数量
	 * 
	 * @return
	 */
	public int getCompliteCount() {
		return compliteCount;
	}

	/**
	 * 完成数量
	 * 
	 * @param compliteCount
	 */
	public void setCompliteCount(int compliteCount) {
		this.compliteCount = compliteCount;
	}

	/**
	 * 拒单数量
	 * 
	 * @return
	 */
	public int getCanelCount() {
		return canelCount;
	}

	/**
	 * 拒单数量
	 * 
	 * @param canelCount
	 */
	public void setCanelCount(int canelCount) {
		this.canelCount = canelCount;
	}

	/**
	 * 配送费总额
	 * 
	 * @return
	 */
	public Double getTotalSettleMoney() {
		return totalSettleMoney;
	}

	/**
	 * 配送费总额
	 * 
	 * @param totalSettleMoney
	 */
	public void setTotalSettleMoney(Double totalSettleMoney) {
		this.totalSettleMoney = totalSettleMoney;
	}

	/**
	 * 菜品总额
	 * 
	 * @return
	 */
	public Double getTotalAmount() {
		return totalAmount;
	}

	/**
	 * 菜品总额
	 * 
	 * @param totalAmount
	 */
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	/**
	 * 日详情
	 * 
	 * @return
	 */
	public List<GroupOrderDaystatistics> getDays() {
		return days;
	}

	/**
	 * 日详情
	 * 
	 * @param days
	 */
	public void setDays(List<GroupOrderDaystatistics> days) {
		this.days = days;
	}

}

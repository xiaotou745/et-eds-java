package com.edaisong.toolsentity.domain;
/**
 * C端任务统计接口 骑士日订单统计实体
 * @author WangXuDan
 * @date 20150910
 */
public class DaySatisticsC {
	private int orderCount;
	private double orderCommission;
	private String dateInfo;
	private String monthDate;
	/**
	 * 日订单量
	 * 
	 * @return
	 */
	public int getOrderCount() {
		return orderCount;
	}

	/**
	 * 日订单量
	 * 
	 * @param orderCount
	 */
	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}
	/**
	 * 日期概述
	 * 
	 * @return
	 */
	public String getDateInfo() {
		return dateInfo;
	}

	/**
	 * . 日期概述
	 * 
	 * @param dateInfo
	 */
	public void setDateInfo(String dateInfo) {
		this.dateInfo = dateInfo;
	}
	/**
	 * 日订单佣金
	 * 
	 * @return
	 */
	public double getOrderCommission() {
		return orderCommission;
	}

	/**
	 * 日订单佣金
	 * 
	 * @param orderCommission
	 */
	public void setOrderCommission(double orderCommission) {
		this.orderCommission = orderCommission;
	}
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
}

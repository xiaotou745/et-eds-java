package com.edaisong.toolsentity.domain;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * B端任务统计接口 商户日订单信息统计概述
 * 
 * @author CaoHeYang
 * @date 20150910
 */
public class DaySatisticsB {
	private int orderCount;
	private int serviceClienterCount;
	private String dateInfo;
	@JsonIgnore
	private String monthDate;
	private List<ServiceClienter> serviceClienters;

	public String getMonthDate() {
		return monthDate;
	}

	public void setMonthDate(String monthDate) {
		this.monthDate = monthDate;
	}

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
	 * 日服务骑士数量
	 * 
	 * @return
	 */
	public int getServiceClienterCount() {
		return serviceClienterCount;
	}

	/**
	 * 日服务骑士数量
	 * 
	 * @param serviceClienterCount
	 */
	public void setServiceClienterCount(int serviceClienterCount) {
		this.serviceClienterCount = serviceClienterCount;
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
	 * 
	 * @return
	 */
	public List<ServiceClienter> getServiceClienters() {
		return serviceClienters;
	}

	/**
	 * 
	 * @param serviceClienters
	 */
	public void setServiceClienters(List<ServiceClienter> serviceClienters) {
		this.serviceClienters = serviceClienters;
	}
}

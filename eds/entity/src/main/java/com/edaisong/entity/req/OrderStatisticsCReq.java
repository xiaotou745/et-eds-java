package com.edaisong.entity.req;
/**
 * C端任务统计接口 参数实体
 * @author WangXuDan
 * @date 20150910
 */
public class OrderStatisticsCReq {
	private String monthInfo;
	private int clienterId;
	private int platform;
	/**
	 * 年月
	 * 
	 * @return
	 */
	public String getMonthInfo() {
		return monthInfo;
	}

	/**
	 * 年月
	 * 
	 * @param monthInfo
	 */
	public void setMonthInfo(String monthInfo) {
		this.monthInfo = monthInfo;
	}

	/**
	 * 骑士ID
	 * 
	 * @return
	 */
	public int getClienterId() {
		return clienterId;
	}

	/**
	 * 骑士ID
	 * 
	 * @param clienterId
	 */
	public void setClienterId(int clienterId) {
		this.clienterId = clienterId;
	}

	public int getPlatform() {
		return platform;
	}

	public void setPlatform(int platform) {
		this.platform = platform;
	}
}

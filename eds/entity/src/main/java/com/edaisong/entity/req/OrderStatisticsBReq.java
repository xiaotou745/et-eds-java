package com.edaisong.entity.req;

/**
 * B端任务统计接口 参数
 * 
 * @author CaoHeYang
 * @date 20150910
 * @return
 */
public class OrderStatisticsBReq {
	private String monthInfo;
	private int businessId;
	private int platform;
	/**
	 * 月份
	 * 
	 * @return
	 */
	public String getMonthInfo() {
		return monthInfo;
	}

	/**
	 * 月份
	 * 
	 * @param monthInfo
	 */
	public void setMonthInfo(String monthInfo) {
		this.monthInfo = monthInfo;
	}

	/**
	 * 商户ID
	 * 
	 * @return
	 */
	public int getBusinessId() {
		return businessId;
	}

	/**
	 * 商户ID
	 * 
	 * @param businessId
	 */
	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}

	public int getPlatform() {
		if(platform == 0){
			return 1;
		}
		return platform;
	}

	public void setPlatform(int platform) {
		this.platform = platform;
	}

}

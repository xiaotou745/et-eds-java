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
	private String platform;
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

	public String getPlatform() {
		if(platform == null ||platform == ""){
			return "1,3";
		}
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

}

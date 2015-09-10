package com.edaisong.entity.req;

/**
 * 
 * @author CaoHeYang
 *
 */
public class QueryOrderBReq extends QueryOrderBaseReq {
	private String dateInfo;
	private Integer clienterId;

	/**
	 * 日期 可空
	 * 
	 * @return
	 */
	public String getDateInfo() {
		return dateInfo;
	}

	/**
	 * 日期可空
	 * 
	 * @param dateInfo
	 */
	public void setDateInfo(String dateInfo) {
		this.dateInfo = dateInfo;
	}

	/**
	 * 骑士 id 可空
	 * 
	 * @return
	 */
	public Integer getClienterId() {
		return clienterId;
	}

	/**
	 * 骑士 id 可空
	 * 
	 * @param clienterId
	 */
	public void setClienterId(Integer clienterId) {
		this.clienterId = clienterId;
	}
}

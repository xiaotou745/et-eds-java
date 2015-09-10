package com.edaisong.entity.req;

/**
 * 
 * @author CaoHeYang
 *
 */
public class QueryOrderBReq extends QueryOrderBaseReq {
	private String DateInfo;
	private int clienterId;

	/**
	 * 日期 可空
	 * 
	 * @return
	 */
	public String getDateInfo() {
		return DateInfo;
	}

	/**
	 * 日期可空
	 * 
	 * @param dateInfo
	 */
	public void setDateInfo(String dateInfo) {
		DateInfo = dateInfo;
	}

	/**
	 * 骑士 id 可空
	 * 
	 * @return
	 */
	public int getClienterId() {
		return clienterId;
	}

	/**
	 * 骑士 id 可空
	 * 
	 * @param clienterId
	 */
	public void setClienterId(int clienterId) {
		this.clienterId = clienterId;
	}
}

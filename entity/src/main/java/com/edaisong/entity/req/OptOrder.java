package com.edaisong.entity.req;

import com.edaisong.entity.common.RequestBase;

/**
 * 审核通过订单入参
 * @author CaoHeYang
 * @date 20150828
 */
public class OptOrder extends RequestBase{
    private int optUserId ;
    private String optUserName ;
    private String optLog ;
    private int orderId ;
    private String orderNo ;
    /**
     * 操作人id
     * @return
     */
	public int getOptUserId() {
		return optUserId;
	}
	/**
	 * 操作人id
	 * @param optUserId
	 */
	public void setOptUserId(int optUserId) {
		this.optUserId = optUserId;
	}
	/**
	 * 操作人名称
	 * @return
	 */
	public String getOptUserName() {
		return optUserName;
	}
	/**
	 * 操作人名称
	 * @param optUserName
	 */
	public void setOptUserName(String optUserName) {
		this.optUserName = optUserName;
	}
	/**
	 * 操作描述
	 * @return
	 */
	public String getOptLog() {
		return optLog;
	}
	/**
	 * 操作描述
	 * @param optLog
	 */
	public void setOptLog(String optLog) {
		this.optLog = optLog;
	}
	/**
	 * 订单id
	 * @return
	 */
	public int getOrderId() {
		return orderId;
	}
	/**
	 * 订单id
	 * @param orderId
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	/**
	 * 订单号
	 * @return
	 */
	public String getOrderNo() {
		return orderNo;
	}
	/**
	 * 订单号
	 * @param orderNo
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
    
}

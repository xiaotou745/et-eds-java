package com.edaisong.toolsentity.req;


/**
 * 商家后台 订单详情页面基础数据   查询条件实体
 * @author CaoHeYang
 * Date 20150804
 */
public class OrderDetailBusinessReq {

	private String orderNo;
	
	private int businessId;

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

	/**
	 * 商户id
	 * @return
	 */
	public int getBusinessId() {
		return businessId;
	}

	/**
	 * 商户id
	 * @param businessId
	 */
	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}


}

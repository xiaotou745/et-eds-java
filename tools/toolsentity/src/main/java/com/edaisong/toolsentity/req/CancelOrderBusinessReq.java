package com.edaisong.toolsentity.req;

/**
 * 商户取消订单功能 参数实体
 * @author CaoHeYang
 * @Date 20150804
 */
public class CancelOrderBusinessReq {
	  
    private int businessId ;
   
    private int orderId ;
   
    private String orderNo ;

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

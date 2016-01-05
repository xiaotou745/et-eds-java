package com.edaisong.entity.req;

/**
 * 里程计算 订单被处理后给移动端推送通知
 * 
 * @author CaoHeYang
 * @date 20160105
 */
public class ShanSongPushOrderReq {
	private Long orderId;

	/**
	 * 订单id
	 * 
	 * @return
	 */
	public Long getOrderId() {
		return orderId;
	}

	/**
	 * 订单id
	 * 
	 * @param orderId
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

}

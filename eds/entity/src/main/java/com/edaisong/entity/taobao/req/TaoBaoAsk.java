package com.edaisong.entity.taobao.req;

/**
 * 淘宝 api 调用 返回值数据结构
 * @author CaoHeYang
 * @date 2015年11月13日 14:48:49
 */
public class TaoBaoAsk {

	/** 
	* 物流单号
	 */
	private Long deliveryOrderNo;

	public void setDeliveryOrderNo(Long deliveryOrderNo) {
		this.deliveryOrderNo = deliveryOrderNo;
	}
	public Long getDeliveryOrderNo() {
		return this.deliveryOrderNo;
	}
}

package com.edaisong.toolsentity.req;

/**
 * 订单Other 查询实体类 
 * @author CaoHeYang
 *@date 20150831
 */
public class OrderOtherSearch {
    private int orderId;
    private String deductCommissionReason;
    private int deductCommissionType ;
    private Double realOrderCommission ;
    /**
     *  订单ID
     * @return
     */
	public int getOrderId() {
		return orderId;
	}
	/**
	 *  订单ID
	 * @param orderId
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	/**
	 * 扣除补贴原因
	 * @return
	 */
	public String getDeductCommissionReason() {
		return deductCommissionReason;
	}
	/**
	 * 扣除补贴原因
	 * @param deductCommissionReason
	 */
	public void setDeductCommissionReason(String deductCommissionReason) {
		this.deductCommissionReason = deductCommissionReason;
	}
	/**
	 *  扣除补贴类型: 1 自动扣除    2 人工扣除
	 * @return
	 */
	public int getDeductCommissionType() {
		return deductCommissionType;
	}
	/**
	 *  扣除补贴类型: 1 自动扣除    2 人工扣除
	 * @param deductCommissionType
	 */
	public void setDeductCommissionType(int deductCommissionType) {
		this.deductCommissionType = deductCommissionType;
	}
	/**
	 * 真实订单佣金
	 * @return
	 */
	public Double getRealOrderCommission() {
		return realOrderCommission;
	}
	/**
	 * 真实订单佣金
	 * @param realOrderCommission
	 */
	public void setRealOrderCommission(Double realOrderCommission) {
		this.realOrderCommission = realOrderCommission;
	}
    
}

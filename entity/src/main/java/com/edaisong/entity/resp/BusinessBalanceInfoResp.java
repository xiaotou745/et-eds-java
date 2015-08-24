package com.edaisong.entity.resp;

import com.edaisong.entity.common.ResponseBase;

/**
 * 商户发单，点击按纽钱查询商户余额信息，以及该订单的结算信息
 * @author CaoHeYang
 * @Date 20150824 
 */
public class BusinessBalanceInfoResp extends ResponseBase {

	/**
	 * 当前商户剩余余额
	 */
	private double balanceprice;
	
	/**
	 * 结算金额
	 */
	private double settleMoney;
    
	/**
	 * 当前商户剩余余额
	 * @return
	 */
	public double getBalanceprice() {
		return balanceprice;
	}

	/**
	 * 当前商户剩余余额
	 * @param balanceprice
	 */
	public void setBalanceprice(double balanceprice) {
		this.balanceprice = balanceprice;
	}

	/**
	 * 当前订单结算金额
	 * @return
	 */
	public double getSettleMoney() {
		return settleMoney;
	}

	/**
	 * 当前订单结算金额
	 * @param settleMoney
	 */
	public void setSettleMoney(double settleMoney) {
		this.settleMoney = settleMoney;
	}

}

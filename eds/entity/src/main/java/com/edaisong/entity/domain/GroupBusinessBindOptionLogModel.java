package com.edaisong.entity.domain;

import com.edaisong.entity.GroupBusinessBindOptionLog;

/**
 *
 * @author  pengyi 
 * @date 2015年9月9日 下午5:43:55
 * @version 1.0
 * @parameter
 * @since
 */
public class GroupBusinessBindOptionLogModel extends GroupBusinessBindOptionLog{
	private String phoneNo;
	private String businessName;
	private double balancePrice;
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public double getBalancePrice() {
		return balancePrice;
	}
	public void setBalancePrice(double balancePrice) {
		this.balancePrice = balancePrice;
	}
}

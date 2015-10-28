package com.edaisong.entity.domain;

import java.text.DecimalFormat;

import com.edaisong.entity.GroupBusinessRelation;

/**
 *
 * @author  pengyi 
 * @date 2015年9月9日 上午10:44:22
 * @version 1.0
 * @parameter
 * @since
 */
public class GroupBusinessRelationModel extends GroupBusinessRelation{
	private String name;//门店名称
	private String groupName;//集团名称
	private String phoneNo;
	private double balancePrice;
	private double useGroupMoney;//消费集团金额
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public double getBalancePrice() {
		return balancePrice;
	}
	public String getBalancePriceString() {
		return new DecimalFormat("0.00").format(balancePrice);
	}
	public void setBalancePrice(double balancePrice) {
		this.balancePrice = balancePrice;
	}
	public double getUseGroupMoney() {
		return useGroupMoney;
	} 
	public String getUseGroupMoneyString() {
		return  new DecimalFormat("0.00").format(useGroupMoney);
	}
	public void setUseGroupMoney(double useGroupMoney) {
		this.useGroupMoney = useGroupMoney;
	}
}

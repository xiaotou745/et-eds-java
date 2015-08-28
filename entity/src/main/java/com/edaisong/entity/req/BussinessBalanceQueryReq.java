package com.edaisong.entity.req;

import com.edaisong.entity.common.PagedRequestBase;

public class BussinessBalanceQueryReq extends PagedRequestBase{
	//开始执日期
    private String startDate;
    //结束日期
    private String endDate;
    //商户名称
    private String name;
    //商户id
    private String businessId;
    //商户账号
    private String phoneNo;
    //城市id
    private String cityId;
    //排序方式(0为充值时间倒序，1为充值金额降序，2为充值金额升序)
    private int orderType;
    //充值类型:1系统充值；2客户端充值
    private int rechargeType;
    //充值金额
    private double rechargePrice;
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public int getOrderType() {
		return orderType;
	}
	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}
	public int getRechargeType() {
		return rechargeType;
	}
	public void setRechargeType(int rechargeType) {
		this.rechargeType = rechargeType;
	}
	public double getRechargePrice() {
		return rechargePrice;
	}
	public void setRechargePrice(double rechargePrice) {
		this.rechargePrice = rechargePrice;
	}
}

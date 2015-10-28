package com.edaisong.toolsentity.domain;

/*
 * 月账单类型
 * */
public class AccountBillModel {
	private String dayInfo;
	private Double inMoney;
	private Double outMoney;
	private Integer hasDatas;
	public Integer getHasDatas() {
		return hasDatas;
	}
	public void setHasDatas(Integer hasDatas) {
		this.hasDatas = hasDatas;
	}
	public String getDayInfo() {
		return dayInfo;
	}
	public void setDayInfo(String dayInfo) {
		this.dayInfo = dayInfo;
	}
	public Double getInMoney() {
		return inMoney;
	}
	public void setInMoney(Double inMoney) {
		this.inMoney = inMoney;
	}
	public Double getOutMoney() {
		return outMoney;
	}
	public void setOutMoney(Double outMoney) {
		this.outMoney = outMoney;
	}

}

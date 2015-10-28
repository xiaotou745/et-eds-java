package com.edaisong.entity;

import java.util.ArrayList;
import java.util.List;

import com.edaisong.entity.domain.AccountBillModel;
/**
 * 月账单返回类实体 商户骑士公用
 * 茹化肖
 * 2015年9月10日13:51:50
 * 
 * */
public class AccountBillResultModel {
	
	public AccountBillResultModel(){
		this.listDays=new ArrayList<AccountBillModel>();
	}
	private double inMoney;
	private double outMoney;
	private List<AccountBillModel> listDays;
	public double getInMoney() {
		return inMoney;
	}
	public void setInMoney(double inMoney) {
		this.inMoney = inMoney;
	}
	public double getOutMoney() {
		return outMoney;
	}
	public void setOutMoney(double outMoney) {
		this.outMoney = outMoney;
	}
	public List<AccountBillModel> getListDays() {
		return listDays;
	}
	public void setListDays(List<AccountBillModel> listDays) {
		this.listDays = listDays;
	}
	

}

package com.edaisong.entity.domain;

import java.awt.List;
import java.util.ArrayList;

/**
 * 
 * API接口 获取商家日账单返回值
 * 
 * */
public class AccountBillDayCResultModel {

	public AccountBillDayCResultModel() {
		this.listRecordS = new ArrayList<AccountBillDayCModel>();
	}

	private double inMoney;
	private double outMoney;
	private ArrayList<AccountBillDayCModel> listRecordS;

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

	public ArrayList<AccountBillDayCModel> getListRecordS() {
		return this.listRecordS;
	}

	public void setListRecordS(ArrayList<AccountBillDayCModel> objects) {
		this.listRecordS = objects;
	}

}

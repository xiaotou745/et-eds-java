package com.edaisong.entity.domain;

import java.awt.List;
import java.util.ArrayList;

/**
 * 
 * API接口 获取商家日账单返回值
 * 
 * */
public class AccountBillDayResultModel {

	public AccountBillDayResultModel() {
		this.listRecordS = new ArrayList<AccountBillDayModel>();
	}

	private double inMoney;
	private double outMoney;
	private ArrayList<AccountBillDayModel> listRecordS;

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

	public ArrayList<AccountBillDayModel> getListRecordS() {
		return this.listRecordS;
	}

	public void setListRecordS(ArrayList<AccountBillDayModel> objects) {
		this.listRecordS = objects;
	}

}

package com.edaisong.entity.resp;

public class TaskDistributionResp {
	private int masterKM;
	private int masterKG;
	private double masterDistributionPrice;
	private int oneKM;
	private double oneDistributionPrice;
	private int twoKG;
	private double twoDistributionPrice;
	
	public double getMasterDistributionPrice() {
		return masterDistributionPrice;
	}
	public void setMasterDistributionPrice(double masterDistributionPrice) {
		this.masterDistributionPrice = masterDistributionPrice;
	}
	public int getMasterKG() {
		return masterKG;
	}
	public void setMasterKG(int masterKG) {
		this.masterKG = masterKG;
	}
	public int getMasterKM() {
		return masterKM;
	}
	public void setMasterKM(int masterKM) {
		this.masterKM = masterKM;
	}
	
	
	public int getOneKM() {
		return oneKM;
	}
	public void setOneKM(int oneKM) {
		this.oneKM = oneKM;
	}
	public double getOneDistributionPrice() {
		return oneDistributionPrice;
	}
	public void setOneDistributionPrice(double oneDistributionPrice) {
		this.oneDistributionPrice = oneDistributionPrice;
	}
	public int getTwoKG() {
		return twoKG;
	}
	public void setTwoKG(int twoKG) {
		this.twoKG = twoKG;
	}
	public double getTwoDistributionPrice() {
		return twoDistributionPrice;
	}
	public void setTwoDistributionPrice(double twoDistributionPrice) {
		this.twoDistributionPrice = twoDistributionPrice;
	}
}

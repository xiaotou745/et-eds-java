package com.edaisong.entity.req;

public class TaskDistributionConfigReq {
	private int masterKM;
	private int masterKG;
	private double masterPrice;
	private int oneKM;
	private double oneDistributionPrice;
	private int twoKG;
	private double twoDistributionPrice;
	private String isUpdate;
	private String logMsg;
	private int optId;
	private String optName;

	public int getMasterKG() {
		return masterKG;
	}

	public void setMasterKG(int masterKG) {
		this.masterKG = masterKG;
	}

	public int getOptId() {
		return optId;
	}

	public void setOptId(int optId) {
		this.optId = optId;
	}

	public String getOptName() {
		return optName;
	}

	public void setOptName(String optName) {
		this.optName = optName;
	}

	public String getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(String isUpdate) {
		this.isUpdate = isUpdate;
	}

	public String getLogMsg() {
		return logMsg;
	}

	public void setLogMsg(String logMsg) {
		this.logMsg = logMsg;
	}

	public int getMasterKM() {
		return masterKM;
	}

	public void setMasterKM(int masterKM) {
		this.masterKM = masterKM;
	}

	public double getMasterPrice() {
		return masterPrice;
	}

	public void setMasterPrice(double masterPrice) {
		this.masterPrice = masterPrice;
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

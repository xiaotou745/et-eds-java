package com.edaisong.entity;

public class TaskDistributionConfig {
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTaskDistributionId() {
		return taskDistributionId;
	}

	public void setTaskDistributionId(int taskDistributionId) {
		this.taskDistributionId = taskDistributionId;
	}

	public double getkM() {
		return kM;
	}

	public void setkM(double kM) {
		this.kM = kM;
	}

	public double getkG() {
		return kG;
	}

	public void setkG(double kG) {
		this.kG = kG;
	}

	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}

	public double getDistributionPrice() {
		return distributionPrice;
	}

	public void setDistributionPrice(double distributionPrice) {
		this.distributionPrice = distributionPrice;
	}

	public int getIsMaster() {
		return isMaster;
	}

	public void setIsMaster(int isMaster) {
		this.isMaster = isMaster;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	private int id;
	
	private int taskDistributionId;
	
	private double kM;
	
	private double kG;
	
	private int steps;	

	private double distributionPrice;
	
	private int isMaster;	
	
	private String remark;
	

}

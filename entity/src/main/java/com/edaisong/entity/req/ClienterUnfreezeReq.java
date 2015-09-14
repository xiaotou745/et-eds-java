package com.edaisong.entity.req;

public class ClienterUnfreezeReq {
	private Integer id;
	private Integer clienterId;
	private double forzenAmount;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getClienterId() {
		return clienterId;
	}
	public void setClienterId(Integer clienterId){
		this.clienterId = clienterId;
	}
	
	public double getForzenAmount() {
		return forzenAmount;
	}
	public void setForzenAmount(double forzenAmount){
		this.forzenAmount= forzenAmount;
	}
}

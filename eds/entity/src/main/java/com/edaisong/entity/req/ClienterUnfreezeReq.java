package com.edaisong.entity.req;

public class ClienterUnfreezeReq {
	private Integer id;
	private Integer clienterId;
	private Double forzenAmount;
	private String unfreezeReason;
	private String operator;
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
	
	public Double getForzenAmount() {
		return forzenAmount;
	}
	public void setForzenAmount(Double forzenAmount){
		this.forzenAmount= forzenAmount;
	}
	public String getUnfreezeReason(){
		return unfreezeReason;
	}
	public void setUnfreezeReason(String unfreezeReason){
		this.unfreezeReason=unfreezeReason;
	}
	
	public String getOperator(){
		return operator;
	}
	public void setOperator(String operator){
		this.operator = operator;
	}
}

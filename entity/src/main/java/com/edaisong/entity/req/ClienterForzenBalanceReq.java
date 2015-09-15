package com.edaisong.entity.req;

public class ClienterForzenBalanceReq {	 
	private Integer clienterId;
	private Double forzenAmount;
	private String forzenReason;
	
	private String operator ;
	
	public Integer getClienterId() {
		return clienterId;
	}
	public void setClienterId(Integer clienterId) {
		this.clienterId = clienterId;
	}
	 
	public Double getForzenAmount(){
		return forzenAmount;
	}
	public void setForzenAmount(Double forzenAmount){
		this.forzenAmount=forzenAmount;
	}
	public void setCityCode (Double forzenAmount){
		this.forzenAmount = forzenAmount;
	}
	
	public String getForzenReason(){
		return forzenReason;
	}
	public void setForzenReason(String forzenReason){
		this.forzenReason = forzenReason;
	}
	public String getOperator(){
		return operator;
	}
	public void setOperator(String operator){
		this.operator = operator;
	}
	
}

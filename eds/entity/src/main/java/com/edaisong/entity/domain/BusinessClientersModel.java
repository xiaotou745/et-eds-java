package com.edaisong.entity.domain;

/**
 * 商户下骑士列表
 * @author pengyi
 * @date 20150821
 *
 */
public class BusinessClientersModel {
	private Integer id;//clienterId

    private String phoneno;

    private String truename;

    private Double accountbalance;//账户余额

    private Integer bussinessid;//所属商户id

    private Integer workstatus;//工作状态  0上班 1下班

    private Double allowwithdrawprice;//允许提现金额
    
    private int finishedOrderCount;//完成订单数量

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public String getTruename() {
		return truename;
	}

	public void setTruename(String truename) {
		this.truename = truename;
	}

	public Double getAccountbalance() {
		return accountbalance;
	}

	public void setAccountbalance(Double accountbalance) {
		this.accountbalance = accountbalance;
	}

	public Integer getBussinessid() {
		return bussinessid;
	}

	public void setBussinessid(Integer bussinessid) {
		this.bussinessid = bussinessid;
	}

	public Integer getWorkstatus() {
		return workstatus;
	}

	public void setWorkstatus(Integer workstatus) {
		this.workstatus = workstatus;
	}

	public Double getAllowwithdrawprice() {
		return allowwithdrawprice;
	}

	public void setAllowwithdrawprice(Double allowwithdrawprice) {
		this.allowwithdrawprice = allowwithdrawprice;
	}

	public int getFinishedOrderCount() {
		return finishedOrderCount;
	}

	public void setFinishedOrderCount(int finishedOrderCount) {
		this.finishedOrderCount = finishedOrderCount;
	}
}

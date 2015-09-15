package com.edaisong.entity;

import java.lang.Double;
import java.util.Date;

public class BusinessBalanceRecord {
    private Long id;

    private Integer businessid;

    private Double amount;

    private Short status;

    private Double balance;

    private Short recordtype;

    private String operator;

    private Date operatetime;

    private Long withwardid;

    private String relationno;

    private String remark;

    private Short isenable;
    
    private Integer groupid;

    private double groupafterbalance;

    private double groupamount;

    public double getGroupamount() {
		return groupamount;
	}

	public void setGroupamount(double groupamount) {
		this.groupamount = groupamount;
	}

	public Integer getGroupid() {
		return groupid;
	}

	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBusinessid() {
        return businessid;
    }

    public void setBusinessid(Integer businessid) {
        this.businessid = businessid;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Short getRecordtype() {
        return recordtype;
    }

    public void setRecordtype(Short recordtype) {
        this.recordtype = recordtype;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Date getOperatetime() {
        return operatetime;
    }
    public void setOperatetime(Date operatetime) {
        this.operatetime = operatetime;
    }

    public Long getWithwardid() {
        return withwardid;
    }

    public void setWithwardid(Long withwardid) {
        this.withwardid = withwardid;
    }

    public String getRelationno() {
        return relationno;
    }

    public void setRelationno(String relationno) {
        this.relationno = relationno == null ? null : relationno.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Short getIsenable() {
        return isenable;
    }

    public void setIsenable(Short isenable) {
        this.isenable = isenable;
    }

	public double getGroupafterbalance() {
		return groupafterbalance;
	}

	public void setGroupafterbalance(double groupafterbalance) {
		this.groupafterbalance = groupafterbalance;
	}
}
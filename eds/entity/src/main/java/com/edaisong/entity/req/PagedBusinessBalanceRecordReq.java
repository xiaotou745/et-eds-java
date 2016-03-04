package com.edaisong.entity.req;

import java.lang.Double;
import java.util.Date;
import java.util.List;

import com.edaisong.entity.common.PagedRequestBase;
import com.edaisong.entity.common.RequestBase;

public class PagedBusinessBalanceRecordReq extends PagedRequestBase {
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
    
	private String operateTimeStart ;
    
    private String operateTimeEnd ;
    
    private Integer groupBusinessId;
    
	private String businessName ; 
    
    private String orderNo;
    
    public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}


    
    public Integer getGroupBusinessId() {
		return groupBusinessId;
	}

	public void setGroupBusinessId(Integer groupBusinessId) {
		this.groupBusinessId = groupBusinessId;
	}

	public String getOperateTimeStart() {
		return operateTimeStart;
	}

	public void setOperateTimeStart(String operateTimeStart) {
		this.operateTimeStart = operateTimeStart;
	}

	public String getOperateTimeEnd() {
		return operateTimeEnd;
	}

	public void setOperateTimeEnd(String operateTimeEnd) {
		this.operateTimeEnd = operateTimeEnd;
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
		this.operator = operator;
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
		this.relationno = relationno;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Short getIsenable() {
		return isenable;
	}

	public void setIsenable(Short isenable) {
		this.isenable = isenable;
	}

	public Integer getGroupid() {
		return groupid;
	}

	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}

	public double getGroupafterbalance() {
		return groupafterbalance;
	}

	public void setGroupafterbalance(double groupafterbalance) {
		this.groupafterbalance = groupafterbalance;
	}

	public double getGroupamount() {
		return groupamount;
	}

	public void setGroupamount(double groupamount) {
		this.groupamount = groupamount;
	}


}

package com.edaisong.entity.domain;

import java.util.Date;

import com.edaisong.core.enums.BusinessBalanceRecordRecordType;
import com.edaisong.entity.BusinessBalanceRecord;
/*
 * 集团收支记录
 */
public class GroupBusinessBalanceRecord {
	private Long id;

    private Integer businessid;
    private String businessname;
    private Double amount;

    private Short status;
    private String statusString;
    
    private Double balance;

    private Short recordtype;
    
    private String recordtypeString;
    private String operator;

    private Date operatetime;

    private Long withwardid;

    private String relationno;

    private String remark;

    private Short isenable;
    
    private Integer groupid;

    private double groupafterbalance;

    public Integer getGroupid() {
		return groupid;
	}

	public void setBusinessname(String businessname) {
		this.businessname = businessname;
	}
	
	public String getBusinessname() {
		return businessname;
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
    
    public String getStatusString() {
        return statusString;
    }

    public void setStatusString(String statusString) {
        this.statusString = statusString;
    }
    public String getRecordtypeString() {
        return  BusinessBalanceRecordRecordType.getEnum(this.recordtype).desc();
    }

    public void setRecordtypeString(String recordtypeString) {
        this.recordtypeString = recordtypeString;
    }
}

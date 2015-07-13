package com.edaisong.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ClienterBalanceRecord {
    private Long id;

    private Integer clienterid;

    private BigDecimal amount;

    private Short status;

    private BigDecimal balance;

    private Short recordtype;

    private String operator;

    private Date operatetime;

    private Long withwardid;

    private String relationno;

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getClienterid() {
        return clienterid;
    }

    public void setClienterid(Integer clienterid) {
        this.clienterid = clienterid;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
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
}
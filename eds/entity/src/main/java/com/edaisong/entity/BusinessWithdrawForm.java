package com.edaisong.entity;

import java.lang.Double;
import java.util.Date;

public class BusinessWithdrawForm {
    private Long id;

    private String withwardno;

    private Integer businessid;

    private Double balanceprice;

    private Double allowwithdrawprice;

    private Short status;

    private Double amount;

    private Double balance;

    private Date withdrawtime;

    private String auditor;

    private Date audittime;

    private String auditfailedreason;

    private String payer;

    private Date paytime;

    private String payfailedreason;

    private String truename;

    private String accountno;

    private Short accounttype;

    private Short belongtype;

    private String openbank;

    private String opensubbank;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWithwardno() {
        return withwardno;
    }

    public void setWithwardno(String withwardno) {
        this.withwardno = withwardno == null ? null : withwardno.trim();
    }

    public Integer getBusinessid() {
        return businessid;
    }

    public void setBusinessid(Integer businessid) {
        this.businessid = businessid;
    }

    public Double getBalanceprice() {
        return balanceprice;
    }

    public void setBalanceprice(Double balanceprice) {
        this.balanceprice = balanceprice;
    }

    public Double getAllowwithdrawprice() {
        return allowwithdrawprice;
    }

    public void setAllowwithdrawprice(Double allowwithdrawprice) {
        this.allowwithdrawprice = allowwithdrawprice;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Date getWithdrawtime() {
        return withdrawtime;
    }

    public void setWithdrawtime(Date withdrawtime) {
        this.withdrawtime = withdrawtime;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor == null ? null : auditor.trim();
    }

    public Date getAudittime() {
        return audittime;
    }

    public void setAudittime(Date audittime) {
        this.audittime = audittime;
    }

    public String getAuditfailedreason() {
        return auditfailedreason;
    }

    public void setAuditfailedreason(String auditfailedreason) {
        this.auditfailedreason = auditfailedreason == null ? null : auditfailedreason.trim();
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer == null ? null : payer.trim();
    }

    public Date getPaytime() {
        return paytime;
    }

    public void setPaytime(Date paytime) {
        this.paytime = paytime;
    }

    public String getPayfailedreason() {
        return payfailedreason;
    }

    public void setPayfailedreason(String payfailedreason) {
        this.payfailedreason = payfailedreason == null ? null : payfailedreason.trim();
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename == null ? null : truename.trim();
    }

    public String getAccountno() {
        return accountno;
    }

    public void setAccountno(String accountno) {
        this.accountno = accountno == null ? null : accountno.trim();
    }

    public Short getAccounttype() {
        return accounttype;
    }

    public void setAccounttype(Short accounttype) {
        this.accounttype = accounttype;
    }

    public Short getBelongtype() {
        return belongtype;
    }

    public void setBelongtype(Short belongtype) {
        this.belongtype = belongtype;
    }

    public String getOpenbank() {
        return openbank;
    }

    public void setOpenbank(String openbank) {
        this.openbank = openbank == null ? null : openbank.trim();
    }

    public String getOpensubbank() {
        return opensubbank;
    }

    public void setOpensubbank(String opensubbank) {
        this.opensubbank = opensubbank == null ? null : opensubbank.trim();
    }
}
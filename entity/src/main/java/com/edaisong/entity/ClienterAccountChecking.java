package com.edaisong.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ClienterAccountChecking {
    private Integer id;

    private Integer clienterid;

    private Date createdate;

    private BigDecimal flowstatmoney;

    private BigDecimal clientertotalmoney;

    private Date startdate;

    private Date enddate;

    private BigDecimal lasttotalmoney;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClienterid() {
        return clienterid;
    }

    public void setClienterid(Integer clienterid) {
        this.clienterid = clienterid;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public BigDecimal getFlowstatmoney() {
        return flowstatmoney;
    }

    public void setFlowstatmoney(BigDecimal flowstatmoney) {
        this.flowstatmoney = flowstatmoney;
    }

    public BigDecimal getClientertotalmoney() {
        return clientertotalmoney;
    }

    public void setClientertotalmoney(BigDecimal clientertotalmoney) {
        this.clientertotalmoney = clientertotalmoney;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public BigDecimal getLasttotalmoney() {
        return lasttotalmoney;
    }

    public void setLasttotalmoney(BigDecimal lasttotalmoney) {
        this.lasttotalmoney = lasttotalmoney;
    }
}
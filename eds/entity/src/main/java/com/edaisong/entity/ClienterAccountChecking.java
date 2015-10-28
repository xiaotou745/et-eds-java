package com.edaisong.entity;

import java.lang.Double;
import java.util.Date;

public class ClienterAccountChecking {
    private Integer id;

    private Integer clienterid;

    private Date createdate;

    private Double flowstatmoney;

    private Double clientertotalmoney;

    private Date startdate;

    private Date enddate;

    private Double lasttotalmoney;

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

    public Double getFlowstatmoney() {
        return flowstatmoney;
    }

    public void setFlowstatmoney(Double flowstatmoney) {
        this.flowstatmoney = flowstatmoney;
    }

    public Double getClientertotalmoney() {
        return clientertotalmoney;
    }

    public void setClientertotalmoney(Double clientertotalmoney) {
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

    public Double getLasttotalmoney() {
        return lasttotalmoney;
    }

    public void setLasttotalmoney(Double lasttotalmoney) {
        this.lasttotalmoney = lasttotalmoney;
    }
}
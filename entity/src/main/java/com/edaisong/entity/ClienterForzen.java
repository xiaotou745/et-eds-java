package com.edaisong.entity;

import java.util.Date;

public class ClienterForzen {
    private Integer id;

    private Integer clienterid;

    private Long forzenamount;

    private String forzenreason;

    private Date forzendate;

    private String thawreason;

    private Date thawdate;

    private Integer status;

    private String operator;

    private Date createdate;

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

    public Long getForzenamount() {
        return forzenamount;
    }

    public void setForzenamount(Long forzenamount) {
        this.forzenamount = forzenamount;
    }

    public String getForzenreason() {
        return forzenreason;
    }

    public void setForzenreason(String forzenreason) {
        this.forzenreason = forzenreason == null ? null : forzenreason.trim();
    }

    public Date getForzendate() {
        return forzendate;
    }

    public void setForzendate(Date forzendate) {
        this.forzendate = forzendate;
    }

    public String getThawreason() {
        return thawreason;
    }

    public void setThawreason(String thawreason) {
        this.thawreason = thawreason == null ? null : thawreason.trim();
    }

    public Date getThawdate() {
        return thawdate;
    }

    public void setThawdate(Date thawdate) {
        this.thawdate = thawdate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
}
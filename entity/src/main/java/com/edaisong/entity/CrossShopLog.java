package com.edaisong.entity;

import java.util.Date;

public class CrossShopLog {
    private Integer id;

    private Integer clienterid;

    private Long amount;

    private Integer businesscount;

    private Integer platform;

    private String remark;

    private Date inserttime;

    private Date rewardtime;

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

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Integer getBusinesscount() {
        return businesscount;
    }

    public void setBusinesscount(Integer businesscount) {
        this.businesscount = businesscount;
    }

    public Integer getPlatform() {
        return platform;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getInserttime() {
        return inserttime;
    }

    public void setInserttime(Date inserttime) {
        this.inserttime = inserttime;
    }

    public Date getRewardtime() {
        return rewardtime;
    }

    public void setRewardtime(Date rewardtime) {
        this.rewardtime = rewardtime;
    }
}
package com.edaisong.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Statistic {
    private Integer id;

    private Date inserttime;

    private Integer businesscount;

    private Integer rzqscount;

    private Integer ddrzqscount;

    private BigDecimal orderprice;

    private Integer misstioncount;

    private Integer ordercount;

    private Double businessaverageordercount;

    private Double missionaverageordercount;

    private Double clienteraverageordercount;

    private BigDecimal ysprice;

    private BigDecimal yfprice;

    private BigDecimal ykprice;

    private Integer onesubsidyordercount;

    private Integer twosubsidyordercount;

    private Integer threesubsidyordercount;

    private Integer zerosubsidyordercount;

    private Integer activebusiness;

    private Integer activeclienter;

    private BigDecimal incometotal;

    private BigDecimal rechargetotal;

    private BigDecimal withdrawbusinessprice;

    private BigDecimal businessbalance;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getInserttime() {
        return inserttime;
    }

    public void setInserttime(Date inserttime) {
        this.inserttime = inserttime;
    }

    public Integer getBusinesscount() {
        return businesscount;
    }

    public void setBusinesscount(Integer businesscount) {
        this.businesscount = businesscount;
    }

    public Integer getRzqscount() {
        return rzqscount;
    }

    public void setRzqscount(Integer rzqscount) {
        this.rzqscount = rzqscount;
    }

    public Integer getDdrzqscount() {
        return ddrzqscount;
    }

    public void setDdrzqscount(Integer ddrzqscount) {
        this.ddrzqscount = ddrzqscount;
    }

    public BigDecimal getOrderprice() {
        return orderprice;
    }

    public void setOrderprice(BigDecimal orderprice) {
        this.orderprice = orderprice;
    }

    public Integer getMisstioncount() {
        return misstioncount;
    }

    public void setMisstioncount(Integer misstioncount) {
        this.misstioncount = misstioncount;
    }

    public Integer getOrdercount() {
        return ordercount;
    }

    public void setOrdercount(Integer ordercount) {
        this.ordercount = ordercount;
    }

    public Double getBusinessaverageordercount() {
        return businessaverageordercount;
    }

    public void setBusinessaverageordercount(Double businessaverageordercount) {
        this.businessaverageordercount = businessaverageordercount;
    }

    public Double getMissionaverageordercount() {
        return missionaverageordercount;
    }

    public void setMissionaverageordercount(Double missionaverageordercount) {
        this.missionaverageordercount = missionaverageordercount;
    }

    public Double getClienteraverageordercount() {
        return clienteraverageordercount;
    }

    public void setClienteraverageordercount(Double clienteraverageordercount) {
        this.clienteraverageordercount = clienteraverageordercount;
    }

    public BigDecimal getYsprice() {
        return ysprice;
    }

    public void setYsprice(BigDecimal ysprice) {
        this.ysprice = ysprice;
    }

    public BigDecimal getYfprice() {
        return yfprice;
    }

    public void setYfprice(BigDecimal yfprice) {
        this.yfprice = yfprice;
    }

    public BigDecimal getYkprice() {
        return ykprice;
    }

    public void setYkprice(BigDecimal ykprice) {
        this.ykprice = ykprice;
    }

    public Integer getOnesubsidyordercount() {
        return onesubsidyordercount;
    }

    public void setOnesubsidyordercount(Integer onesubsidyordercount) {
        this.onesubsidyordercount = onesubsidyordercount;
    }

    public Integer getTwosubsidyordercount() {
        return twosubsidyordercount;
    }

    public void setTwosubsidyordercount(Integer twosubsidyordercount) {
        this.twosubsidyordercount = twosubsidyordercount;
    }

    public Integer getThreesubsidyordercount() {
        return threesubsidyordercount;
    }

    public void setThreesubsidyordercount(Integer threesubsidyordercount) {
        this.threesubsidyordercount = threesubsidyordercount;
    }

    public Integer getZerosubsidyordercount() {
        return zerosubsidyordercount;
    }

    public void setZerosubsidyordercount(Integer zerosubsidyordercount) {
        this.zerosubsidyordercount = zerosubsidyordercount;
    }

    public Integer getActivebusiness() {
        return activebusiness;
    }

    public void setActivebusiness(Integer activebusiness) {
        this.activebusiness = activebusiness;
    }

    public Integer getActiveclienter() {
        return activeclienter;
    }

    public void setActiveclienter(Integer activeclienter) {
        this.activeclienter = activeclienter;
    }

    public BigDecimal getIncometotal() {
        return incometotal;
    }

    public void setIncometotal(BigDecimal incometotal) {
        this.incometotal = incometotal;
    }

    public BigDecimal getRechargetotal() {
        return rechargetotal;
    }

    public void setRechargetotal(BigDecimal rechargetotal) {
        this.rechargetotal = rechargetotal;
    }

    public BigDecimal getWithdrawbusinessprice() {
        return withdrawbusinessprice;
    }

    public void setWithdrawbusinessprice(BigDecimal withdrawbusinessprice) {
        this.withdrawbusinessprice = withdrawbusinessprice;
    }

    public BigDecimal getBusinessbalance() {
        return businessbalance;
    }

    public void setBusinessbalance(BigDecimal businessbalance) {
        this.businessbalance = businessbalance;
    }
}
package com.edaisong.entity;

import java.math.BigDecimal;
import java.util.Date;

public class DeliveryCompany {
    private Integer id;

    private String deliverycompanyname;

    private String deliverycompanycode;

    private Integer isenable;

    private Integer settletype;

    private BigDecimal clienterfixmoney;

    private BigDecimal clientersettleratio;

    private BigDecimal deliverycompanysettlemoney;

    private BigDecimal deliverycompanyratio;

    private Integer businessquantity;

    private Integer clienterquantity;

    private Date createtime;

    private String createname;

    private String modifyname;

    private Date modifytime;

    private Integer isdisplay;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeliverycompanyname() {
        return deliverycompanyname;
    }

    public void setDeliverycompanyname(String deliverycompanyname) {
        this.deliverycompanyname = deliverycompanyname == null ? null : deliverycompanyname.trim();
    }

    public String getDeliverycompanycode() {
        return deliverycompanycode;
    }

    public void setDeliverycompanycode(String deliverycompanycode) {
        this.deliverycompanycode = deliverycompanycode == null ? null : deliverycompanycode.trim();
    }

    public Integer getIsenable() {
        return isenable;
    }

    public void setIsenable(Integer isenable) {
        this.isenable = isenable;
    }

    public Integer getSettletype() {
        return settletype;
    }

    public void setSettletype(Integer settletype) {
        this.settletype = settletype;
    }

    public BigDecimal getClienterfixmoney() {
        return clienterfixmoney;
    }

    public void setClienterfixmoney(BigDecimal clienterfixmoney) {
        this.clienterfixmoney = clienterfixmoney;
    }

    public BigDecimal getClientersettleratio() {
        return clientersettleratio;
    }

    public void setClientersettleratio(BigDecimal clientersettleratio) {
        this.clientersettleratio = clientersettleratio;
    }

    public BigDecimal getDeliverycompanysettlemoney() {
        return deliverycompanysettlemoney;
    }

    public void setDeliverycompanysettlemoney(BigDecimal deliverycompanysettlemoney) {
        this.deliverycompanysettlemoney = deliverycompanysettlemoney;
    }

    public BigDecimal getDeliverycompanyratio() {
        return deliverycompanyratio;
    }

    public void setDeliverycompanyratio(BigDecimal deliverycompanyratio) {
        this.deliverycompanyratio = deliverycompanyratio;
    }

    public Integer getBusinessquantity() {
        return businessquantity;
    }

    public void setBusinessquantity(Integer businessquantity) {
        this.businessquantity = businessquantity;
    }

    public Integer getClienterquantity() {
        return clienterquantity;
    }

    public void setClienterquantity(Integer clienterquantity) {
        this.clienterquantity = clienterquantity;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getCreatename() {
        return createname;
    }

    public void setCreatename(String createname) {
        this.createname = createname == null ? null : createname.trim();
    }

    public String getModifyname() {
        return modifyname;
    }

    public void setModifyname(String modifyname) {
        this.modifyname = modifyname == null ? null : modifyname.trim();
    }

    public Date getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    public Integer getIsdisplay() {
        return isdisplay;
    }

    public void setIsdisplay(Integer isdisplay) {
        this.isdisplay = isdisplay;
    }
}
package com.edaisong.entity;

import java.math.BigDecimal;
import java.util.Date;

public class OrderDraft {
    private Integer id;

    private String orderno;

    private String pubname;

    private Date pubdate;

    private Double publongitude;

    private Double publatitude;

    private String pubphoneno;

    private String pubaddress;

    private Short taketype;

    private Date taketime;

    private String takecode;

    private Double takelongitude;

    private Double takelatitude;

    private String recevicename;

    private String recevicephoneno;

    private String receviceaddress;

    private String recevicecity;

    private Double recevicelongitude;

    private Double recevicelatitude;

    private String receiveprovince;

    private String receivearea;

    private String receiveprovincecode;

    private String receivecitycode;

    private String receiveareacode;

    private Boolean ispay;

    private String productname;

    private String remark;

    private BigDecimal amount;

    private Integer businessid;

    private BigDecimal weight;

    private Double km;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno == null ? null : orderno.trim();
    }

    public String getPubname() {
        return pubname;
    }

    public void setPubname(String pubname) {
        this.pubname = pubname == null ? null : pubname.trim();
    }

    public Date getPubdate() {
        return pubdate;
    }

    public void setPubdate(Date pubdate) {
        this.pubdate = pubdate;
    }

    public Double getPublongitude() {
        return publongitude;
    }

    public void setPublongitude(Double publongitude) {
        this.publongitude = publongitude;
    }

    public Double getPublatitude() {
        return publatitude;
    }

    public void setPublatitude(Double publatitude) {
        this.publatitude = publatitude;
    }

    public String getPubphoneno() {
        return pubphoneno;
    }

    public void setPubphoneno(String pubphoneno) {
        this.pubphoneno = pubphoneno == null ? null : pubphoneno.trim();
    }

    public String getPubaddress() {
        return pubaddress;
    }

    public void setPubaddress(String pubaddress) {
        this.pubaddress = pubaddress == null ? null : pubaddress.trim();
    }

    public Short getTaketype() {
        return taketype;
    }

    public void setTaketype(Short taketype) {
        this.taketype = taketype;
    }

    public Date getTaketime() {
        return taketime;
    }

    public void setTaketime(Date taketime) {
        this.taketime = taketime;
    }

    public String getTakecode() {
        return takecode;
    }

    public void setTakecode(String takecode) {
        this.takecode = takecode == null ? null : takecode.trim();
    }

    public Double getTakelongitude() {
        return takelongitude;
    }

    public void setTakelongitude(Double takelongitude) {
        this.takelongitude = takelongitude;
    }

    public Double getTakelatitude() {
        return takelatitude;
    }

    public void setTakelatitude(Double takelatitude) {
        this.takelatitude = takelatitude;
    }

    public String getRecevicename() {
        return recevicename;
    }

    public void setRecevicename(String recevicename) {
        this.recevicename = recevicename == null ? null : recevicename.trim();
    }

    public String getRecevicephoneno() {
        return recevicephoneno;
    }

    public void setRecevicephoneno(String recevicephoneno) {
        this.recevicephoneno = recevicephoneno == null ? null : recevicephoneno.trim();
    }

    public String getReceviceaddress() {
        return receviceaddress;
    }

    public void setReceviceaddress(String receviceaddress) {
        this.receviceaddress = receviceaddress == null ? null : receviceaddress.trim();
    }

    public String getRecevicecity() {
        return recevicecity;
    }

    public void setRecevicecity(String recevicecity) {
        this.recevicecity = recevicecity == null ? null : recevicecity.trim();
    }

    public Double getRecevicelongitude() {
        return recevicelongitude;
    }

    public void setRecevicelongitude(Double recevicelongitude) {
        this.recevicelongitude = recevicelongitude;
    }

    public Double getRecevicelatitude() {
        return recevicelatitude;
    }

    public void setRecevicelatitude(Double recevicelatitude) {
        this.recevicelatitude = recevicelatitude;
    }

    public String getReceiveprovince() {
        return receiveprovince;
    }

    public void setReceiveprovince(String receiveprovince) {
        this.receiveprovince = receiveprovince == null ? null : receiveprovince.trim();
    }

    public String getReceivearea() {
        return receivearea;
    }

    public void setReceivearea(String receivearea) {
        this.receivearea = receivearea == null ? null : receivearea.trim();
    }

    public String getReceiveprovincecode() {
        return receiveprovincecode;
    }

    public void setReceiveprovincecode(String receiveprovincecode) {
        this.receiveprovincecode = receiveprovincecode == null ? null : receiveprovincecode.trim();
    }

    public String getReceivecitycode() {
        return receivecitycode;
    }

    public void setReceivecitycode(String receivecitycode) {
        this.receivecitycode = receivecitycode == null ? null : receivecitycode.trim();
    }

    public String getReceiveareacode() {
        return receiveareacode;
    }

    public void setReceiveareacode(String receiveareacode) {
        this.receiveareacode = receiveareacode == null ? null : receiveareacode.trim();
    }

    public Boolean getIspay() {
        return ispay;
    }

    public void setIspay(Boolean ispay) {
        this.ispay = ispay;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname == null ? null : productname.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getBusinessid() {
        return businessid;
    }

    public void setBusinessid(Integer businessid) {
        this.businessid = businessid;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public Double getKm() {
        return km;
    }

    public void setKm(Double km) {
        this.km = km;
    }
}
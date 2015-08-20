package com.edaisong.entity;

import java.util.Date;

public class OrderOther {
    private Integer id;

    private Integer orderid;

    private Integer needuploadcount;

    private String receiptpic;

    private Integer haduploadcount;

    private Integer isjoinwithdraw;

    private Double publongitude;

    private Double publatitude;

    private Date grabtime;

    private Double grablongitude;

    private Double grablatitude;

    private Double completelongitude;

    private Double completelatitude;

    private Date taketime;

    private Double takelongitude;

    private Double takelatitude;

    private Double pubtograbdistance;

    private Double grabtocompletedistance;

    private Double pubtocompletedistance;

    private Integer onekeypuborder;

    private Integer isnotrealorder;
    
    private Integer isorderchecked;
    
    private Date cancelTime;

    public Date getCancelTime() {
		return cancelTime;
	}

	public void setCancelTime(Date cancelTime) {
		this.cancelTime = cancelTime;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getNeeduploadcount() {
        return needuploadcount;
    }

    public void setNeeduploadcount(Integer needuploadcount) {
        this.needuploadcount = needuploadcount;
    }

    public String getReceiptpic() {
        return receiptpic;
    }

    public void setReceiptpic(String receiptpic) {
        this.receiptpic = receiptpic == null ? null : receiptpic.trim();
    }

    public Integer getHaduploadcount() {
        return haduploadcount;
    }

    public void setHaduploadcount(Integer haduploadcount) {
        this.haduploadcount = haduploadcount;
    }

    public Integer getIsjoinwithdraw() {
        return isjoinwithdraw;
    }

    public void setIsjoinwithdraw(Integer isjoinwithdraw) {
        this.isjoinwithdraw = isjoinwithdraw;
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

    public Date getGrabtime() {
        return grabtime;
    }

    public void setGrabtime(Date grabtime) {
        this.grabtime = grabtime;
    }

    public Double getGrablongitude() {
        return grablongitude;
    }

    public void setGrablongitude(Double grablongitude) {
        this.grablongitude = grablongitude;
    }

    public Double getGrablatitude() {
        return grablatitude;
    }

    public void setGrablatitude(Double grablatitude) {
        this.grablatitude = grablatitude;
    }

    public Double getCompletelongitude() {
        return completelongitude;
    }

    public void setCompletelongitude(Double completelongitude) {
        this.completelongitude = completelongitude;
    }

    public Double getCompletelatitude() {
        return completelatitude;
    }

    public void setCompletelatitude(Double completelatitude) {
        this.completelatitude = completelatitude;
    }

    public Date getTaketime() {
        return taketime;
    }

    public void setTaketime(Date taketime) {
        this.taketime = taketime;
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

    public Double getPubtograbdistance() {
        return pubtograbdistance;
    }

    public void setPubtograbdistance(Double pubtograbdistance) {
        this.pubtograbdistance = pubtograbdistance;
    }

    public Double getGrabtocompletedistance() {
        return grabtocompletedistance;
    }

    public void setGrabtocompletedistance(Double grabtocompletedistance) {
        this.grabtocompletedistance = grabtocompletedistance;
    }

    public Double getPubtocompletedistance() {
        return pubtocompletedistance;
    }

    public void setPubtocompletedistance(Double pubtocompletedistance) {
        this.pubtocompletedistance = pubtocompletedistance;
    }

    public Integer getOnekeypuborder() {
        return onekeypuborder;
    }

    public void setOnekeypuborder(Integer onekeypuborder) {
        this.onekeypuborder = onekeypuborder;
    }

    public Integer getIsnotrealorder() {
        return isnotrealorder;
    }

    public void setIsnotrealorder(Integer isnotrealorder) {
        this.isnotrealorder = isnotrealorder;
    }

	public Integer getIsorderchecked() {
		return isorderchecked;
	}

	public void setIsorderchecked(Integer isorderchecked) {
		this.isorderchecked = isorderchecked;
	}
}
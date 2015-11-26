package com.edaisong.entity.domain;

import java.sql.Date;

import com.edaisong.core.enums.PayType;
import com.edaisong.core.enums.ShanSongOrderStatus;

/**
 * 快单导出订单
 * 
 * @author CaoHeYang
 * @date
 */
public class ExportShanSongOrder {
	private String orderNo;
	private String businessPhoneNo;
	private String pubName;
	private String pubPhoneNo;
	private String pickUpAddress;
	private String receviceName;
	private String recevicePhoneNo;
	private String receviceAddress;
	private Double weight;
	private Double amount;
	private Double km;
	private int status;
	private Integer payType;
	private String pubDate;
    private String pickupCode;
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getBusinessPhoneNo() {
		return businessPhoneNo;
	}
	public void setBusinessPhoneNo(String businessPhoneNo) {
		this.businessPhoneNo = businessPhoneNo;
	}
	public String getPubName() {
		return pubName;
	}
	public void setPubName(String pubName) {
		this.pubName = pubName;
	}
	public String getPubPhoneNo() {
		return pubPhoneNo;
	}
	public void setPubPhoneNo(String pubPhoneNo) {
		this.pubPhoneNo = pubPhoneNo;
	}
	public String getPickUpAddress() {
		return pickUpAddress;
	}
	public void setPickUpAddress(String pickUpAddress) {
		this.pickUpAddress = pickUpAddress;
	}
	public String getReceviceName() {
		return receviceName;
	}
	public void setReceviceName(String receviceName) {
		this.receviceName = receviceName;
	}
	public String getRecevicePhoneNo() {
		return recevicePhoneNo;
	}
	public void setRecevicePhoneNo(String recevicePhoneNo) {
		this.recevicePhoneNo = recevicePhoneNo;
	}
	public String getReceviceAddress() {
		return receviceAddress;
	}
	public void setReceviceAddress(String receviceAddress) {
		this.receviceAddress = receviceAddress;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getKm() {
		return km;
	}
	public void setKm(Double km) {
		this.km = km;
	}
	public String getStatus() {
		return ShanSongOrderStatus.getEnum(this.status).desc() ;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getPayType() {
		return this.payType==null?"": PayType.getEnum(this.payType).desc();
	}
	public void setPayType(int payType) {
		this.payType = payType;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	public String getPickupCode() {
		return pickupCode;
	}
	public void setPickupCode(String pickupCode) {
		this.pickupCode = pickupCode;
	}
	
}

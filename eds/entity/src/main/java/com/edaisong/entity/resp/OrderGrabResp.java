package com.edaisong.entity.resp;

import java.util.Date;

import com.edaisong.entity.common.ResponseBase;

/**
 * 商户抢单
 * @author 胡灵波
 * @Date 2015年11月2日 15:37:25
 */
public class OrderGrabResp {
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}

	public Integer getActualdoneCount() {
		return actualdoneCount;
	}

	public void setActualdoneCount(Integer actualdoneCount) {
		this.actualdoneCount = actualdoneCount;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public Date getPickupTime() {
		return pickupTime;
	}

	public void setPickupTime(Date pickupTime) {
		this.pickupTime = pickupTime;
	}

	public Date getActualdoneDate() {
		return actualdoneDate;
	}

	public void setActualdoneDate(Date actualdoneDate) {
		this.actualdoneDate = actualdoneDate;
	}

	private Integer id;
	
	private Integer orderCount;
	
	private Integer actualdoneCount;
	
	private short status;
	
	private Date pickupTime;	
	
	private Date actualdoneDate;	
	

}

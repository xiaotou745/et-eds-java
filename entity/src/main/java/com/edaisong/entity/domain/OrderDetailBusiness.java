package com.edaisong.entity.domain;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * 商家后台 订单详情页面基础数据
 * 
 * @author CaoHeYang Date 20150804
 */
public class OrderDetailBusiness {
	private int id;
	private String orderNo;
	private int status;
	private int orderFrom;
	private String originalOrderNo;
	private String canelReason;
	private Date pubDate;
	private Date grabTime;
	private Date takeTime;
	private Date actualDoneDate;
	private String recevicePhoneNo;
	private String receviceAddress;
	private String trueName;
	private String phoneNo;
	private BigDecimal amount;
	private int orderCommission;
	private int isPay;

	/**
	 * ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * ID
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 订单号
	 */
	public String getOrderNo() {
		return orderNo;
	}

	/**
	 * 订单号
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * 订单状态
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * 订单状态
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * 订单来源
	 */
	public int getOrderFrom() {
		return orderFrom;
	}

	/**
	 * 订单来源
	 */
	public void setOrderFrom(int orderFrom) {
		this.orderFrom = orderFrom;
	}

	/**
	 * 第三方订单号
	 */
	public String getOriginalOrderNo() {
		return originalOrderNo;
	}

	/**
	 * 第三方订单号
	 */
	public void setOriginalOrderNo(String originalOrderNo) {
		this.originalOrderNo = originalOrderNo;
	}

	/**
	 * 关闭原因
	 */
	public String getCanelReason() {
		return canelReason;
	}

	/**
	 * 关闭原因
	 */
	public void setCanelReason(String canelReason) {
		this.canelReason = canelReason;
	}

	/**
	 * 发布日期
	 */
	public Date getPubDate() {
		return pubDate;
	}

	/**
	 * 发布日期
	 */
	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	/**
	 * 抢单时间
	 */
	public Date getGrabTime() {
		return grabTime;
	}

	/**
	 * 抢单时间
	 */
	public void setGrabTime(Date grabTime) {
		this.grabTime = grabTime;
	}

	/**
	 * 取货时间
	 */
	public Date getTakeTime() {
		return takeTime;
	}

	/**
	 * 取货时间
	 */
	public void setTakeTime(Date takeTime) {
		this.takeTime = takeTime;
	}

	/**
	 * 完成时间
	 */
	public Date getActualDoneDate() {
		return actualDoneDate;
	}

	/**
	 * 完成时间
	 */
	public void setActualDoneDate(Date actualDoneDate) {
		this.actualDoneDate = actualDoneDate;
	}

	/**
	 * 收货人手机号
	 */
	public String getRecevicePhoneNo() {
		return recevicePhoneNo;
	}

	/**
	 * 收货人手机号
	 */
	public void setRecevicePhoneNo(String recevicePhoneNo) {
		this.recevicePhoneNo = recevicePhoneNo;
	}

	/**
	 * 收货人地址
	 */
	public String getReceviceAddress() {
		return receviceAddress;
	}

	/**
	 * 收货人地址
	 */
	public void setReceviceAddress(String receviceAddress) {
		this.receviceAddress = receviceAddress;
	}

	/**
	 * 骑士姓名
	 */
	public String getTrueName() {
		return trueName;
	}

	/**
	 * 骑士姓名
	 */
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	/**
	 * 骑士手机号
	 */
	public String getPhoneNo() {
		return phoneNo;
	}

	/**
	 * 骑士手机号
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	/**
	 * 金额
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * 金额
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * 佣金
	 */
	public int getOrderCommission() {
		return orderCommission;
	}

	/**
	 * 佣金
	 */
	public void setOrderCommission(int orderCommission) {
		this.orderCommission = orderCommission;
	}

	/**
	 * 支付状态
	 */
	public int getIsPay() {
		return isPay;
	}

	/**
	 * 支付状态
	 */
	public void setIsPay(int isPay) {
		this.isPay = isPay;
	}

}

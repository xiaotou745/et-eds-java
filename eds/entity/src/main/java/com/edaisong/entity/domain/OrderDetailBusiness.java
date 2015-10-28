package com.edaisong.entity.domain;

import java.lang.Double;
import java.util.Date;

/**
 * 商家后台 订单详情页面基础数据
 * 
 * @author CaoHeYang Date 20150804
 */
public class OrderDetailBusiness {
	private int id;
	private String orderNo;
	private int status;
	private String groupName;
	private String originalOrderNo;
	private String remark;
	private Date pubDate;
	private Date grabTime;
	private Date takeTime;
	private Date actualDoneDate;
	private String recevicePhoneNo;
	private String receviceAddress;
	private String trueName;
	private String phoneNo;
	private Double amount;
	private Double orderCommission;
	private int isPay;
	private Double settleMoney;
	private Date cancelTime;
    private Integer orderFrom ;
    private String orderFromName ;

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
	public String getGroupName() {
		return groupName;
	}

	/**
	 * 订单来源
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
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
	public String getRemark() {
		return remark;
	}

	/**
	 * 关闭原因
	 */
	public void setRemark(String remark) {
		this.remark = remark;
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
	public Double getAmount() {
		return amount;
	}

	/**
	 * 金额
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	/**
	 * 佣金
	 */
	public Double getOrderCommission() {
		return orderCommission;
	}

	/**
	 * 佣金
	 */
	public void setOrderCommission(Double orderCommission) {
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

	/**
	 * 结算金额
	 * 
	 * @return
	 */
	public Double getSettleMoney() {
		return settleMoney;
	}

	/**
	 * 结算金额
	 * 
	 * @param settleMoney
	 */
	public void setSettleMoney(Double settleMoney) {
		this.settleMoney = settleMoney;
	}

	/**
	 * 取消时间
	 * 
	 * @return
	 */
	public Date getCancelTime() {
		return cancelTime;
	}

	/**
	 * 取消时间
	 * 
	 * @param cancelTime
	 */
	public void setCancelTime(Date cancelTime) {
		this.cancelTime = cancelTime;
	}

	public int getOrderFrom() {
		return orderFrom;
	}

	public void setOrderFrom(int orderFrom) {
		this.orderFrom = orderFrom;
	}

	public String getOrderFromName() {
		return orderFromName;
	}

	public void setOrderFromName(String orderFromName) {
		this.orderFromName = orderFromName;
	}

}

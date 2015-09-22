package com.edaisong.entity.domain;

import java.util.Date;

public class DeliveryStatistics {
private String deliveryCompanyID;
private String deliveryCompanyName;
private String deliveryCompanyCode;
private Double clienterTotalSettleMoney;
private Double companyTotalSettleMoney;
private Integer taskTotalNum;
private Integer orderTotalNum;
private Integer amountTotalNum;
private String auditDate;
/**
 * 物流公司名称
 * @author hailongzhao
 * @return
 */
public String getDeliveryCompanyName() {
	return deliveryCompanyName;
}
/**
 * 物流公司名称
 * @author hailongzhao
 * @return
 */
public void setDeliveryCompanyName(String deliveryCompanyName) {
	this.deliveryCompanyName = deliveryCompanyName;
}
/**
 * 物流公司编码
 * @author hailongzhao
 * @return
 */
public String getDeliveryCompanyCode() {
	return deliveryCompanyCode;
}
/**
 * 物流公司编码
 * @author hailongzhao
 * @return
 */
public void setDeliveryCompanyCode(String deliveryCompanyCode) {
	this.deliveryCompanyCode = deliveryCompanyCode;
}
/**
 * 骑士结算总金额
 * @author hailongzhao
 * @return
 */
public Double getClienterTotalSettleMoney() {
	return clienterTotalSettleMoney;
}
/**
 * 骑士结算总金额
 * @author hailongzhao
 * @return
 */
public void setClienterTotalSettleMoney(Double clienterTotalSettleMoney) {
	this.clienterTotalSettleMoney = clienterTotalSettleMoney;
}
/**
 * 物流公司结算总金额
 * @author hailongzhao
 * @return
 */
public Double getCompanyTotalSettleMoney() {
	return companyTotalSettleMoney;
}
/**
 * 物流公司结算总金额
 * @author hailongzhao
 * @return
 */
public void setCompanyTotalSettleMoney(Double companyTotalSettleMoney) {
	this.companyTotalSettleMoney = companyTotalSettleMoney;
}
/**
 * 任务总量
 * @author hailongzhao
 * @return
 */
public Integer getTaskTotalNum() {
	return taskTotalNum;
}
/**
 * 任务总量
 * @author hailongzhao
 * @return
 */
public void setTaskTotalNum(Integer taskTotalNum) {
	this.taskTotalNum = taskTotalNum;
}
/**
 * 订单总量
 * @author hailongzhao
 * @return
 */
public Integer getOrderTotalNum() {
	return orderTotalNum;
}
/**
 * 订单总量
 * @author hailongzhao
 * @return
 */
public void setOrderTotalNum(Integer orderTotalNum) {
	this.orderTotalNum = orderTotalNum;
}
/**
 * 订单总金额
 * @author hailongzhao
 * @return
 */
public Integer getAmountTotalNum() {
	return amountTotalNum;
}
/**
 * 订单总金额
 * @author hailongzhao
 * @return
 */
public void setAmountTotalNum(Integer amountTotalNum) {
	this.amountTotalNum = amountTotalNum;
}
public String getAuditDate() {
	return auditDate;
}
public void setAuditDate(String auditDate) {
	this.auditDate = auditDate;
}
public String getDeliveryCompanyID() {
	return deliveryCompanyID;
}
public void setDeliveryCompanyID(String deliveryCompanyID) {
	this.deliveryCompanyID = deliveryCompanyID;
}
}

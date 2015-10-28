package com.edaisong.entity.req;

import java.util.Date;

import com.edaisong.entity.common.PagedRequestBase;

public class PagedDeliveryStatisticsReq extends PagedRequestBase{
private String settlementYear;
private String settlementMonth;
private String deliveryName;
private String start;
private String end;
/**
 * 结算年月
 * @author hailongzhao
 * @date20150921
 * @return
 */
public String getSettlementMonth() {
	return settlementMonth;
}
/**
 * 结算年月
 * @author hailongzhao
 * @date20150921
 * @return
 */
public void setSettlementMonth(String settlementMonth) {
	this.settlementMonth = settlementMonth;
}
/**
 * 物流公司名称
 * @author hailongzhao
 * @date 20150921
 * @return
 */
public String getDeliveryName() {
	return deliveryName;
}
/**
 * 物流公司名称
 * @author hailongzhao
 * @date 20150921
 * @return
 */
public void setDeliveryName(String deliveryName) {
	this.deliveryName = deliveryName;
}

public String getSettlementYear() {
	return settlementYear;
}
public void setSettlementYear(String settlementYear) {
	this.settlementYear = settlementYear;
}
public String getStart() {
	return start;
}
public void setStart(String start) {
	this.start = start;
}
public String getEnd() {
	return end;
}
public void setEnd(String end) {
	this.end = end;
}
}

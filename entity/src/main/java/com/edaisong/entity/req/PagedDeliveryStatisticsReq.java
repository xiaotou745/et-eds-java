package com.edaisong.entity.req;

import com.edaisong.entity.common.PagedRequestBase;

public class PagedDeliveryStatisticsReq extends PagedRequestBase{
private String settlementMonth;
private String deliveryName;
private String settlementEndMonth;
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
public String getSettlementEndMonth() {
	return settlementEndMonth;
}
public void setSettlementEndMonth(String settlementEndMonth) {
	this.settlementEndMonth = settlementEndMonth;
}
}

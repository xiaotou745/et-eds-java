package com.edaisong.entity.req;

import com.edaisong.entity.common.PagedRequestBase;

/**
 * 商户获取日账单参数 分页
 * */
public class PagedAccountBillDayCReq extends PagedRequestBase{
private int clienterId;
private String dayInfo;
private int billType;//出入帐类型0 全部 1出账 2 入账
private int recordType;// 交易类型(1：订单佣金 2：取消订单 3：提现申请 4：提现拒绝 5：打款失败 6：系统奖励 7：系统赔偿 8:余额调整 9:手续费 10:现金提现 11:异常订单)
private String stratDate;
private String endDate;
private int platform;
public int getBillType() {
	return billType;
}
public void setBillType(int billType) {
	this.billType = billType;
}
public int getRecordType() {
	return recordType;
}
public int getClienterId() {
	return clienterId;
}
public void setClienterId(int clienterId) {
	this.clienterId = clienterId;
}
public void setRecordType(int recordType) {
	this.recordType = recordType;
}
public String getStratDate() {
	return stratDate;
}
public void setStratDate(String stratDate) {
	this.stratDate = stratDate;
}
public String getEndDate() {
	return endDate;
}
public void setEndDate(String endDate) {
	this.endDate = endDate;
}
public String getDayInfo() {
	return dayInfo;
}
public void setDayInfo(String dayInfo) {
	this.dayInfo = dayInfo;
}
public int getPlatform() {
	return platform;
}
public void setPlatform(int platform) {
	this.platform = platform;
}

}

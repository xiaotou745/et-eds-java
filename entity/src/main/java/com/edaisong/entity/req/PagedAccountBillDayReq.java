package com.edaisong.entity.req;

import com.edaisong.entity.common.PagedRequestBase;

/**
 * 商户获取日账单参数 分页
 * */
public class PagedAccountBillDayReq extends PagedRequestBase{
private int businessId;
private String dayInfo;
private int billType;//出入帐类型0 全部 1出账 2 入账
private int recordType;//  --出账(1：发布订单 3：提款申请 )
//--入账( 2：取消订单 5：打款失败  8：订单菜品费 9：充值 12充值赠送)
public int getBusinessId() {
	return businessId;
}
public void setBusinessId(int businessId) {
	this.businessId = businessId;
}
public String getDayInfo() {
	return dayInfo;
}
public void setDayInfo(String dayInfo) {
	this.dayInfo = dayInfo;
}

}

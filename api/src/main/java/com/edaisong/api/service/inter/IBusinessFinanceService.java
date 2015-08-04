package com.edaisong.api.service.inter;

import com.edaisong.entity.BusinessBalanceRecord;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.TransDetailReq;
/**
 * 商户财务相关模块
 * 2015年8月4日13:08:38
 * 茹化肖
 * */
public interface IBusinessFinanceService {
	/**
	 * 获取商户交易明细列表分页
	 * 2015年8月4日13:09:00
	 * 茹化肖
	 * */
	PagedResponse<BusinessBalanceRecord> getTransDetailList(TransDetailReq par);
}

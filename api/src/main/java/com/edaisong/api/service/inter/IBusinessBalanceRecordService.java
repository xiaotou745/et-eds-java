package com.edaisong.api.service.inter;

import com.edaisong.entity.BusinessBalanceRecord;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.PagedCustomerSearchReq;
import com.edaisong.entity.req.PagedTransDetailReq;

/**
 * 商户财务相关模块
 * 2015年8月4日13:08:38
 * 茹化肖
 * */
public interface IBusinessBalanceRecordService {
	/**
	 *  商家中心，商户交易明细列表分页
	 * @param par
	 * @author hailongzhao
	 * @Date 20150821
	 * @return
	 */
	PagedResponse<BusinessBalanceRecord> getTransDetailList(PagedTransDetailReq par);

	/**
	 *  商家中心，商户交易明细列表分页，右上角自定义查询
	 * @param par
	 * @author hailongzhao
	 * @Date 20150821
	 * @return
	 */
	PagedResponse<BusinessBalanceRecord> customerGetTransDetailList(PagedCustomerSearchReq par);
}

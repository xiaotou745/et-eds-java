package com.edaisong.api.dao.inter;

import java.util.List;

import com.edaisong.entity.BusinessBalanceRecord;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.PagedCustomerSearchReq;
import com.edaisong.entity.req.PagedTransDetailReq;

public interface IBusinessBalanceRecordDao {
    int insert(BusinessBalanceRecord record);
	/**
	 *  商家中心，商户交易明细列表分页，右上角自定义查询
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
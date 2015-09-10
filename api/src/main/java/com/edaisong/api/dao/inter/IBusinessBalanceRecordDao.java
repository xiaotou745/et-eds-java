package com.edaisong.api.dao.inter;

import java.text.ParseException;
import java.util.Dictionary;
import java.util.List;
import java.util.Map;

import com.edaisong.entity.BusinessBalanceRecord;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.AccountBillModel;
import com.edaisong.entity.domain.BusinessBalanceRecordModel;
import com.edaisong.entity.req.AccountBillBReq;
import com.edaisong.entity.req.BussinessBalanceQueryReq;
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
	
	/**
	 * 查询商户充值总金额
	 * @author pengyi
	 * @param par
	 * @return
	 */
	double queryBusinessRechargeTotalAmount(BussinessBalanceQueryReq par) throws ParseException;
	
	/**
	 * 导出商家收支记录数据
	 * @author pengyi
	 * @date 20150902
	 * @param par
	 * @return
	 */
	List<BusinessBalanceRecordModel> getBusinessBalanceRecordListForExport(PagedTransDetailReq par);
	/**
	 * API 获取商户月账单信息
	 * 茹化肖
	 * 2015年9月10日10:24:07
	 * */
	Map<String,AccountBillModel>  getAccountBillListB(AccountBillBReq par);
}
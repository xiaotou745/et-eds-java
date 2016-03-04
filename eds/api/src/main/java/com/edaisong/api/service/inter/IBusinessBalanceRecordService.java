package com.edaisong.api.service.inter;

import java.text.ParseException;
import java.util.List;

import com.edaisong.entity.AccountBillResultModel;
import com.edaisong.entity.BusinessBalanceRecord;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.AccountBillDayResultModel;
import com.edaisong.entity.domain.AccountBillDetailModel;
import com.edaisong.entity.domain.AccountBillModel;
import com.edaisong.entity.domain.BusinessBalanceRecordModel;
import com.edaisong.entity.req.AccountBillBReq;
import com.edaisong.entity.req.AccountBillCReq;
import com.edaisong.entity.req.AccountBillDetailReq;
import com.edaisong.entity.req.BussinessBalanceQueryReq;
import com.edaisong.entity.req.PagedAccountBillDayReq;
import com.edaisong.entity.req.PagedBusinessBalanceRecordReq;
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
	 * 2015年9月10日10:25:04
	 * 
	 * */
	AccountBillResultModel getAccountBillListB(AccountBillBReq par);
	/**
	 * API 获取商户日账单信息
	 * 茹化肖
	 * 2015年9月10日10:25:04
	 * 
	 * */
	AccountBillDayResultModel getAccountBillListDayB(PagedAccountBillDayReq par);
	
	/**
	 * API 获取商户账单详情
	 * 茹化肖
	 * 2015年9月10日10:25:04
	 * 
	 * */
	AccountBillDetailModel getAccountBillDetailB(AccountBillDetailReq par);
	
	PagedResponse<BusinessBalanceRecordModel> getGroupBalanceRecord(PagedBusinessBalanceRecordReq search);
	
	List<BusinessBalanceRecordModel>  exportgrouplist(PagedBusinessBalanceRecordReq search);
}

package com.edaisong.api.service.inter;


import com.edaisong.entity.AccountBillResultModel;
import com.edaisong.entity.ClienterBalanceRecord;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.AccountBillDayCResultModel;
import com.edaisong.entity.domain.AccountBillDetailCModel;
import com.edaisong.entity.req.AccountBillCReq;
import com.edaisong.entity.req.AccountBillDetailCReq;
import com.edaisong.entity.req.PagedAccountBillDayCReq;
import com.edaisong.entity.req.PagedClienterBalanceRecordReq;


public interface IClienterBalanceRecordService {
	PagedResponse<ClienterBalanceRecord>  query(PagedClienterBalanceRecordReq req);
	/**
	 * API 获取骑士月账单信息
	 * 茹化肖
	 * 2015年9月10日10:25:04
	 * 
	 * */
	AccountBillResultModel getAccountBillListC(AccountBillCReq par);
	
	/**
	 * API 获取骑士日账单信息
	 * 茹化肖
	 * 2015年9月14日11:10:12
	 * 
	 * */
	AccountBillDayCResultModel getAccountBillListDayC(PagedAccountBillDayCReq par);
	
	/**
	 * API 获取骑士账单详情
	 * 茹化肖
	 * 2015年9月10日10:25:04
	 * 
	 * */
	AccountBillDetailCModel getAccountBillDetailC(AccountBillDetailCReq par);
}

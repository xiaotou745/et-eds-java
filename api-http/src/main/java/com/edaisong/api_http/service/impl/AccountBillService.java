package com.edaisong.api_http.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.service.inter.IBusinessBalanceRecordService;
import com.edaisong.api.service.inter.IClienterBalanceRecordService;
import com.edaisong.api_http.service.inter.IAccountBillService;
import com.edaisong.core.enums.returnenums.AccountBillReturnEnum;
import com.edaisong.core.enums.returnenums.HttpReturnRnums;
import com.edaisong.core.util.RegexHelper;
import com.edaisong.entity.AccountBillResultModel;
import com.edaisong.entity.common.HttpResultModel;
import com.edaisong.entity.domain.AccountBillDayCResultModel;
import com.edaisong.entity.domain.AccountBillDayResultModel;
import com.edaisong.entity.domain.AccountBillDetailCModel;
import com.edaisong.entity.domain.AccountBillDetailModel;
import com.edaisong.entity.domain.QueryOrder;
import com.edaisong.entity.req.AccountBillBReq;
import com.edaisong.entity.req.AccountBillCReq;
import com.edaisong.entity.req.AccountBillDetailCReq;
import com.edaisong.entity.req.AccountBillDetailReq;
import com.edaisong.entity.req.PagedAccountBillDayCReq;
import com.edaisong.entity.req.PagedAccountBillDayReq;
import com.edaisong.entity.resp.QueryOrderBResp;

/*
 * 商户 骑士账单相关实现
 * 茹化肖
 * 2015年9月10日10:00:48
 * */
@Service
public class AccountBillService implements IAccountBillService {
	@Autowired
	private IBusinessBalanceRecordService businessBalanceRecordService;
	@Autowired
	private IClienterBalanceRecordService clienterBalanceRecordService;

	/**
	 * 获取商户月账单
	 * */
	@Override
	public HttpResultModel<AccountBillResultModel> getBillListB(AccountBillBReq par) {
		HttpResultModel<AccountBillResultModel> result=new HttpResultModel<AccountBillResultModel>();
		if(!RegexHelper.yearMotnReg(par.getMonthInfo()))
		{
			//参数错误
			result.setStatus(AccountBillReturnEnum.ParError.value());
			result.setMessage(AccountBillReturnEnum.ParError.desc());
			return result;  
		}
		AccountBillResultModel listResult = businessBalanceRecordService.getAccountBillListB(par);
	    result.setMessage(AccountBillReturnEnum.Success.desc());
		result.setStatus(AccountBillReturnEnum.Success.value());
	    result.setResult(listResult);

		return result;
	}

	/**
	 * 商家获取日账单 茹化肖 2015年9月11日10:55:46
	 */
	@Override
	public HttpResultModel<AccountBillDayResultModel> getBillListDayB(PagedAccountBillDayReq par) {
		HttpResultModel<AccountBillDayResultModel> result=new HttpResultModel<AccountBillDayResultModel>();
		if(!RegexHelper.yearMotnDayReg(par.getDayInfo()))
		{
			result.setStatus(AccountBillReturnEnum.ParError.value());
			result.setMessage(AccountBillReturnEnum.ParError.desc());
			return result; 
		}
		AccountBillDayResultModel model = businessBalanceRecordService.getAccountBillListDayB(par);
		result.setStatus(AccountBillReturnEnum.Success.value());
		result.setMessage(AccountBillReturnEnum.Success.desc());
		result.setResult(model);
		return result; 
	}

	/**
	 * 商家获取账单详情 茹化肖 2015年9月11日15:38:33
	 */
	@Override
	public HttpResultModel<AccountBillDetailModel> getBillDetail(AccountBillDetailReq par) {
		AccountBillDetailModel model = businessBalanceRecordService.getAccountBillDetailB(par);
		HttpResultModel<AccountBillDetailModel> result=new HttpResultModel<AccountBillDetailModel>();
		result.setStatus(AccountBillReturnEnum.Success.value());
		result.setMessage(AccountBillReturnEnum.Success.desc());
		result.setResult(model);
		return result; 
	}
	/**
	 * 骑士获取月账单 茹化肖 2015年9月14日09:48:29
	 */
	@Override
	public HttpResultModel<AccountBillResultModel> getBillListC(
			AccountBillCReq par) {
		HttpResultModel<AccountBillResultModel> result=new HttpResultModel<AccountBillResultModel>();
		if(!RegexHelper.yearMotnReg(par.getMonthInfo()))
		{
			//参数错误
			result.setStatus(AccountBillReturnEnum.ParError.value());
			result.setMessage(AccountBillReturnEnum.ParError.desc());
			return result; 
		}
		AccountBillResultModel listResult = clienterBalanceRecordService.getAccountBillListC(par);
		result.setStatus(AccountBillReturnEnum.Success.value());
		result.setMessage(AccountBillReturnEnum.Success.desc());
		result.setResult(listResult);
		return result; 
	}
	/**
	 * 
	 * 骑士获取日账单
	 * 茹化肖
	 * 2015年9月14日11:08:44
	 * 
	 */
	@Override
	public HttpResultModel<AccountBillDayCResultModel> getBillListDayC(
			PagedAccountBillDayCReq par) {
		HttpResultModel<AccountBillDayCResultModel> result=new HttpResultModel<AccountBillDayCResultModel>();
		if(!RegexHelper.yearMotnDayReg(par.getDayInfo()))
		{
			//参数错误
			result.setStatus(AccountBillReturnEnum.ParError.value());
			result.setMessage(AccountBillReturnEnum.ParError.desc());
			return result; 
		}
		AccountBillDayCResultModel model = clienterBalanceRecordService.getAccountBillListDayC(par);
		result.setStatus(AccountBillReturnEnum.Success.value());
		result.setMessage(AccountBillReturnEnum.Success.desc());
		result.setResult(model);
		return result; 
	}
	/**
	 * 骑士获取账单详情
	 * 茹化肖
	 * 2015年9月14日12:51:43
	 */
	@Override
	public HttpResultModel<AccountBillDetailCModel> getBillDetailC(
			AccountBillDetailCReq par) {
		AccountBillDetailCModel model =clienterBalanceRecordService.getAccountBillDetailC(par);
		HttpResultModel<AccountBillDetailCModel> result=new HttpResultModel<AccountBillDetailCModel>();
		result.setStatus(AccountBillReturnEnum.Success.value());
		result.setMessage(AccountBillReturnEnum.Success.desc());
		result.setResult(model);
		return result; 
	}

}

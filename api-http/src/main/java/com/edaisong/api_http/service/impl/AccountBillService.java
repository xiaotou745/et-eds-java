package com.edaisong.api_http.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.service.inter.IBusinessBalanceRecordService;
import com.edaisong.api_http.entity.ResultModel;
import com.edaisong.api_http.service.inter.IAccountBillService;
import com.edaisong.core.util.ParseHelper;
import com.edaisong.entity.AccountBillResultModel;
import com.edaisong.entity.domain.AccountBillDayResultModel;
import com.edaisong.entity.domain.AccountBillModel;
import com.edaisong.entity.req.AccountBillBReq;
import com.edaisong.entity.req.PagedAccountBillDayReq;

/*
 * 商户 骑士账单相关实现
 * 茹化肖
 * 2015年9月10日10:00:48
 * */
@Service
public class AccountBillService implements IAccountBillService {
	@Autowired
	private IBusinessBalanceRecordService businessBalanceRecordService;

	/**
	 * 获取商户月账单
	 * */
	@Override
	public ResultModel<AccountBillResultModel> getBillListB(AccountBillBReq par) {
		
		try {
			System.out.println(par);
			AccountBillResultModel listResult = businessBalanceRecordService.getAccountBillListB(par);
			return new ResultModel<AccountBillResultModel>().setMessage("获取成功").setResult(listResult).setStatus(1);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
		
	}
	/**
	 * 商家获取日账单
	 * 茹化肖
	 * 2015年9月11日10:55:46
	 */
	@Override
	public ResultModel<AccountBillDayResultModel> getBillListDayB(
			PagedAccountBillDayReq par) {
		AccountBillDayResultModel model=businessBalanceRecordService.getAccountBillListDayB(par);
		return new ResultModel<AccountBillDayResultModel>().setMessage("成功").setStatus(1).setResult(model);
	}
	

}

package com.edaisong.api_http.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.service.inter.IBusinessBalanceRecordService;
import com.edaisong.api_http.service.inter.IAccountBillService;
import com.edaisong.core.enums.returnenums.AccountBillReturnEnum;
import com.edaisong.core.util.ParseHelper;
import com.edaisong.entity.AccountBillResultModel;
import com.edaisong.entity.common.HttpResultModel;
import com.edaisong.entity.domain.AccountBillDayResultModel;
import com.edaisong.entity.domain.AccountBillDetailModel;
import com.edaisong.entity.domain.AccountBillModel;
import com.edaisong.entity.req.AccountBillBReq;
import com.edaisong.entity.req.AccountBillDetailReq;
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
	public HttpResultModel<AccountBillResultModel> getBillListB(AccountBillBReq par) {

		AccountBillResultModel listResult = businessBalanceRecordService.getAccountBillListB(par);
		return new HttpResultModel<AccountBillResultModel>().setMessage(AccountBillReturnEnum.Success.desc()).setResult(listResult)
				.setStatus(AccountBillReturnEnum.Success.value());
	}

	/**
	 * 商家获取日账单 茹化肖 2015年9月11日10:55:46
	 */
	@Override
	public HttpResultModel<AccountBillDayResultModel> getBillListDayB(PagedAccountBillDayReq par) {
		AccountBillDayResultModel model = businessBalanceRecordService.getAccountBillListDayB(par);
		return new HttpResultModel<AccountBillDayResultModel>().setMessage(AccountBillReturnEnum.Success.desc())
				.setStatus(AccountBillReturnEnum.Success.value()).setResult(model);
	}

	/**
	 * 商家获取账单详情 茹化肖 2015年9月11日15:38:33
	 */
	@Override
	public HttpResultModel<AccountBillDetailModel> getBillDetail(AccountBillDetailReq par) {
		AccountBillDetailModel model = businessBalanceRecordService.getAccountBillDetailB(par);
		return new HttpResultModel<AccountBillDetailModel>().setMessage(AccountBillReturnEnum.Success.desc()).setStatus(AccountBillReturnEnum.Success.value())
				.setResult(model);
	}

}

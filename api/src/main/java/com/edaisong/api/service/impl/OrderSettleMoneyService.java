package com.edaisong.api.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dal.dao.inter.IAccountDao;
import com.edaisong.api.service.inter.IAccountService;
import com.edaisong.entity.Account;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.AccountReq;
import com.edaisong.entity.resp.AccountResp;

public class OrderSettleMoneyService {
	public static BigDecimal GetSettleMoney(BigDecimal amount, BigDecimal businessCommission, BigDecimal commissionFixValue,
			int ordercount, BigDecimal distribSubsidy, int orderform) {
		if (orderform > 0){ // 第三方订单 不考虑外送费
			distribSubsidy = new BigDecimal(0);
		}
		commissionFixValue.add(distribSubsidy).multiply(new BigDecimal(ordercount));
		return amount.multiply(businessCommission).multiply(new BigDecimal(0.01))
				.add(commissionFixValue.add(distribSubsidy).multiply(new BigDecimal(ordercount))
				.setScale(2, RoundingMode.HALF_UP));
	}
}

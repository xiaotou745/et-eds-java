package com.edaisong.api.common;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class OrderSettleMoneyHelper {
/**
 * 计算订单应收
 * @author hailongzhao
 * @param amount 订单金额
 * @param businessCommissionRate 商家结算比例
 * @param commissionFixValue  商家结算固定金额
 * @param ordercount 订单数量
 * @param distribSubsidy 商家外送费
 * @param orderform 订单来源
 * @Date 20150818
 * @return
 */
	public static BigDecimal GetSettleMoney(BigDecimal amount, BigDecimal businessCommissionRate, BigDecimal commissionFixValue,
			int ordercount, BigDecimal distribSubsidy, int orderform) {
		if (orderform > 0){ // 第三方订单 不考虑外送费
			distribSubsidy = new BigDecimal(0);
		}
		return amount.multiply(businessCommissionRate).multiply(new BigDecimal(0.01))
				.add(commissionFixValue.add(distribSubsidy).multiply(new BigDecimal(ordercount))
				.setScale(2, RoundingMode.HALF_UP));
	}
}

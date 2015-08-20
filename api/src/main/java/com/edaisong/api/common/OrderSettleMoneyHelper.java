package com.edaisong.api.common;

import java.lang.Double;
import java.math.RoundingMode;

import com.edaisong.core.enums.OrderFrom;

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
	public static Double GetSettleMoney(Double amount, Double businessCommissionRate, Double commissionFixValue,
			int ordercount, Double distribSubsidy, int orderform) {
		if (orderform > 0&&orderform!=OrderFrom.BusinessWeb.value()){ // 第三方订单 不考虑外送费
			distribSubsidy = 0d;
		}
		//订单金额*结算比例+(固定金额+外送费)*订单数量
		return amount*businessCommissionRate*0.01+(commissionFixValue+distribSubsidy)*ordercount;
	}
}

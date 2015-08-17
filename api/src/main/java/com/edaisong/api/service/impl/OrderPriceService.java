package com.edaisong.api.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.edaisong.entity.domain.OrderCommission;

/**
 * 佣金补贴策略基类
 * 
 * @author pengyi
 * @author pengyi
 * @date 20150817
 *
 */
public abstract class OrderPriceService {
	/**
	 * 获取订单的骑士佣金
	 * 
	 * @param model
	 * @author pengyi
	 * @date 20150817
	 * @return
	 */
	public abstract BigDecimal getCurrenOrderCommission(OrderCommission model);

	/**
	 * 获取订单的网站补贴
	 * 
	 * @param model
	 * @author pengyi
	 * @date 20150817
	 * @return
	 */
	public abstract BigDecimal getOrderWebSubsidy(OrderCommission model);

	/**
	 * 获取订单的佣金比例
	 * 
	 * @param model
	 * @author pengyi
	 * @date 20150817
	 * @return
	 */
	public abstract BigDecimal getCommissionRate(OrderCommission model);

	/**
	 * 获取订单基本佣金
	 * 
	 * @param model
	 * @author pengyi
	 * @date 20150817
	 * @return
	 */
	public abstract BigDecimal getBaseCommission(OrderCommission model);

	/**
	 * 获取订单的额外补贴金额
	 * 
	 * @param model
	 * @author pengyi
	 * @date 20150817
	 * @return
	 */
	public abstract BigDecimal getAdjustment(OrderCommission model);

	/**
	 * C端 获取订单的金额
	 * 
	 * @param model
	 * @author pengyi
	 * @date 20150817
	 * @return
	 */
	public static BigDecimal getCurrenOrderPrice(OrderCommission model) {
		BigDecimal amount = model.getAmount();
		BigDecimal orderCount = BigDecimal.valueOf(model.getOrderCount());
		BigDecimal distribSubsidy = model.getDistribSubsidy();
		// 需进行四舍五入
		return amount.add(distribSubsidy.multiply(orderCount)).setScale(2, RoundingMode.HALF_UP);
	}
}

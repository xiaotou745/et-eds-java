package com.edaisong.api.common;

import java.lang.Double;
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
public abstract class OrderPriceBaseProvider {
	/**
	 * 获取订单的骑士佣金
	 * 
	 * @param model
	 * @author pengyi
	 * @date 20150817
	 * @return
	 */
	public abstract Double getCurrenOrderCommission(OrderCommission model);

	/**
	 * 获取订单的网站补贴
	 * 
	 * @param model
	 * @author pengyi
	 * @date 20150817
	 * @return
	 */
	public abstract Double getOrderWebSubsidy(OrderCommission model);

	/**
	 * 获取订单的佣金比例
	 * 
	 * @param model
	 * @author pengyi
	 * @date 20150817
	 * @return
	 */
	public abstract Double getCommissionRate(OrderCommission model);

	/**
	 * 获取订单基本佣金
	 * 
	 * @param model
	 * @author pengyi
	 * @date 20150817
	 * @return
	 */
	public abstract Double getBaseCommission(OrderCommission model);

	/**
	 * 获取订单的额外补贴金额
	 * 
	 * @param model
	 * @author pengyi
	 * @date 20150817
	 * @return
	 */
	public abstract Double getAdjustment(OrderCommission model);

	/**
	 * C端 获取订单的金额
	 * 
	 * @param model
	 * @author pengyi
	 * @date 20150817
	 * @return
	 */
	public static Double getCurrenOrderPrice(OrderCommission model) {
		Double amount = model.getAmount();
		Double orderCount = Double.valueOf(model.getOrderCount());
		Double distribSubsidy = model.getDistribSubsidy();
		// 需进行四舍五入
		return amount+distribSubsidy*orderCount;
	}
}

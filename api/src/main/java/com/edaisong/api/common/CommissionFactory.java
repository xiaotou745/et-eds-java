package com.edaisong.api.common;

import java.util.HashMap;
import java.util.Map;

import com.edaisong.core.util.SpringBeanHelper;

/**
 * 骑士佣金计算工厂
 * 
 * @author pengyi
 * @date 20150817
 *
 */
public class CommissionFactory {
	private final static Map<Integer, OrderPriceBaseProvider> services;
	static {
		// Provider中用到了@Autowired，不能new
		DefaultOrPriceProvider defaultOrPriceProvider = SpringBeanHelper
				.getCustomBeanByType(DefaultOrPriceProvider.class);
		TimeOrPriceProvider timeOrPriceProvider = SpringBeanHelper
				.getCustomBeanByType(TimeOrPriceProvider.class);
		BreakEvenPointOrPriceProvider breakEvenPointOrPriceProvider = SpringBeanHelper
				.getCustomBeanByType(BreakEvenPointOrPriceProvider.class);
		AmountOrPriceProvider amountOrPriceProvider = SpringBeanHelper
				.getCustomBeanByType(AmountOrPriceProvider.class);
		BaseCommissionOrPriceProvider baseCommissionOrPriceProvider = SpringBeanHelper
				.getCustomBeanByType(BaseCommissionOrPriceProvider.class);
		
		services = new HashMap<Integer, OrderPriceBaseProvider>();
		services.put(0, defaultOrPriceProvider);
		services.put(1, timeOrPriceProvider);
		services.put(2, breakEvenPointOrPriceProvider);
		services.put(3, amountOrPriceProvider);
		services.put(4, baseCommissionOrPriceProvider);
	}

	public static OrderPriceBaseProvider GetCommission(int commissionFormulaMode) {
		if (services.containsKey(commissionFormulaMode)) {
			return services.get(commissionFormulaMode);
		} else {
			return services.get(0);
		}
	}
}
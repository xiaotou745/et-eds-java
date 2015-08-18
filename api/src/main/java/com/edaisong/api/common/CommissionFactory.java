package com.edaisong.api.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 骑士佣金计算工厂
 * @author pengyi
 * @date 20150817
 *
 */
public class CommissionFactory {
	private final static Map<Integer, OrderPriceBaseProvider> services;
	static{
		services = new HashMap<Integer, OrderPriceBaseProvider>();
		services.put(0, new DefaultOrPriceProvider());
		services.put(1, new TimeOrPriceProvider());
		services.put(2, new BreakEvenPointOrPriceProvider());
		services.put(3, new AmountOrPriceProvider());
		services.put(4, new BaseCommissionOrPriceProvider());
	}
	
	public static OrderPriceBaseProvider GetCommission(int commissionFormulaMode) {
		if (services.containsKey(commissionFormulaMode)) {
			return services.get(commissionFormulaMode);
		}else{
			return services.get(0);
		}
	}
}
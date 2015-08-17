package com.edaisong.api.service.impl;

import java.util.HashMap;
import java.util.Map;


public class CommissionFactory {
	private final static Map<Integer, OrderPriceService> services;
	static{
		services = new HashMap<Integer, OrderPriceService>();
		services.put(0, new DefaultOrPriceService());
		services.put(1, new TimeOrPriceService());
		services.put(2, new BreakEvenPointOrPriceService());
		services.put(3, new AmountOrPriceService());
		services.put(4, new BaseCommissionOrPriceService());
	}
	
	public static OrderPriceService GetCommission(int commissionFormulaMode) {
		if (services.containsKey(commissionFormulaMode)) {
			return services.get(commissionFormulaMode);
		}else{
			return new DefaultOrPriceService();
		}
	}
}

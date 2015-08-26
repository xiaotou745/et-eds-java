package com.edaisong.api.common;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.edaisong.core.util.SpringBeanHelper;

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
		//BaseCommissionOrPriceProvider中用到了@Autowired，不能new
		BaseCommissionOrPriceProvider baseProvider=(BaseCommissionOrPriceProvider)SpringBeanHelper.getCustomBean("baseCommissionOrPriceProvider");
		services.put(4, baseProvider);
	}
	
	public static OrderPriceBaseProvider GetCommission(int commissionFormulaMode) {
		if (services.containsKey(commissionFormulaMode)) {
			return services.get(commissionFormulaMode);
		}else{
			return services.get(0);
		}
	}
}
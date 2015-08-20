package com.edaisong.api.common;

import java.lang.Double;

import org.springframework.stereotype.Service;

import com.edaisong.entity.domain.OrderCommission;


public class BreakEvenPointOrPriceProvider extends OrderPriceBaseProvider{

	@Override
	public Double getCurrenOrderCommission(OrderCommission model) {
		return Double.valueOf(0);
	}

	@Override
	public Double getOrderWebSubsidy(OrderCommission model) {
		return Double.valueOf(0);
	}

	@Override
	public Double getCommissionRate(OrderCommission model) {
		return Double.valueOf(0);
	}

	@Override
	public Double getBaseCommission(OrderCommission model) {
		return Double.valueOf(0);
	}

	@Override
	public Double getAdjustment(OrderCommission model) {
		return Double.valueOf(0);
	}

}

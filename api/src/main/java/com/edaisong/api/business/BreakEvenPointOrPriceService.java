package com.edaisong.api.business;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.edaisong.api.service.impl.OrderPriceService;
import com.edaisong.entity.domain.OrderCommission;


@Service("breakEvenPointOrPriceService")
public class BreakEvenPointOrPriceService extends OrderPriceService{

	@Override
	public BigDecimal getCurrenOrderCommission(OrderCommission model) {
		return BigDecimal.valueOf(0);
	}

	@Override
	public BigDecimal getOrderWebSubsidy(OrderCommission model) {
		return BigDecimal.valueOf(0);
	}

	@Override
	public BigDecimal getCommissionRate(OrderCommission model) {
		return BigDecimal.valueOf(0);
	}

	@Override
	public BigDecimal getBaseCommission(OrderCommission model) {
		return BigDecimal.valueOf(0);
	}

	@Override
	public BigDecimal getAdjustment(OrderCommission model) {
		return BigDecimal.valueOf(0);
	}

}

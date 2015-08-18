package com.edaisong.api.business;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;

import com.edaisong.api.service.impl.OrderPriceService;
import com.edaisong.entity.domain.OrderCommission;


@Service
public class DefaultOrPriceService extends OrderPriceService{

	@Override
	public BigDecimal getCurrenOrderCommission(OrderCommission model) {		
		BigDecimal distribe;  //默认外送费，网站补贴都为0
        BigDecimal commissionRate = getCommissionRate(model); //佣金比例 
        int orderCount = model.getOrderCount(); //订单数量 
        if (model.getDistribSubsidy().compareTo(new BigDecimal(0))> 0){ //如果外送费有数据，按照外送费计算骑士佣金
            distribe = model.getDistribSubsidy();
        }else{ //如果外送费没数据，按照网站补贴计算骑士佣金
            distribe = getOrderWebSubsidy(model);
        }
        return model.getAmount().multiply(commissionRate).
        		add(distribe.multiply(BigDecimal.valueOf(orderCount))).
        		setScale(2, RoundingMode.HALF_UP);//计算佣金
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

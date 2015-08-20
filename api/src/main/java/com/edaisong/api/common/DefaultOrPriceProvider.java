package com.edaisong.api.common;

import java.lang.Double;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;

import com.edaisong.entity.domain.OrderCommission;


@Service
public class DefaultOrPriceProvider extends OrderPriceBaseProvider{

	@Override
	public Double getCurrenOrderCommission(OrderCommission model) {		
		Double distribe;  //默认外送费，网站补贴都为0
        Double commissionRate = getCommissionRate(model); //佣金比例 
        int orderCount = model.getOrderCount(); //订单数量 
        if (model.getDistribSubsidy()> 0){ //如果外送费有数据，按照外送费计算骑士佣金
            distribe = model.getDistribSubsidy();
        }else{ //如果外送费没数据，按照网站补贴计算骑士佣金
            distribe = getOrderWebSubsidy(model);
        }
        //订单金额*佣金比例+外送费（或网站补贴）*订单数量
        return model.getAmount()*commissionRate+distribe*orderCount;
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

package com.edaisong.api.service.impl;

import org.springframework.stereotype.Service;

import com.edaisong.entity.domain.OrderCommission;


@Service
public class DefaultOrPriceService extends OrderPriceService{

	@Override
	public float GetCurrenOrderCommission(OrderCommission model) {		
   
        float distribe = 0;  //默认外送费，网站补贴都为0
        float commissionRate = GetCommissionRate(model); //佣金比例 
        int orderCount = model.getOrderCount(); //订单数量 
        if (model.getDistribSubsidy() > 0) //如果外送费有数据，按照外送费计算骑士佣金
        {
            distribe = model.getDistribSubsidy();
        }
        else //如果外送费没数据，按照网站补贴计算骑士佣金
        {
            distribe = GetOrderWebSubsidy(model);
        }
        return model.getAmount() * commissionRate + distribe * orderCount;//计算佣金
	}

	@Override
	public float GetOrderWebSubsidy(OrderCommission model) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float GetCommissionRate(OrderCommission model) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float GetBaseCommission(OrderCommission model) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float GetAdjustment(OrderCommission model) {
		// TODO Auto-generated method stub
		return 0;
	}

}

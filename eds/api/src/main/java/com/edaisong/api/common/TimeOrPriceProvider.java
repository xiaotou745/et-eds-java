package com.edaisong.api.common;

import java.lang.Double;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.edaisong.api.service.inter.IGlobalConfigService;
import com.edaisong.core.util.ParseHelper;
import com.edaisong.entity.domain.OrderCommission;

@Component
public class TimeOrPriceProvider extends OrderPriceBaseProvider{
	@Autowired
	IGlobalConfigService globalConfigService;
	
	@Override
	public Double getCurrenOrderCommission(OrderCommission model) {
        if (model.getAmount() == null)
            return 0d;
        Double distribe = 0d;  //默认外送费，网站补贴都为0
        Double commissionRate = getCommissionRate(model); //佣金比例 
        int orderCount = model.getOrderCount(); //佣金比例 
        if (model.getDistribSubsidy() != null && model.getDistribSubsidy() > 0)//如果外送费有数据，按照外送费计算骑士佣金
            distribe = model.getDistribSubsidy();
        else
            distribe = getOrderWebSubsidy(model);
        return model.getAmount() * commissionRate + distribe * orderCount;//计算佣金
	}

	@Override
	public Double getOrderWebSubsidy(OrderCommission model) {
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		 return (hour >= 10 && hour <= 13) || (hour >= 16 && hour <= 19) ?
				 ParseHelper.ToDouble(globalConfigService.getConfigValueByKey(model.getBusinessGroupId(),"TimeSpanInPrice"),0d) 
				 :ParseHelper.ToDouble(globalConfigService.getConfigValueByKey(model.getBusinessGroupId(),"TimeSpanOutPrice"),0d);
	}

	@Override
	public Double getCommissionRate(OrderCommission model) {
		return ParseHelper.ToDouble(
				globalConfigService.getConfigValueByKey(model.getBusinessGroupId(), "TimeSpanCommissionRatio"), 0d);
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

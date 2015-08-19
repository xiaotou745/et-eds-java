package com.edaisong.api.common;

import java.lang.Double;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.edaisong.api.dao.impl.GlobalConfigDao;
import com.edaisong.api.service.inter.IGlobalConfigService;
import com.edaisong.entity.domain.GlobalGroupConfigModel;
import com.edaisong.entity.domain.OrderCommission;

/**
 * 基本佣金+补贴
 * @author pengyi
 * @date 20150817
 */
@Component("baseCommissionOrPriceService")
public class BaseCommissionOrPriceProvider extends OrderPriceBaseProvider{
	
	@Autowired
	IGlobalConfigService globalConfigService;
	@Override
	public Double getCurrenOrderCommission(OrderCommission model) {
        if (model.getAmount() == null)
        	return 0d;
        Double distribe = getOrderWebSubsidy(model);  //默认外送费，网站补贴都为0
        return (getBaseCommission(model)+distribe)*model.getOrderCount();//计算佣金:（基本佣金（可配置）+ 代收客配或网站补贴）*订单数量
	}

	@Override
	public Double getOrderWebSubsidy(OrderCommission model) {
        if (model.getOrderWebSubsidy() != null && !model.getOrderWebSubsidy().equals(Double.valueOf(0))){
            return model.getOrderWebSubsidy();
        }
        String baseSiteSubsidies=globalConfigService.getConfigValueByKey(model.getBusinessGroupId(), "baseSiteSubsidies");
        if(baseSiteSubsidies != null&&!baseSiteSubsidies.isEmpty()){
        	return new Double(baseSiteSubsidies);
        }else{
        	return 0d;
        }
	}

	@Override
	public Double getCommissionRate(OrderCommission model) {
		return Double.valueOf(0);
	}

	@Override
	public Double getBaseCommission(OrderCommission model) {
		String baseCommission=globalConfigService.getConfigValueByKey(model.getBusinessGroupId(), "baseCommission");
        if(baseCommission != null&&!baseCommission.isEmpty()){
        	return new Double(baseCommission);
        }else{
        	return Double.valueOf(0);
        }
	}

	@Override
	public Double getAdjustment(OrderCommission model) {
		return Double.valueOf(0);
	}

}

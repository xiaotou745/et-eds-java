package com.edaisong.api.common;

import java.lang.Double;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.edaisong.api.service.inter.IGlobalConfigService;
import com.edaisong.core.util.ParseHelper;
import com.edaisong.entity.domain.OrderCommission;

@Component
public class AmountOrPriceProvider extends OrderPriceBaseProvider{
	@Autowired
	IGlobalConfigService globalConfigService;
	@Override
	public Double getCurrenOrderCommission(OrderCommission model) {
		if (model.getAmount() == null)
            return (double) 0;
        double commissionRate = getCommissionRate(model);//佣金比例 
        int orderCount = ParseHelper.ToInt(model.getOrderCount()); //订单数量 
        if (model.getDistribSubsidy() != null && model.getDistribSubsidy() > 0){//如果外送费有数据，按照外送费计算骑士佣金{
            return getAdjustment(model) + model.getAmount() * commissionRate
                    + model.getDistribSubsidy() * orderCount;//计算佣金
        }
        else  //无外送费按照网站补贴计算佣金金额
            return getAdjustment(model) + model.getAmount() * commissionRate + getOrderWebSubsidy(model) * orderCount;//计算佣金
	}

	@Override
	public Double getOrderWebSubsidy(OrderCommission model) {
		if (model.getOrderWebSubsidy() != null && model.getOrderWebSubsidy() != 0){
            return model.getOrderWebSubsidy();
        }
        String orderWebSubsidy=globalConfigService.getConfigValueByKey(model.getBusinessGroupId(), "PriceSiteSubsidies");
        if(orderWebSubsidy != null&&!orderWebSubsidy.isEmpty()){
        	return new Double(orderWebSubsidy);
        }else{
        	return 0d;
        }
	}

	@Override
	public Double getCommissionRate(OrderCommission model) {
		double temp = model.getBusinessCommission() - ParseHelper.ToDouble(globalConfigService.getConfigValueByKey(model.getBusinessGroupId(), "PriceCommissionRatio"),0d);
        if (temp == 0)
            return 0d;
        else
            return temp / 100;
	}

	@Override
	public Double getBaseCommission(OrderCommission model) {
		return Double.valueOf(0);
	}

	@Override
	public Double getAdjustment(OrderCommission model) {
		String globalConfigModel =globalConfigService.getConfigValueByKey(model.getBusinessGroupId(), "PriceSubsidies");
        String[] globalConfigList = globalConfigModel.split(";");
        Double adjustment = 0d; //额外补贴金额
        for (int i = globalConfigList.length - 1; i >= 0; i--){
            String[] tempInfo = globalConfigList[i].split(",");
            Double money = ParseHelper.ToDouble(tempInfo[0],0d);  //满金额
            Double addmony = ParseHelper.ToDouble(tempInfo[1],0d); //补贴金额
            if (ParseHelper.ToDouble(model.getAmount(),0d) >= money){
                adjustment = addmony;
                break;
            }
        }
        return adjustment;
	}
}

package com.edaisong.api.business;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dal.dao.impl.GlobalConfigDao;
import com.edaisong.api.service.impl.OrderPriceService;
import com.edaisong.entity.domain.GlobalGroupConfigModel;
import com.edaisong.entity.domain.OrderCommission;

/**
 * 基本佣金+补贴
 * @author pengyi
 * @date 20150817
 *
 */
@Service("baseCommissionOrPriceService")
public class BaseCommissionOrPriceService extends OrderPriceService{
	
	@Autowired
	GlobalConfigDao globalConfigDao;
	@Override
	public BigDecimal getCurrenOrderCommission(OrderCommission model) {
        if (model.getAmount() == null)
        	return BigDecimal.valueOf(0);
        BigDecimal distribe = getOrderWebSubsidy(model);  //默认外送费，网站补贴都为0
        return getBaseCommission(model).add(distribe)
        		.multiply(new BigDecimal(model.getOrderCount()))
        		.setScale(2, RoundingMode.HALF_UP);//计算佣金:（基本佣金（可配置）+ 代收客配或网站补贴）*订单数量
	}

	@Override
	public BigDecimal getOrderWebSubsidy(OrderCommission model) {
        if (model.getOrderWebSubsidy() != null && !model.getOrderWebSubsidy().equals(BigDecimal.valueOf(0))){
            return model.getOrderWebSubsidy();
        }
        GlobalGroupConfigModel globalConfigModel = globalConfigDao.GlobalConfigMethod(model.getBusinessGroupId());
        if(globalConfigModel != null){
        	return new BigDecimal(globalConfigModel.getBaseSiteSubsidies());
        }else{
        	return BigDecimal.valueOf(0);
        }
	}

	@Override
	public BigDecimal getCommissionRate(OrderCommission model) {
		return BigDecimal.valueOf(0);
	}

	@Override
	public BigDecimal getBaseCommission(OrderCommission model) {
        GlobalGroupConfigModel globalConfigModel = globalConfigDao.GlobalConfigMethod(model.getBusinessGroupId());
        if(globalConfigModel != null){
        	return new BigDecimal(globalConfigModel.getBaseCommission());
        }else{
        	return BigDecimal.valueOf(0);
        }
	}

	@Override
	public BigDecimal getAdjustment(OrderCommission model) {
		return BigDecimal.valueOf(0);
	}

}

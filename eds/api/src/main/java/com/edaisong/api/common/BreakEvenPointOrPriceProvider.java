package com.edaisong.api.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.edaisong.api.service.inter.IGlobalConfigService;
import com.edaisong.core.util.ParseHelper;
import com.edaisong.entity.domain.OrderCommission;

@Component
public class BreakEvenPointOrPriceProvider extends OrderPriceBaseProvider {
	@Autowired
	IGlobalConfigService globalConfigService;

	@Override
	public Double getCurrenOrderCommission(OrderCommission model) {
		if (model.getAmount() == null)
			return 0d;
		Double commissionRate = getCommissionRate(model);// 佣金比例
		int orderCount = model.getOrderCount(); // 订单数量
		if (model.getDistribSubsidy() != null && model.getDistribSubsidy() > 0){ // 如果外送费有数据，按照外送费计算骑士佣金
			return model.getAmount() * commissionRate + ParseHelper.ToDouble(model.getDistribSubsidy(), 0d)
					* orderCount; // 计算佣金
		} else {// 无外送费按照网站补贴计算佣金金额
			return model.getAmount() * commissionRate + getOrderWebSubsidy(model) * orderCount;// 计算佣金
		}
	}

	@Override
	public Double getOrderWebSubsidy(OrderCommission model) {
		if (model.getOrderWebSubsidy() != null && model.getOrderWebSubsidy() != 0) {
			return ParseHelper.ToDouble(model.getOrderWebSubsidy(), 0d);
		}
		return ParseHelper.ToDouble(
				globalConfigService.getConfigValueByKey(model.getBusinessGroupId(), "SiteSubsidies"), 0d);
	}

	@Override
	public Double getCommissionRate(OrderCommission model) {
		double temp = model.getBusinessCommission()
				- ParseHelper.ToDouble(
						globalConfigService.getConfigValueByKey(model.getBusinessGroupId(), "CommissionRatio"), 0d);
		if (temp == 0)
			return 0d;
		else
			return temp / 100d;
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

package com.edaisong.api.service.impl;

import org.springframework.stereotype.Service;

import com.edaisong.entity.domain.OrderCommission;


@Service
public abstract class OrderPriceService{
	   /// <summary>
    /// 获取订单的骑士佣金 add by caoheyang 20150305
    /// </summary>
    /// <param name="model">订单</param>
    /// <returns></returns>
    public abstract float GetCurrenOrderCommission(OrderCommission model);

    /// <summary>
    /// 获取订单的网站补贴 add by caoheyang 20150402
    /// </summary>
    /// <param name="model">订单</param>
    /// <returns></returns>
    public abstract float GetOrderWebSubsidy(OrderCommission model);

    /// <summary>
    /// 获取订单的佣金比例 add by caoheyang 20150402
    /// </summary>
    /// <param name="model">订单</param>
    /// <returns></returns>
    public abstract float GetCommissionRate(OrderCommission model);

    /// <summary>
    /// 获取订单基本佣金 add by 彭宜 20150807
    /// </summary>
    /// <param name="model"></param>
    /// <returns></returns>
    public abstract float GetBaseCommission(OrderCommission model);

    /// <summary>
    /// 获取订单的额外补贴金额 add by caoheyang 20150409
    /// </summary>
    /// <param name="model">订单</param>
    /// <returns></returns>
    public abstract float GetAdjustment(OrderCommission model);


    /// <summary>
    ///C端 获取订单的金额 add by caoheyang 20150305
    /// </summary>
    /// <param name="model">订单</param>
    /// <returns></returns>
    public static float GetCurrenOrderPrice(OrderCommission model)
    {
    	float amount = model.getAmount(); 
        int orderCount = model.getOrderCount(); 
        float distribSubsidy = model.getDistribSubsidy(); 
        //需进行四舍五入
        return amount + orderCount * distribSubsidy;
    }

}

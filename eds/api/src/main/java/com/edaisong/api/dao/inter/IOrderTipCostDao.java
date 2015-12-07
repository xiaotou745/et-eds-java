package com.edaisong.api.dao.inter;

import com.edaisong.entity.OrderTipCost;

public interface IOrderTipCostDao {
    int insert(OrderTipCost record);

    int insertSelective(OrderTipCost record);
}
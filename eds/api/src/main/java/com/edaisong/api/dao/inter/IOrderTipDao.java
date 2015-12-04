package com.edaisong.api.dao.inter;

import com.edaisong.entity.OrderTip;

public interface IOrderTipDao {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderTip record);

    int insertSelective(OrderTip record);

    OrderTip selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderTip record);

    int updateByPrimaryKey(OrderTip record);
}
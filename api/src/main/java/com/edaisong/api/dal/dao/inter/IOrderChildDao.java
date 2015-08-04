package com.edaisong.api.dal.dao.inter;

import com.edaisong.entity.OrderChild;

public interface IOrderChildDao {
    int deleteByPrimaryKey(Long id);

    int insert(OrderChild record);

    int insertSelective(OrderChild record);

    OrderChild selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderChild record);

    int updateByPrimaryKey(OrderChild record);
}
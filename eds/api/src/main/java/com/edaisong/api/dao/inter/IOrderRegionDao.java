package com.edaisong.api.dao.inter;

import com.edaisong.entity.OrderRegion;

public interface IOrderRegionDao {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderRegion record);

    int insertSelective(OrderRegion record);

    OrderRegion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderRegion record);

    int updateByPrimaryKey(OrderRegion record);
}
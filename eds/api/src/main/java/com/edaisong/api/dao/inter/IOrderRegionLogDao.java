package com.edaisong.api.dao.inter;

import com.edaisong.entity.OrderRegionLog;

public interface IOrderRegionLogDao {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderRegionLog record);

    int insertSelective(OrderRegionLog record);

    OrderRegionLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderRegionLog record);

    int updateByPrimaryKey(OrderRegionLog record);
}
package com.edaisong.api.dao.inter;

import com.edaisong.entity.OrderSubsidiesLog;

public interface IOrderSubsidiesLogDao {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderSubsidiesLog record);

    int insertSelective(OrderSubsidiesLog record);

    OrderSubsidiesLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderSubsidiesLog record);

    int updateByPrimaryKey(OrderSubsidiesLog record);
}
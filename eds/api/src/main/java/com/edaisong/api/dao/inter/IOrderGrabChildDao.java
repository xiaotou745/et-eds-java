package com.edaisong.api.dao.inter;

import com.edaisong.entity.OrderGrabChild;

public interface IOrderGrabChildDao {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderGrabChild record);

    int insertSelective(OrderGrabChild record);

    OrderGrabChild selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderGrabChild record);

    int updateByPrimaryKey(OrderGrabChild record);
}
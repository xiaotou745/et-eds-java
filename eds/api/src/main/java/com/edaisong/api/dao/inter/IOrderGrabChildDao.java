package com.edaisong.api.dao.inter;

import java.util.List;

import com.edaisong.entity.OrderGrabChild;

public interface IOrderGrabChildDao {
    int deleteByPrimaryKey(Integer id);
    List<OrderGrabChild> selectByGrabOrderId(Long grabOrderId);
    int insert(OrderGrabChild record);

    int insertSelective(OrderGrabChild record);

    OrderGrabChild selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderGrabChild record);

    int updateByPrimaryKey(OrderGrabChild record);
}
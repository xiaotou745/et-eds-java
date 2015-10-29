package com.edaisong.api.dao.inter;

import com.edaisong.entity.OrderGrab;

public interface IOrderGrabDao {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderGrab record);

    int insertSelective(OrderGrab record);

    OrderGrab selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderGrab record);

    int updateByPrimaryKey(OrderGrab record);
}
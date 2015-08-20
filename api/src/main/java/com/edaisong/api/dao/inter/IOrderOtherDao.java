package com.edaisong.api.dao.inter;

import java.util.List;

import com.edaisong.entity.OrderOther;

public interface IOrderOtherDao {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderOther record);

    int insertSelective(OrderOther record);

    OrderOther selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderOther record);

    int updateByPrimaryKey(OrderOther record);
    
    
    
   
}
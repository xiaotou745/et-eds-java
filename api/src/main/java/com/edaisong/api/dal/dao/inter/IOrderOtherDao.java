package com.edaisong.api.dal.dao.inter;

import java.util.List;

import com.edaisong.entity.OrderOther;

public interface IOrderOtherDao {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderOther record);

    int insertSelective(OrderOther record);

    OrderOther selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderOther record);

    int updateByPrimaryKey(OrderOther record);
    
    
    
    /**
     * 根据订单信息查询 子订单集合 
     * @param orderNo 订单号
     * @param businessId 商户id
     * @author CaoHeYang
     * @Date 20150804
     * @return
     */
    List<OrderOther> getOrderChildByOrderInfo(String orderNo,int businessId);
}
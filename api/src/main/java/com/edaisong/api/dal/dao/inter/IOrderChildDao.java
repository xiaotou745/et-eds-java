package com.edaisong.api.dal.dao.inter;

import java.util.List;

import com.edaisong.entity.OrderChild;
import com.edaisong.entity.OrderOther;

public interface IOrderChildDao {
    //int deleteByPrimaryKey(Long id);

    int insert(OrderChild record);
    int insertList(List<OrderChild> record);

//    int insertSelective(OrderChild record);
//
//    OrderChild selectByPrimaryKey(Long id);
//
//    int updateByPrimaryKeySelective(OrderChild record);
//
//    int updateByPrimaryKey(OrderChild record);
    
    /**
     * 根据订单信息查询 子订单集合 
     * @param orderNo 订单号
     * @param businessId 商户id
     * @author CaoHeYang
     * @Date 20150804
     * @return
     */
    List<OrderChild> getOrderChildByOrderInfo(String orderNo,int businessId);
}
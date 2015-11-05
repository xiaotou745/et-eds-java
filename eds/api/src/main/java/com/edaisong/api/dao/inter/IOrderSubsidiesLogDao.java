package com.edaisong.api.dao.inter;

import java.util.List;

import com.edaisong.entity.OrderSubsidiesLog;

public interface IOrderSubsidiesLogDao {
    int insert(OrderSubsidiesLog record);
    
    /**
     * 获取订单操作日志
     * @author CaoHeYang
     * @param orderId 订单id
     * @date 20150827  
     * @return
     */
   List<OrderSubsidiesLog>  GetOrderOptionLog(Long orderId);
   /**
    * 获取快单操作日志
    * @author zhaohl
    * @param orderId 订单id
    * @date 20151105
    * @return
    */
  List<OrderSubsidiesLog>  GetFastOrderOptionLog(Long orderId);
}
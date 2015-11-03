package com.edaisong.api.service.inter;

import java.util.List;

import com.edaisong.entity.OrderSubsidiesLog;

public interface IOrderSubsidiesLogService {
	 /**
     * 获取订单操作日志
     * @author CaoHeYang
     * @param orderId 订单id
     * @date 20150827  
     * @return
     */
   List<OrderSubsidiesLog>  GetOrderOptionLog(Long orderId);
}

package com.edaisong.api.dao.inter;

import java.util.List;

import com.edaisong.entity.OrderChild;
import com.edaisong.entity.OrderOther;
import com.edaisong.entity.req.OrderGrabReq;

public interface IOrderChildDao {
    int insert(OrderChild record);
    int insertList(List<OrderChild> record);
    /**
     * 根据订单信息查询 子订单集合 
     * @param orderNo 订单号
     * @param businessId 商户id
     * @author CaoHeYang
     * @Date 20150804
     * @return
     */
    List<OrderChild> getOrderChildByOrderInfo(String orderNo,int businessId);
    
    
   /**
    *  获取任务支付状态（0：未支付 1：部分支付 2：已支付）
    *  @author CaoHeYang
    * @param orderId
    * @date  20150831
    * @return
    */
    int   getOrderTaskPayStatus(int orderId);
    
    List<Integer>   updateGradOne(OrderGrabReq record);  
	

    List<Integer>   updateGradTwo(OrderGrabReq record) ;
}
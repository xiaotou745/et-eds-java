package com.edaisong.api.dao.inter;

import java.util.List;

import com.edaisong.entity.CommissionType;
import com.edaisong.entity.OrderChild;
import com.edaisong.entity.OrderGrabChild;
import com.edaisong.entity.OrderOther;
import com.edaisong.entity.req.OrderChildCancelReq;
import com.edaisong.entity.req.OrderGrabReq;

public interface IOrderChildDao {
    int insert(OrderChild record);
    int insertList(List<OrderChild> record);
    
    int updateByPrimaryKeySelective(OrderChild record);
	int updateList(List<OrderChild> record);	
    /**
     * 取消订单 （取消前一天智能调度发单且未被抢单的子订单时）
     * @param 日期
     * @author 胡灵波
     * @Date 2015年11月5日 11:40:37
     * @return
     */
	List<Integer> updateCancel(OrderChildCancelReq req);
	
	
    List<Integer>   updateGradOne(OrderGrabReq record);  	

    List<Integer>   updateGradTwo(OrderGrabReq record) ;
    /**
     * 根据订单信息查询 子订单集合 
     * @param orderNo 订单号
     * @param businessId 商户id
     * @author CaoHeYang
     * @Date 20150804
     * @return
     */
    List<OrderChild> getOrderChildByOrderInfo(String orderNo,int businessId);
    
    List<OrderChild> getOrderChildByOrderId(int orderId);
   /**
    *  获取任务支付状态（0：未支付 1：部分支付 2：已支付）
    *  @author CaoHeYang
    * @param orderId
    * @date  20150831
    * @return
    */
    int   getOrderTaskPayStatus(int orderId); 

   
    OrderChild selectByPrimaryKey(Integer id);    
}
package com.edaisong.api.dao.inter;

import java.util.List;

import com.edaisong.entity.OrderOther;
import com.edaisong.entity.req.OrderOtherSearch;

public interface IOrderOtherDao {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderOther record);

    int insertSelective(OrderOther record);

    OrderOther selectByPrimaryKey(Integer id);
    
    OrderOther selectByOrderId(Integer orderId);

    int updateByPrimaryKeySelective(OrderOther record);

    int updateByPrimaryKey(OrderOther record);
    
    /**
     * 更新已提现状态
     * @author CaoHeYang
     * @param orderId
     * @date 20150831
     * @return
     */
     int  updateJoinWithdraw(int orderId);
     /**
      * 更新订单审核状态
      * @param orderId
      * @param auditstatus
      * @return
      */
     int  updateAuditStatus(int orderId, int auditstatus);
 	/**
 	 * 更新订单是否无效的标记
 	 * 
 	 * @author CaoHeYang
 	 * @param orderId
 	 * @param auditstatus
 	 * @date 20150831
 	 * @return
 	 */
	int updateOrderIsReal(OrderOtherSearch orderOtherSearch);
     
}
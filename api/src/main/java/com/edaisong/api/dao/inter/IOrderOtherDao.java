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
}
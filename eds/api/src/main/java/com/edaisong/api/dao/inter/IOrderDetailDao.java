package com.edaisong.api.dao.inter;



import java.util.List;

import com.edaisong.entity.OrderDetail;

public interface IOrderDetailDao {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderDetail record);

    int insertSelective(OrderDetail record);

    OrderDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderDetail record);

    int updateByPrimaryKey(OrderDetail record);
    

    /**
     * 根据订单号/订单id查订单详情信息 
     * @author CaoHeYang
     * @param ordernNo 订单号
     * @param orderId  订单id
     * @Date 20150827
     * @return
     */
    List<OrderDetail> getOrderDetailIByNoId(String ordernNo, int orderId);
    

}
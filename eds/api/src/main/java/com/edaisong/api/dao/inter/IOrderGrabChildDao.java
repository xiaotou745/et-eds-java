package com.edaisong.api.dao.inter;

import java.util.List;

import com.edaisong.entity.OrderGrabChild;
import com.edaisong.entity.req.OrderGrabReq;

public interface IOrderGrabChildDao {	

    int deleteByPrimaryKey(Integer id);
    List<OrderGrabChild> selectByGrabOrderId(Long grabOrderId);
    
    public OrderGrabChild selectTop1ByGrabOrderId(Long grabOrderId);
    
    int insert(OrderGrabChild record);

    int insertSelective(OrderGrabChild record);

    OrderGrabChild selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderGrabChild record);
    
    int updateByGraborderidSelective(OrderGrabChild record);       

    int updateByPrimaryKey(OrderGrabChild record);   
	
	int insertList(List<OrderGrabChild> record);

}
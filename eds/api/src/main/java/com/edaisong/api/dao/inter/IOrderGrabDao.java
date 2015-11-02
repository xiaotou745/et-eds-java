package com.edaisong.api.dao.inter;

import com.edaisong.entity.OrderGrab;
import com.edaisong.entity.domain.OrderGrabDetailModel;
import com.edaisong.entity.req.OrderDetailCReq;

public interface IOrderGrabDao {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderGrab record);

    int insertSelective(OrderGrab record);

    OrderGrab selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderGrab record);

    int updateByPrimaryKey(OrderGrab record);

	OrderGrabDetailModel getMyOrderDetailC(OrderDetailCReq orderDetailCReq);
}
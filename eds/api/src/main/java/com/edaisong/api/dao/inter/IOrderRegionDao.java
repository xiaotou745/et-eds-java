package com.edaisong.api.dao.inter;

import java.util.List;

import com.edaisong.entity.OrderRegion;
import com.edaisong.entity.req.OrderRegionReq;
import com.edaisong.entity.resp.OrderRegionResp;

public interface IOrderRegionDao {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderRegion record);

    int insertSelective(OrderRegion record);

    OrderRegion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderRegion record);

    int updateByPrimaryKey(OrderRegion record);
    
    /*
     * 获取商户区域信息
     * wangchao
     */
    List<OrderRegion> getOrderRegion(OrderRegionReq orderRegionReq);
}
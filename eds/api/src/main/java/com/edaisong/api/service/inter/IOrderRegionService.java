package com.edaisong.api.service.inter;

import java.util.List; 

import com.edaisong.entity.OrderRegion;
import com.edaisong.entity.req.OrderRegionReq;
 
public interface IOrderRegionService {
	List<OrderRegion> getOrderRegion(OrderRegionReq orderRegionReq );
    int deleteById(Integer id);
	Integer updateRegionList(List<OrderRegion> regionList);
	Integer insertRegionList(List<OrderRegion> regionList);
}
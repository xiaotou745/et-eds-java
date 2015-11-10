package com.edaisong.api.service.inter;

import java.util.List; 

import com.edaisong.entity.OrderRegion;
import com.edaisong.entity.req.OrderRegionReq;
 
public interface IOrderRegionService {
	List<OrderRegion> getOrderRegion(OrderRegionReq orderRegionReq );
	List<String> deleteRegionList(List<Integer> regionList,List<OrderRegion> oldList);
	Integer updateRegionList(List<OrderRegion> regionList,List<OrderRegion> oldList);
	Integer insertRegionList(List<OrderRegion> regionList,List<OrderRegion> oldList);
}

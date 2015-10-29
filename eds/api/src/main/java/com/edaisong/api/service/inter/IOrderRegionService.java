package com.edaisong.api.service.inter;

import java.util.List; 
import com.edaisong.entity.OrderRegion;
 
public interface IOrderRegionService {
	List<OrderRegion> getOrderRegion(Integer businessId );
}

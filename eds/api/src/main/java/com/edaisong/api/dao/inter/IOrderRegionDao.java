package com.edaisong.api.dao.inter;

import java.util.List;

import com.edaisong.entity.OrderRegion;
import com.edaisong.entity.req.OrderRegionReq;

public interface IOrderRegionDao {
	OrderRegion getById(Integer id);
	List<OrderRegion> getByParentId(Integer parentId);
    int deleteByIds(List<Integer> idList);
    int updateHasNoChild(Integer id);
	Integer updateRegionList(List<OrderRegion> regionList);
	Integer insertRegionList(List<OrderRegion> regionList);
    
    /*
     * 获取商户区域信息
     * wangchao
     */
    List<OrderRegion> getOrderRegion(OrderRegionReq orderRegionReq);
}
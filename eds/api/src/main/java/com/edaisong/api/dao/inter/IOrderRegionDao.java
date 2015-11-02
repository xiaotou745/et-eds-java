package com.edaisong.api.dao.inter;

import java.util.List;

import com.edaisong.entity.OrderRegion;
import com.edaisong.entity.domain.InStoreOrderRegionInfo;
import com.edaisong.entity.req.InStoreTaskReq;
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
    
    int updateByPrimaryKeySelective(OrderRegion record);
    
    /**
	 * 骑士端获取店内任务  获取当前骑士的所有区域订单的信息
	 * @version 3.0  
	 * @author CaoHeYang
	 * @date 20151102
	 * @param para
	 * @return
	 */
    List<InStoreOrderRegionInfo> getInStoreOrderRegions(InStoreTaskReq para);
    
}
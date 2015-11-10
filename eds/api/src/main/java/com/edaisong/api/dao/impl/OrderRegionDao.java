package com.edaisong.api.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Repository; 

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IOrderRegionDao;
import com.edaisong.entity.OrderRegion;
import com.edaisong.entity.domain.InStoreOrderRegionInfo;
import com.edaisong.entity.req.InStoreTaskReq;
import com.edaisong.entity.req.OrderRegionReq;
@Repository
public class OrderRegionDao extends DaoBase implements IOrderRegionDao {
	@Override
	public List<OrderRegion> getOrderRegion(OrderRegionReq orderRegionReq) {
		return getReadOnlySqlSessionUtil().selectList(
				"com.edaisong.api.dao.inter.IOrderRegionDao.getOrderRegion", orderRegionReq);
	}
				
	@Override
	public int updateByPrimaryKeySelective(OrderRegion record) {	
		return getMasterSqlSessionUtil()
				.update("com.edaisong.api.dao.inter.IOrderRegionDao.updateByPrimaryKeySelective",
						record);
	}
	
	@Override
	public Integer updateRegionList(List<OrderRegion> regionList) {
		return getMasterSqlSessionUtil().update("com.edaisong.api.dao.inter.IOrderRegionDao.updateRegionList", regionList);
	}

	@Override
	public Integer insert(OrderRegion region) {
		return getMasterSqlSessionUtil().insert("com.edaisong.api.dao.inter.IOrderRegionDao.insert", region);
	}

	@Override
	public OrderRegion getById(Integer id) {
		return getReadOnlySqlSessionUtil().selectOne(
				"com.edaisong.api.dao.inter.IOrderRegionDao.getById", id);
	}
	
	@Override
	public OrderRegion getByIdWrite(Integer id) {
		return getMasterSqlSessionUtil().selectOne(
				"com.edaisong.api.dao.inter.IOrderRegionDao.getByIdWirte", id);
	}

	@Override
	public List<OrderRegion> getByParentId(Integer parentId) {
		return getReadOnlySqlSessionUtil().selectList(
				"com.edaisong.api.dao.inter.IOrderRegionDao.getByParentId", parentId);
	}

	@Override
	public int deleteByIds(List<Integer> idList) {
		return getMasterSqlSessionUtil().update(
				"com.edaisong.api.dao.inter.IOrderRegionDao.deleteByIds", idList);
	}

	@Override
	public int updateHasChildByIds(Integer hasChilds,List<Integer> idList) {
		if (idList==null||idList.size()==0) {
			return 0;
		}
		Map<String, Object> paramMap = new HashedMap();
		paramMap.put("hasChilds", hasChilds);
		paramMap.put("idList", idList);
		return getMasterSqlSessionUtil().update(
				"com.edaisong.api.dao.inter.IOrderRegionDao.updateHasChildByIds", paramMap);
	}

	
    
    /**
	 * 骑士端获取店内任务  获取当前骑士的所有区域订单的信息
	 * @version 3.0  
	 * @author CaoHeYang
	 * @date 20151102
	 * @param para
	 * @return
	 */
    public List<InStoreOrderRegionInfo> getInStoreOrderRegions(InStoreTaskReq para){
    	return getReadOnlySqlSessionUtil().selectList(
				"com.edaisong.api.dao.inter.IOrderRegionDao.getInStoreOrderRegions", para);
    }
    


}

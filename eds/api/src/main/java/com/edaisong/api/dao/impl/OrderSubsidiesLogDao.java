package com.edaisong.api.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IOrderSubsidiesLogDao;
import com.edaisong.entity.OrderSubsidiesLog;

@Repository
public class OrderSubsidiesLogDao extends DaoBase implements IOrderSubsidiesLogDao{

	@Override
	public int insert(OrderSubsidiesLog record) {
		return getMasterSqlSessionUtil().insert("IOrderSubsidiesLogDao.insert",record);
	}


	  /**
   * 获取订单操作日志
   * @author CaoHeYang
   * @param orderId 订单id
   * @date 20150827  
   * @return
   */
	@Override
	public List<OrderSubsidiesLog> GetOrderOptionLog(Long orderId) {
		return getReadOnlySqlSessionUtil().selectList("IOrderSubsidiesLogDao.GetOrderOptionLog", 
				orderId);
	}


	@Override
	public List<OrderSubsidiesLog> GetFastOrderOptionLog(Long orderId) {
		return getReadOnlySqlSessionUtil().selectList("IOrderSubsidiesLogDao.GetFastOrderOptionLog", 
				orderId);
	}

}

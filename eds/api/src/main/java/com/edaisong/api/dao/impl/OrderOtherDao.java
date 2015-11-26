package com.edaisong.api.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IOrderOtherDao;
import com.edaisong.entity.OrderOther;
import com.edaisong.entity.domain.OrderDetailBusiness;
import com.edaisong.entity.req.OrderOtherSearch;

@Repository
public class OrderOtherDao extends DaoBase implements IOrderOtherDao {

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(OrderOther record) {
		// TODO Auto-generated method stub
		return getMasterSqlSessionUtil().insert(
				"IOrderOtherDao.insertSelective",
				record);
	}

	@Override
	public int insertSelective(OrderOther record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public OrderOther selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(OrderOther record) {
		return getMasterSqlSessionUtil()
				.update("IOrderOtherDao.updateByPrimaryKeySelective",
						record);
		// TODO Auto-generated method stub
	}

	@Override
	public int updateByPrimaryKey(OrderOther record) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 更新已提现状态
	 * 
	 * @author CaoHeYang
	 * @param orderId
	 * @date 20150831
	 * @return
	 */
	@Override
	public int updateJoinWithdraw(int orderId) {
		Map<String, Object> maps = new HashedMap();
		maps.put("orderId", orderId);
		return getMasterSqlSessionUtil().update(
				"IOrderOtherDao.updateJoinWithdraw",
				maps);
	}

	/**
	 * 更新订单审核状态
	 * 
	 * @author CaoHeYang
	 * @param orderId
	 * @param auditstatus
	 * @date 20150831
	 * @return
	 */
	@Override
	public int updateAuditStatus(int orderId, int auditstatus) {
		Map<String, Object> maps = new HashedMap();
		maps.put("orderId", orderId);
		maps.put("auditstatus", auditstatus);
		return getMasterSqlSessionUtil().update(
				"IOrderOtherDao.updateAuditStatus",
				maps);
	}
	/**
	 * 更新订单是否无效的标记
	 * 
	 * @author CaoHeYang
	 * @param orderId
	 * @param auditstatus
	 * @date 20150831
	 * @return
	 */
	@Override
	public int updateOrderIsReal(OrderOtherSearch orderOtherSearch) {
		return getMasterSqlSessionUtil().update(
				"IOrderOtherDao.updateOrderIsReal",
				orderOtherSearch);
	}
	
	
}

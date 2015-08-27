package com.edaisong.api.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IOrderDetailDao;
import com.edaisong.entity.OrderDetail;
@Repository
public class OrderDetailDao  extends DaoBase implements IOrderDetailDao {

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(OrderDetail record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(OrderDetail record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public OrderDetail selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(OrderDetail record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(OrderDetail record) {
		// TODO Auto-generated method stub
		return 0;
	}

	  /**
     * 根据订单号/订单id查订单详情信息 
     * @author CaoHeYang
     * @param ordernNo 订单号
     * @param orderId  订单id
     * @Date 20150827
     * @return
     */
	public List<OrderDetail> getOrderDetailIByNoId(String ordernNo, int orderId) {
		Map<String, Object> paramMap = new HashedMap();
		paramMap.put("orderNo", ordernNo);  //订单号
		paramMap.put("orderId", orderId);  //订单id
		return getReadOnlySqlSessionUtil().selectList("com.edaisong.api.dao.inter.IOrderDetailDao.getOrderDetailIByNoId", paramMap);
	}

}

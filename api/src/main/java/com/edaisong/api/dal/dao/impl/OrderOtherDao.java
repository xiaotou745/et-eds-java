package com.edaisong.api.dal.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.edaisong.api.dal.dao.inter.IOrderOtherDao;
import com.edaisong.entity.OrderOther;
import com.edaisong.entity.domain.OrderDetailBusiness;

@Repository
public class OrderOtherDao extends DaoBase implements IOrderOtherDao   {

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(OrderOther record) {
		// TODO Auto-generated method stub
		return 0;
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(OrderOther record) {
		// TODO Auto-generated method stub
		return 0;
	}


    /**
     * 根据订单信息查询 子订单集合 
     * @param orderNo 订单号
     * @param businessId 商户id
     * @author CaoHeYang
     * @Date 20150804
     * @return
     */
	@Override
	public List<OrderOther> getOrderChildByOrderInfo(String orderNo,
			int businessId) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("OrderNo", orderNo);
		paramMap.put("BusinessId", businessId);
		List<OrderOther> result=getReadOnlySqlSessionUtil().selectList("com.edaisong.api.dal.dao.inter.IOrderOtherDao.getOrderChildByOrderInfo"
				 , paramMap);
	     return result;

	}

}

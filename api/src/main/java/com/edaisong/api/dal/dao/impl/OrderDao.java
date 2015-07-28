package com.edaisong.api.dal.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.edaisong.api.dal.dao.inter.IOrderDao;
import com.edaisong.entity.domain.OrderListModel;
import com.edaisong.entity.req.OrderSearchWebReq;


@Repository
public class OrderDao  extends DaoBase implements IOrderDao {


	/**
	 * 后台订单列表页面 
	 * @author CaoHeYang
	 * @Date 20150728
	 * @param search 查询条件实体
	 * @return
	 */
	@Override
    public List<OrderListModel> GetOrders(OrderSearchWebReq search){
		List<OrderListModel> list = getReadOnlySqlSessionUtil()
				.selectList(
						"com.edaisong.api.dal.dao.inter.IOrderDao.GetOrders",
						search);
		return list;
    }

}

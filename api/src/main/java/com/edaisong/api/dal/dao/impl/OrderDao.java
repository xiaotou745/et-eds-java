package com.edaisong.api.dal.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edaisong.api.dal.dao.inter.IOrderDao;
import com.edaisong.core.util.SqlSessionUtil;
import com.edaisong.entity.Order;
import com.edaisong.entity.domain.OrderListModel;
import com.edaisong.entity.req.OrderSearchWeb;
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
    public List<OrderListModel> GetOrders(OrderSearchWeb search){
    	
    	return null;
    }

}

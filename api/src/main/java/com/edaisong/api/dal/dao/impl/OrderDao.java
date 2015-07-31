package com.edaisong.api.dal.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.edaisong.api.dal.dao.inter.IOrderDao;
import com.edaisong.entity.Account;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.OrderListModel;
import com.edaisong.entity.domain.OrderMapDetail;
import com.edaisong.entity.req.PagedOrderSearchReq;


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
    public PagedResponse<OrderListModel> getOrders(PagedOrderSearchReq search){
		
		PagedResponse<OrderListModel> result=new PagedResponse<OrderListModel>();
		result=getReadOnlySqlSessionUtil()
				.selectPageList(
						"com.edaisong.api.dal.dao.inter.IOrderDao.GetOrders",
						search);
		return result;
    }

	 /**
	  * 根据orderID获取订单地图数据
	  * @param orderId
	  * @author CaoHeYang
	  * @Date 20150730
	  * @return
	  */
	 public OrderMapDetail  getOrderMapDetail(long orderId){
		 OrderMapDetail result=getReadOnlySqlSessionUtil()
					.selectOne(
							"com.edaisong.api.dal.dao.inter.IOrderDao.getOrderMapDetail",
							orderId);
	     return result;
	 }
}

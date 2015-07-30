package com.edaisong.api.dal.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.edaisong.api.dal.dao.inter.IOrderDao;
import com.edaisong.entity.Account;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.OrderListModel;
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

}

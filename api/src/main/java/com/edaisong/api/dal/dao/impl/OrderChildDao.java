package com.edaisong.api.dal.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.edaisong.api.dal.dao.inter.IOrderChildDao;
import com.edaisong.api.dal.dao.inter.IOrderDao;
import com.edaisong.entity.Account;
import com.edaisong.entity.Order;
import com.edaisong.entity.OrderChild;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.OrderDetailBusiness;
import com.edaisong.entity.domain.OrderListModel;
import com.edaisong.entity.domain.OrderMapDetail;
import com.edaisong.entity.req.OrderDetailBusinessReq;
import com.edaisong.entity.req.PagedOrderSearchReq;

@Repository
public class OrderChildDao extends DaoBase implements IOrderChildDao {	
	
	@Override
	public int insert(OrderChild record)
	{
		return getMasterSqlSessionUtil().insert(
				"com.edaisong.api.dal.dao.inter.IOrderChildDao.insert", record);
	}	
	
}

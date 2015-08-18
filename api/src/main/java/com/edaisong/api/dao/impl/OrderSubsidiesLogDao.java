package com.edaisong.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IOrderSubsidiesLogDao;
import com.edaisong.entity.OrderSubsidiesLog;

@Repository
public class OrderSubsidiesLogDao extends DaoBase implements IOrderSubsidiesLogDao{

	@Override
	public int insert(OrderSubsidiesLog record) {
		return getMasterSqlSessionUtil().insert("com.edaisong.api.dao.inter.IOrderSubsidiesLogDao.insert",record);
	}

}

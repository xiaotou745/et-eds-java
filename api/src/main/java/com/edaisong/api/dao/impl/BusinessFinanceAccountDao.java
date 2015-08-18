package com.edaisong.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IBusinessFinanceAccountDao;
import com.edaisong.entity.BusinessFinanceAccount;

@Repository
public class BusinessFinanceAccountDao extends DaoBase implements
		IBusinessFinanceAccountDao {

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(BusinessFinanceAccount record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BusinessFinanceAccount getDetailByBusinesID(Integer businessID) {
		return getReadOnlySqlSessionUtil()
				.selectOne(
						"com.edaisong.api.dal.dao.inter.IBusinessFinanceAccountDao.getDetailByBusinesID",
						businessID);
	}

}

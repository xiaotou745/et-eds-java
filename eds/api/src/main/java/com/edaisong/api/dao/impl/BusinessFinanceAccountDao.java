package com.edaisong.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IBusinessFinanceAccountDao;
import com.edaisong.entity.BusinessFinanceAccount;
import com.edaisong.entity.domain.BusinessRechargeLog;

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
						"IBusinessFinanceAccountDao.getDetailByBusinesID",
						businessID);
	}
	/**
	 * 商户充值修改
	 * 茹化肖
	 * 2016年2月29日16:48:53
	 */
	@Override
	public boolean businessRecharge(BusinessRechargeLog log) {
		int res=getMasterSqlSessionUtil().update("IBusinessFinanceAccountDao.businessRecharge", log);
		return res>0;
	}

}

package com.edaisong.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.IBusinessFinanceAccountDao;
import com.edaisong.api.service.inter.IBusinessFinanceAccountService;
import com.edaisong.entity.BusinessFinanceAccount;
@Service
public class BusinessFinanceAccountService implements IBusinessFinanceAccountService{

	@Autowired
	IBusinessFinanceAccountDao bsinessFinanceAccountDao;
	@Override
	public BusinessFinanceAccount getDetailByBusinesID(Integer businessID) {
		return bsinessFinanceAccountDao.getDetailByBusinesID(businessID);
	}

}

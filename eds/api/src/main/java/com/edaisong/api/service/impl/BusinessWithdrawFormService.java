package com.edaisong.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.IBusinessWithdrawFormDao;
import com.edaisong.api.service.inter.IBusinessWithdrawFormService;
import com.edaisong.core.util.ParseHelper;
import com.edaisong.entity.domain.BusinessWithdrawFormModel;

@Service
public class BusinessWithdrawFormService implements IBusinessWithdrawFormService{
	@Autowired
	private IBusinessWithdrawFormDao businessWithdrawFormDao;
	@Override
	public BusinessWithdrawFormModel getBusinessWithdrawListById(String withwardId) {
		BusinessWithdrawFormModel model = businessWithdrawFormDao.getBusinessWithdrawListById(withwardId);
		if(model != null){
			model.setAccountno(ParseHelper.toDecrypt(model.getAccountno()));
		}
		return model;
	}

}

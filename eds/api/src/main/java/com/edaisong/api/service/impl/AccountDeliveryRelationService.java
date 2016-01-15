package com.edaisong.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.IAccountDeliveryRelationDao;
import com.edaisong.api.service.inter.IAccountDeliveryRelationService;
import com.edaisong.entity.AccountDeliveryRelation;

@Service
public class AccountDeliveryRelationService implements IAccountDeliveryRelationService{

	@Autowired
	private IAccountDeliveryRelationDao accountDeliveryRelationDao;
	@Override
	public int modifyAuthList(List<AccountDeliveryRelation> recordList) {
return accountDeliveryRelationDao.modifyAuthList(recordList);
	}

	@Override
	public List<Integer> getAuthorityCitys(int userId) {
return accountDeliveryRelationDao.getAuthorityCitys(userId);
	}

}

package com.edaisong.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dal.dao.inter.IBusinessThirdRelationDao;
import com.edaisong.api.service.inter.IBusinessThirdRelationService;
import com.edaisong.entity.domain.BusinessThirdRelationModel;

@Service
public class BusinessThirdRelationService implements IBusinessThirdRelationService{

	@Autowired
	private IBusinessThirdRelationDao businessThirdRelationDao;
	@Override
	public List<BusinessThirdRelationModel> getListByBusinessID(int businessID) {
		return businessThirdRelationDao.getListByBusinessID(businessID);
	}

}

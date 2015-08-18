package com.edaisong.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.IBusinessExpressRelationDao;
import com.edaisong.api.service.inter.IBusinessExpressRelationService;
import com.edaisong.entity.BusinessExpressRelation;
@Service
public class BusinessExpressRelationService implements IBusinessExpressRelationService{
	@Autowired
	IBusinessExpressRelationDao  businessExpressRelationDao;
	@Override
	public List<BusinessExpressRelation> selectByBusinessID(Integer businessID) {
		return businessExpressRelationDao.selectByBusinessID(businessID);
	}

}

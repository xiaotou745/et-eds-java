package com.edaisong.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IBusinessExpressRelationDao;
import com.edaisong.entity.BusinessExpressRelation;
@Repository
public class BusinessExpressRelationDao extends DaoBase implements
		IBusinessExpressRelationDao {

	@Override
	public List<BusinessExpressRelation> selectByBusinessID(Integer businessID) {
		List<BusinessExpressRelation> model = getReadOnlySqlSessionUtil()
				.selectList(
						"IBusinessExpressRelationDao.selectByBusinessID",
						businessID);
		return model;
	}
}

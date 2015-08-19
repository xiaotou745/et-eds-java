package com.edaisong.api.dao.impl;

import java.util.List;




import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IBusinessThirdRelationDao;
import com.edaisong.entity.BusinessThirdRelation;
import com.edaisong.entity.domain.BusinessThirdRelationModel;

@Repository
public class BusinessThirdRelationDao extends DaoBase implements
		IBusinessThirdRelationDao {

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(BusinessThirdRelation record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(BusinessThirdRelation record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BusinessThirdRelation selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(BusinessThirdRelation record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(BusinessThirdRelation record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BusinessThirdRelationModel> getListByBusinessID(int businessID) {
		return getReadOnlySqlSessionUtil().selectList(
				"com.edaisong.api.dao.inter.IBusinessThirdRelationDao.getListByBusinessID",
				businessID);
	}

}

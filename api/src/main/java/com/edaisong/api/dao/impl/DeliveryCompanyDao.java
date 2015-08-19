package com.edaisong.api.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IDeliveryCompanyDao;
import com.edaisong.entity.DeliveryCompany;

@Repository
public class DeliveryCompanyDao extends DaoBase implements IDeliveryCompanyDao {

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(DeliveryCompany record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(DeliveryCompany record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DeliveryCompany selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(DeliveryCompany record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(DeliveryCompany record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<DeliveryCompany> getDeliveryCompanyList() {
		List<DeliveryCompany> list = getReadOnlySqlSessionUtil()
				.selectList(
						"com.edaisong.api.dao.inter.IDeliveryCompanyDao.getDeliveryCompanyList"	);
		return list;

	}

}

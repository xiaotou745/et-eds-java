package com.edaisong.api.dao.impl;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IBusinessMessageDao;
import com.edaisong.entity.BusinessMessage;

/**
 * 商户消息数据访问层
 * @author pengyi
 * @date 20150818
 *
 */
@Repository
public class BusinessMessageDao extends DaoBase implements IBusinessMessageDao{

	@Override
	public int deleteByPrimaryKey(Long id) {
		throw new NotImplementedException();
	}

	@Override
	public int insert(BusinessMessage record) {
		throw new NotImplementedException();
	}

	@Override
	public int insertSelective(BusinessMessage record) {
		throw new NotImplementedException();
	}

	@Override
	public BusinessMessage selectByPrimaryKey(Long id) {
		throw new NotImplementedException();
	}

	@Override
	public int updateByPrimaryKeySelective(BusinessMessage record) {
		throw new NotImplementedException();
	}

	@Override
	public int updateByPrimaryKey(BusinessMessage record) {
		throw new NotImplementedException();
	}

	/**
	 * 获得最新一条商户消息
	 * @author pengyi
	 * @date 20150818
	 */
	@Override
	public BusinessMessage getLatestMessage(int businessId) {
		return getReadOnlySqlSessionUtil()
				.selectOne("com.edaisong.api.dal.dao.inter.IBusinessMessageDao.getLatestMessage"
						, businessId);
	}

}

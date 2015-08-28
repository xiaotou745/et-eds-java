package com.edaisong.api.dao.impl;

import java.util.List;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IAuthorityAccountMenuSetDao;
import com.edaisong.entity.AuthorityAccountMenuSet;

/**
 * 用户菜单设置
 * @author pengyi
 * @date 20150828
 *
 */
@Repository
public class AuthorityAccountMenuSetDao extends DaoBase implements IAuthorityAccountMenuSetDao{

	@Override
	public int deleteByPrimaryKey(Integer id) {
		throw new NotImplementedException();
	}

	@Override
	public int insert(AuthorityAccountMenuSet record) {
		throw new NotImplementedException();
	}

	@Override
	public int insertSelective(AuthorityAccountMenuSet record) {
		throw new NotImplementedException();
	}

	@Override
	public AuthorityAccountMenuSet selectByPrimaryKey(Integer id) {
		throw new NotImplementedException();
	}

	@Override
	public int updateByPrimaryKeySelective(AuthorityAccountMenuSet record) {
		throw new NotImplementedException();
	}

	@Override
	public int updateByPrimaryKey(AuthorityAccountMenuSet record) {
		throw new NotImplementedException();
	}

	@Override
	public List<Integer> getMenuIdsByAccountId(Integer id) {
		return getReadOnlySqlSessionUtil().selectList(
				"com.edaisong.api.dao.inter.IAuthorityAccountMenuSetDao.getMenuIdsByAccountId",id);
	}

}

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
	public List<Integer> getMenuIdsByAccountId(Integer id) {
		return getReadOnlySqlSessionUtil().selectList(
				"com.edaisong.api.dao.inter.IAuthorityAccountMenuSetDao.getMenuIdsByAccountId",id);
	}

	@Override
	public boolean modifyAuthList(List<AuthorityAccountMenuSet> authList) {
		return getMasterSqlSessionUtil()
				.update("com.edaisong.api.dao.inter.IAuthorityAccountMenuSetDao.modifyAuthList",
						authList) > 0;

	}

}

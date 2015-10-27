package com.edaisong.toolsapi.dao.impl;

import java.util.List;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;

import com.edaisong.toolsapi.common.DaoBase;
import com.edaisong.toolsapi.dao.inter.IAuthorityAccountMenuSetDao;
import com.edaisong.toolsentity.AuthorityAccountMenuSet;

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
				"com.edaisong.toolsapi.dao.inter.IAuthorityAccountMenuSetDao.getMenuIdsByAccountId",id);
	}

	@Override
	public boolean modifyAuthList(List<AuthorityAccountMenuSet> authList) {
		return getMasterSqlSessionUtil()
				.update("com.edaisong.toolsapi.dao.inter.IAuthorityAccountMenuSetDao.modifyAuthList",
						authList) > 0;

	}

}

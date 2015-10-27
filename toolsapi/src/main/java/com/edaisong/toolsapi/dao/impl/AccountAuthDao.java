package com.edaisong.toolsapi.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.edaisong.toolsapi.common.DaoBase;
import com.edaisong.toolsapi.dao.inter.IAccountAuthDao;
import com.edaisong.toolsentity.AccountAuthority;
 

/**
 * 用户菜单设置
 * @author pengyi
 * @date 20150828
 *
 */
@Repository
public class AccountAuthDao extends DaoBase implements IAccountAuthDao{

	@Override
	public List<Integer> getMenuIdsByAccountId(Integer id) {
		return getReadOnlySqlSessionUtil().selectList(
				"com.renrentui.renrenapi.dao.inter.IAccountAuthDao.getMenuIdsByAccountId",id);
	}

	@Override
	public boolean modifyAuthList(List<AccountAuthority> authList) {
		return getMasterSqlSessionUtil()
				.update("com.renrentui.renrenapi.dao.inter.IAccountAuthDao.modifyAuthList",
						authList) > 0;

	}

}

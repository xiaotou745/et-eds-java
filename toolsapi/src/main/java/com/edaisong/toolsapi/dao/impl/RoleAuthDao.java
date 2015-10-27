package com.edaisong.toolsapi.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.edaisong.toolsapi.common.DaoBase;
import com.edaisong.toolsapi.dao.inter.IRoleAuthDao;
import com.edaisong.toolsentity.RoleAuth;
import com.edaisong.toolsentity.RoleInfo;

@Repository
public class RoleAuthDao extends DaoBase implements
		IRoleAuthDao {

	@Override
	public boolean modifyAuthList(List<RoleAuth> authList) {
		return getMasterSqlSessionUtil()
				.update("com.renrentui.renrenapi.dao.inter.IRoleAuthDao.modifyAuthList",
						authList) > 0;
	}




}

package com.edaisong.toolsapi.dao.impl;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.edaisong.toolsapi.common.DaoBase;
import com.edaisong.toolsentity.domain.RolePrivilege;
import com.edaisong.toolsapi.dao.inter.IRolePrivilegeDao;

/**
 * 数据访问对象 RolePrivilegeDao
 * 
 * @author wangyuchuan
 * @date 2015-11-26 13:05:55
 *
 */
@Repository
public class RolePrivilegeDao extends DaoBase implements IRolePrivilegeDao {

	@Override
	public void insertBatch(List<RolePrivilege> rolePrivileges) {
		getMasterSqlSessionUtil().insert("com.edaisong.toolsapi.dao.inter.IRolePrivilegeDao.insertBatch",
				rolePrivileges);
	}

	@Override
	public void deleteByRoleId(Integer roleId) {
		// TODO Auto-generated method stub
		getMasterSqlSessionUtil().delete("com.edaisong.toolsapi.dao.inter.IRolePrivilegeDao.deleteByRoleId", roleId);
	}

	@Override
	public List<RolePrivilege> getByRoleId(Integer roleId) {
		// TODO Auto-generated method stub
		return getMasterSqlSessionUtil().selectList("com.edaisong.toolsapi.dao.inter.IRolePrivilegeDao.getByRoleId",
				roleId);
	}
}
package com.edaisong.toolsapi.dao.inter;

import java.util.List;

import com.edaisong.toolsentity.domain.RolePrivilege;

/**
 * 领域对象接口 IRolePrivilegeDao
 * 
 * @author wangyuchuan
 * @date 2015-11-26 13:05:55
 *
 */
public interface IRolePrivilegeDao {
	/**
	 * 批量添加角色权限
	 * @param rolePrivileges 角色权限列表
	 */
	void insertBatch(List<RolePrivilege> rolePrivileges);

	/**
	 * 删除指定角色的所有权限
	 * 
	 * @param roleId
	 *            角色Id
	 */
	void deleteByRoleId(Integer roleId);

	/**
	 * 获取某个角色的权限
	 * 
	 * @param roleId
	 *            角色ID
	 * @return 返回指定角色的权限
	 */
	List<RolePrivilege> getByRoleId(Integer roleId);
}
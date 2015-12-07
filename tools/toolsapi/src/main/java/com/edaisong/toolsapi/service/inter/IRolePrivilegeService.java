package com.edaisong.toolsapi.service.inter;

import java.util.List;

import com.edaisong.toolsentity.domain.RolePrivilege;
import com.edaisong.toolsentity.req.RolePrivilegeSaveReq;

/**
 * 服务接口 IRolePrivilegeService
 * 
 * @author wangyuchuan
 * @date 2015-11-26 13:05:55
 *
 */
public interface IRolePrivilegeService {

	/**
	 * 批量添加权限
	 * 
	 * @param rolePrivileges
	 *            角色权限列表
	 */
//	void createBatch(List<RolePrivilege> rolePrivileges);

	/**
	 * 获取某个角色的权限
	 * 
	 * @param roleId
	 *            角色ID
	 * @return 返回指定角色的权限
	 */
//	List<RolePrivilege> getByRoleId(Integer roleId);

	/**
	 * 获取角色权限ID
	 * 
	 * @param roleId
	 *            角色ID
	 * @return 返回角色权限的菜单IDs
	 */
	List<Integer> getRolePrivileges(Integer roleId);

	/**
	 * 删除指定角色的所有权限
	 * 
	 * @param roleId
	 *            角色Id
	 */
//	void removeByRoleId(Integer roleId);

	/**
	 * 保存角色权限
	 * 
	 * @param saveParams
	 *            参数
	 */
	void saveRolePrivilege(RolePrivilegeSaveReq saveParams);
}
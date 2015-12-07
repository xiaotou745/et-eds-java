package com.edaisong.toolsentity.req;

import java.util.List;

/**
 * 角色权限参数
 * 
 * @author wangyuchuan
 *
 */
public class RolePrivilegeSaveReq {
	private Integer roleId;
	
	private String menuIds;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}
}

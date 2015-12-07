package com.edaisong.toolsentity.domain;

/**
 * 实体类RolePrivilege. (属性说明自动提取数据库字段的描述信息)
 * 
 * @author wangyuchuan
 * @date 2015-11-26 13:05:55
 *
 */
public class RolePrivilege {
	/**
	 * 自增ID
	 */
	private Integer id;

	/**
	 * 角色ID
	 */
	private Integer roleId;

	/**
	 * 菜单ID
	 */
	private Integer menuId;

	/**
	 * 获取自增ID
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 设置自增ID
	 * 
	 * @param id
	 *            自增ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 获取角色ID
	 */
	public Integer getRoleId() {
		return roleId;
	}

	/**
	 * 设置角色ID
	 * 
	 * @param roleId
	 *            角色ID
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	/**
	 * 获取菜单ID
	 */
	public Integer getMenuId() {
		return menuId;
	}

	/**
	 * 设置菜单ID
	 * 
	 * @param menuId
	 *            菜单ID
	 */
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

}
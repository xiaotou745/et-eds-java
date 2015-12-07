package com.edaisong.toolsapi.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.edaisong.toolsapi.dao.inter.IMenuDao;
import com.edaisong.toolsapi.dao.inter.IRolePrivilegeDao;
import com.edaisong.toolsapi.dao.inter.IUserDao;
import com.edaisong.toolsapi.redis.RedisService;
import com.edaisong.toolsapi.service.inter.IUserService;
import com.edaisong.toolscore.util.JsonUtil;
import com.edaisong.toolscore.util.ListUtils;
import com.edaisong.toolsentity.domain.Menu;
import com.edaisong.toolsentity.domain.RolePrivilege;
import com.edaisong.toolsentity.domain.User;

@Service
public class ServiceCacheProvider {
	@Autowired
	private IMenuDao menuDao;
	@Autowired
	private IRolePrivilegeDao rolePrivilegeDao;
	@Autowired
	private RedisService redisService;
	@Autowired
	private IUserDao userDao;

	public List<Menu> getAllMenusFromCache() {
		List<Menu> cacheMenus = redisService.get(ServiceCacheKeys.MENU_CACHE_KEY, List.class);
		if (cacheMenus == null) {
			return refreshMenusInCache();
		}
		return cacheMenus;
	}

	public List<Menu> refreshMenusInCache() {
		List<Menu> cacheMenus = menuDao.getAll();
		redisService.set(ServiceCacheKeys.MENU_CACHE_KEY, cacheMenus, 1, TimeUnit.DAYS);
		return cacheMenus;
	}

	public List<Integer> getRolePrivilegeFromCache(Integer roleId) {
		String key = String.format(String.format(ServiceCacheKeys.ROLE_PRIVILEGE_CACHE_KEY, roleId));
		List<Integer> roleMenuIds = redisService.get(key, List.class);
		if (roleMenuIds == null) {
			return refreshRolePrivilegeInCache(roleId);
		}
		return roleMenuIds;
	}

	/**
	 * 更新角色权限，并更新此角色下所有用户的权限
	 * 
	 * @param roleId
	 *            角色ID
	 * @return 返回角色权限列表
	 */
	public List<Integer> refreshRolePrivilegeInCache(Integer roleId) {
		List<Integer> roleMenuIds = new ArrayList<Integer>();
		// 从数据库中获取该角色最新的权限
		List<RolePrivilege> rolePrivileges = rolePrivilegeDao.getByRoleId(roleId);
		if (rolePrivileges != null) {
			roleMenuIds = rolePrivileges.stream().map(RolePrivilege::getMenuId).collect(Collectors.toList());
		}
		// 保存到缓存中
		String key = String.format(String.format(ServiceCacheKeys.ROLE_PRIVILEGE_CACHE_KEY, roleId));
		redisService.set(key, roleMenuIds, 1, TimeUnit.DAYS);

		// 获取该角色下所有的用户，并刷新所有用户的权限
		List<Integer> lstUserIds = getRoleUsersFromCache(roleId);
		for (Integer userId : lstUserIds) {
			List<Integer> lstUserRoleIds = getUserRolesFromCache(userId);
			refreshUserPrivilegesInCache(userId, lstUserRoleIds);
		}
		return roleMenuIds;
	}

	/**
	 * 从缓存中获取用户的菜单权限列表
	 * 
	 * @param userId
	 *            用户ID
	 * @return 返回用户菜单权限ID列表
	 */
	public List<Integer> getUserPrivilegesFromCache(Integer userId) {
		String redisKey = String.format(ServiceCacheKeys.USER_PRIVILEGE_CACHE_KEY, userId);
		List<Integer> menuIds = redisService.get(redisKey, List.class);
		if (menuIds == null) {
			return refreshUserPrivilegesInCache(userId);
		}
		return menuIds;
	}

	/**
	 * 刷新缓存中的用户权限
	 * 
	 * @param user
	 *            用户对象
	 * @return 返回用户的菜单权限列表
	 */
	public List<Integer> refreshUserPrivilegesInCache(User user) {
		if (user == null) {
			return new ArrayList<Integer>();
		}
		List<Integer> lstRoleIds = ListUtils.str2intlist(user.getRoleIds(), ",");
		return refreshUserPrivilegesInCache(user.getId(), lstRoleIds);
	}

	public List<Integer> refreshUserPrivilegesInCache(Integer userId, List<Integer> roleIds) {
		if (roleIds == null) {
			roleIds = new ArrayList<Integer>();
		}
		refreshUserRolesInCache(userId, roleIds);// 刷新缓存中的用户所属角色
		// 用户权限等于该用户所属角色权限并集
		List<Integer> menuIds = new ArrayList<Integer>();
		for (Integer roleId : roleIds) {
			List<Integer> roleMenuIds = getRolePrivilegeFromCache(roleId);
			for (Integer menuId : roleMenuIds) {
				if (!menuIds.contains(menuId)) {
					menuIds.add(menuId);
				}
			}
		}
		String redisKey = String.format(ServiceCacheKeys.USER_PRIVILEGE_CACHE_KEY, userId);
		redisService.set(redisKey, menuIds, 1, TimeUnit.DAYS);
		return menuIds;
	}

	/**
	 * 刷新缓存中的用户权限
	 * 
	 * @param userId
	 *            用户ID
	 * @return 返回用户权限
	 */
	public List<Integer> refreshUserPrivilegesInCache(Integer userId) {
		User user = userDao.getById(userId);
		return refreshUserPrivilegesInCache(user);
	}

	private List<Integer> getRoleUsersFromCache(Integer roleId) {
		String redisKey = String.format(ServiceCacheKeys.ROLE_USERIDS_CACHE_KEY, roleId);
		List<Integer> lstUserIds = redisService.get(redisKey, List.class);
		if (lstUserIds == null) {
			return refreshRoleUsersInCache(roleId);
		}
		return lstUserIds;
	}

	private List<Integer> refreshRoleUsersInCache(Integer roleId) {
		List<Integer> lstUserIds = new ArrayList<Integer>();
		List<User> lstUsers = userDao.getAll();
		for (User user : lstUsers) {
			List<Integer> userRoleIds = ListUtils.str2intlist(user.getRoleIds(), ",");
			if (userRoleIds.contains(roleId)) {
				lstUserIds.add(roleId);
			}
		}
		String redisKey = String.format(ServiceCacheKeys.ROLE_USERIDS_CACHE_KEY, roleId);
		redisService.set(redisKey, lstUserIds, 1, TimeUnit.DAYS);
		return lstUserIds;
	}

	/**
	 * 从缓存中获取用户所属的角色列表
	 * 
	 * @param userId
	 *            用户ID
	 * @return 返回缓存中用户所属角色列表
	 */
	private List<Integer> getUserRolesFromCache(Integer userId) {
		String redisKey = String.format(ServiceCacheKeys.USER_ROLEIDS_CACHE_KEY, userId);
		List<Integer> lstRoleIds = redisService.get(redisKey, List.class);
		if (lstRoleIds == null) {
			return refreshUserRolesInCache(userId);
		}
		return lstRoleIds;
	}

	/**
	 * 从缓存中移除用户所属角色列表
	 * 
	 * @param userId
	 */
	private void removeUserRolesInCache(Integer userId) {
		String redisKey = String.format(ServiceCacheKeys.USER_ROLEIDS_CACHE_KEY, userId);
		redisService.remove(redisKey);
	}

	/**
	 * 刷新缓存中的用户所属角色列表
	 * 
	 * @param userId
	 *            用户ID
	 * @return 返回刷新后用户所属角列表
	 */
	private List<Integer> refreshUserRolesInCache(Integer userId) {
		User user = userDao.getById(userId);
		if(user==null){
			return new ArrayList<Integer>();
		}
		return refreshUserRolesInCache(userId, user.getRoleIds());
	}

	/**
	 * 刷新缓存中的用户所属角色列表
	 * 
	 * @param userId
	 *            用户ID
	 * @param strRoleIds
	 *            用户所属角色的字符串
	 * @return 返回刷新后用户所属角列表
	 */
	private List<Integer> refreshUserRolesInCache(Integer userId, String strRoleIds) {
		if (StringUtils.isEmpty(strRoleIds)) {
			strRoleIds = "";
		}
		List<Integer> roleIds = ListUtils.str2intlist(strRoleIds, ",");
		return refreshUserRolesInCache(userId, roleIds);
	}

	/**
	 * 刷新缓存中的用户所属角色列表
	 * 
	 * @param userId
	 *            用户ID
	 * @param roleIds
	 *            用户所属角色ID的List
	 * @return 返回刷新后用户所属角列表
	 */
	private List<Integer> refreshUserRolesInCache(Integer userId, List<Integer> roleIds) {
		String redisKey = String.format(ServiceCacheKeys.USER_ROLEIDS_CACHE_KEY, userId);
		redisService.set(redisKey, roleIds, 1, TimeUnit.DAYS);
		return roleIds;
	}

}

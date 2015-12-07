package com.edaisong.toolsadmin.common;

import java.util.List;
import java.util.stream.Collectors;

import com.edaisong.toolsapi.service.ServiceCacheProvider;
import com.edaisong.toolsapi.service.inter.IMenuService;
import com.edaisong.toolscore.util.JsonUtil;
import com.edaisong.toolscore.util.SpringBeanHelper;
import com.edaisong.toolsentity.domain.Menu;

/**
 * 菜单工具类
 * 
 * @author wangyuchuan
 *
 */
public class MenuUtils {
	private final static IMenuService menuService;
	private final static ServiceCacheProvider serviceCache;
	static {
		menuService = SpringBeanHelper.getCustomBeanByType(IMenuService.class);
		serviceCache = SpringBeanHelper.getCustomBeanByType(ServiceCacheProvider.class);
	}

	/**
	 * 获取所有的菜单
	 * 
	 * @return 返回所有的菜单项
	 */
	public static List<Menu> getAllMenus() {
		return menuService.getAll();
	}

	/**
	 * 获取一级菜单
	 * 
	 * @return 返回一级菜单
	 */
	public static List<Menu> getTopMenus() {
		List<Menu> lstRootMenus = getAllMenus().stream().filter(m -> m.getParentId() == 0)
				.sorted((m1, m2) -> m1.getOrderBy() - m2.getOrderBy()).collect(Collectors.toList());
		return lstRootMenus;
	}

	/**
	 * 根据父ID获取子菜单列表
	 * 
	 * @param parentId
	 *            父菜单ID
	 * @return 返回指定菜单的子菜单项
	 */
	public static List<Menu> getChildMenus(Integer parentId) {
		return getAllMenus().stream().filter(m -> m.getParentId().equals(parentId))
				.sorted((m1, m2) -> m1.getOrderBy() - m2.getOrderBy()).collect(Collectors.toList());
	}

	/**
	 * 获取当前用户的菜单权限
	 */
	public static List<Integer> getCurrentUserPrivileges(Integer userId) {
		List<Integer> lstMenuIds = serviceCache.getUserPrivilegesFromCache(userId);
		return lstMenuIds;
	}
}

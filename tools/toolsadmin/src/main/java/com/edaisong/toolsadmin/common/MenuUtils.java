package com.edaisong.toolsadmin.common;

import java.util.List;
import java.util.stream.Collectors;

import com.edaisong.toolsapi.service.ServiceCacheProvider;
import com.edaisong.toolsapi.service.inter.IMenuService;
import com.edaisong.toolscore.util.JsonUtil;
import com.edaisong.toolscore.util.SpringBeanHelper;
import com.edaisong.toolsentity.domain.Menu;

public class MenuUtils {
	private final static IMenuService menuService;
	static {
		menuService = SpringBeanHelper.getCustomBeanByType(IMenuService.class);
	}

	/**
	 * 获取所有的菜单
	 * 
	 * @return 返回所有的菜单项
	 */
	public static List<Menu> getAllMenus() {
		return menuService.getAll();

	}

	public static List<Menu> getTopMenus() {
		List<Menu> lstRootMenus = getAllMenus().stream().filter(m -> m.getParentId() == 0)
				.sorted((m1, m2) -> m1.getOrderBy() - m2.getOrderBy()).collect(Collectors.toList());
		return lstRootMenus;
	}

	public static List<Menu> getChildMenus(Integer parentId) {
		return getAllMenus().stream().filter(m -> m.getParentId().equals(parentId))
				.sorted((m1, m2) -> m1.getOrderBy() - m2.getOrderBy()).collect(Collectors.toList());
	}
}

package com.edaisong.api.service.inter;

import java.util.List;

import com.edaisong.entity.AuthorityMenuClass;
import com.edaisong.entity.MenuEntity;

public interface IAuthorityMenuClassService {
	/**
	 * 获取指定用户有权限的菜单list
	 * @author hailongzhao
	 * @date 20150828
	 * @param req
	 * @return
	 */
	public List<MenuEntity> getMenuListByUserID(int userID) ;
/**
 * 判断给定用户是否有某个菜单的权限
 * @author hailongzhao
 * @date 20150828
 * @param userID
 * @param menuID
 * @return
 */
	public boolean checkHasAuth(int userID,int menuID) ;
	/**
	 * 获取所有菜单列表
	 * @author hailongzhao
	 * @date 20150828
	 * @return
	 */
	public List<AuthorityMenuClass> getMenuList() ;
}

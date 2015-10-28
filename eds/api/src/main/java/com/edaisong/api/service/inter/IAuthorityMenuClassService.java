package com.edaisong.api.service.inter;

import java.util.List;

import com.edaisong.entity.AuthorityMenuClass;
import com.edaisong.entity.MenuEntity;
import com.edaisong.entity.req.AddNewMenuReq;

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
	 * 修改权限时，获取给定用户的权限列表（包括没有权限的menu）
	 * @author hailongzhao
	 * @date 20150828
	 * @return
	 */
	public List<MenuEntity> getAuthSettingList(int userID) ;
	
	/**
	 * 修改权限时，获取给定角色的权限列表（包括没有权限的menu）
	 * @author hailongzhao
	 * @date 20150902
	 * @return
	 */
	public List<MenuEntity> getRoleAuthSettingList(int roleID) ;
	
	/**
	 * 根据父id获得所有子菜单
	 * @author pengyi
	 * @date 2015年9月10日 上午11:50:57
	 * @version 1.0
	 * @param parId
	 * @return
	 */
	List<AuthorityMenuClass> getListMenuByParId(int parId);
	
	/**
	 * 获得指定id的菜单
	 * @author pengyi
	 * @date 2015年9月10日 下午2:36:04
	 * @version 1.0
	 * @param id
	 * @return
	 */
	AuthorityMenuClass getMenuById(int id);
	
	/**
	 * 新加菜单
	 * @author pengyi
	 * @date 2015年9月10日 下午2:36:33
	 * @version 1.0
	 * @param req
	 * @return
	 */
	boolean addMenu(AuthorityMenuClass req);
}

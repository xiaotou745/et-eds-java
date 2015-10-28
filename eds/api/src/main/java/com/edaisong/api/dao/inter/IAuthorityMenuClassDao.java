package com.edaisong.api.dao.inter;

import java.util.List;

import com.edaisong.entity.AuthorityAccountMenuSet;
import com.edaisong.entity.AuthorityMenuClass;
import com.edaisong.entity.MenuEntity;
import com.edaisong.entity.req.AddNewMenuReq;

public interface IAuthorityMenuClassDao {
    int deleteByPrimaryKey(Integer id);

    int insert(AuthorityMenuClass record);

    List<MenuEntity> getMenuListByUserID(int accountId);
    
	/**
	 * 获取给定用户的权限列表
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
	public List<AuthorityMenuClass> getListMenuByParId(int parId);
	
	/**
	 * 根据Id获得单个菜单
	 * @author pengyi
	 * @date 2015年9月10日 下午1:30:22
	 * @version 1.0
	 * @param id
	 * @return
	 */
	AuthorityMenuClass getMenuById(int id);

	/**
	 * 新增菜单
	 * @author pengyi
	 * @date 2015年9月10日 下午2:37:38
	 * @version 1.0
	 * @param req
	 * @return
	 */
	boolean addMenu(AuthorityMenuClass req);
}
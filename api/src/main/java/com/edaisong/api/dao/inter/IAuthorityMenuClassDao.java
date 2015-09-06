package com.edaisong.api.dao.inter;

import java.util.List;

import com.edaisong.entity.AuthorityAccountMenuSet;
import com.edaisong.entity.AuthorityMenuClass;
import com.edaisong.entity.MenuEntity;

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
	
}
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
	public List<MenuEntity> getAuthList(int userID) ;
	
}
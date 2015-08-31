package com.edaisong.api.dao.inter;

import java.util.List;

import com.edaisong.entity.AuthorityMenuClass;
import com.edaisong.entity.MenuEntity;

public interface IAuthorityMenuClassDao {
    int deleteByPrimaryKey(Integer id);

    int insert(AuthorityMenuClass record);

    List<MenuEntity> getMenuListByUserID(int accountId);
    
	/**
	 * 获取所有菜单列表
	 * @author hailongzhao
	 * @date 20150828
	 * @return
	 */
	public List<AuthorityMenuClass> getMenuList() ;
}
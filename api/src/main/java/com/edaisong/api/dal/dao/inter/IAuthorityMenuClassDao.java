package com.edaisong.api.dal.dao.inter;

import java.util.List;

import com.edaisong.entity.AuthorityMenuClass;
import com.edaisong.entity.MenuEntity;

public interface IAuthorityMenuClassDao {
    int deleteByPrimaryKey(Integer id);

    int insert(AuthorityMenuClass record);

    int insertSelective(AuthorityMenuClass record);

    AuthorityMenuClass selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AuthorityMenuClass record);

    int updateByPrimaryKey(AuthorityMenuClass record);
    
    List<MenuEntity> getMenuListByUserID(String accountId);
}
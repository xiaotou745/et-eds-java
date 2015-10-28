package com.edaisong.toolsapi.dao.inter;

import com.edaisong.toolsentity.Authority;

public interface IAuthorityDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Authority record);

    int insertSelective(Authority record);

    Authority selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Authority record);

    int updateByPrimaryKey(Authority record);
}
package com.edaisong.api.dao.inter;

import com.edaisong.entity.AuthorityDepartment;

public interface IAuthorityDepartmentDao {
    int deleteByPrimaryKey(Integer id);

    int insert(AuthorityDepartment record);

    int insertSelective(AuthorityDepartment record);

    AuthorityDepartment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AuthorityDepartment record);

    int updateByPrimaryKey(AuthorityDepartment record);
}
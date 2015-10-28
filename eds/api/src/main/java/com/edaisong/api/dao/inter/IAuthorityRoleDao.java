package com.edaisong.api.dao.inter;



import java.util.List;

import com.edaisong.entity.AuthorityRole;

public interface IAuthorityRoleDao {
    int insert(AuthorityRole record);

    int update(AuthorityRole record);
    List<AuthorityRole> selectList();
}
package com.edaisong.api.service.inter;

import java.util.List;

import com.edaisong.entity.AuthorityRole;

public interface IAuthorityRoleService {
    int insert(AuthorityRole record);

    int update(AuthorityRole record);
    List<AuthorityRole> selectList();
}

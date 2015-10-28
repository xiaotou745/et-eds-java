package com.edaisong.toolsapi.service.inter;

import java.util.List;

import com.edaisong.toolsentity.AuthorityRole;

public interface IAuthorityRoleService {
    int insert(AuthorityRole record);

    int update(AuthorityRole record);
    List<AuthorityRole> selectList();
}

package com.edaisong.toolsapi.service.inter;

import java.util.List;

import com.edaisong.toolsentity.RoleInfo;



public interface IRoleInfoService {
    int insert(RoleInfo record);

    int update(RoleInfo record);
    List<RoleInfo> selectList();
}

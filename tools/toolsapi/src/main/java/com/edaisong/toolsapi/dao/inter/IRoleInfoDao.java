package com.edaisong.toolsapi.dao.inter;

import java.util.List;

import com.edaisong.toolsentity.RoleInfo;

public interface IRoleInfoDao {
    int insert(RoleInfo record);

    int update(RoleInfo record);
    List<RoleInfo> selectList();
}
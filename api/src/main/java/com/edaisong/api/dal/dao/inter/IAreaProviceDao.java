package com.edaisong.api.dal.dao.inter;

import com.edaisong.entity.AreaProvice;

public interface IAreaProviceDao {
    int insert(AreaProvice record);

    int insertSelective(AreaProvice record);
}
package com.edaisong.api.dal.dao.inter;

import com.edaisong.entity.Test;

public interface ITestDao {
    int insert(Test record);

    int insertSelective(Test record);
}
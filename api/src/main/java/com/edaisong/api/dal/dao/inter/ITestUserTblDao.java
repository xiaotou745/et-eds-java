package com.edaisong.api.dal.dao.inter;

import com.edaisong.entity.TestUserTbl;

public interface ITestUserTblDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TestUserTbl record);

    int insertSelective(TestUserTbl record);

    TestUserTbl selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TestUserTbl record);

    int updateByPrimaryKey(TestUserTbl record);
}
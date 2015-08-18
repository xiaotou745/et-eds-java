package com.edaisong.api.dao.inter;

import com.edaisong.entity.UserOptRecord;

public interface IUserOptRecordDao {
    int deleteByPrimaryKey(Integer id);

    int insert(UserOptRecord record);

    int insertSelective(UserOptRecord record);

    UserOptRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserOptRecord record);

    int updateByPrimaryKey(UserOptRecord record);
}
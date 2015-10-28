package com.edaisong.api.dao.inter;

import com.edaisong.entity.PushMessage;

public interface IPushMessageDao {
    int deleteByPrimaryKey(Integer id);

    int insert(PushMessage record);

    int insertSelective(PushMessage record);

    PushMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PushMessage record);

    int updateByPrimaryKey(PushMessage record);
}
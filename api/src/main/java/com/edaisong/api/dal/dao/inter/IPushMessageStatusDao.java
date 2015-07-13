package com.edaisong.api.dal.dao.inter;

import com.edaisong.entity.PushMessageStatus;

public interface IPushMessageStatusDao {
    int deleteByPrimaryKey(Integer id);

    int insert(PushMessageStatus record);

    int insertSelective(PushMessageStatus record);

    PushMessageStatus selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PushMessageStatus record);

    int updateByPrimaryKey(PushMessageStatus record);
}